import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


/*class TS //a test suite: name, a list of prs per seeds
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
}*/
public class Comm_sp_rds_gp {
	public String pname,af,rf;
	public int n;
	ArrayList<String> allA, allB;

	public Comm_sp_rds_gp(String a, String b, String c,int x)
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
			else if (str.length()>0 &&str.charAt(0)>='0' && str.charAt(0)<='9')
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
	public void getInfor() throws IOException
	{
		int i,j;
		
		String str,str1,str2,strts="";
		 String matlab="";
                ArrayList<String> rdsts=new ArrayList<String>();
                String oneData="";
		String v,seed;
		ArrayList<PR10EP10> A=new ArrayList<PR10EP10>();
		ArrayList<PR100EP10> B=new ArrayList<PR100EP10>();
                String allET="";//all repair qualities with respect to an evaluation TS
                String avgrd="";
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
        int c=0,k,x;
        ArrayList<String> tt=new ArrayList<String>();
        for(i=0;i<tmp1.size();i++)
        {
        	v=tmp1.get(i).name;
        	for(x=0;x<tmp1.get(i).PR.size();x++)
        	{
        		seed=tmp1.get(i).PR.get(x).seed;
        	if(this.SearchForOne100(v,seed, tmp2).size()>0) 
        	{
        		c++;
        		System.out.println("------------"+v+"----------------------"+seed);
        		System.out.print("[");
                        oneData="[";
        		for(j=0;j<A.size();j++)
        		{
        			tt=new ArrayList<String>();
        			tt=this.SearchForOne10(v,seed, A.get(j).T);
            			for(k=0;k<tt.size();k++)
            			{
                		  System.out.print(tt.get(k)+" ");
            		           oneData=oneData+tt.get(k)+" ";
                		  this.allA.add(tt.get(k));
            			}
        		}
        		System.out.println("]");
            		oneData=oneData+"]";
                        matlab=matlab+"mf"+Integer.toString(c)+"="+oneData+";\n";
                System.out.println("****************************************************************************up-MFCC-below-RDS");
                System.out.print("[");
                        oneData="[";
                 rdsts=new ArrayList<String>();
                  for(k=0;k<10;k++){                 
            		    rdsts.add("[");
                    }
        		System.out.print("[");
                 avgrd="argrds"+Integer.toString(c)+"=[";
        		for(j=0;j<B.size();j++)
        		{
                                allET="[";
        			tt=new ArrayList<String>();
        			tt=this.SearchForOne100(v,seed, B.get(j).T);
        			for(k=0;k<tt.size();k++)
        			{
                                  allET=allET+tt.get(k)+" ";
            		  System.out.print(tt.get(k)+" ");
            		           oneData=oneData+tt.get(k)+" ";
                       rdsts.set(k, rdsts.get(k)+tt.get(k)+" ");     
            		  this.allB.add(tt.get(k));

        			}
                    /*if(k<10)
        			{
        				for(;k<10;k++)
        					rdsts.set(k, rdsts.get(k)+"NaN ");  
        			}*/
                                allET=allET+"]";
 matlab=matlab+"rds_allq"+Integer.toString(j+1)+"="+allET+"\n";
                                avgrd=avgrd+"mean("+"rds_allq"+Integer.toString(j+1)+") ";
        		}
                        avgrd=avgrd+"]\n";
                        matlab=matlab+avgrd;
        		System.out.println("]");
                        strts="mnts"+Integer.toString(c)+"=[";
        		for(k=0;k<tt.size();k++){                 
                           rdsts.set(k, rdsts.get(k)+"]");
                           matlab=matlab+"rds_ts"+Integer.toString(c)+"_"+Integer.toString(k)+"="+rdsts.get(k)+";\n";
                           strts=strts+"mean(rds_ts"+Integer.toString(c)+"_"+Integer.toString(k)+") ";
                         }
        		/*if(k<10)
    			{
    				for(;k<10;k++)
    				{
    					rdsts.set(k, rdsts.get(k)+"]");
                                        matlab=matlab+"rds_ts"+Integer.toString(c)+"_"+Integer.toString(k)+"="+rdsts.get(k)+";\n";
                                        strts=strts+"mean(rds_ts"+Integer.toString(c)+"_"+Integer.toString(k)+") ";    				
                                }
    			}*/
        		strts=strts+"];\n";
        		matlab=matlab+strts;
            	        oneData=oneData+"]";
        		System.out.println(oneData);
                        matlab=matlab+"rds"+Integer.toString(c)+"="+oneData+";\n";
        	  }
        	}
        }
        System.out.println("****informativeness: "+Integer.toString(c)); 
        System.out.println("MFCC size "+tmp1.size());
        System.out.println("RDS size "+tmp2.size());
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
 
oneData="mn1=[";
        str="mn2=[";
        str1="mfs=[";
        str2="rdss=[";
        strts="mnts=[";
        String str4="avgrdss=[";
        for(i=1;i<=c;i++)
        {
          oneData=oneData+"mean(mf"+Integer.toString(i)+") ";
          str=str+"mean(rds"+Integer.toString(i)+") ";
          str1=str1+"mf"+Integer.toString(i)+" ";
          str4=str4+"argrds"+Integer.toString(i)+" ";
          str2=str2+"rds"+Integer.toString(i)+" ";
          strts=strts+"max(mnts"+Integer.toString(i)+")"+" mean(mnts"+Integer.toString(i)+")"+" min(mnts"+Integer.toString(i)+");";
        }
        matlab=matlab+oneData+"]/3000\n"+str+"]/3000\n"+str1+"]/3000\n"+str2+"]/3000\n"+strts+"]/3000\n"+str4+"]\n";
System.out.println("-----------------------------------------------------------------------------------matlab");
        System.out.println(matlab);
        
}
	public static void main(String[] args) throws IOException
	{
		Comm_sp_rds_gp uscc=new Comm_sp_rds_gp(args[0],args[1],args[2],Integer.parseInt(args[3]));
		uscc.getInfor();
	}
}
