inList = [0,2,9,11,15]
target = 9

for i in range(len(inList)):
    for j in range(len(inList)):
        if i != j and inList[i] + inList[j] == target:
            print([i, j])
            break
    else:
        continue
    break
