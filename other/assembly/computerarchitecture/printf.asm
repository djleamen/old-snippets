section .text
    mov rdi, resultFormat   ; Load the address of the format str
    mov rsi, message        ; Load the address of the string mes
    mov rax, 0              ; Clear rax to store return value fr
    push rbx                ; Align the stack
    call printf             ; Call printf function
    pop rbx                 ; Restore stack

section .data
message db "Hello, World!", 0   ; Define the string message
resultFormat db "%s", 0         ; Format string for printf 