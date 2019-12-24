import java.io.*;
import java.util.ArrayList;

/*
 * for ceti or angelix, a rds quality for a version: an average quality from at most 10 rds test suites for this version
 * checked:ceti:T1_schedule_genprog_rds.txt
 */
class Tpr{
	ArrayList<String> spr;
	public Tpr()
	{
		spr=new ArrayList<String>();
		for(int i=0;i<=10;i++)
			spr.add("");
	}
}
class Vpr{
	ArrayList<Tpr> tpr;
	public Vpr()
	{
		tpr=new ArrayList<Tpr>();
	}
}
public class Collect_rds_avg100 {

	public String infile;
	int cr;
	int avgr;
	public Collect_rds_avg100(String a)
	{
		cr=0;
		this.infile=a;
		avgr=0;
	}
	/**
	 * 
	 * @param one
	 * for seed1: t = t1,seed1 + t2,seed1, + ... + t10,seed1 /10
	 * @return
	 */
			
	public String avg(Vpr one)
	{
		String r="";
		int t,c;
		String ele="";
		int i,s;
		for (s=1;s<=10;s++)
		{
			t=0;
			c=0;
			//System.out.println();
			//System.out.print("seed"+Integer.toString(s)+": ");
		  for(i=0;i<one.tpr.size();i++)
		  {
			  if(one.tpr.get(i).spr.size()>s) //the ith test suite
			  {
				  ele=one.tpr.get(i).spr.get(s); //the pr of seed s of ith test suite
				  if(!ele.equals("")) //have a value
				  {
					  //System.out.println(ele+" ");
				    t=t+Integer.parseInt(ele);
				    c++;
				  }
			  }
		  }
		  if(t>0)
		  {
		  t=t/c;
		  this.cr+=c;
		  r=r+Integer.toString(t)+" ";
		  this.avgr++;
		  }
		}
		return r;
	}
	public void collect() throws IOException
	{
		String data="[ ";
		String x,tmp;
		Vpr oneV=null;
		Tpr oneT=null;
		String line="";
		int p,s=0;
		BufferedReader br=new BufferedReader(new FileReader(new File(this.infile)));
		
		while((line=br.readLine())!=null)
		{
			if(line.contains("-------v"))
			{
				if(oneV!=null)
				{
					if(oneT!=null)
						oneV.tpr.add(oneT);
					x=this.avg(oneV);
					data=data+x+" ";
					//System.out.println(oneV);
				}
				oneV=new Vpr();
				oneT=new Tpr();
				//System.out.println(line);

			}
			if(line.contains("--------test"))
			{
				if(oneT!=null)
					oneV.tpr.add(oneT);
				oneT=new Tpr();
			}
			if(line.contains("-----seed"))
			{
				p=line.indexOf("seed");
				tmp=line.substring(p+4,line.length());
				s=Integer.parseInt(tmp.trim());
			}
			if(line.length()>0 && (line.charAt(0)>='0' && line.charAt(0)<='9'))
			{
				oneT.spr.add(s, line.trim());
			}
		}
		if(oneV!=null)
		{
			if(oneT!=null)
				oneV.tpr.add(oneT);
			x=this.avg(oneV);
			data=data+x+" ";
			//System.out.println(oneV);
		}
		data=data+"]";
		System.out.println("avg pr:"+data);
		System.out.println("total repairs="+Integer.toString(this.cr)+" total avg prs="+Integer.toString(this.avgr));
	}
	public static void main(String[] args) throws IOException
	{
		Collect_rds_avg100 cr=new Collect_rds_avg100(args[0]);
		cr.collect();
		
	}
}
