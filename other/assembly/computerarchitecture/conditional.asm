section .text
    global main

main:
    mov rax, [x]                ; Load the value of x into a regi
    cmp rax, 5                  ; Compare the value of x with 5
    jb less_than_label          ; Jump if less than 5
    mov rdi, greater_than_msg   ; Load the address of the message
    mov rax, 0
    push rbx
    call printf                 ; Call printf to print the messag
    pop rbx

less_than_label:
    mov rdi, less_than_msg 
    mov rax, 0
    push rbx
    call printf
    pop rbx

section .data
    x dq 10 
    less_than_msg db "x is less than 5", 0 
    greater_than_msg db "x is greater than or equal to 5", 0 