/**
 * in: the evaluation result of rds from Angelix or CETI
 * tth: the serial number of a random test suites
 * 
 * return: all pr related to the tth test suite
 * @author jmy
 *
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

//ceti,angelix,rds
public class Collect_pr_Ards1 {

	public String in;
	public String tth;
	public Collect_pr_Ards1(String a,String b)
	{
		this.in=a;
		this.tth=b;
	}
	public void calculateoneTS(ArrayList<String> data,String ts)
	{
		int i;
		String line="";
		int flag=0;
		int rp=0;
		int rr=0;
		ArrayList<String> prs=new ArrayList<String>();
		
		for(i=0;i<data.size();i++)
		{
			line=data.get(i);
			if(line.contains("-----------------"))
			{
				if(rr>0) rp++;
				rr=0;
				flag=0;
			}
			if(line.equals(ts))
				flag=1;
			else if(line.contains("test")) 
				flag=0;
			 if(line.length()>0 && line.charAt(0)>='0' && line.charAt(0)<='9')
			 {
				 if(flag==1)
				 {
					prs.add(line.trim());
			    	rr++;
				 }
			 }		
		}
		if(rr>0) 
		{
			rp++;
		}
     
		System.out.println("%-------------------"+ts+":"+Integer.toString(rp));
	    line="[";
		for(i=0;i<prs.size();i++)
	    {
	      line=line+prs.get(i)+" ";
	    }
		line=line+"];";
		System.out.println(line);
	}
	public void calculate() throws IOException
	{
		BufferedReader br=new BufferedReader(new FileReader(new File(this.in)));
		String line;
		ArrayList<String> data=new ArrayList<String>();
		while((line=br.readLine())!=null)
		{
			data.add(line);
		}
			this.calculateoneTS(data, "----------test suite"+this.tth);
	}
	public static void main(String[] args) throws IOException
	{
		Collect_pr_Ards1 am=new Collect_pr_Ards1(args[0],args[1]);
		am.calculate();
	}
}