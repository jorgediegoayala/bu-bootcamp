#include <stdio.h> 
 
/* Forward declarations (prototypes) */
int add(int a, int b);
void print_result(int sum, int product); 
 
int main() { 
  int x = 6, y = 7; 
  int s = add(x, y); 
  print_result(s, x * y); 
  return 0;
} 
 
int add(int a, int b) { 
  return a + b; 
} 
 
void print_result(int sum, int product) { 
  printf("Sum: %d\n",   sum); 
  printf("Product: %d\n", product);
} 