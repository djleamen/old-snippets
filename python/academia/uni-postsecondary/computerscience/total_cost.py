# Description: This program calculates the total cost of an order of items

def main():
    cost_per_item = 19.99
    quantity = 5 
    subtotal_cost = cost_per_item * quantity
    tax = 0.13*subtotal_cost
    total_cost = subtotal_cost+tax

    print(f'cost_per_item = ${cost_per_item:0.2f}') # a sample for you to use for the other prices
    print('quantity = ' +str(quantity))
    print(f'subtotal_cost = ${subtotal_cost:0.2f}')
    print(f'tax = ${tax:0.2f}')
    print(f'total_cost = ${total_cost:0.2f}')

if __name__ == "__main__":
    main()