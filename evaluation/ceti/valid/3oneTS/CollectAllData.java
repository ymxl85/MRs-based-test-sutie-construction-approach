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
        public int ith;

	public CollectAllData(String a,int b)
	{
		this.in=a;
                this.ith=b;

	}
	public void collect() throws IOException
	{
		BufferedReader br=null;
		ArrayList<String> data=new ArrayList<String>();
		String line;
                String infile;
		int i;

                //for(i=1;i<=1;i++)
                //{
                i=this.ith;
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
               // }

		String value="[";

		for(i=0;i<data.size()-1;i++)
		{
			value=value+data.get(i)+",";

		}
		value=value+data.get(i)+"]";

		System.out.println(value);
                System.out.println("------------total:"+Integer.toString(data.size()));
                
	}
	public static void main(String[] args) throws IOException
	{
		CollectAllData cad=new CollectAllData(args[0],Integer.parseInt(args[1]));
		cad.collect();
	}
}
