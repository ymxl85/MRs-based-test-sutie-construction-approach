import java.io.*;
import java.util.ArrayList;
/**
 * for gp-rds
 * @author jmy
 *
 */
class Ele_invalid{
	String subject;
	ArrayList<String> vs;//v1 test suite 1:[1,2,3,4] :test suite (1),seeds 1,2,3,4,
	public Ele_invalid(String a,ArrayList<String> t)
	{
		this.subject=a;
		this.vs=t;
	}
}

public class Valid_gp {

	public String rvalid;
	public String rdir;
	public String tdir;
	ArrayList<Ele_invalid> inps;
	public Valid_gp(String a,String b,String d)
	{
	  this.rvalid=a;
	  this.rdir=b;
	  this.tdir=d;
	  this.inps=new ArrayList<Ele_invalid>();
	}
	public void getInvalidProgs() throws IOException
	{
		BufferedReader br=new BufferedReader(new FileReader(new File(this.rvalid)));
		String curs="";
		String curt="";
		String curv="";
		String ts="";
		String sd="";
		String sds="";
		ArrayList<String> tv=null;
		String line;
		int i,j;
		Ele_invalid ele=null;
		int p;
		int total=0;
		String check="";
		while((line=br.readLine())!=null)
		{
			if(line.startsWith("###"))
			{
				if(!curs.equals(""))
				{
					if(!sds.equals(""))
					{
						sds=sds.substring(1,sds.length());
						curt=curt+" : "+sds+" # ";
						ts=ts+curt;
					}
					if(!ts.equals(""))
					{
						curv=curv+" # "+ts;
						tv.add(curv);
					}
					ele=new Ele_invalid(curs,tv);
                    System.out.println(curs+":"+Integer.toString(total));
					System.out.println(curs+": "+tv);
                                        
					this.inps.add(ele);
				}
				tv=new ArrayList<String>();
				ts="";
				sds="";
                                total=0;
				curs=line.substring(3,line.length());
				curs.trim();
			}
			if(line.startsWith("-------------------------------v"))
			{
				if(!ts.equals(""))
				{
                    if(!sds.equals(""))
				    {
					sds=sds.substring(1,sds.length());
					curt=curt+" : "+sds+" # ";
					ts=ts+curt;
				     }
					curv=curv+" # "+ts;
					tv.add(curv);
				}
				else if(!sds.equals(""))
				{
					sds=sds.substring(1,sds.length());
					curt=curt+" : "+sds+" # ";
					ts=ts+curt;
					curv=curv+" # "+ts;
					tv.add(curv);
				}
				p=line.indexOf("v");
				curv=line.substring(p,line.length());
				curv.trim();
				ts="";
                sds="";
			}
			if(line.startsWith("----------test suite"))
			{
				if(!sds.equals(""))
				{
					sds=sds.substring(1,sds.length());
					curt=curt+" : "+sds+" # ";
					ts=ts+curt;
				}
				p=line.indexOf("test");
				curt=line.substring(p,line.length());
				curt=curt.trim();
				sds="";
			}
			if(line.startsWith("----------seed"))
			{
			  p=line.indexOf("seed");
			  sd=line.substring(p,line.length());
			  sd.trim();
			}
			if(line.contains("***********invalid"))
			{
				check="***omit "+curs+"-"+curv+"-"+curt+"-"+sd;
				System.out.println(check);
				if(check.equals("***omit tcas-v24-test suite10-seed1"))
					System.out.println("check");
				total++;
				sds=sds+" , "+sd;
			}
		}
		///////////////////////////////////////////////////
		if(!curs.equals(""))
		{
			if(!sds.equals(""))
			{
				sds=sds.substring(1,sds.length());
				curt=curt+" : "+sds+" # ";
				ts=ts+curt;
			}
			if(!ts.equals(""))
			{
				curv=curv+" # "+ts;
				tv.add(curv);
			}
			ele=new Ele_invalid(curs,tv);
            System.out.println(curs+" : "+Integer.toString(total));
			System.out.println(curs+" : "+tv);
			this.inps.add(ele);
		}
		br.close();
	}
	public int isInvalid(ArrayList<String> data,String v,String t,String s)
	{
		int r=-1;
		int i;
		String one;
		String ts;
		int p,q;
		for(i=0;i<data.size();i++)
		{
		  one=data.get(i);
		  if(one.startsWith(v+" "))
		  {
			  p=one.indexOf(t+" ");
			  if(p>0)//have the test suite
			  {
				q=one.indexOf("test suite",p+1);  
				if(q>p)
					ts=one.substring(p,q);
				else
					ts=one.substring(p,one.length());
				
				p=ts.indexOf(s+" ");
				if(p>0)
					r=1;
			  }
			  break;
		  }
		}
		return r;
	}
	public void filterInvalidResult() throws IOException
	{
		int t,i,p;
		String subject;
		String[] array;
		Ele_invalid ele;
		String fname;
		BufferedReader br=null;
		BufferedWriter bw=null;
		String line;
		String curs="";
		String curt="";
		String curv="";
		String sfile,tfile;
		int total=0;
		for(t=0;t<this.inps.size();t++)
		{
			ele=this.inps.get(t);
			subject=ele.subject;
                        total=0;
		    for(i=1;i<=10;i++)
		    {
			  fname="T"+Integer.toString(i)+"_"+subject+"_Genprog_RDS.txt";
			  sfile=this.rdir+"/"+fname;
			  tfile=this.tdir+"/"+fname;
			  br=new BufferedReader(new FileReader(new File(sfile)));
			  bw=new BufferedWriter(new FileWriter(new File(tfile)));
			
			  while((line=br.readLine())!=null)
			  {
				if(line.contains("-------------------v"))
				{
					p=line.indexOf("v");
					curv=line.substring(p,line.length());
					curv.trim();
				}
				if(line.contains("test suite"))
				{
					p=line.indexOf("test suite");
					curt=line.substring(p,line.length());
					curt.trim();
				}
				if(line.contains("seed"))
				{
					p=line.indexOf("seed");
					curs=line.substring(p,line.length());
					curs.trim();
				}
				if(line.length()>0 && line.charAt(0)>='0' && line.charAt(0)<='9')
				{
					if(this.isInvalid(ele.vs, curv, curt, curs)==1)
					{
						System.out.println("***omit "+subject+"-"+curv+"-"+curt+"-"+curs);
						total++;
						continue;
					}
				}
				bw.write(line);bw.newLine();
			  }
			  bw.flush();bw.close();
			  br.close();
		   }
                        System.out.println("-----------------------------omitted:"+subject+"--"+Integer.toString(total));

		}


	}
	public static void main(String[] args) throws IOException
	{
		Valid_gp vg=new Valid_gp(args[0],args[1],args[2]);
		vg.getInvalidProgs();
		vg.filterInvalidResult();
	}
}
