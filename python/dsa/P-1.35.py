#!/usr/bin/env python3

'''
P-1.35: The birthday paradox says that the probability that two people in a room
will have the same birthday is more than half, provided n, the number of
people in the room, is more than 23. This property is not really a paradox,
but many people ﬁnd it surprising. Design a Python program that can test
this paradox by a series of experiments on randomly generated birthdays,
which test this paradox for n = 5, 10, 15, 20, . . . , 100.
'''

def main():
    import random 

    def birthday_paradox(n):
        birthdays = []
        matchFound = False
        for i in range(n):
            if random.randint(1, 365) in birthdays:
                matchFound = True
                break
            else: 
                birthdays.append(random.randint(1, 365))

        if matchFound:
            print("In a room of " + str(n) + " people, at least two people share a birthday.")
        else:
            print("In a room of " + str(n) + " people, no two people share a birthday.")
    
    for n in range(5, 101, 5):
        print("Testing for n = " + str(n) + ":" )
        birthday_paradox(n)
        print()

 

if __name__ == "__main__":
    main()