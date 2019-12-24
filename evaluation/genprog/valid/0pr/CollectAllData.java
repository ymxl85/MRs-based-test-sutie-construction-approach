import java.io.*;
import java.util.ArrayList;

/**
 * input: "_prog_TS.txt"
 * outptu: a list of data
 * @author jmy
 *
 */
public class CollectAllData {

	public String in;

	public CollectAllData(String a)
	{
		this.in=a;

	}
	public void collect() throws IOException
	{
		BufferedReader br=null;
		ArrayList<String> data=new ArrayList<String>();
		String line;
                String infile;
		int i;

                for(i=1;i<=10;i++)
                {
                infile="../T"+Integer.toString(i)+this.in;
                br=new BufferedReader(new FileReader(new File(infile)));
		while((line=br.readLine())!=null)
		{
			if(line.length()>0 && (line.charAt(0)=='-' || line.charAt(0)=='/'))
				continue;
			else
			{
                                line.trim();
                                if(line.length()>0)
				  data.add(line);
			}
		}
                br.close();
                }

		String value="[";
                if(data.size()>0){
		for(i=0;i<data.size()-1;i++)
		{
			value=value+data.get(i)+",";

		}
		value=value+data.get(i)+"]";
                }
		System.out.println(value);
                System.out.println("------------total:"+Integer.toString(data.size()/10));
                
	}
	public static void main(String[] args) throws IOException
	{
		CollectAllData cad=new CollectAllData(args[0]);
		cad.collect();
	}
}
