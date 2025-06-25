import random

threeXthree = [[0,0,0], [0,0,0], [0,0,0]]

for i in threeXthree:
    for j in range(len(i)):
        i[j] = random.randint(1, 9)

print(threeXthree[0])
print(threeXthree[1])
print(threeXthree[2])

print()


# Top row corners
for c in [0, 2]:
    print(threeXthree[0][c])