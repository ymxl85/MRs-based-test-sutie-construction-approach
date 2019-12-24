import java.io.*;
import java.util.ArrayList;

/**
 * 
 * @author jmy
 *Analyze the usefulness of sp and rds, in the context of CETI
 *input:pname: targer program name
 *      mf: MFCC
 *      n: number of files to be analyzed
 *      rf: RDS
 *      For example, to analyze the usefulness of sp, in the context of CETI, tcas
 *      pname=tcas, af=MFCC n=10, the pr file to be analyzed are: T1_tcas_MFCC.txt T2_tcas_MFCC.txt,
 *      the reference file is: T1_tcas_COV.txt
 */
/*class seedpr
{
	String seed;
	String pr;
	public seedpr(String a,String b)
	{
		this.seed=a;
		this.pr=b;
	}
}
class PRs10 //each program only has 10 repairs per individual seeds
{
	String name;
	ArrayList<seedpr> PR;
	public PRs10(String a)
	{
		this.name=a;
		this.PR=new ArrayList<seedpr>();
	}
}
class PR10EP10 // evaluation result of one evalution test suite: a list PRs10, each of which is the quality of repairs of one faulty program
{
	  ArrayList<PRs10> T=new ArrayList<PRs10>();

}*/
public class Comm_sp_cov_gp {

	public String pname,af,rf;
	public int n;
	ArrayList<String> allA, allB;
	public Comm_sp_cov_gp(String a, String b, String c,int x)
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
	public void getInfo() throws IOException
	{
		int i,j;
		
		String str1,str2,str;
		String v,seed;
		ArrayList<PR10EP10> A=new ArrayList<PR10EP10>();
		ArrayList<PR10EP10> B=new ArrayList<PR10EP10>();
                String matlab="";

                String oneData="";	
		ArrayList<PRs10> tmp1=null;
		ArrayList<PRs10> tmp2=null;
		PR10EP10 tone=null;
		PR10EP10 rone=null;
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
            tmp2=new ArrayList<PRs10>();
            this.readAFile10r(str2, tmp2);
            rone=new PR10EP10();
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
        	  if(this.SearchForOne10(v,seed, tmp2).size()>0) //MFCC repair it, but the cov test suite also repair it.
        	  {
        		c++;
        		System.out.println("------------"+v+"----------------------"+seed);
                        oneData="[";
                System.out.print("[");
        		for(j=0;j<A.size();j++)
        		{
        			tt=new ArrayList<String>();
        			tt=this.SearchForOne10(v,seed, A.get(j).T);
            			for(k=0;k<tt.size();k++)
            			{
                		  System.out.print(tt.get(k)+" ");
                                  oneData=oneData+tt.get(k)+" ";
                		  allA.add(tt.get(k));
            			}
        		}
        		System.out.println("]");
                        oneData=oneData+"]";
                        matlab=matlab+"mf"+Integer.toString(c)+"="+oneData+";\n";
                System.out.println("****************************************************************************up-MFCC-below-COV");
                System.out.print("[");
                        oneData="[";
        		for(j=0;j<B.size();j++)
        		{
        			tt=new ArrayList<String>();
        			tt=this.SearchForOne10(v,seed, B.get(j).T);
        			for(k=0;k<tt.size();k++)
        			{
            		  System.out.print(tt.get(k)+" ");
                                  oneData=oneData+tt.get(k)+" ";
            		  allB.add(tt.get(k));
        			}
        		}
        		System.out.println("]");
                        oneData=oneData+"]";
                        matlab=matlab+"cov"+Integer.toString(c)+"="+oneData+";\n";
        	  }
        	}
        }


        System.out.println("****informativeness: "+Integer.toString(c)); 
        System.out.println("MFCC size "+tmp1.size());
        System.out.println("COV size "+tmp2.size());
        System.out.println("MFCC***********************************");
        System.out.print("[");
        for(i=0;i<this.allA.size();i++)
        {
  		  System.out.print(this.allA.get(i)+" ");
        }
        System.out.println("]");
        System.out.println("COV***********************************");
        System.out.print("[");
        for(i=0;i<this.allB.size();i++)
        {
  		  System.out.print(this.allB.get(i)+" ");
        }
        System.out.println("]");
        System.out.println("-----------------------------------------------------------------------------------matlab");

oneData="mn1=[";
        str="mn2=[";
        str1="mfs=[";
        str2="covs=[";
        for(i=1;i<=c;i++)
        {
          oneData=oneData+"mean(mf"+Integer.toString(i)+") ";
          str=str+"mean(cov"+Integer.toString(i)+") ";
          str1=str1+"mf"+Integer.toString(i)+" ";
          str2=str2+"cov"+Integer.toString(i)+" ";
        }
        matlab=matlab+oneData+"]/3000\n"+str+"]/3000\n"+str1+"]/3000\n"+str2+"]/3000\n";

        System.out.println(matlab);





	}
	public static void main(String[] args) throws IOException
	{
		Comm_sp_cov_gp uscc=new Comm_sp_cov_gp(args[0],args[1],args[2],Integer.parseInt(args[3]));
		uscc.getInfo();
	}
}
