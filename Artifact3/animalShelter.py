#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Wed Sep 25 21:24:12 2024

@author: craigrowell_snhu
"""

from pymongo import MongoClient
from bson.objectid import ObjectId

class AnimalShelter(object):
    """ CRUD operations for Animal collection in MongoDB """

    def __init__(self, USER, PASS):
        # Initializing the MongoClient. This helps to 
        # access the MongoDB databases and collections.
        # This is hard-wired to use the aac database, the 
        # animals collection, and the aac user.
        # Definitions of the connection string variables are
        # unique to the individual Apporto environment.
        #
        # You must edit the connection variables below to reflect
        # your own instance of MongoDB!
        #
        # Connection Variables
        #
        #USER = 'aacuser'
        #PASS = 'SNHU1234'
        HOST = 'nv-desktop-services.apporto.com'
        PORT = 30535
        DB = 'AAC'
        COL = 'animals'
        #
        # Initialize Connection
        #
        self.client = MongoClient('mongodb://%s:%s@%s:%d' % (USER,PASS,HOST,PORT))
        self.database = self.client['%s' % (DB)]
        self.collection = self.database['%s' % (COL)]
        print("Connected to database\n")

# Complete this create method to implement the C in CRUD.
    def create(self, data: dict) -> bool: # added type hints to parameter and return value
        if data is not None:
            self.database.animals.insert_one(data)  # data should be dictionary
            return True # returns true if data is not a null value
        else:
            raise Exception("Nothing to save, because data parameter is empty") # null value error handling
            return False

# Create method to implement the R in CRUD.
    def read(self, data: dict) -> list: # added type hints to parameter and return value
        if data is not None: # validates that the data doesn't have a null value
            results = list(self.database.animals.find(data, {"_id": False})) # reads records matching the data dict in the animals database
            return results # returns the results of the find function
        else:
            raise Exception("No records found because data parameter is empty") # null value error handling
    
# Create method to implement the U in CRUD.
    def update(self, data: dict, new: dict) -> dict: # added type hints to parameter and return value
        if data is not None and new is not None:
            record = self.database.animals.update_many(data, {"$set":new}) # adds a new record to the database
            return record.raw_result # raw results produce human readable output, including the number of records modified
        else:
            raise Exception("Nothing to update, because data parameter is empty") # null value error handling

# Create method to implement the D in CRUD.
    def delete(self, data):
        if data is not None:
            remove = self.database.animals.delete_many(data) # removes existing record(s) from the database
            return remove.raw_result # raw results produce human readable output, including the number of records modified
        else:
            raise Exception("Nothing to delete, because data parameter is empty") # null value error handling