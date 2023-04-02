inc
mov ax,5
mov bx,5
push ax
pop ax
mov ax,10
mov bx,5
cmp ax,bx
jne 2
swap bx,ax
mov cx,2
mov dx,8