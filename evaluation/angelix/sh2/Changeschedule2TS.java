

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Changeschedule2TS {

	public String path;
	  public String inputdir;
	  public String idirname;
	  public Changeschedule2TS(String a,String b,String c)
	  {
		  this.path=a;
		  this.inputdir=b;
		  this.idirname=c;
	  }
	  public String getOperand (String a)
         {
	  String rstr="";
	  float r=0.0f;
	  float x=0.1f;
	  float rr = 1.0f;
	  String useful;
	  int ia;
	  int n=a.length();
	  int end=0;
	  int start=0;
	  int increase;
	  int i;
	  int len;
	  i=n-1;
	  while(i>=0 && a.charAt(i)=='0')
	  {
		  i--;
	  }
	  end=i+1;
	  
	  i=0;
	  while(i<n && a.charAt(i)=='0')
		  i++;
	  start=i;
	  
	  if(end>start)
	   useful=a.substring(start,end);
	  else
	   useful="0";
	  
	  ia=Integer.parseInt(useful);
	  
	  increase=n-end;
	  
	  len=end-increase;
	  for(i=0;i<len;i++)
		  rr=rr*x;
	  
	  r=rr*ia;

	  rstr=Float.toString(r);
	  return rstr;
  }
	  public String processArg(String a)
	  {
		  String all="";
		  String r="";
		  String[] array=a.split("\n");
		  int i;
		  String str;
		  String[] sub;
		  String cmd;
		  
		  
		  for(i=0;i<array.length;i++)
		  {
			  r="";
		    str=array[i];//a line
		    str=str.trim();
		    
		    sub=str.split(" ");
		    cmd=sub[0];
		    
		    if(cmd.equals("4"))
		    {
		        r=r+cmd+" ";	
		    	if(sub.length >1)
		    	{
		    	sub[1]=this.getOperand(sub[1]);
		    	r=r+sub[1]+"\n";
		    	}
		    }
		    else if(cmd.equals("2"))
		    {
		    	r=r+cmd+" ";
		    	if(sub.length >1)
		    	{
		    	  r=r+sub[1]+" ";
			    	if(sub.length >2)
		    	  {
		  	    	  sub[2]=this.getOperand(sub[2]);
			    	  r=r+sub[2]+"\n";
		    	  }
		    	}
		    }
		    else
		     r=r+str+"\n";
		    
		    all=all+r;
		  }
		 
		  
		  all=all.trim();
		  return all;
	  }
	 
	  
	  public int splitPos(String s)
	  {
		  int r=-1;
		  int p,q;
		  
		  p=s.indexOf(" ");
		  q=s.indexOf(" ", p+1);
		  
		  p=s.indexOf("\n",q+1);
		  if(p>-1)
			  r=p;
		  else
			  r=s.length();
		  
		  return r;
	  }
	  
	  public void processT0() throws IOException
	  {
		  File in =new File(this.path);
		  BufferedReader br =new BufferedReader(new FileReader(in));
		  BufferedWriter ow=new BufferedWriter(new FileWriter("runtc.sh"));
		  BufferedWriter inw=null;
		  String line;
		  String tc;
		  
		  String arg;
		  String str;
		  String cstr;
		  int c=1;
		  int p,q;
		  ///////////////////////////////////////////////////////////////////////
		  ow.write("#!/bin/bash");ow.newLine();
		  

		  ////////////////////////////////////////////////////////////////////////
		  tc="";
		  while((line=br.readLine())!=null)
		  {
			  tc=tc+line+"\n";
			  if(line.indexOf("> $2/O")>0)
			  {

				  cstr=Integer.toString(c);
				  
				  p=tc.indexOf("'");
				  tc=tc.substring(p+1,tc.length());//delete the first'''
				  p=tc.indexOf("'");
				  tc=tc.substring(0,p);
				  
				  q=this.splitPos(tc);
				  
				  
				  arg=tc.substring(0,q);
				  str="./limit ./$1 ";
				  str=str+" "+arg+" < "+this.idirname+"/in"+cstr+" > $2/O"+cstr;
				  ow.write(str);ow.newLine();
				  
				  inw=new BufferedWriter(new FileWriter(new File(this.inputdir+"/in"+cstr)));
				  q=q+1;
	              if(q<tc.length())
	              {
	            	  arg=tc.substring(q,tc.length());
	            	  arg=this.processArg(arg);
	              }
	              else
	            	  arg="";
	              inw.write(arg);
				  inw.flush();
				  inw.close();
				  


				  tc="";
				  c++;
			  }		  
		  }
		  ow.flush();ow.close();

	  }
	  public static void main(String[] args) throws IOException
	  {
		  Changeschedule2TS tt=new Changeschedule2TS(args[0],args[1],args[2]);
		  tt.processT0();
	  }
}
