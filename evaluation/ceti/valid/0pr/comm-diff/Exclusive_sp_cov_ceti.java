import java.io.*;
import java.util.ArrayList;

/**
 * 
 * @author jmy
 *Analyze the usefulness of sp or cov, in the context of CETI
 *input:pname: targer program name
 *      af: the test suite to be analyzed
 *      n: number of files to be analyzed
 *      rf: the reference file
 *      For example, to analyze the usefulness of sp, in the context of CETI, tcas
 *      pname=tcas, af=MFCC n=10, the pr file to be analyzed are: T1_tcas_MFCC.txt T2_tcas_MFCC.txt,
 *      the reference file is: T1_tcas_COV.txt
 */

/*class PR1to1 //each program only has one repair
{
	String name;
	String PR;
	public PR1to1(String a, String b)
	{
		this.name=a;
		this.PR=b;
	}
}
class OneEPR
{
  ArrayList<PR1to1> T=new ArrayList<PR1to1>();
}*/
public class Exclusive_sp_cov_ceti {

	public String pname,af,rf;
	public int n;
	public Exclusive_sp_cov_ceti(String a, String b, String c,int x)
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
	public void getUsefulness() throws IOException
	{
		int i,j;
		
		String str;
		String v;
		ArrayList<OneEPR> A=new ArrayList<OneEPR>();
		ArrayList<PR1to1> ref=new ArrayList<PR1to1>();
		ArrayList<PR1to1> tmp=null;
        OneEPR tone=null;
		this.readAFile(this.rf, ref);
        for(i=1;i<=n;i++)
        {
        	str="../../T"+Integer.toString(i)+"_"+this.pname+"_"+this.af+".txt";
        	tmp=new ArrayList<PR1to1>();
        	this.readAFile(str, tmp);
        	tone=new OneEPR();
        	tone.T=tmp;
        	A.add(tone);
        }
        /////////////////////////////////////////////////////
        int c=0;
        for(i=0;i<tmp.size();i++)
        {
        	//System.out.println("***************************************************");
        	v=tmp.get(i).name;
        	if(this.SearchForOne(v, ref).equals("")) //A repair it, but the ref test suite does not.
        	{
        		c++;
        		System.out.println("------------"+v);
        		System.out.print("[");
        		for(j=0;j<A.size();j++)
        		{
        			str=this.SearchForOne(v, A.get(j).T);
            		System.out.print(str+" ");
        		}
        		System.out.println("]");
        	}
        }
        System.out.println("****"+Integer.toString(c)); 
        System.out.println("a size "+tmp.size());
        System.out.println("ref size "+ref.size());

	}
	public static void main(String[] args) throws IOException
	{
		Exclusive_sp_cov_ceti uscc=new Exclusive_sp_cov_ceti(args[0],args[1],args[2],Integer.parseInt(args[3]));
		uscc.getUsefulness();
	}
}
