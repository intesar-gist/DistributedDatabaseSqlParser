package sqlParser.fjdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/** Class to create a SQL statement
 *  Created by: X_Team_Member
 * */
public class FedStatement implements FedStatementInterface {
    Statement statement;

    public FedStatement (Statement statement) {
        this.statement = statement;
    }

    @Override
    public int executeUpdate(String sql) throws FedException {
        String SQL = sql.toUpperCase();
        try {
            // CREATE & DROP
            if (SQL.contains("TABLE")) {
                String table = SQL.substring(SQL.indexOf("TABLE")+5).trim();
                if (SQL.contains("CREATE")) {
                    table = table.substring(0, table.indexOf("(")).trim();
                    if (SQL.contains("HORIZONTAL")) {
                        // DISTR FKNG CREATE
                        String col = SQL.substring(SQL.indexOf("HORIZONTAL")+10).trim();
                        col = col.substring(1,col.length()-2).trim();
                        String which_column = col.substring(0,col.indexOf("(")).trim();
                        col = col.substring(col.indexOf("(")+1).trim();
                        Integer lower_bound, upper_bound = null;
                        if (col.contains(",")) {
                            lower_bound = Integer.parseInt(col.split(",")[0]);
                            upper_bound = Integer.parseInt(col.split(",")[1]);
                        }
                        else {
                            lower_bound = Integer.parseInt(col);
                        }
                        String distroyer = "INSERT INTO SPLIT_INFO VALUES ('" + table + "', '" + which_column + "', "
                                            + lower_bound + ", " + upper_bound + ")";
                        try {
                            sql = sql.substring(0, SQL.indexOf("HORIZONTAL")).trim();
                            // Create on URL1
                            FedConnection.startConnection(1);
                            Statement stmt = FedConnection.connection.createStatement();
                            stmt.executeUpdate(sql);

                            if (upper_bound != null) {
                                // Create on URL2
                                FedConnection.startConnection(2);
                                stmt = FedConnection.connection.createStatement();
                                stmt.executeUpdate(sql);
                            }
                            FedConnection.startConnection(3);
                            statement.executeUpdate(distroyer);
                        }
                        catch (SQLException es) {
                            es.printStackTrace();
                        }
                    }

                    // Create on URL3
                    return statement.executeUpdate(sql);
                }
                else {
                    // Hardcore DISTR FKNG DROP
                    int res = statement.executeUpdate("DELETE FROM SPLIT_INFO WHERE affected_table = '" + table + "'");
                    if (res != 0) {
                        // DROP from URL1
                        try {
                            FedConnection.startConnection(1);
                            Statement stmt = FedConnection.connection.createStatement();
                            stmt.executeUpdate(sql);
                        }
                        catch (Exception chill) {
                        }
                        // DROP from URL2
                        try {
                            FedConnection.startConnection(2);
                            Statement stmt = FedConnection.connection.createStatement();
                            stmt.executeUpdate(sql);
                        }
                        catch (Exception chill) {
                        }
                        FedConnection.startConnection(3);
                    }
                    // Drop from URL3
                    return statement.executeUpdate(sql);
                }
            }
            else if (SQL.contains("DELETE")) {
                String table = SQL.substring(SQL.indexOf("FROM")+4).trim();
                if (SQL.contains("WHERE")) {
                    table = table.substring(0, table.indexOf("WHERE")).trim();
                }
                // Hardcore DISTR FKNG DELETE
                int res = statement.executeUpdate("SELECT * FROM SPLIT_INFO WHERE affected_table = '" + table + "'");
                if (res != 0) {
                    // DELETE from URL1
                    try {
                        FedConnection.startConnection(1);
                        Statement stmt = FedConnection.connection.createStatement();
                        stmt.executeUpdate(sql);
                    }
                    catch (Exception chill) {
                        chill.getMessage();
                    }
                    // DELETE from URL2
                    try {
                        FedConnection.startConnection(2);
                        Statement stmt = FedConnection.connection.createStatement();
                        stmt.executeUpdate(sql);
                    }
                    catch (Exception chill) {
                        chill.getMessage();
                    }
                    FedConnection.startConnection(3);
                }
                // DELETE from URL3
                return statement.executeUpdate(sql);
            }
            else if (SQL.contains("INSERT")) {
                String table = SQL.substring(SQL.indexOf("INTO")+4, SQL.indexOf("VALUES")).trim();
                ResultSet rs = statement.executeQuery("SELECT * FROM SPLIT_INFO WHERE affected_table = '" + table + "'");
                boolean normalize = false;
                int lower_bound = 0;
                String which_column = "";
                if (rs.next()) {
                    // Hardcore DISTR FKNG PORKY INSERT
                    which_column = rs.getString(2);
                    lower_bound = rs.getInt(3);
                    int upper_bound = rs.getInt(4);
                    normalize = true;

                    // Insert into 1
                    FedConnection.startConnection(1);
                    Statement stmt = FedConnection.connection.createStatement();
                    stmt.executeUpdate(sql);
                    stmt.executeUpdate("DELETE from " + table + " where " + which_column + "<" + lower_bound);

                    if (upper_bound > 0) {
                        stmt.executeUpdate("DELETE from " + table + " where " + which_column + ">=" + upper_bound);
                        // Insert into 2
                        FedConnection.startConnection(2);
                        stmt = FedConnection.connection.createStatement();
                        stmt.executeUpdate(sql);
                        stmt.executeUpdate("DELETE from " + table + " where " + which_column + "<" + upper_bound);
                    }
                    FedConnection.startConnection(3);
                }

                // Insert into URL3
                statement.executeUpdate(sql);
                if (normalize) {
                    statement.executeUpdate("DELETE from " + table + " where " + which_column + ">=" + lower_bound);
                }

                return 1;
            }
        } catch (SQLException e) {
            throw new FedException(e.getCause());
        }

        return 0;
    }

    @Override
    public FedResultSet executeQuery(String sql) throws FedException {
        String SQL = sql.toUpperCase();
        try {
            String table = SQL.substring(SQL.indexOf("FROM")+4).trim();
            if (table.contains("WHERE")) {
                table = table.substring(0,table.indexOf("WHERE")).trim();
            }
            ResultSet rs = statement.executeQuery("SELECT upper_bound FROM SPLIT_INFO WHERE affected_table = '" + table + "'");
            if (rs.next()) {
                // DISTR FKNG SELECT
                Statement stmt;
                ResultSet res1, res2 = null;
                boolean ist_sucunt = false;
                String attrs = SQL.substring(0, SQL.indexOf("FROM"));

                if (attrs.contains("COUNT") || attrs.contains("SUM"))
                    ist_sucunt = true;

                if (rs.getInt(1) > 0) {
                    // Select from URL2
                    FedConnection.startConnection(2);
                    stmt = FedConnection.connection.createStatement();
                    res2 = stmt.executeQuery(sql);
                }
                // Select from URL1
                FedConnection.startConnection(1);
                stmt = FedConnection.connection.createStatement();
                res1 = stmt.executeQuery(sql);

                FedConnection.startConnection(3);
                if (res2 != null)
                    return new FedResultSet(ist_sucunt, statement.executeQuery(sql), res1, res2);
                else
                    return new FedResultSet(ist_sucunt, statement.executeQuery(sql), res1);
            }

            // Table ist alleine
            return new FedResultSet(false, statement.executeQuery(sql));
        } catch (SQLException e) {
            throw new FedException(e.getCause());
        }

    }

    @Override
    public FedConnection getConnection() throws FedException {
        try {
            return (FedConnection) statement.getConnection();
        } catch (SQLException e) {
            throw new FedException(e.getCause());
        }
    }

    @Override
    public void close() throws FedException {
        try {
            statement.close();
        } catch (SQLException e) {
            throw new FedException(e.getCause());
        }
    }
}
