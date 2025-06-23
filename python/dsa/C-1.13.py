#!/usr/bin/env python3
'''
C-1.13 Write a pseudo-code description of a function that reverses a list of n
integers, so that the numbers are listed in the opposite order than they
were before, and compare this method to an equivalent Python function
for doing the same thing.
'''
def main():
    myList = [1, 2, 3, 4, 5, 4]
    newList = []
    for i in myList:
        newList.insert(0, i)
    print(newList)

if __name__ == "__main__":
    main()
