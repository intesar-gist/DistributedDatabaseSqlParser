package sqlParserDemo;

import java.io.FileReader;
import java.util.ArrayList;

/*for testing the parser class*/
public class ParseDemoTest { 
	  public static void main(String[] args) { 
	    try{
			SqlParser parser = new SqlParser(new FileReader("sqlParserDemo/test_queries.sql"));

			ArrayList<TableStruct> tableList = parser.initParser();

			for(TableStruct t1 : tableList)
			{  System.out.println("--------------------------");
				System.out.println("Table Name :"+t1.TableName);
				System.out.println("Field names :"+t1.Variables.keySet());
				System.out.println("Data Types :"+t1.Variables.values());
				System.out.println("--------------------------");
			}


		} catch (Exception ex) {
			ex.printStackTrace() ;
		}
	}
}