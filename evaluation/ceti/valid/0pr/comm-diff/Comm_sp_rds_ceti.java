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

}*/
public class Comm_sp_rds_ceti {

	public String pname,af,rf;
	public int n;
	public Comm_sp_rds_ceti(String a, String b, String c,int x)
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
	public void getInfo() throws IOException
	{
		int i,j;
		 String matlab="";

                String oneData="";
		String str1,str2,str,strts;
		String v;
		ArrayList<OneEPR> A=new ArrayList<OneEPR>();
		ArrayList<OneEPR10> B=new ArrayList<OneEPR10>();
                ArrayList<String> rdsts=new ArrayList<String>();
                String allET="";//all repair qualities with respect to an evaluation TS
                String avgrd="";
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
        int c=0,k;
        ArrayList<String> tt=new ArrayList<String>();
        for(i=0;i<tmp1.size();i++)
        {
        	//System.out.println("***************************************************");
        	v=tmp1.get(i).name;
        	if(this.SearchForOne10(v, tmp2).size()>0) //MFCC repair it, but the rds also repair it.
        	{
        		c++;
        		System.out.println("------------"+v);
                        oneData="[";
        		for(j=0;j<A.size();j++)
        		{
        		str1=this.SearchForOne(v, A.get(j).T);
            		oneData=oneData+str1+" ";
        		}
            		oneData=oneData+"]";
        		System.out.println(oneData);
                        matlab=matlab+"mf"+Integer.toString(c)+"="+oneData+";\n";
                System.out.println("****************************************************************************up-MFCC-below-RDS");
                        oneData="[";
                 rdsts=new ArrayList<String>();
                 avgrd="argrds"+Integer.toString(c)+"=[";
        		for(j=0;j<B.size();j++)
        		{
                                allET="[";
        			tt=new ArrayList<String>();
        			tt=this.SearchForOne10(v, B.get(j).T);
                                for(k=0;k<10;k++){                 
            		    rdsts.add("[");
                                 }
        			for(k=0;k<tt.size();k++){
                                  allET=allET+tt.get(k)+" ";
                                  rdsts.set(k, rdsts.get(k)+tt.get(k)+" ");     
            		          oneData=oneData+tt.get(k)+" ";
                                }
        			///*if(k<10)
        			//{
        			//	for(;k<10;k++)
        			//		rdsts.set(k, rdsts.get(k)+"NaN ");  
        			//}*/
                                allET=allET+"]";
                                matlab=matlab+"rds_allq"+Integer.toString(j+1)+"="+allET+"\n";
                                avgrd=avgrd+"mean("+"rds_allq"+Integer.toString(j+1)+") ";
        		}
                        avgrd=avgrd+"]\n";
                        matlab=matlab+avgrd;
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
                        strts=strts+"mean(rds_ts"+Integer.toString(c)+"_"+Integer.toString(k)+") ";    				}
    			}*/
        		strts=strts+"];\n";
        		matlab=matlab+strts;
            	oneData=oneData+"]";
        		System.out.println(oneData);
                matlab=matlab+"rds"+Integer.toString(c)+"="+oneData+";\n";


                        //matlab=matlab+"[p,h,stats]=ranksum(mf"+Integer.toString(c)+",rds"+Integer.toString(c)+")/3000"+";\n";
                       // matlab=matlab+"p"+Integer.toString(c)+"=stats.p"+";\n";
                       // matlab=matlab+"rs"+Integer.toString(c)+"=stats.ranksum"+";\n";
                       // matlab=matlab+"m=length(mf"+Integer.toString(c)+");\n";
                       // matlab=matlab+"n=length(cov"+Integer.toString(c)+");\n";
                       // matlab=matlab+"A"+Integer.toString(c)+"=A12(rs,m,n)"+";\n";
        	}
        }
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

        System.out.println("****: "+Integer.toString(c)); 
        System.out.println("MFCC size "+tmp1.size());
        System.out.println("rds size "+tmp2.size());
System.out.println("-----------------------------------------------------------------------------------matlab");
        System.out.println(matlab);
	}
	public static void main(String[] args) throws IOException
	{
		Comm_sp_rds_ceti uscc=new Comm_sp_rds_ceti(args[0],args[1],args[2],Integer.parseInt(args[3]));
		uscc.getInfo();
	}
}
