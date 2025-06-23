#!/usr/bin/env python3

'''
Write a short Python function that takes a string s, representing a sentence,
and returns a copy of the string with all punctuation removed. For exam-
ple, if given the string "Let s try, Mike.", this function would return
"Lets try Mike".
'''

def main():
    def punct_remover(string):
        return string.replace('.', '').replace(',', '').replace('!', '').replace('?', '')
    print(punct_remover("Hello, World! How are you?"))

 

if __name__ == "__main__":
    main()