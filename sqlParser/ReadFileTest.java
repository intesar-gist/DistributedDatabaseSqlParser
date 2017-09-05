package sqlParser;

import sqlParser.fjdbc.FedConnection;
import sqlParser.fjdbc.FedResultSet;
import sqlParser.fjdbc.FedStatement;
import sqlParser.utilities.FedLogger;

import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Write a simple program similar to the previous assignment that creates and loads a large global distributed
 * table with several thousand tuples.
 * Created by: X_Team_Member
 */
public class ReadFileTest {

    public static void main(String[] args) {
        try {
            FedLogger.l("Read queries file");

            // Arm SqlParser and provide the queries from file to see if queries are
            // syntactically correct
            SqlParser parser = new SqlParser(new FileReader("./sqlParser/test_queries.sql"));

            Boolean parseSuccess = parser.initParser();

            if (parseSuccess) {
                FedLogger.l("All queries have successfully satisfied the grammar");
                FedLogger.l("Start FDBS");

                // Read queries
                List<String> lines = Files.readAllLines(Paths.get("./sqlParser/test_queries.sql"), Charset.defaultCharset());

                // store all lines in all string var
                String all = "";
                for (String line : lines) all += line;

                // split all string at ; to make array of SQL query
                String SQLs[] = all.split(";");

                // Connect to database
                FedConnection connection = new FedConnection();
                FedLogger.l("Connect to database oralv8a with user " + connection.USPASS);
                connection.startConnection(3);
                FedStatement statement = new FedStatement(connection.createStatement());

                // Execute queries from file
                FedResultSet res;
                for (int i = 0; i < SQLs.length; i++) {
                    // ignore dashed lines
                    if (SQLs[i].contains("--") || SQLs[i].toUpperCase().contains("UPDATE")) continue;

                    FedLogger.l("Received FJDBC: " + SQLs[i]);

                    // if its a select statement, execute query
                    // otherwise execute update
                    if (SQLs[i].toUpperCase().contains("SELECT")) {
                        res = statement.executeQuery(SQLs[i]);
                        //res.next();
                    } else {
                        statement.executeUpdate(SQLs[i]);
                    }
                }

                // Execute federated queries
                FedResultSet resultSet;

                String distQL = "SELECT * FROM simple_d";
                FedLogger.l("Received FJDBC: " + distQL);
                resultSet = statement.executeQuery(distQL);
                System.out.println("\nResults------------------------");
                while (resultSet.next()) {
                    System.out.println(resultSet.getInt(1) + "\t" + resultSet.getString(2));
                }

                distQL = "SELECT * FROM simple_d2";
                FedLogger.l("Received FJDBC: " + distQL);
                resultSet = statement.executeQuery(distQL);
                System.out.println("\nResults------------------------");
                while (resultSet.next()) {
                    System.out.println(resultSet.getInt(1) + "\t" + resultSet.getString(2));
                }

                distQL = "SELECT * FROM simple_d2 WHERE (simple_d2.col_a > 3)";
                FedLogger.l("Received FJDBC: " + distQL);
                resultSet = statement.executeQuery(distQL);
                System.out.println("\nResults------------------------");
                while (resultSet.next()) {
                    System.out.println(resultSet.getInt(1) + "\t" + resultSet.getString(2));
                }

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