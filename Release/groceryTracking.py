import re
import string

"""
 printList

 @Brief reads file "groceries.txt" and iterates through each item printing itemName:Qty

 @Return void
"""
def printList():
    #open file "groceries.txt" with read permission
    f = open("groceries.txt", "r");

    #init array variables
    fileResult = [];
    groceryItems = [];

    print()
    print("----------Grocery List----------\n\n")

    #for line in "groceries.txt" append to fileResult and for unique populate groceryItems then print itemName:Qty
    for x in f:
        fileResult.append(x)

        if x not in groceryItems:
            groceryItems.append(x);

    for item in groceryItems:
        #print itemName:Qty
        print("{itemName} - {count}".format(itemName = item.strip(), count = str(fileResult.count(item)).strip()));

    print();
        

"""
 countSpecificItem

 @Brief reads file "groceries.txt" and finds specified item returning Qty

 @Param itemName String item name to search for in file
 @Return int itemCount
"""
def countSpecificItem(itemName):
    #open file "groceries.txt" with read permission
    f = open("groceries.txt", "r");

    #init array and integer variable
    fileResult = [];
    retInt = 0;

    #for line in "groceries.txt" append to fileResult with item (remove newline and make lowercase)
    for x in f:
        fileResult.append(x.strip().lower())

    #return specified item count in "groceries.txt"
    return fileResult.count(itemName.lower());


"""
 writeFrequency

 @Brief reads file "groceries.txt" and writes to file "frequency.dat" with itemName:Qty

 @Return void
"""
def writeFrequency():
    #open file "groceries.txt" with read permission
    f = open("groceries.txt", "r");

    #init array variables
    fileResult = [];
    groceryItems = [];

    #for line in "groceries.txt" append to fileResult and for unique populate groceryItems
    for x in f:
        fileResult.append(x)

        if x not in groceryItems:
            groceryItems.append(x);

    #close file "groceries.txt"
    f.close()

    #write to "frequency.dat" file with histogram data (overwrite or create new if file DNE)
    f = open("frequency.dat", "w")

    #for item in arr write itemName:Qty to "frequency.dat"
    for item in groceryItems:
        if fileResult.index(item) != (len(fileResult) - 1):
            f.write("{itemName} {count}\n".format(itemName = item.strip(), count = str(fileResult.count(item)).strip()));
        else:
            f.write("{itemName} {count}".format(itemName = item.strip(), count = str(fileResult.count(item)).strip()));

    #close file "frequency.dat"
    f.close()