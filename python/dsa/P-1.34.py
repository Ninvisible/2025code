#!/usr/bin/env python3

'''
P-1.34 A common punishment for school children is to write out a sentence mul-
tiple times. Write a Python stand-alone program that will write out the
following sentence one hundred times: “I will never spam my friends
again.” Your program should number each of the sentences and it should
make eight different random-looking typos.
'''

def main():
    import random

    sentence = "I will never spam my friends again."
    typoSentences = []

    for i in range(0, 8):
        typoSentences.append(random.randint(0,100))
                             
    for i in range(0, 100):
        if i in typoSentences:
            print(str(i+1) + ": " + sentence.replace(sentence[random.randint(0,35)], "", 1 ))
        else:
            print(str(i+1) + ": " + sentence)
    print(typoSentences)
        
    
 

if __name__ == "__main__":
    main()