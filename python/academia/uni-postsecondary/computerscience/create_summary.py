# Description: This script reads a file containing product data and creates a summary of the data.

import json

def create_summary(input_filename, output_filename):
    cheapest_product = ''
    costliest_product = ''
    min_price = 0.0
    max_price = 0.0
    total_price = 0
    total_count = 0

    with open(input_filename, 'r') as file:
        for line in file:
            parts = line.strip().split(',')
            if len(parts) >= 3:
                product_name = parts[1]
                price = float(parts[-1])

                if price < min_price:
                    min_price, cheapest_product = price, product_name
                if price > max_price:
                    max_price, costliest_product = price, product_name

                total_price += price
                total_count += 1

    avg_price = total_price / total_count if total_count > 0 else 0

    summary_data = {
        "cheapest_product": cheapest_product,
        "costliest_product": costliest_product,
        "average_price": avg_price
    }

    with open(output_filename, 'w') as outfile:
        json.dump(summary_data, outfile, indent=4)

create_summary('input_data.txt', 'results.json')