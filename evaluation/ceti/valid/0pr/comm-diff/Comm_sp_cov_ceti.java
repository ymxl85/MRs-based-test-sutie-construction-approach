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
public class Comm_sp_cov_ceti {

	public String pname,af,rf;
	public int n;
	public Comm_sp_cov_ceti(String a, String b, String c,int x)
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
	public void getInfo() throws IOException
	{
		int i,j;
                String matlab="";

                String oneData="";		
		String str,str1,str2;
		String v;
		ArrayList<OneEPR> A=new ArrayList<OneEPR>();
		ArrayList<OneEPR> B=new ArrayList<OneEPR>();

		ArrayList<PR1to1> tmp1=null, tmp2=null;
        OneEPR tone=null;
        for(i=1;i<=n;i++)
        {
        	str="../../T"+Integer.toString(i)+"_"+this.pname+"_"+this.af+".txt";
        	tmp1=new ArrayList<PR1to1>();
        	this.readAFile(str, tmp1);
        	tone=new OneEPR();
        	tone.T=tmp1;
        	A.add(tone);
        	////////////////////////////////
        	str="../../T"+Integer.toString(i)+"_"+this.pname+"_"+this.rf+".txt";
        	tmp2=new ArrayList<PR1to1>();
        	this.readAFile(str, tmp2);
        	tone=new OneEPR();
        	tone.T=tmp2;
        	B.add(tone);
        }
        /////////////////////////////////////////////////////
        int c=0;
        for(i=0;i<tmp1.size();i++)
        {
        	//System.out.println("***************************************************");
        	v=tmp1.get(i).name;
        	if(!this.SearchForOne(v, tmp2).equals("")) //A repair it, and B also repair it.
        	{
                        
        		c++;
        		System.out.println("------------"+v);

                        oneData="[";
        		for(j=0;j<A.size();j++)
        		{
        			str=this.SearchForOne(v, A.get(j).T);
            		oneData=oneData+str+" ";
        		}
            		oneData=oneData+"]";
        		System.out.println(oneData);
                        matlab=matlab+"mf"+Integer.toString(c)+"="+oneData+";\n";
                System.out.println("****************************************************************************up-MFCC-below-COV");
                        oneData="[";
        		for(j=0;j<B.size();j++)
        		{
        			str=this.SearchForOne(v, B.get(j).T);
            		oneData=oneData+str+" ";
        		}
            		oneData=oneData+"]";
        		System.out.println(oneData);
                        matlab=matlab+"cov"+Integer.toString(c)+"="+oneData+";\n";
                        //matlab=matlab+"[p,h,stats]=ranksum(mf"+Integer.toString(c)+"/3000,cov"+Integer.toString(c)+"/3000)"+";\n";
                        //matlab=matlab+"p"+Integer.toString(c)+"=p"+";\n";
                        //matlab=matlab+"rs=stats.ranksum"+";\n";
                        //matlab=matlab+"m=length(mf"+Integer.toString(c)+");\n";
                        //matlab=matlab+"n=length(cov"+Integer.toString(c)+");\n";
                       //matlab=matlab+"B"+Integer.toString(c)+"=A12(rs,m,n)"+";\n";
        	}
        }

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

        System.out.println("****"+Integer.toString(c)); 
        System.out.println("mfcc size "+tmp1.size());
        System.out.println("cov size "+tmp2.size());
        System.out.println("-----------------------------------------------------------------------------------matlab");
        System.out.println(matlab);
	}
	public static void main(String[] args) throws IOException
	{
		Comm_sp_cov_ceti uscc=new Comm_sp_cov_ceti(args[0],args[1],args[2],Integer.parseInt(args[3]));
		uscc.getInfo();
	}
}
