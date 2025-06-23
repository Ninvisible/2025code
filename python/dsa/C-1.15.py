#!/usr/bin/env python3

# 1.15
'''
Write a Python function that takes a sequence of numbers and determines
if all the numbers are different from each other (that is, they are distinct).
'''

def main():
    myList = [1, 2, 3, 4, 5, 4]
    for i in range(len(myList)):
        for j in range(len(myList)):
            if i == j:
                continue
            elif (myList[i] == myList[j]):
                print("Equal numbers found at indexes " + str(i) + " and " + str(j)) 

if __name__ == "__main__":
    main()