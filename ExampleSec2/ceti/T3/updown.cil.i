# 1 "T3/updown.cil.c"
# 1 "<built-in>"
# 1 "<command-line>"
# 1 "T3/updown.cil.c"



extern int printf(char const * __restrict __format , ...) ;
int mainQ(int in , int up , int down )
{
  int bias ;
  int r ;

  {
  bias = -1;
  r = -1;
  if (in == 1) {
    bias = down;
  } else {
    bias = up;
  }
  if (bias > down) {
    r = 1;
  } else {
    r = 0;
  }
  return (r);
}
}
extern int ( atoi)() ;
int main(int argc , char **argv )
{
  int a ;
  int b ;
  int c ;
  int tmp ;

  {
  a = 0;
  a = atoi(*(argv + 1));
  b = 0;
  b = atoi(*(argv + 2));
  c = 0;
  c = atoi(*(argv + 3));
  tmp = mainQ(a, b, c);
  printf((char const * __restrict )"%d\n", tmp);
  return (0);
}
}
