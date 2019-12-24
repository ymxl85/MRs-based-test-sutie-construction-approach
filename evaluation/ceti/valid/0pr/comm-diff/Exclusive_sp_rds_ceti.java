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

class PR1to1 //each program only has one repair
{
	String name;
	String PR;
	public PR1to1(String a, String b)
	{
		this.name=a;
		this.PR=b;
	}
}
class seedts
{
	String ts;
	String pr;
	public seedts(String a,String b)
	{
		this.ts=a;
		this.pr=b;
	}
}
class PR1to10 //each program only has one repair
{
	String name;
	ArrayList<seedts> PR;
	public PR1to10(String a)
	{
		this.name=a;
		this.PR=new ArrayList<seedts>();
	}
}
class OneEPR
{
  ArrayList<PR1to1> T=new ArrayList<PR1to1>();
}
class OneEPR10
{
	  ArrayList<PR1to10> T=new ArrayList<PR1to10>();

}
public class Exclusive_sp_rds_ceti {

	public String pname,af,rf;
	public int n;
	public Exclusive_sp_rds_ceti(String a, String b, String c,int x)
	{
		this.pname=a;
		this.af=b;
		this.rf=c;
		this.n=x;
	}
	public void readAFile(String file,ArrayList<PR1to1> result) throws IOException
	{
		BufferedReader br=new BufferedReader(new FileReader(new File(file)));
		String str,pr="";
		PR1to1 vpr=null;
		String name="";
		int k=0;
		while((str=br.readLine())!=null)
		{
                        str.trim();
                        if(str.length()>0)
                        {
			if(str.charAt(0)=='-')
			{
				k=str.lastIndexOf('-');
				name=str.substring(k+1,str.length());
			}
			else
			{
				pr=str;
				vpr=new PR1to1(name,pr);
				result.add(vpr);
			}
                         }
		}
		br.close();
	}
	public void readAFile10r(String file,ArrayList<PR1to10> result) throws IOException
	{
		BufferedReader br=new BufferedReader(new FileReader(new File(file)));
		String str,pr="";
		PR1to10 vpr=null;
		seedts ctpr=null;
		String name="",ts="";
		int k=0;
		while((str=br.readLine())!=null)
		{
                        str.trim();
                        if(str.length()>0)
                        {
			if(str.charAt(0)=='-')
			{
				if(str.contains("test suite"))
				{
				k=str.lastIndexOf('-');
				ts=str.substring(k+1,str.length());
				}
				else
				{
					if(!name.equals(""))
					{
						result.add(vpr);
					}
					k=str.lastIndexOf('-');
				    name=str.substring(k+1,str.length());
					vpr=new PR1to10(name);
				}
			}
			else
			{
				pr=str;
				ctpr=new seedts(ts,pr);
				vpr.PR.add(ctpr);
			}
}
		}
		result.add(vpr);
		br.close();
	}
	public String SearchForOne(String v,ArrayList<PR1to1> result)
	{
		String r="";
		int i;
		for(i=0;i<result.size();i++)
		{
			if(result.get(i).name.equals(v))
			{
				r=result.get(i).PR;
				break;
			}
		}
		return r;
	}
	public ArrayList<String> SearchForOne10(String v,ArrayList<PR1to10> result)
	{
		ArrayList<String> r=new ArrayList<String>();
		int i,j;
		for(i=0;i<result.size();i++)
		{
			if(result.get(i).name.equals(v))
			{
				for(j=0;j<result.get(i).PR.size();j++)
					r.add(result.get(i).PR.get(j).pr);
				break;
			}
		}
		return r;
	}
	public void getUsefulness() throws IOException
	{
		int i,j;
		
		String str1,str2;
		String v;
		ArrayList<OneEPR> A=new ArrayList<OneEPR>();
		ArrayList<OneEPR10> B=new ArrayList<OneEPR10>();

		ArrayList<PR1to1> m1=new ArrayList<PR1to1>();
		ArrayList<PR1to1> tmp1=null;
		ArrayList<PR1to10> r1=new ArrayList<PR1to10>();
		ArrayList<PR1to10> tmp2=null;
        OneEPR tone=null;
        OneEPR10 rone=null;
        for(i=1;i<=n;i++)
        {
        	str1="../../T"+Integer.toString(i)+"_"+this.pname+"_"+this.af+".txt";
        	tmp1=new ArrayList<PR1to1>();
        	this.readAFile(str1, tmp1);
        	tone=new OneEPR();
        	tone.T=tmp1;
        	A.add(tone);
        	/////////////////////////////rds
        	str2="../../T"+Integer.toString(i)+"_"+this.pname+"_"+this.rf+".txt";
            tmp2=new ArrayList<PR1to10>();
            this.readAFile10r(str2, tmp2);
            rone=new OneEPR10();
            rone.T=tmp2;
            B.add(rone);
        }
        /////////////////////////////////////////////////////
        int c1=0,c2=0,k;
        ArrayList<String> tt=new ArrayList<String>();
        for(i=0;i<tmp1.size();i++)
        {
        	//System.out.println("***************************************************");
        	v=tmp1.get(i).name;
        	if(this.SearchForOne10(v, tmp2).size()==0) //MFCC repair it, but the rds test suite does not.
        	{
        		c1++;
        		System.out.println("------------"+v);
        		System.out.print("[");
        		for(j=0;j<A.size();j++)
        		{
        			str1=this.SearchForOne(v, A.get(j).T);
            		System.out.print(str1+" ");
        		}
        		System.out.println("]");
        	}
        }
        System.out.println("****MFCC usefulness: "+Integer.toString(c1)); 
        System.out.println("MFCC size "+tmp1.size());
        System.out.println("rds size "+tmp2.size());
        System.out.println("****************************************************************************up-MFCC-below-RDS");
        for(i=0;i<tmp2.size();i++)
        {
        	//System.out.println("***************************************************");
        	v=tmp2.get(i).name;
        	if(this.SearchForOne(v, tmp1).equals("")) //RDS repair it, but the MFCC test suite does not.
        	{
        		c2++;
        		System.out.println("------------"+v);
        		System.out.print("[");
        		for(j=0;j<B.size();j++)
        		{
        			tt=new ArrayList<String>();
        			tt=this.SearchForOne10(v, B.get(j).T);
        			for(k=0;k<tt.size();k++)
            		  System.out.print(tt.get(k)+" ");
        		}
        		System.out.println("]");
        	}
        }
        System.out.println("****RDS usefulness: "+Integer.toString(c2)); 
        System.out.println("MFCC size "+tmp1.size());
        System.out.println("rds size "+tmp2.size());
	}
	public static void main(String[] args) throws IOException
	{
		Exclusive_sp_rds_ceti uscc=new Exclusive_sp_rds_ceti(args[0],args[1],args[2],Integer.parseInt(args[3]));
		uscc.getUsefulness();
	}
}
