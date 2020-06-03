package Hackathon;

import java.io.*;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Map;
import java.text.DecimalFormat;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException; 
import java.util.LinkedHashMap; 
import org.json.simple.JSONObject; 
import org.json.simple.JSONArray; 

public class UsecaseName_CPU {

	public static void main(String[] args) {
		
		String text = "";
		
		ArrayList<Double> values=new ArrayList<Double>();

		try {
			FileReader readfile = new FileReader("C:\\Users\\sathv\\OneDrive\\Desktop\\CPU.txt");
			BufferedReader readbuffer = new BufferedReader(readfile);
			
			for (int i = 1; i <= 680; i++) {
				text = readbuffer.readLine() + "\n";
				text = text.replaceAll("( )+", " ");
				String[] str=text.split(" ");
				String value=str[8].toString();
				
				values.add(Double.parseDouble(value));	
			}
			
			readbuffer.close();
		}
		catch(FileNotFoundException e){e.printStackTrace();}
		catch(IOException e){e.printStackTrace();}
		

		double avg=0;
		double max=0;
		
		DecimalFormat df1 = new DecimalFormat("#0.0"); 
		DecimalFormat df2 = new DecimalFormat("#0.00"); 
		

		for(int i=0;i<values.size();i++)
		{
			avg=avg+values.get(i);
			if(values.get(i)>max)
			{
				max=values.get(i);
			}
		}
		max = Double.valueOf(df2.format(max));
		avg = avg/values.size();
		avg = Double.valueOf(df2.format(avg));
		
		
		JSONObject obj1 = new JSONObject();
		JSONObject obj2 = new JSONObject();
		
		
		Map m = new LinkedHashMap(values.size()); 
	       
		for(int i=0;i<values.size();i++)
		{	
			 m.put((i+1)+"s", values.get(i)); 
		}
		obj2.put("values", m);
		obj2.put("maxcpu", max);
		obj2.put("avgcpu", avg);
		
		obj1.put("sampletransaction", obj2);
		
		JSONArray a = new JSONArray(); 
		
		a.add(obj1);
		
		
		
		try(FileWriter file = new FileWriter("myJSON.json"))
		{
			file.write(a.toString());
			file.flush();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		
		System.out.println(a);
		
	}


}