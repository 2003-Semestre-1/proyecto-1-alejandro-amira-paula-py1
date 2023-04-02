mov al, -10
mov ah, 3ch
mov bx,4
inc
inc bx
dec
dec bx
mov ax, 5
mov bx, 20
mov cx,bx
mov ax,10
mov bx,20
swap ax,bx
mov dx, xd.txt
mov ah, 3ch
int 21h
mov ah, 3dh
int 21h
mov ah, 4dh
int 21h
mov al, prueba
mov ah, 40h
int 21h
mov ah, 41h
int 21h
