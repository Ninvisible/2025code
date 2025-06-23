#!/usr/bin/env python3

'''
C-1.24 Write a short Python function that counts the number of vowels in a given
character string.
'''

def main():
    def vowel_counter(string):
        count = 0
        for char in string:
            if char in 'aeiouAEIOU':
                count += 1
        return count

    print(vowel_counter("Hello World")) 

if __name__ == "__main__":
    main()