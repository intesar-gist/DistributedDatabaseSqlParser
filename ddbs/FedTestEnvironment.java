package ddbs;

import ddbs.fjdbc.FedConnection;
import ddbs.fjdbc.FedException;
import ddbs.fjdbc.FedResultSet;
import ddbs.fjdbc.FedStatement;
import ddbs.sqlparser.SqlParser;

import java.io.FileReader;
import java.util.Scanner;

/**
 * Created by Intesar.
 */
public class FedTestEnvironment {

    private FedConnection fedConnection;

    public FedTestEnvironment(FedConnection fedConnection) {
        this.fedConnection = fedConnection;

        try {
            this.fedConnection.setAutoCommit(false);
        } catch (FedException fedException) {
            System.err.println(fedException.getMessage());
            System.out.flush();
            System.err.flush();
        }
    }

    public void run(String filename) {
        run(filename, false);
    }

    public void run(String filename, boolean debug) {

        try {
            SqlParser parser = new SqlParser(new FileReader(filename));
            if(parser.initParser()) {
                System.out.println("**************************************************************************");
                System.out.println("Parse Status: " + filename + " is OKAY ");
            }
        } catch (Exception e) {
            System.out.println("**************************************************************************");
            System.out.println("Parse Exception in (" + filename+ "): " + e.getMessage());
            System.out.println("**************************************************************************");
            return;
        }

        System.out.println("**************************************************************************");
        System.out.println("Executing script file '" + filename + "'...");
        long start, end, delta, op = 0;

        start = System.currentTimeMillis();


        Scanner scanner = new Scanner(getClass().getClassLoader().getResourceAsStream(filename)).useDelimiter(";");
        while (scanner.hasNext()) {

            String statement = scanner.next().trim();

            while (statement.startsWith("/*") || statement.startsWith("--")) {
                if (statement.startsWith("/*")) {
                    String comment = statement.substring(0, statement.indexOf("*/") + 2);
                    if (debug) {
                        System.out.println("--> " + comment + " <--");
                    }
                    statement = statement.substring(statement.indexOf("*/") + 2).trim();
                } else {
                    String comment = statement.substring(0, statement.length());
                    if (debug) {
                        System.out.println("--> " + comment + " <--");
                    }
                    statement = statement.substring(statement.length()).trim();
                }
            }

            if (!"".equals(statement)) {
                if (!"SET ECHO ON".equals(statement.toUpperCase()) && !statement.toUpperCase().startsWith("ALTER SESSION")) {
                    if (debug) {
                        System.out.println("Executing \"" + statement + "\"...\n");
                        System.out.flush();
                    }
                    if (statement.toUpperCase().equals("COMMIT")) {
                        try {
                            fedConnection.commit();
                            if (debug) {
                                System.out.println("Transaction commit");
                            }
                        } catch (FedException fedException) {
                            System.out.println(fedException.getMessage());
                            System.out.flush();
                        }
                    } else {
                        if (statement.toUpperCase().equals("ROLLBACK")) {
                            try {
                                fedConnection.rollback();
                                if (debug) {
                                    System.out.println("Transaction rollback");
                                }
                            } catch (FedException fedException) {
                                System.out.println(fedException.getMessage());
                                System.out.flush();
                            }
                        } else {
                            if (statement.toUpperCase().startsWith("SELECT")) {
                                // SELECT
                                try {
                                    FedStatement fedStatement = fedConnection.getStatement();
                                    FedResultSet fedResultSet = fedStatement.executeQuery(statement);

                                    op++;

                                    if (debug) {
                                        for (int i = 1; i <= fedResultSet.getColumnCount(); i++) {
                                            System.out.printf("%-15s", fedResultSet.getColumnName(i));
                                        }
                                        System.out.println();

                                        for (int i = 1; i <= fedResultSet.getColumnCount(); i++) {
                                            System.out.print("-------------- ");
                                        }
                                        System.out.println();
                                        while (fedResultSet.next()) {
                                            for (int i = 1; i <= fedResultSet.getColumnCount(); i++) {
                                                System.out.printf("%-15s", fedResultSet.getString(i));
                                            }
                                            System.out.println();
                                        }
                                        System.out.println();
                                    }
                                    fedStatement.close();
                                } catch (FedException fedException) {
                                    System.out.println(fedException.getMessage());
                                    System.out.flush();
                                }
                            } else {
                                // UPDATE, INSERT, DELETE
                                try {
                                    FedStatement fedStatement = fedConnection.getStatement();
                                    int count = fedStatement.executeUpdate(statement);

                                    op++;

                                    if (statement.toUpperCase().startsWith("UPDATE")) {
                                        if (debug) {
                                            System.out.println(count + " rows updated\n");
                                        }
                                    } else {
                                        if (statement.toUpperCase().startsWith("INSERT")) {
                                            if (debug) {
                                                System.out.println(count + " rows inserted\n");
                                            }
                                        } else {
                                            if (statement.toUpperCase().startsWith("DELETE")) {
                                                if (debug) {
                                                    System.out.println(count + " rows deleted\n");
                                                }
                                            } else {
                                                if (debug) {
                                                    System.out.println(count + " OK");
                                                }
                                            }
                                        }
                                    }

                                    fedStatement.close();
                                } catch (FedException fedException) {
                                    System.out.println(fedException.getMessage());
                                    System.out.flush();
                                }
                            }
                        }
                    }
                }
            }
        }
        end = System.currentTimeMillis();

        delta = end - start;
        System.out.println("File '" + filename + "', " + op + " operations, " + delta + " milliseconds");
        System.out.println("**************************************************************************\n");
    }
}

