import java.io.*;
import java.util.ArrayList;

/*
 * for ceti or angelix, a rds quality for a version: an average quality from at most 10 rds test suites for this version
 * checked:ceti:T1_repalce_rds.txt
 */
public class Collect_rds_avg10 {

	public String infile;
	public Collect_rds_avg10(String a)
	{
		this.infile=a;
	}
	public int avg(ArrayList<String> a)
	{
		int r=0;
		int i;
		for(i=0;i<a.size();i++)
		{
			r=r+Integer.parseInt(a.get(i));
		}
		r=r/a.size();
		return r;
	}
	public void collect() throws IOException
	{
		String data="[ ";
		int c=0;
		int x;
		ArrayList<String> oneV=new ArrayList<String>();
		String line="";
		BufferedReader br=new BufferedReader(new FileReader(new File(this.infile)));
		
		while((line=br.readLine())!=null)
		{
			if(line.contains("------------------------------"))
			{
				if(oneV.size()>0)
				{
					x=this.avg(oneV);
					data=data+Integer.toString(x)+" ";
					c++;
					//System.out.println(oneV);
				}
				oneV.clear();
				//System.out.println(line);

			}
			if(line.length()>0 && (line.charAt(0)>='0' && line.charAt(0)<='9'))
			{
				oneV.add(line.trim());
			}
		}
		if(oneV.size()>0)
		{
			x=this.avg(oneV);
			data=data+Integer.toString(x)+" ";
			//System.out.println(oneV);

			c++;
		}
		data=data+"]";
		System.out.println("avg pr:"+data);
		System.out.println("total="+Integer.toString(c));
	}
	public static void main(String[] args) throws IOException
	{
		Collect_rds_avg10 cr=new Collect_rds_avg10(args[0]);
		cr.collect();
		
	}
}
