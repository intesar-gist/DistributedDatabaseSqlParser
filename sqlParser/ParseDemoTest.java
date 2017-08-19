package sqlParser;

import sqlParser.utilities.TableStruct;

import java.io.FileReader;
import java.util.ArrayList;

/*for testing the parser class*/
public class ParseDemoTest { 
	  public static void main(String[] args) { 
	    try{
			SqlParser parser = new SqlParser(new FileReader("sqlParser/test_queries.sql"));

			ArrayList<TableStruct> tableList = parser.initParser();

			for(TableStruct t1 : tableList)
			{  System.out.println("--------------------------");
				System.out.println("Query Type :"+t1.getQueryType());
				System.out.println("Table Name :"+t1.getTableName());
				System.out.println("Field names :"+t1.getAttributes().keySet());
				System.out.println("Data Types :"+t1.getAttributes().values());
				System.out.println("--------------------------");
			}


		} catch (Exception ex) {
			ex.printStackTrace() ;
		}
	}
}