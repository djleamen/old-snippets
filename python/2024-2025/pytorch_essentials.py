# Description: PyTorch Essentials

# Create tensors

# import MockTensor, since torch library is not supported in coderpad
from mock_tensor import MockTensor as torch

# import NumPy
import numpy as np

show_expected_result = False
show_hints = False


def create_tensor_from_list(input_list):
    return torch.tensor(input_list)

def create_tensor_from_array(input_array):
    return torch.tensor(input_array)

def create_tensor_ones():
    return torch.ones(3, 4)

def create_tensor_full():
    return torch.full((4, 5), 5)



# Indexing and slicing

# import MockTensor, since torch library is not supported in coderpad
from mock_tensor import MockTensor as torch

show_expected_result = False
show_hints = False


def split_x_into_chunks(x):
    return torch.chunk(x, chunks=4, dim=0)

def split_y_into_chunks(y):
    return torch.chunk(y, chunks=4, dim=0)

def split_x_custom(x):
    return torch.split(x, split_size_or_sections=[5, 3], dim=0)

def split_y_custom(y):
    return torch.split(y, split_size_or_sections=[4, 6, 6], dim=0)