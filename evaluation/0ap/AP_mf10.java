import java.io.*;


public class AP_mf10 {

	public String in;
	public AP_mf10(String a)
	{
		this.in=a;
	}
	public void calculate() throws IOException
	{
		BufferedReader br=new BufferedReader(new FileReader(new File(this.in)));
		String line;
		int rr=0;
		String pname="";
		int trp=0;
		int trr=0;
		while((line=br.readLine())!=null)
		{
			if(line.contains("--------------------v"))
			{
				if(!pname.equals(""))
				{
					if(rr>0)
					 trp++;
					trr=trr+rr;
				}
				pname=line;
				rr=0;
			}
		    if(line.length()>0 && line.charAt(0)>='0' && line.charAt(0)<='9')
		    {
		    	rr++;
		    }
		}
		if(!pname.equals(""))
		{
			if(rr>0)
			 trp++;
			trr=trr+rr;
		}
		
		
		
		System.out.println("repair progs:"+Integer.toString(trp)+": repairs:"+Integer.toString(trr));
	}
	public static void main(String[] args) throws IOException
	{
		AP_mf10 am=new AP_mf10(args[0]);
		am.calculate();
	}
}
