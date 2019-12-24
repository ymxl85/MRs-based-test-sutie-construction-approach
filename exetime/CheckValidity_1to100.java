import java.io.*;
import java.util.ArrayList;


public class CheckValidity_1to100 {
	public String vf;
	public String prefix;
	public String ts;
	String outf;
	public CheckValidity_1to100(String a,String b,String d,String c)
	{
		this.vf=a;
		this.prefix=b;
		this.ts=d;
		this.outf=c;
	}
	public void check() throws IOException
	{
		BufferedReader br=new BufferedReader(new FileReader(new File(this.vf)));
		BufferedWriter bw=new BufferedWriter(new FileWriter(new File(this.outf)));
		ArrayList<String> data=new ArrayList<String>();
		String line;
		int p;
		String prog="";
		String version="";
		String tsuite="";
		String seed="";
		String one;
		while((line=br.readLine())!=null)
		{
			if(line.contains("###"))
			{
				prog=line.substring(3,line.length());
				bw.write("-------------------------"+prog);bw.newLine();

			}
			if(line.contains("-------------------------------"))  //verson
			{
				p=line.lastIndexOf("-");
				version=line.substring(p+1,line.length());
				tsuite="";seed="";
			}
			else if(line.contains("----------test"))
			{
				p=line.indexOf("suite");
				tsuite=line.substring(p+5,line.length());
				seed="";
			}
			else if(line.contains("----------seed"))
			{
				p=line.indexOf("seed");
				seed=line.substring(p+4,line.length());
			}
			else if(line.contains("valid"))
			{
				if(line.startsWith("valid"))
				{
					one=this.prefix+"/"+prog+"/"+this.ts+"/1repairs/"+version+"/log"+tsuite+"-"+seed;
					bw.write(one);bw.newLine();
				}
				else
				{
					System.out.println(version+"ts:"+tsuite+":seed"+seed);
				}
			}
			
		}
		//if(!version.equals("") && !seed.equals(""))
		//{
		////  one=this.prefix+version+"/repair.debug.*-"+seed;
		//  bw.write(one);bw.newLine();
		//}
		bw.flush();bw.close();
		br.close();
	}
    public static void main(String[] args) throws IOException
    {
    	CheckValidity_1to100 ct=new CheckValidity_1to100(args[0],args[1],args[2],args[3]);
    	ct.check();
    }
}
