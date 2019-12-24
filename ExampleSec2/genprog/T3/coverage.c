extern  __attribute__((__nothrow__)) void *memset(void *__s , int __c ,
                                                  unsigned int __n )  __attribute__((__nonnull__(1))) ;
struct _IO_FILE;
extern int fprintf(struct _IO_FILE * __restrict  __stream ,
                   char const   * __restrict  __format  , ...) ;
extern struct _IO_FILE *fopen(char const   * __restrict  __filename ,
                              char const   * __restrict  __modes ) ;
extern int fflush(struct _IO_FILE *__stream ) ;
extern int fclose(struct _IO_FILE *__stream ) ;
struct _IO_FILE *_coverage_fout  ;
extern int ( /* missing proto */  printf)() ;
void mainQ(int in , int up , int down ) 
{ int bias ;
  int r ;

  {
  {
  if (_coverage_fout == 0) {
    {
    _coverage_fout = fopen("/home/mingyue/experiments/genprog-source-v3.0/TSE0/updown3/./coverage.path",
                           "wb");
    }
  }
  }
  {
  fprintf(_coverage_fout, "5\n");
  fflush(_coverage_fout);
  }
  bias = -1;
  {
  fprintf(_coverage_fout, "6\n");
  fflush(_coverage_fout);
  }
  r = -1;
  {
  fprintf(_coverage_fout, "7\n");
  fflush(_coverage_fout);
  }
  if (in == 1) {
    {
    fprintf(_coverage_fout, "1\n");
    fflush(_coverage_fout);
    }
    bias = down;
  } else {
    {
    fprintf(_coverage_fout, "2\n");
    fflush(_coverage_fout);
    }
    bias = up;
  }
  {
  fprintf(_coverage_fout, "8\n");
  fflush(_coverage_fout);
  }
  if (bias > down) {
    {
    fprintf(_coverage_fout, "3\n");
    fflush(_coverage_fout);
    }
    r = 1;
  } else {
    {
    fprintf(_coverage_fout, "4\n");
    fflush(_coverage_fout);
    }
    r = 0;
  }
  {
  fprintf(_coverage_fout, "9\n");
  fflush(_coverage_fout);
  }
  printf("%d\n", r);
  {
  fprintf(_coverage_fout, "10\n");
  fflush(_coverage_fout);
  }
  return;
}
}
extern int ( /* missing proto */  atoi)() ;
int main(int argc , char **argv ) 
{ int a ;
  int b ;
  int c ;

  {
  {
  if (_coverage_fout == 0) {
    {
    _coverage_fout = fopen("/home/mingyue/experiments/genprog-source-v3.0/TSE0/updown3/./coverage.path",
                           "wb");
    }
  }
  }
  {
  fprintf(_coverage_fout, "11\n");
  fflush(_coverage_fout);
  }
  a = 0;
  {
  fprintf(_coverage_fout, "12\n");
  fflush(_coverage_fout);
  }
  a = atoi(*(argv + 1));
  {
  fprintf(_coverage_fout, "13\n");
  fflush(_coverage_fout);
  }
  b = 0;
  {
  fprintf(_coverage_fout, "14\n");
  fflush(_coverage_fout);
  }
  b = atoi(*(argv + 2));
  {
  fprintf(_coverage_fout, "15\n");
  fflush(_coverage_fout);
  }
  c = 0;
  {
  fprintf(_coverage_fout, "16\n");
  fflush(_coverage_fout);
  }
  c = atoi(*(argv + 3));
  {
  fprintf(_coverage_fout, "17\n");
  fflush(_coverage_fout);
  }
  mainQ(a, b, c);
  {
  fprintf(_coverage_fout, "18\n");
  fflush(_coverage_fout);
  }
  return (0);
}
}
