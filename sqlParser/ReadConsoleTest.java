package sqlParser;

import sqlParser.fjdbc.FedConnection;
import sqlParser.fjdbc.FedResultSet;
import sqlParser.fjdbc.FedStatement;
import sqlParser.utilities.FedLog;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/** Write a simple program that reads a string of characters (containing the SQL statement to be tested)
 *  from the console, invokes your FJDBC-implementation and prints the results on the console.
 *  Created by: X_Team_Member
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

            Boolean parseSuccess = parser.initParser();

            if (parseSuccess) {
                FedLog.logLine("Query has successfully satisfied the grammar");
                FedLog.logLine("Start FDBS");

                // Connect to database
                FedConnection connection = new FedConnection();
                FedLog.logLine("Connect to database with user " + connection.USPASS);
                connection.startConnection(3);
                connection.setAutoCommit(false);
                FedStatement statement = new FedStatement(connection.createStatement());

                // Execute query from console
                System.out.println("Received FJDBC: " + command);
                FedResultSet res;
                FedLog.logLine("Received FJDBC: " + command);
                if (command.toUpperCase().contains("SELECT")) {
                    // Simple test: SELECT COUNT(*) FROM SIMPLE;
                    res = statement.executeQuery(command.replace(";",""));
                    if (res.next())
                        System.out.println("Result: " + res.getInt(1));
                    // --
                }
                else {
                    statement.executeUpdate(command);
                    System.out.println("Command executed");
                }

                connection.commit();
                //connection.setAutoCommit(true);
                connection.close();
            }
        } catch (Exception ex) {
            try {
                FedLog.logLine("Exception: " + ex.getMessage());
                ex.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}