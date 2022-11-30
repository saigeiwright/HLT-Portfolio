import sys
import pathlib
import time
import re
import pickle
import os


# Create a function to process the input file.
def processingLines(persons_dict):
    # The user needs to specify the relative path ‘data/data.csv’ in a sysarg.
    
    if len(sys.argv) < 2:
        print('Please enter a filename as a system arg in terminal ex. $python program.py filename')
        time.sleep(5)
        quit()

    #try reading file 
    try:
        file = open(pathlib.Path.cwd().joinpath(sys.argv[1]), 'r')
        line = file.readline()  # Remove the heading line of the CSV file
    except: 
        print("File not found")
        quit()

    while True:
        #split on comma, modify last/first to captialize, middle initial capatalize, ID, phone number if necessary
        line = file.readline().strip()
        if not line:
            break
        peopleList = line.split(',')
        peopleList[0] = peopleList[0].capitalize()
        peopleList[1] = peopleList[1].capitalize()

        #middle initial
        if len(peopleList[2]) != 1:
            peopleList[2] = 'X'

        #id
        while re.match('[A-Za-z][A-Za-z]\d{4}', peopleList[3]) is None:
            print('ID invalid: ', peopleList[3])
            print('ID is two letters followed by 4 digits')
            peopleList[3] = input('Please enter a valid id: ')

        #phone number
        while re.match('\w{3}-\w{3}-\w{4}', peopleList[4]) is None:
            print('Phone ', peopleList[4], ' is invalid')
            print('Enter phone number in form 123-456-7890')
            peopleList[4] = input('Enter phone number: ')

        #Create a Person object and save the object to a dict of persons, where id is the key.
        person = Person(peopleList[0], peopleList[1], peopleList[2].capitalize(), peopleList[3], peopleList[4])
        persons_dict[peopleList[3]] = person

    return persons_dict

# Define a Person class with fields last, first, mi, id, and phone w display() method 
class Person:
    def __init__(self, last, first, mi, id, phone):
        self.last = last
        self.first = first
        self.mi = mi
        self.id = id
        self.phone = phone

    def display(self):
        print('Employee id: ', self.id)
        print('\t', self.first, ' ', self.mi, ' ', self.last)
        print('\t', self.phone, '\n')


# Main function, dictionary as pickle file, display() to show unpiclked correctly 
if __name__ == '__main__':
    people = {}
    print ("The arguments are: " , str(sys.argv))
    people = processingLines(people)

    pickle.dump(people, open('people.pickle', 'wb'))
    people_input = pickle.load(open('people.pickle', 'rb'))  # Open the pickle file for reading

    print('\nPerson list:\n')
    for p_ID in people_input.keys():
        people_input[p_ID].display()