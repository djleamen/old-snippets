section .data
    num1 dd 10
    num2 dd 20
    result dd 0         ; placeholder for result
    
section .text
    global _start

_start:
    mov eax, [num1]     ; load num1 into register eax
    add eax, [num2]
    mov [result], eax 

    mov eax, 60
    xor rdi, rdi
    syscall