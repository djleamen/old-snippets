# Description: Draw a parallelogram with height h and width w

def drawParallelogram(h, w):
    for i in range(h):
        for j in range(i):
            print(" ", end="")
        for k in range(w):
            print("*", end="")
        print()

drawParallelogram(7, 13)