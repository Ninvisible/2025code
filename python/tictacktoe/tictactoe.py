board = [[" ", " ", " "], [" ", " ", " "], [" ", " ", " "]]

def print_board(list):
    counter = 0
    print(" A B C") 
    for i in list:
        counter += 1
        print(str(counter) + " " + i[0] + "|" + i[1] + "|" + i[2])
        if counter < 3:
            print("  -----")

def game_over_check(list):
    for i in range(3):
        if list[i][0] != " " and list[i][0] == list[i][1] == list[i][2]:
            return (str(list[i][0]) + " won")
        if list[0][i] != " " and list[0][i] == list[1][i] == list[2][i]:
            return (str(list[0][i]) + " won")
    if list[0][0] != " " and list[0][0] == list[1][1] == list[2][2]:
        return (str(list[0][0]) + " won")
    if list[0][2] != " " and list[0][2] == list[1][1] == list[2][0]:
        return (str(list[0][2]) + " won")
    for row in list:
        if " " in row:
            return None
    return "Nobody won"

def player_move(player):
    while True:
        move = input("What is your move, " + player + "? (A2 for example): ").upper()
        if len(move) == 2 and move[0] in "ABC" and move[1] in "123":
            col = "ABC".index(move[0])
            row = int(move[1]) - 1
            if board[row][col] == " ":
                board[row][col] = player
                break
            else:
                print("That spot is already taken!")
        else:
            print("Invalid move. Try again.")

#loop
current_player = "X"
while True:
    print_board(board)
    player_move(current_player)
    result = game_over_check(board)
    if result:
        print_board(board)
        print(result)
        break
    current_player = "O" if current_player == "X" else "X"
