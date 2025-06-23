#!/usr/bin/env python3

# 1.14 
'''
Write a short Python function that takes a sequence of integer values and
determines if there is a distinct pair of numbers in the sequence whose
product is odd.
'''


def main():
    myList = [1, 2, 3, 4, 5, 6]
    for i in myList:
        for j in myList:
            if i == j:
               continue
            elif (i * j)% 2 != 0:
                print("Odd pair found: " + str(i) + ", " + str(j))

if __name__ == "__main__":
    main()