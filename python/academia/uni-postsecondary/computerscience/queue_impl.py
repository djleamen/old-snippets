# Description: Implementation of a queue data structure in Python.

class Queue:
    def __init__(self):
        self.queue = []
        self.front = 0
        self.back = 0

    def enqueue(self, item):
        self.queue.append(item)
        self.back += 1

    def front(self):
        if not self.is_empty():
            return self.queue[self.front]
        else:
            return None

    def dequeue(self):
        if not self.is_empty():
            item = self.queue[self.front]
            self.front += 1
            return item
        return None

    def is_empty(self):
        return self.front == self.back

    def __str__(self):
        return str(self.queue[self.front:self.back])

'''
queue = Queue()
print('is_empty():', queue.is_empty())
print('empty:', queue)
queue.enqueue(10)
print('after enqueue(10):', queue)
print('is_empty():', queue.is_empty())
queue.enqueue(1)
print('after enqueue(1):', queue)
print('dequeue():', queue.dequeue())
print('after dequeue():', queue)
queue.enqueue(2)
print('after enqueue(2):', queue)
queue.enqueue(3)
print('after enqueue(3):', queue)
queue.enqueue(4)
print('after enqueue(4):', queue)
print('dequeue():', queue.dequeue())
print('after dequeue():', queue)
print('dequeue():', queue.dequeue())
print('after dequeue():', queue)
print('dequeue():', queue.dequeue())
print('after dequeue():', queue)
print('dequeue():', queue.dequeue())
print('after dequeue():', queue)
print('is_empty():', queue.is_empty())
'''