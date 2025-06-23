#!/usr/bin/env python3

'''
P-1.29 Write a Python program that outputs all possible strings formed by using
the characters c , a , t , d , o , and g exactly once.
'''

def main():
    chars = ['c', 'a', 't', 'd', 'o', 'g']
    for i in range(6):
        for j in range(6):
            if j == i:
                continue
            for k in range(6):
                if k == i or k == j:
                    continue
                for l in range(6):
                    if l == i or l == j or l == k:
                        continue
                    for m in range(6):
                        if m == i or m == j or m == k or m == l:
                            continue
                        for n in range(6):
                            if n == i or n == j or n == k or n == l or n == m:
                                continue
                            print(chars[i] + chars[j] + chars[k] + chars[l] + chars[m] + chars[n])

 

if __name__ == "__main__":
    main()