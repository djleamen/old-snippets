# Description: This file contains examples of greedy algorithms in Python

def greedy_change(amount, denoms):
    denoms.sort(reverse=True)
    change = []
    for denom in denoms:
        while amount >= denom:
            amount -= denom
            change.append(denom)
    return change

if __name__ == '__main__':

    denoms = [1,2,5,10,20,50]

 

    print(f'greedy_change(174, {denoms}) == {greedy_change(174, denoms)}')

    # output: greedy_change(174, [1,2,5,10,20,50]) == [50, 50, 50, 20, 2, 2]

 

    print(f'greedy_change(99, {denoms}) == {greedy_change(99, denoms)}')

    # output: greedy_change(99, [1,2,5,10,20,50]) == [50, 20, 20, 5, 2, 2]