import random

threeXthree = [[0,0,0], [0,0,0], [0,0,0]]

for i in threeXthree:
    for j in range(len(i)):
        i[j] = random.randint(1, 9)