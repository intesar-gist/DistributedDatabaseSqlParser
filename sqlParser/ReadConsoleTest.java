package sqlParser;

import sqlParser.fjdbc.FedConnection;
import sqlParser.fjdbc.FedResultSet;
import sqlParser.fjdbc.FedStatement;
import sqlParser.utilities.FedLogger;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * Write a simple program that reads a string of characters (containing the SQL statement to be tested)
 * from the console, invokes your FJDBC-implementation and prints the results on the console.
 * Created by: X_Team_Member
 */
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
                FedLogger.l("Query has successfully satisfied the grammar");
                FedLogger.l("Start FDBS");

                // Connect to database
                FedConnection connection = new FedConnection();
                FedLogger.l("Connect to database with user " + connection.USPASS);
                connection.startConnection(3);
                connection.setAutoCommit(false);
                FedStatement statement = new FedStatement(connection.createStatement());

                // Execute query from console
                System.out.println("Received FJDBC: " + command);
                FedResultSet resultSet;
                FedLogger.l("Received FJDBC: " + command);
                if (command.toUpperCase().contains("SELECT")) {
                    resultSet = statement.executeQuery(command.replace(";", ""));
                    if (resultSet.next())
                        System.out.println("Result: " + resultSet.getInt(1));
                    // --
                } else {
                    statement.executeUpdate(command);
                    System.out.println("Command executed");
                }

                connection.commit();
                //connection.setAutoCommit(true);
                connection.close();
            }
        } catch (Exception ex) {
            try {
                FedLogger.l("Exception: " + ex.getMessage());
                ex.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}