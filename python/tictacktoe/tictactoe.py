board = [[" ", " ", " "], [" ", " ", " "], [" ", " ", " "]]

def print_board(list):
    counter = 0
    for i in list:
        counter += 1
        print(i[0] + "|" + i[1] + "|" + i[2])
        if counter < 3:
            print("-----")


print_board(board)
