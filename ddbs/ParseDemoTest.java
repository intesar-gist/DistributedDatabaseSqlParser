package ddbs;

import ddbs.sqlparser.SqlParser;

import java.io.FileReader;

/*for testing the parser class*/
public class ParseDemoTest { 
	  public static void main(String[] args) { 
	    try{
			SqlParser parser = new SqlParser(new FileReader("ddbs/test_queries.sql"));

			Boolean parseSuccess = parser.initParser();

			if(parseSuccess) {
				System.out.println("Status: All queries have successfully satisfied the grammar!\n");
			}


		} catch (Exception ex) {
			System.out.println("Status: Query failed to satisfy the grammar!\n");
			ex.printStackTrace() ;
		}
	}
}