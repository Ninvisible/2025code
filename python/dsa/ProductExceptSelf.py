nums = [0,0,0,0]

zero_count = 0
for num in nums:
    if num == 0:
        zero_count += 1
total_product = 1
for num in nums:
    if num != 0:
        total_product *= num
    answer = []
    for num in nums:
        if zero_count > 1:
            # If more than one zero, all products will be zero
            answer.append(0)
        elif zero_count == 1:
            # If exactly one zero, only the position with zero gets the product
            if num == 0:
                answer.append(total_product)
            else:
                answer.append(0)
        else:
            # No zeros, divide total product by the current number
            answer.append(total_product // num)
print(answer)