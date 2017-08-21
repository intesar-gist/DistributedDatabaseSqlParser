package sqlParser;

import sqlParser.utilities.DDLQuery;
import sqlParser.utilities.QueryType;

import java.io.FileReader;
import java.util.ArrayList;

/*for testing the parser class*/
public class ParseDemoTest { 
	  public static void main(String[] args) { 
	    try{
			SqlParser parser = new SqlParser(new FileReader("sqlParser/test_queries.sql"));

			ArrayList<QueryType> tableList = parser.initParser();

			for(QueryType query : tableList) {
				if(query instanceof DDLQuery) {
					DDLQuery ddlQuery = (DDLQuery)query;
					System.out.println("--------------------------");
					System.out.println("Query Type :"+ddlQuery.getQueryType());
					System.out.println("Table Name :"+ddlQuery.getTableName());
					System.out.println("Field names :"+ddlQuery.getAttributes().keySet());
					System.out.println("Data Types :"+ddlQuery.getAttributes().values());
					System.out.println("--------------------------");
				}
			}


		} catch (Exception ex) {
			ex.printStackTrace() ;
		}
	}
}