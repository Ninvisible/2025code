#!/usr/bin/env python3

'''
P-1.36 Write a Python program that inputs a list of words, separated by white-
space, and outputs how many times each word appears in the list. You
need not worry about efﬁciency at this point, however, as this topic is
something that will be addressed later in this book.
'''
word_list = input("Enter a list of words separated by whitespace: ").split()
word_count = {}

for word in word_list:
    if word in word_count:
        word_count[word] += 1
    else:
        word_count[word] = 1

print("Word counts: " + str(word_count))