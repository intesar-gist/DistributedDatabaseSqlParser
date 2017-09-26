package ddbs.fjdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/** Class to create a SQL statement
 *  Created by: Intesar Haider
 * */
public class FedStatement implements FedStatementInterface, FJDBCConstants {
    public static final int NO_TABLE_EXISTS = 942;
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
                        // DISTRIBUTIVE CREATE
                        String col = SQL.substring(SQL.indexOf("HORIZONTAL")+10).trim();
                        col = col.substring(1,col.length()-2).trim();
                        String columnName = col.substring(0,col.indexOf("(")).trim();
                        col = col.substring(col.indexOf("(")+1).trim();
                        Integer lowerBound, upperBound = null;
                        if (col.contains(",")) {
                            lowerBound = Integer.parseInt(col.split(",")[0]);
                            upperBound = Integer.parseInt(col.split(",")[1]);
                        }
                        else {
                            lowerBound = Integer.parseInt(col);
                        }
                        String catalogQuery = "INSERT INTO SPLIT_INFO VALUES ('" + table + "', '" + columnName + "', "
                                            + lowerBound + ", " + upperBound + ")";
                        try {
                            sql = sql.substring(0, SQL.indexOf("HORIZONTAL")).trim();
                            // Create on URL1
                            FedConnection.startConnection(PINATUBO);
                            Statement stmt = FedConnection.connection.createStatement();
                            stmt.executeUpdate(sql);

                            if (upperBound != null) {
                                // Create on URL2
                                FedConnection.startConnection(KRAKATAU);
                                stmt = FedConnection.connection.createStatement();
                                stmt.executeUpdate(sql);
                            }
                            FedConnection.startConnection(MTSTHELENS);
                            statement.executeUpdate(catalogQuery);
                        }
                        catch (SQLException se) {
                            se.printStackTrace();
                        }
                    }

                    // Create on URL3
                    return statement.executeUpdate(sql);
                }
                else {
                    // DISTRIBUTIVE DROP
                    int res = statement.executeUpdate("DELETE FROM SPLIT_INFO WHERE affected_table = '" + table + "'");
                    if (res != 0) {
                        // DROP from URL1
                        try {
                            FedConnection.startConnection(PINATUBO);
                            Statement stmt = FedConnection.connection.createStatement();
                            stmt.executeUpdate(sql);
                        }
                        catch (Exception ex) {
                        }
                        // DROP from URL2
                        try {
                            FedConnection.startConnection(KRAKATAU);
                            Statement stmt = FedConnection.connection.createStatement();
                            stmt.executeUpdate(sql);
                        }
                        catch (Exception ex) {
                        }
                        FedConnection.startConnection(MTSTHELENS);
                    }

                    try {
                        // Drop from URL3
                        return statement.executeUpdate(sql);
                    } catch (SQLException e) {
                        if(e.getErrorCode() == NO_TABLE_EXISTS) {
                            System.out.println("Cannot drop table because it doesn't exist in database [" + sql + "].");
                        }
                        return 0;
                    }

                }
            }

            // DELETE
            else if (SQL.contains("DELETE")) {
                String table = SQL.substring(SQL.indexOf("FROM")+4).trim();
                if (SQL.contains("WHERE")) {
                    table = table.substring(0, table.indexOf("WHERE")).trim();
                }
                int res = statement.executeUpdate("SELECT * FROM SPLIT_INFO WHERE affected_table = '" + table + "'");
                if (res != 0) {
                    // DELETE from URL1
                    try {
                        FedConnection.startConnection(PINATUBO);
                        Statement stmt = FedConnection.connection.createStatement();
                        stmt.executeUpdate(sql);
                    }
                    catch (Exception ex) {
                        ex.getMessage();
                    }
                    // DELETE from URL2
                    try {
                        FedConnection.startConnection(KRAKATAU);
                        Statement stmt = FedConnection.connection.createStatement();
                        stmt.executeUpdate(sql);
                    }
                    catch (Exception ex) {
                        ex.getMessage();
                    }
                    FedConnection.startConnection(MTSTHELENS);
                }
                // DELETE from URL3
                return statement.executeUpdate(sql);
            }

            // INSERT
            else if (SQL.contains("INSERT")) {
                String table = SQL.substring(SQL.indexOf("INTO")+4, SQL.indexOf("VALUES")).trim();
                ResultSet rs = statement.executeQuery("SELECT * FROM SPLIT_INFO WHERE affected_table = '" + table + "'");
                boolean fixTableData = false;
                int lowerBound = 0;
                String columnName = "";
                if (rs.next()) {
                    //insert should be done in distributed way
                    columnName = rs.getString(2); //get column name
                    lowerBound = rs.getInt(3);
                    int upperBound = rs.getInt(4);
                    fixTableData = true;

                    // Insert into PINATUBO
                    FedConnection.startConnection(PINATUBO);
                    Statement stmt = FedConnection.connection.createStatement();
                    stmt.executeUpdate(sql);
                    stmt.executeUpdate("DELETE from " + table + " where " + columnName + "<" + lowerBound);

                    //if upper bound is also given, means third DB server should be utilized as well
                    if (upperBound > 0) {
                        stmt.executeUpdate("DELETE from " + table + " where " + columnName + ">=" + upperBound);
                        // Insert into KRAKATAU
                        FedConnection.startConnection(KRAKATAU);
                        stmt = FedConnection.connection.createStatement();
                        stmt.executeUpdate(sql);
                        stmt.executeUpdate("DELETE from " + table + " where " + columnName + "<" + upperBound);
                    }
                    FedConnection.startConnection(MTSTHELENS);
                }

                // Insert into URL3
                statement.executeUpdate(sql);
                if (fixTableData) {
                    statement.executeUpdate("DELETE from " + table + " where " + columnName + ">=" + lowerBound);
                }

                return 1;
            } else if (SQL.contains("COMMIT") || SQL.contains("ROLLBACK")) {
                statement.executeUpdate(sql);

                FedConnection.startConnection(PINATUBO);
                Statement stmt = FedConnection.connection.createStatement();
                stmt.executeUpdate(sql);

                FedConnection.startConnection(KRAKATAU);
                stmt = FedConnection.connection.createStatement();
                stmt.executeUpdate(sql);

                FedConnection.startConnection(MTSTHELENS);
            }
        } catch (SQLException e) {
//            e.printStackTrace();
            throw new FedException(e);
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
                // DISTR SELECT
                Statement stmt;
                ResultSet res1, res2 = null;
                if (rs.getInt(1) > 0) {
                    // Select from URL2
                    FedConnection.startConnection(KRAKATAU);
                    stmt = FedConnection.connection.createStatement();
                    res2 = stmt.executeQuery(sql);
                }
                // Select from URL1
                FedConnection.startConnection(PINATUBO);
                stmt = FedConnection.connection.createStatement();
                res1 = stmt.executeQuery(sql);

                FedConnection.startConnection(MTSTHELENS);
                if (res2 != null)
                    return new FedResultSet(statement.executeQuery(sql), res1, res2);
                else
                    return new FedResultSet(statement.executeQuery(sql), res1);
            }
            // single table
            return new FedResultSet(statement.executeQuery(sql));
        } catch (SQLException e) {
            throw new FedException(e);
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
