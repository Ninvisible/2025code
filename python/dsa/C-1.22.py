#!/usr/bin/env python3
'''
Write a short Python program that takes two arrays a and b of length n
storing int values, and returns the dot product of a and b. That is, it returns
an array c of length n such that c[i] = a[i] · b[i], for i = 0, . . . , n − 1.
'''



def main():
    lista = [1, 2, 3, 4, 5]
    listb = [6, 7, 8, 9, 10]
    listc = []
    for i in range(len(lista)):
        listc.append(lista[i] * listb[i])
    print(listc)

if __name__ == "__main__":
    main()