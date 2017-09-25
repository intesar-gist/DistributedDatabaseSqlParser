package ddbs;

import ddbs.fjdbc.FedConnection;
import ddbs.fjdbc.FedResultSet;
import ddbs.fjdbc.FedStatement;
import ddbs.sqlparser.SqlParser;
import ddbs.utilities.Logger;

import java.io.*;
import java.nio.charset.StandardCharsets;

/** Write a simple program that reads a string of characters (containing the SQL statement to be tested)
 *  from the console, invokes your FJDBC-implementation and prints the results on the console.
 *  Created by: Touhidur Rahman
 * */
public class ReadConsoleTest {
    public static void main(String[] args) {
        try {
            InputStream is = null;
            BufferedReader br = null;
            is = System.in;
            br = new BufferedReader(new InputStreamReader(is));

            System.out.println("Enter SQL command: ");
            String command = br.readLine();
            InputStream stream = new ByteArrayInputStream(command.getBytes(StandardCharsets.UTF_8));
            SqlParser parser = new SqlParser(stream);

            Boolean parseOK = parser.initParser();

            if (parseOK) {
                Logger.write("Query has successfully satisfied the grammar");
                Logger.write("Start FDBS");

                // Connect to database
                FedConnection conn = new FedConnection();
                Logger.write("Connect to database with user " + conn.USERNAME);
                conn.startConnection(3);
                conn.setAutoCommit(false);
                FedStatement statement = new FedStatement(conn.createStatement());

                // Execute query from console
                System.out.println("Received FJDBC: " + command);
                FedResultSet res;
                Logger.write("Received FJDBC: " + command);
                if (command.toUpperCase().contains("SELECT")) {
                    res = statement.executeQuery(command.replace(";",""));
                    if (res.next())
                        System.out.println("Result: " + res.getInt(1));
                }
                else {
                    statement.executeUpdate(command);
                    System.out.println("Command executed");
                }

                conn.commit();
                conn.close();
            }
        } catch (Exception ex) {
            try {
                Logger.write("Exception: " + ex.getMessage());
                ex.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}