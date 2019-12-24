import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class CollectTimeAngelix {

	private String path;
	public CollectTimeAngelix(String a)
	{
		this.path=a;
	}
	public void getTime() throws IOException
	{
		BufferedReader br=new BufferedReader(new FileReader(new File(this.path)));
		String line;
		int minute=0;
                int hour=0;
		float second=0;
		float time;
		int p,q;
		String[] array;
		String str,t;
	        String tag="patch successfully generated in";
		while((line=br.readLine())!=null)
		{
                        p=line.indexOf(tag);
			if(p>=0)
			{
                                p=p+tag.length();
				q=line.indexOf(" ",p);
				str=line.substring(p+1,line.length());

                                p=str.indexOf("(");
                                str=str.substring(0,p);
                                str=str.trim();

                                p=str.indexOf("h");
                                if(p>=0)
                                {
                                   t=str.substring(0,p);
                                   hour=Integer.parseInt(t.trim());
                                   str=str.substring(p+1,str.length());
                                }

                                p=str.indexOf("m");

                                if(p>=0)
                                {
				array=str.split("m");
				minute=Integer.parseInt(array[0].trim());
				str=array[1];
				
                                }
                                
                                str=str.substring(0,str.length()-1);//delete 's'
				second=Float.parseFloat(str.trim());

				time=hour*3600+minute*60+second;
				System.out.print(time);
				break;
			}
		}
	}
    public static void main(String[] args) throws IOException
    {
    	CollectTimeAngelix ct=new CollectTimeAngelix(args[0]);
    	ct.getTime();
    	
    }
}
