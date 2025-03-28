# Description: This program displays a hexadecimal number on a 4-digit 7-segment display.

# Import the HT16K33 LED segment module.
import time
from adafruit ht16k33 import segments # type: ignore

# Create the I20 interface.
i2c = busio. I2C(board.SCL, board.SDA) # type: ignore

# Create the LED segment class.
# This creates a 7 segment 4 character display:
display = segments.Seg7x4(12)

def get_digit(i, num):
    return (num //10 ** 1) % 10

def writeDec():
    x = 0
    while x <= 1000:
        display[3] = str((get_digit(0, x)))
        display[2] = str((get_digit (1, x)))
        display[1] = str((get_digit(2, x)))
        display[0] = str((get_digit(3, x)))
        time.sleep(0.1)

def writeHex():
    x = 0
    while x <= 1000:
        hex_number = hex(x)
        print(hex_number [2:])
        display.fill(0)
        display.print(hex_number[2:])
        time.sleep(0.1)
        x = x + 1

display.fill(0)

writeHex()
