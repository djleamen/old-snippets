section .text
    mov rdi, resultFormat   ; Format string for printf
    movss xmm0, dword [age] ; Load the float number from memory
    mov rax, 0              ; Clear rax to store return value fr
    push rbx                ; Align the stack
    call printf             ; Call printf function
    pop rbx                 ; Restore stack
section .data
    age dd 19.5             ; Single precision float number
    resultFormat db "%f", 0 ; Format string for printf