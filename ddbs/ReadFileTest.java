package ddbs;

import ddbs.fjdbc.FedConnection;
import ddbs.fjdbc.FedResultSet;
import ddbs.fjdbc.FedStatement;
import ddbs.sqlparser.SqlParser;
import ddbs.utilities.Logger;

import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/** Write a simple program similar to the previous assignment that creates and loads a large global distributed
 *  table with several thousand tuples.
 *  Created by: Touhidur Rahman
 * */
public class ReadFileTest {
    public static void main(String[] args) {
        String FILE = "./ddbs/test_queries.sql";
        try {
            Logger.write("Read queries file");
            SqlParser parser = new SqlParser(new FileReader(FILE));

            Boolean parseSuccess = parser.initParser();

            if (parseSuccess) {
                Logger.write("All queries have successfully satisfied the grammar");
                Logger.write("Start FDBS");

                // Read queries
                List<String> lines = Files.readAllLines(Paths.get(FILE), Charset.defaultCharset());
                String all = "";
                for (String line : lines) all += line;
                String SQLs[] = all.split(";");

                // Connect to database
                FedConnection connection = new FedConnection();
                Logger.write("Connect to database oralv8a with user " + connection.USERNAME);
                connection.startConnection(3);
                FedStatement statement = new FedStatement(connection.createStatement());

                // Execute queries from file
                FedResultSet res;
                for (int i=0; i<SQLs.length; i++) {
                    if (SQLs[i].contains("--") || SQLs[i].toUpperCase().contains("UPDATE")) continue;
                    Logger.write("Received FJDBC: " + SQLs[i]);
                    if (SQLs[i].toUpperCase().contains("SELECT")) {
                        res = statement.executeQuery(SQLs[i]);
                        while (res.next()) {
                            System.out.println(res.getInt(1) + "\t" + res.getString(2));
                        }
                    }
                    else {
                        statement.executeUpdate(SQLs[i]);
                    }
                }

                // Execute distributed queries
                FedResultSet fresa;


                String distQL = "SELECT COUNT(*) FROM simple_d2 WHERE (simple_d2.col_a > 3)";
                Logger.write("Received FJDBC: " + distQL);
                fresa = statement.executeQuery(distQL);
                System.out.println("\nResults------------------------");
                while (fresa.next()) {
                    System.out.println(fresa.getInt(1));
                }

                distQL = "SELECT SUM(col_a) FROM simple_d2 WHERE (simple_d2.col_a > 3)";
                Logger.write("Received FJDBC: " + distQL);
                fresa = statement.executeQuery(distQL);
                System.out.println("\nResults------------------------");
                while (fresa.next()) {
                    System.out.println(fresa.getInt(1));
                }

                distQL = "SELECT * FROM simple_d";
                Logger.write("Received FJDBC: " + distQL);
                fresa = statement.executeQuery(distQL);
                System.out.println("\nResults------------------------");
                while (fresa.next()) {
                    System.out.println(fresa.getInt(1) + "\t" + fresa.getString(2));
                }

                distQL = "SELECT * FROM simple_d2";
                Logger.write("Received FJDBC: " + distQL);
                fresa = statement.executeQuery(distQL);
                System.out.println("\nResults------------------------");
                while (fresa.next()) {
                    System.out.println(fresa.getInt(1) + "\t" + fresa.getString(2));
                }

                distQL = "SELECT * FROM simple_d2 WHERE (simple_d2.col_a > 3)";
                Logger.write("Received FJDBC: " + distQL);
                fresa = statement.executeQuery(distQL);
                System.out.println("\nResults------------------------");
                while (fresa.next()) {
                    System.out.println(fresa.getInt(1) + "\t" + fresa.getString(2));
                }

                connection.close();
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