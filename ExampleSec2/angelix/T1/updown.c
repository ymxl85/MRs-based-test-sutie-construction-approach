#ifndef ANGELIX_OUTPUT
#define ANGELIX_OUTPUT(type, expr, id) expr
#define ANGELIX_REACHABLE(id)
#endif
#include <stdio.h>

int mainQ(int in,int up,int down)
{
    int bias;
    if(in==1) 
      bias=down;
    else
      bias=up;
    if(bias>down) return 1;
    else return 0;
}

int main(int argc, char *argv[]) {
  int a=atoi(argv[1]);
  int b=atoi(argv[2]);
  int c=atoi(argv[3]);
  fprintf(stdout, "%d\n", ANGELIX_OUTPUT(int, mainQ(a,b,c), "output"));

  return 0; 
} 
