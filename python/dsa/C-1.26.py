#!/usr/bin/env python3

'''
C-1.26: Write a short program that takes as input three integers, a, b, and c, from
the console and determines if they can be used in a correct arithmetic
formula (in the given order), like “a + b = c,” “a = b − c,” or “a ∗ b = c.”
'''

def main():
    a = int(input())
    b = int(input())
    c = int(input())
    if a - b == c:
        print(str(a) + " - " + str(b) + " = " + str(c))
    elif a + b == c:
        print(str(a) + " + " + str(b) + " = " + str(c))
    elif a * b == c:
       print(str(a) + " + " + str(b) + " = " + str(c))
    elif a / b == c:
        print(str(a) + " + " + str(b) + " = " + str(c))
    else:
        print("No valid operation found for the inputs.")

 

if __name__ == "__main__":
    main()