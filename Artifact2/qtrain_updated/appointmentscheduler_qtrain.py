# qlearning_scheduler_integration.py

from Appointment import Appointment
from AnimalShelter import AnimalShelter
import numpy as np
import datetime

# Simple Q-table based scheduler for appointments
class QLearningScheduler:
    def __init__(self, timeslots, epsilon=0.1, alpha=0.5, gamma=0.9):
        self.timeslots = timeslots  # e.g., ["09:00", "10:00", ..., "16:00"]
        self.epsilon = epsilon  # exploration factor
        self.alpha = alpha      # learning rate
        self.gamma = gamma      # discount factor
        self.q_table = np.zeros(len(timeslots))  # one state per timeslot

    def choose_action(self):
        if np.random.rand() < self.epsilon:
            return np.random.randint(len(self.timeslots))  # explore
        return np.argmax(self.q_table)  # exploit best-known option

    def update_q(self, state, reward):
        best_future_q = np.max(self.q_table)
        self.q_table[state] += self.alpha * (reward + self.gamma * best_future_q - self.q_table[state])

    def get_slot(self, index):
        return self.timeslots[index]

# Integration function

def schedule_appointment(scheduler, shelter, animal_id, user_id, date):
    date_str = date.strftime("%Y-%m-%d")

    # Step 1: Check occupied times from DB
    occupied_times = [appt['appointmentDate'].split("T")[1][:5]
                      for appt in shelter.read({"appointmentDate": {"$regex": f"^{date_str}"}})]

    # Step 2: Choose time slot
    slot_index = scheduler.choose_action()
    chosen_slot = scheduler.get_slot(slot_index)

    if chosen_slot in occupied_times:
        scheduler.update_q(slot_index, -1)  # penalty for conflict
        return None
    else:
        appt_id = f"appt-{np.random.randint(100000,999999)}"
        datetime_obj = datetime.datetime.strptime(f"{date_str} {chosen_slot}", "%Y-%m-%d %H:%M")
        appointment = Appointment(appt_id, datetime_obj, f"Adoption appt for animal {animal_id} by user {user_id}")
        shelter.create({
            "appointmentId": appointment.getAppointmentId(),
            "appointmentDate": appointment.getAppointmentDate().isoformat(),
            "appointmentDescription": appointment.getAppointmentDescription(),
            "animalId": animal_id,
            "userId": user_id
        })
        scheduler.update_q(slot_index, 1)  # reward for successful booking
        return appointment

# Example usage
if __name__ == '__main__':
    times = ["09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00"]
    scheduler = QLearningScheduler(times)
    shelter = AnimalShelter("aacuser", "SNHU1234")

    today = datetime.date.today() + datetime.timedelta(days=1)
    result = schedule_appointment(scheduler, shelter, animal_id="A123", user_id="U456", date=today)

    if result:
        print(f"Appointment scheduled: {result.getAppointmentId()} at {result.getAppointmentDate()}")
    else:
        print("No available slot found. Reattempt needed.")