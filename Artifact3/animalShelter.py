from pymongo import MongoClient
from bson.objectid import ObjectId
from datetime import datetime


class AnimalShelter:
    """ CRUD operations for a normalized Animal collection in MongoDB """

    def __init__(self, USER, PASS):
        HOST = 'localhost'
        PORT = 27017
        DB = 'AAC'

        self.client = MongoClient(f'mongodb://{USER}:{PASS}@{HOST}:{PORT}')
        self.db = self.client[DB]

        # Collections in 3NF
        self.animals = self.db.animals
        self.breeds = self.db.breeds
        self.intakes = self.db.intakes
        self.outcomes = self.db.outcomes
        self.locations = self.db.locations
        print("Connected to normalized database.\n")

    # -- CREATE new animal with links to normalized fields --
    def add_animal(self, animal_data: dict) -> str:
        """
        Inserts a new animal into the animals collection.
        animal_data should include foreign key references to breed_id, intake_id, etc.
        """
        required_fields = ['name', 'breed_id', 'intake_id', 'location_id']
        if not all(field in animal_data for field in required_fields):
            raise ValueError("Missing required fields in animal data.")

        result = self.animals.insert_one(animal_data)
        return str(result.inserted_id)

    # -- READ animals with optional filters and joins --
    def read_animals(self, query: dict = {}) -> list:
        """
        Performs an aggregate join to pull full animal records with related info.
        """
        pipeline = [
            {"$match": query},
            {"$lookup": {
                "from": "breeds",
                "localField": "breed_id",
                "foreignField": "_id",
                "as": "breed_info"
            }},
            {"$lookup": {
                "from": "intakes",
                "localField": "intake_id",
                "foreignField": "_id",
                "as": "intake_info"
            }},
            {"$lookup": {
                "from": "outcomes",
                "localField": "outcome_id",
                "foreignField": "_id",
                "as": "outcome_info"
            }},
            {"$lookup": {
                "from": "locations",
                "localField": "location_id",
                "foreignField": "_id",
                "as": "location_info"
            }},
            {"$project": {
                "_id": 1,
                "name": 1,
                "breed": {"$arrayElemAt": ["$breed_info.name", 0]},
                "intake_date": {"$arrayElemAt": ["$intake_info.date", 0]},
                "outcome": {"$arrayElemAt": ["$outcome_info.type", 0]},
                "location": {"$arrayElemAt": ["$location_info.name", 0]}
            }}
        ]
        return list(self.animals.aggregate(pipeline))

    # -- UPDATE adoption outcome --
    def update_adoption(self, animal_id: str, adopter_name: str) -> dict:
        """
        Updates an animal with an adoption outcome and logs adopter info.
        """
        # Step 1: Create outcome record
        outcome_doc = {
            "type": "Adopted",
            "date": datetime.utcnow(),
            "notes": f"Adopted by {adopter_name}"
        }
        outcome_result = self.outcomes.insert_one(outcome_doc)

        # Step 2: Update animal with new outcome_id
        result = self.animals.update_one(
            {"_id": ObjectId(animal_id)},
            {"$set": {"outcome_id": outcome_result.inserted_id}}
        )

        return {
            "modified_count": result.modified_count,
            "outcome_id": str(outcome_result.inserted_id)
        }

    # Appointment methods link animalshelter db to AppointmentApp

    def create_appointment(self, animal_id: str, contact_id: str, appointment_data: dict) -> bool:
        """Create a new appointment linked to an animal and contact"""
        if animal_id and contact_id and appointment_data:
            appointment_data["animal_id"] = ObjectId(animal_id)
            appointment_data["contact_id"] = ObjectId(contact_id)
            self.database["appointments"].insert_one(appointment_data)
            return True
        else:
            raise ValueError("Missing required appointment data")

    def get_appointments_by_animal(self, animal_id: str) -> list:
        """Retrieve all appointments for a given animal"""
        return list(self.database["appointments"].find({"animal_id": ObjectId(animal_id)}))

    def get_appointments_by_contact(self, contact_id: str) -> list:
        """Retrieve all appointments for a given contact"""
        return list(self.database["appointments"].find({"contact_id": ObjectId(contact_id)}))

