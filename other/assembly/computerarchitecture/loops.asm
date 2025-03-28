section .text
    global main

main:
    mov rax, [sum] 
    mov rcx, [x] 

loopStart:
    cmp rcx, 100 
    jg loopExit 
    add rax, rcx 
    inc rcx jmp 
    loopStart 

loopExit:
    mov [x], rcx    ; Store the final value of x back into memor
    mov [sum], rax  ; Store the final value of sum back into mem
    mov rdi, format ; Set printf format string
    mov rsi, rax    ; Set the value of sum as the printf argumen
    xor rax, rax    ; Clear rax (printf return value)
    push rbx
    call printf     ; Call printf function
    pop rbx
    
section .data
    sum dq 0 x dq 1 ; Initialize sum to 0
    format db "Sum of numbers from 1 to 100: %d\n", 0 
