#include <stdio.h> 

void swap(int *a, int *b);
void broken_swap(int a, int b);

int main(){
    int a=10;
    int b=20;

    printf("Before swap: x = %d , y = %d\n", a, b);
    swap(&a, &b);
    printf("After swap: x = %d , y = %d\n", a, b);

    printf("Before broken swap: x = %d , y = %d\n", a, b);
    swap(&a, &b);
    printf("After broken swap: x = %d , y = %d\n", a, b);
    return 0;
}

void swap(int *a, int *b) { 
    int temp = *a;   
    *a = *b;         
    *b = temp;       
} 

void broken_swap(int a, int b){
    int temp = a;   
    a = b;         
    b = temp;       
}