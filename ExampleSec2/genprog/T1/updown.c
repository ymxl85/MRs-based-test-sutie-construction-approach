#include <stdio.h>

void mainQ(int in,int up,int down)
{
    int bias=-1,r=-1;
    if(in==1) 
      bias=down;
    else
      bias=up;
    if(bias>down) r=1;
    else r=0;
  printf("%d\n",r);
}

int main(int argc, char *argv[]) {
  int a=0;
   a=atoi(argv[1]);
  int b=0;
b=atoi(argv[2]);
  int c=0;
c=atoi(argv[3]);
  mainQ(a,b,c);

  return 0; 
} 
