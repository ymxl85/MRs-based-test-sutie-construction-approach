extern int ( /* missing proto */  printf)() ;
void mainQ(int in , int up , int down ) 
{ int bias ;
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
    __repair_app_3__18: /* CIL Label */ 
    {
    r = 0;
    r = 1;
    }
  }
  printf("%d\n", r);
  return;
}
}
extern int ( /* missing proto */  atoi)() ;
int main(int argc , char **argv ) 
{ int a ;
  int b ;
  int c ;

  {
  a = 0;
  a = atoi(*(argv + 1));
  b = 0;
  b = atoi(*(argv + 2));
  c = 0;
  c = atoi(*(argv + 3));
  mainQ(a, b, c);
  return (0);
}
}
