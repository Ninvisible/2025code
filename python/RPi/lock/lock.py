 3-digit code (as integer or string)
LOCK_CODE = "123"

#Initial state
is_locked = True

while True:
    if is_locked:
        user_input = input("Please enter code to unlock: ")
        if user_input == LOCK_CODE:
            print("Unlocked successfully.")
            is_locked = False
        else:
            print("Incorrect code!")
    else:
        user_input = input("Please enter 1 to lock: ")
        if user_input == "1":
            print("Locked.")
            is_locked = True
