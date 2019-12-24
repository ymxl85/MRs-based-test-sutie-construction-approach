import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


class TS //a test suite: name, a list of prs per seeds
{
	String name;
	ArrayList<seedpr> PR;
	public TS(String a)
	{
		this.name=a;
		PR=new ArrayList<seedpr>();
	}
}
class PRts10 //result of a faulty program, which is repair by 10 test suties
{
	String name;
	ArrayList<TS> Ts;
	public PRts10(String a)
	{
		this.name=a;
		Ts=new ArrayList<TS>();
	}
}
class PR100EP10
{
	ArrayList<PRts10> T=new ArrayList<PRts10>();
}
public class Exclusive_sp_rds_gp {
	public String pname,af,rf;
	public int n;
	ArrayList<String> allA, allB;

	public Exclusive_sp_rds_gp(String a, String b, String c,int x)
	{
		this.pname=a;
		this.af=b;
		this.rf=c;
		this.n=x;
		allA=new ArrayList<String>();
		allB=new ArrayList<String>();
	}
	public void readAFile10r(String file,ArrayList<PRs10> result) throws IOException
	{
		BufferedReader br=new BufferedReader(new FileReader(new File(file)));
		String str,pr="";
		PRs10 vpr=null;
		seedpr ctpr=null;
		String name="",ts="";
		int k=0;
		while((str=br.readLine())!=null)
		{
			if(str.length()>0 && str.charAt(0)=='-')
			{
				if(str.contains("test suite"))
				{
				k=str.lastIndexOf('-');
				ts=str.substring(k+1,str.length());
				ts=ts.replace("test suite", "seed");
				}
				else
				{
					if(!name.equals(""))
					{
						result.add(vpr);
					}
					k=str.lastIndexOf('-');
				    name=str.substring(k+1,str.length());
					vpr=new PRs10(name);
				}
			}
			else if (str.length()>0 && str.charAt(0)>='0' && str.charAt(0)<='9')
			{
				pr=str;
				ctpr=new seedpr(ts,pr);
				vpr.PR.add(ctpr);
			}
		}
		result.add(vpr);
		br.close();
	}
	public void readAFile100r(String file,ArrayList<PRts10> result) throws IOException
	{
		BufferedReader br=new BufferedReader(new FileReader(new File(file)));
		String str,pr="";
		PRts10 vpr=null;
		TS ts=null;
		seedpr ctpr=null;
		String name="",seed="";
		int k=0;
		while((str=br.readLine())!=null)
		{
			if(str.length()>0 && str.charAt(0)=='-')
			{
				if(str.contains("test suite"))
				{
				  if(ts!=null)
				  {
					  vpr.Ts.add(ts);
				  }
				  k=str.lastIndexOf('-');
				  name=str.substring(k+1,str.length());
				  ts=new TS(name);
				}
				else if(str.contains("seed"))
				{
					k=str.lastIndexOf('-');
					seed=str.substring(k+1,str.length());
				}
				else
				{
					if(!name.equals(""))
					{
						if(ts!=null) vpr.Ts.add(ts);
						result.add(vpr);
						ts=null;
					}
					k=str.lastIndexOf('-');
				    name=str.substring(k+1,str.length());
					vpr=new PRts10(name);
				}
			}
			else if (str.length()>0 &&str.charAt(0)>='0' && str.charAt(0)<='9')
			{
				pr=str;
				ctpr=new seedpr(seed,pr);
				ts.PR.add(ctpr);
			}
		}
		vpr.Ts.add(ts);
		result.add(vpr);
		br.close();
	}
	public ArrayList<String> SearchForOne10(String v,String seed,ArrayList<PRs10> result)
	{
		ArrayList<String> r=new ArrayList<String>();
		
		int i,j;
		for(i=0;i<result.size();i++)
		{
			if(result.get(i).name.equals(v))
			{
				for(j=0;j<result.get(i).PR.size();j++)
				{
					if(result.get(i).PR.get(j).seed.equals(seed))
					  r.add(result.get(i).PR.get(j).pr);
				}
				break;
			}
		}
		return r;
	}
	public ArrayList<String> SearchForOne100(String v,String seed, ArrayList<PRts10> result)
	{
		ArrayList<String> r=new ArrayList<String>();
		int i,j,k;
		for(i=0;i<result.size();i++)
		{
			if(result.get(i).name.equals(v))
			{
				for(j=0;j<result.get(i).Ts.size();j++)
				{
					for(k=0;k<result.get(i).Ts.get(j).PR.size();k++)
					{
						if(result.get(i).Ts.get(j).PR.get(k).seed.equals(seed))
					      r.add(result.get(i).Ts.get(j).PR.get(k).pr);
					}
				}
				break;
			}
		}
		return r;
	}
	public void getUsefulness() throws IOException
	{
		int i,j;
		
		String str1,str2;
		String v,seed;
		ArrayList<PR10EP10> A=new ArrayList<PR10EP10>();
		ArrayList<PR100EP10> B=new ArrayList<PR100EP10>();

		ArrayList<PRs10> tmp1=null;
		ArrayList<PRts10> tmp2=null;
		PR10EP10 tone=null;
		PR100EP10 rone=null;
        for(i=1;i<=n;i++)
        {
        	str1="../../T"+Integer.toString(i)+"_"+this.pname+"_Genprog_"+this.af+".txt";
        	tmp1=new ArrayList<PRs10>();
        	this.readAFile10r(str1, tmp1);
        	tone=new PR10EP10();
        	tone.T=tmp1;
        	A.add(tone);
        	/////////////////////////////rds
        	str2="../../T"+Integer.toString(i)+"_"+this.pname+"_Genprog_"+this.rf+".txt";
            tmp2=new ArrayList<PRts10>();
            this.readAFile100r(str2, tmp2);
            rone=new PR100EP10();
            rone.T=tmp2;
            B.add(rone);
        }
        /////////////////////////////////////////////////////
        int c1=0,c2=0,k,x;
        ArrayList<String> tt=new ArrayList<String>();
        for(i=0;i<tmp1.size();i++)
        {
        	v=tmp1.get(i).name;
        	for(x=0;x<tmp1.get(i).PR.size();x++)
        	{
        		seed=tmp1.get(i).PR.get(x).seed;
        	if(this.SearchForOne100(v,seed, tmp2).size()==0) //MFCC repair it, but the rds test suite does not.
        	{
        		c1++;
        		System.out.println("------------"+v+"----------------------"+seed);
        		System.out.print("[");
        		for(j=0;j<A.size();j++)
        		{
        			tt=new ArrayList<String>();
        			tt=this.SearchForOne10(v,seed, A.get(j).T);
            			for(k=0;k<tt.size();k++)
            			{
                		  System.out.print(tt.get(k)+" ");
                		  this.allA.add(tt.get(k));
            			}
        		}
        		System.out.println("]");
        	}
        	}
        }
        System.out.println("****MFCC usefulness: "+Integer.toString(c1)); 
        System.out.println("MFCC size "+tmp1.size());
        System.out.println("COV size "+tmp2.size());
        System.out.println("****************************************************************************up-MFCC-below-RDS");
        for(i=0;i<tmp2.size();i++)
        {
        	v=tmp2.get(i).name;
        	for(x=1;x<=10;x++)
        	{
        		seed="seed"+Integer.toString(x);
        	if(this.SearchForOne10(v, seed,tmp1).size()==0 && this.SearchForOne100(v, seed,tmp2).size()>0) //RDS repair it, but the MFCC test suite does not.
        	{
        		c2++;
        		System.out.println("------------"+v+"----------------------"+seed);
        		System.out.print("[");
        		for(j=0;j<B.size();j++)
        		{
        			tt=new ArrayList<String>();
        			tt=this.SearchForOne100(v,seed, B.get(j).T);
        			for(k=0;k<tt.size();k++)
        			{
            		  System.out.print(tt.get(k)+" ");
            		  this.allB.add(tt.get(k));
        			}
        		}
        		System.out.println("]");
        	}
        	}
        }
        System.out.println("****RDS usefulness: "+Integer.toString(c2)); 
        x=0;
        for(i=0;i<tmp1.size();i++)
        {
        	x=x+tmp1.get(i).PR.size();
        }
        System.out.println("MFCC size "+Integer.toString(x));
        x=0;
        for(i=0;i<tmp2.size();i++)
        {
        	for(j=0;j<tmp2.get(i).Ts.size();j++)
        	{
        		x=x+tmp2.get(i).Ts.get(j).PR.size();
        	}
        }
        System.out.println("RDS size "+Integer.toString(x));
        System.out.println("MFCC***********************************");
        System.out.print("[");
        for(i=0;i<this.allA.size();i++)
        {
  		  System.out.print(this.allA.get(i)+" ");
        }
        System.out.println("]");
        System.out.println("RDS***********************************");
        System.out.print("[");
        for(i=0;i<this.allB.size();i++)
        {
  		  System.out.print(this.allB.get(i)+" ");
        }
        System.out.println("]");
	}
	public static void main(String[] args) throws IOException
	{
		Exclusive_sp_rds_gp uscc=new Exclusive_sp_rds_gp(args[0],args[1],args[2],Integer.parseInt(args[3]));
		uscc.getUsefulness();
	}
}
