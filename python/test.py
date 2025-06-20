'''

# 1.26
a = int(input())
b = int(input())
c = int(input())
if a - b == c:
    print(str(a) + " - " + str(b) + " = " + str(c))
elif a + b == c:
    print(str(a) + " + " + str(b) + " = " + str(c))
elif a * b == c:
    print(str(a) + " + " + str(b) + " = " + str(c))
elif a / b == c:
    print(str(a) + " + " + str(b) + " = " + str(c))
else:
    print("No valid operation found for the inputs.")
'''