import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

//ceti,angelix,rds
public class AP_rds1 {

	public String in;
	public AP_rds1(String a)
	{
		this.in=a;
	}
	public void calculateoneTS(ArrayList<String> data,String ts)
	{
		int i;
		String line;
		String pname="";
		int flag=0;
		int rp=0;
		int rr=0;
		for(i=0;i<data.size();i++)
		{
			line=data.get(i);
			if(line.contains("-----------------"))
			{
				if(rr>0) rp++;
				rr=0;
				flag=0;
				pname=line;
			}
			if(line.equals(ts))
				flag=1;
			else if(line.contains("test")) flag=0;
			 if(line.length()>0 && line.charAt(0)>='0' && line.charAt(0)<='9')
			 {
				 if(flag==1)
			    	rr++;
			 }		
		}
		if(rr>0) rp++;

		System.out.println(ts+":"+Integer.toString(rp));
	}
	public void calculate() throws IOException
	{
		BufferedReader br=new BufferedReader(new FileReader(new File(this.in)));
		String line;
		int rr=0;
		String pname="";
		ArrayList<String> data=new ArrayList<String>();
		int i;
		while((line=br.readLine())!=null)
		{
			data.add(line);
		}
		for(i=1;i<=10;i++)
		{
			this.calculateoneTS(data, "----------test suite"+Integer.toString(i));
		}
	}
	public static void main(String[] args) throws IOException
	{
		AP_rds1 am=new AP_rds1(args[0]);
		am.calculate();
	}
}
