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

# Bottom row corners (right to left for clockwise order)
for c in [2, 0]:
    print(threeXthree[2][c])

print()

# Print in clockwise spiral order
n = len(threeXthree)
spiral = []

# Top row
for col in range(n):
    spiral.append(threeXthree[0][col])
# Right column (excluding top)
for row in range(1, n):
    spiral.append(threeXthree[row][n-1])
# Bottom row (excluding rightmost)
for col in range(n-2, -1, -1):
    spiral.append(threeXthree[n-1][col])
# Left column (excluding top and bottom)
for row in range(n-2, 0, -1):
    spiral.append(threeXthree[row][0])


for val in spiral:
    print(val)