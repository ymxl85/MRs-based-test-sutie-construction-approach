

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ChangeScheduleTS {

	public String path;
	  public String inputdir;
	  public String idirname;
	  public ChangeScheduleTS(String a,String b,String c)
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
		  String r="";
		  String[] array=a.split(" ");
		  int i;
		  String str;
		  
		  for(i=0;i<array.length;i++)
		  {
		    str=array[i];
		    if(str.equals("1"))
		    {
		    	r=r+array[i]+" ";
		    	i++;
		    	r=r+array[i]+" ";
	            continue;
		    }
		    else if(str.equals("4"))		    {
		    	r=r+array[i]+" ";
		    	i++;
		    	if(i < array.length)
		    	{
		    	str=array[i];
		    	array[i]=this.getOperand(str);
		    	r=r+array[i]+" ";
		    	}
		    	continue;
		    }
		    else if(str.equals("2"))
		    {
		    	r=r+array[i]+" ";
		    	i++;
		    	if(i < array.length) //second para
		    	{
		    	  r=r+array[i]+" ";
		    	  i++;
		    	  if(i<array.length) //third para
		    	  {
		    		  str=array[i];
		  	    	  array[i]=this.getOperand(str);
			    	  r=r+array[i]+" ";
		    	  }
		    	}
		    	continue;
		    }
		    r=r+array[i]+" ";
		  }
		 
		  
		  r=r.trim();
		  return r;
	  }
	  public int splitPos(String s)
	  {
		  int r=-1;
		  int p,q;
		  
		  p=s.indexOf(" ");
		  q=s.indexOf(" ", p+1);
		  
		  p=s.indexOf(" ",q+1);
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

		  BufferedWriter ow=new BufferedWriter(new FileWriter(new File("runtc.sh")));
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
				  str="./limit ./$1";
				  str=str+" "+this.idirname+"/in"+cstr+" "+arg+" > $2/O"+cstr;

				  ow.write(str);ow.newLine();
				  
				  inw=new BufferedWriter(new FileWriter(new File(this.inputdir+"/in"+cstr)));
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
		  ChangeScheduleTS tt=new ChangeScheduleTS(args[0],args[1],args[2]);
		  tt.processT0();
	  }
}
