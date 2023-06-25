from pymongo import MongoClient
from bson.objectid import ObjectId

class AnimalShelter(object):
    """ CRUD operations for Animal collection in MongoDB """

    def __init__(self):
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
        USER = 'aacuser'
        PASS = 'SNHU1234'
        HOST = 'nv-desktop-services.apporto.com'
        PORT = 31939
        DB = 'AAC'
        COL = 'animals'
        #
        # Initialize Connection
        #
        self.client = MongoClient('mongodb://%s:%s@%s:%d' % (USER,PASS,HOST,PORT))
        self.database = self.client['%s' % (DB)]
        self.collection = self.database['%s' % (COL)]

# Create method to implement the C in CRUD.

    def create(self, data=None):
        if data is not None:
            data_result = self.database.animals.insert_one(data)  # data should be dictionary  
            return data_result.acknowledged
        else:
            print(data)
            raise Exception("Nothing to save, because data parameter is either empty or not a dictionary")
            return False

# Read method to implement the R in CRUD. 

    def read(self, data=None):
        if data is not None:
            retList = list(self.database.animals.find(data))
            return retList
        else:
            retList = list(self.database.animals.find())
            return retList
            
# Update method to implement the R in CRUD.

    def update(self, dataFilter=None, updateData=None, updateMany=False):
        if updateData is None or dataFilter is None:
            return 0

        if updateMany:
            result = self.database.animals.update_many(dataFilter, updateData)
            return result.modified_count
        else:
            result = self.database.animals.update_one(dataFilter, updateData)
            return result.modified_count

# Delete method to implement the D in CRUD.

    def delete(self, dataFilter=None, deleteMany=False):
        if dataFilter is None:
            return 0;
    
        if deleteMany:
            result = self.database.animals.delete_many(dataFilter)
            return result.deleted_count
        else:
            result = self.database.animals.delete_one(dataFilter)
            return result.deleted_count
