package ddbs.fjdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/** Class to create a SQL statement
 *  Created by: Intesar Haider
 * */
public class FedStatement implements FedStatementInterface, FJDBCConstants {
    Statement statement;

    public FedStatement (Statement statement) {
        this.statement = statement;
    }

    @Override
    public int executeUpdate(String sql) throws FedException {
        String SQL = sql.toUpperCase();
        try {
            /***************************
             * CREATE AND DROP QUERIES
             * *************************
             */
            if (SQL.contains("TABLE")) {
                String table = SQL.substring(SQL.indexOf("TABLE")+5).trim();
                if (SQL.contains("CREATE")) {
                    table = table.substring(0, table.indexOf("(")).trim();
                    System.out.println("Creating table : " + table);
                    if (SQL.contains("HORIZONTAL")) {
                        /***********************
                         * DISTRIBUTIVE QUERIES
                         * *********************
                         */
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
                        String catalogQuery = "INSERT INTO ddbs_catalogue VALUES ('" + table + "', '" + columnName + "', "
                                            + lowerBound + ", " + upperBound + ")";
                        try {
                            sql = sql.substring(0, SQL.indexOf("HORIZONTAL")).trim();
                            // change connection to Pinatubo
                            FedConnection.startConnection(PINATUBO_DB1);
                            Statement stmt = FedConnection.connection.createStatement();
                            stmt.executeUpdate(sql);

                            if (upperBound != null) {
                                // change connection to Krakatau if upper bound is also given, means data should be
                                // dispersed across all the three databases
                                FedConnection.startConnection(KRAKATAU_DB2);
                                stmt = FedConnection.connection.createStatement();
                                stmt.executeUpdate(sql);
                            }

                            FedConnection.startConnection(MTSTHELENS_DB3);

                            //update the catalogue in MTSTHELENS_DB3
                            statement.executeUpdate(catalogQuery);
                        }
                        catch (SQLException se) {
                            se.printStackTrace();
                        }
                    }

                    // run the create query to master DB i.e. MTSTHELENS_DB3
                    return statement.executeUpdate(sql);
                }  else {

                    /******************************
                     *  DROP QUERIES (distributive)
                     * ****************************
                     */
                    //split and get only table name in-case query has CASCADE as well. Eg: drop table BUCHUNG cascade constraints;
                    String[] exactTableName = table.split("\\s+");
                    int res = statement.executeUpdate("DELETE FROM ddbs_catalogue WHERE table_name = '" + exactTableName[0] + "'");
                    if (res != 0) {
                        // DROP from PINATUBO_DB1
                        try {
                            FedConnection.startConnection(PINATUBO_DB1);
                            Statement stmt = FedConnection.connection.createStatement();
                            stmt.executeUpdate(sql);
                        }
                        catch (Exception ex) {
                        }
                        // DROP from KRAKATAU_DB2
                        try {
                            FedConnection.startConnection(KRAKATAU_DB2);
                            Statement stmt = FedConnection.connection.createStatement();
                            stmt.executeUpdate(sql);
                        }
                        catch (Exception ex) {
                        }
                        FedConnection.startConnection(MTSTHELENS_DB3);
                    }

                    try {
                        // Drop from MTSTHELENS_DB3 i.e. the master DDBS Servers
                        return statement.executeUpdate(sql);
                    } catch (SQLException e) {
                        if(e.getErrorCode() == NO_TABLE_EXISTS) {
                            System.out.println("Cannot drop table because it doesn't exist in database [" + sql + "].");
                        }
                        return 0;
                    }

                }
            }

            /******************
             *  DELETE QUERIES
             * ****************
             */
            else if (SQL.contains("DELETE")) {
                String table = SQL.substring(SQL.indexOf("FROM")+4).trim();
                if (SQL.contains("WHERE")) {
                    table = table.substring(0, table.indexOf("WHERE")).trim();
                }
                int result = statement.executeUpdate(catalogueSelectQueryBuilder(table));
                if (result != 0) {
                    // DELETE from PINATUBO_DB1
                    try {
                        FedConnection.startConnection(PINATUBO_DB1);
                        Statement stmt = FedConnection.connection.createStatement();
                        stmt.executeUpdate(sql);
                    }
                    catch (Exception ex) {
                        ex.getMessage();
                    }
                    // DELETE from KRAKATAU_DB2
                    try {
                        FedConnection.startConnection(KRAKATAU_DB2);
                        Statement stmt = FedConnection.connection.createStatement();
                        stmt.executeUpdate(sql);
                    }
                    catch (Exception ex) {
                        ex.getMessage();
                    }
                    FedConnection.startConnection(MTSTHELENS_DB3);
                }
                // DELETE from MTSTHELENS_DB3 i.e. MASTER DDB Server
                return statement.executeUpdate(sql);
            }

            /******************
             *  INSERT QUERIES
             * ****************
             */
            else if (SQL.contains("INSERT")) {
                String table = SQL.substring(SQL.indexOf("INTO")+4, SQL.indexOf("VALUES")).trim();
                ResultSet resultSet = statement.executeQuery(catalogueSelectQueryBuilder(table));
                boolean fixTableData = false;
                int lowerBound = 0;
                String columnName = "";
                if (resultSet.next()) {
                    //insert should be done in distributed way
                    columnName = resultSet.getString(2); //get column name
                    lowerBound = resultSet.getInt(3);
                    int upperBound = resultSet.getInt(4);
                    fixTableData = true;

                    // Insert into PINATUBO_DB1
                    FedConnection.startConnection(PINATUBO_DB1);
                    Statement stmt = FedConnection.connection.createStatement();
                    stmt.executeUpdate(sql);
                    stmt.executeUpdate(catalogueDelQueryBuilder(table, columnName, "<", lowerBound));

                    //if upper bound is also given, means third DB server should be utilized as well
                    if (upperBound > 0) {
                        stmt.executeUpdate(catalogueDelQueryBuilder(table, columnName, ">=", upperBound));
                        // Insert into KRAKATAU_DB2
                        FedConnection.startConnection(KRAKATAU_DB2);
                        stmt = FedConnection.connection.createStatement();
                        stmt.executeUpdate(sql);
                        stmt.executeUpdate(catalogueDelQueryBuilder(table, columnName, "<", upperBound));
                    }
                    FedConnection.startConnection(MTSTHELENS_DB3);
                }

                // Insert into MTSTHELENS_DB3 i.e. MASTER DDB Server
                statement.executeUpdate(sql);

                if (fixTableData) {
                    statement.executeUpdate(catalogueDelQueryBuilder(table, columnName, ">=", lowerBound));
                }

                return 1;
            } else if (SQL.contains("COMMIT") || SQL.contains("ROLLBACK")) {
                statement.executeUpdate(sql);

                FedConnection.startConnection(PINATUBO_DB1);
                Statement stmt = FedConnection.connection.createStatement();
                stmt.executeUpdate(sql);

                FedConnection.startConnection(KRAKATAU_DB2);
                stmt = FedConnection.connection.createStatement();
                stmt.executeUpdate(sql);

                FedConnection.startConnection(MTSTHELENS_DB3);
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
            ResultSet catalogeResult = statement.executeQuery("SELECT upper_bound FROM ddbs_catalogue WHERE table_name = '" + table + "'");
            if (catalogeResult.next()) {

                Statement stmt;
                ResultSet resultSet1, resultSet2 = null;
                boolean isAggregateOp = false;
                String attrs = SQL.substring(0, SQL.indexOf("FROM"));

                if (attrs.contains("COUNT") || attrs.contains("SUM")) {
                    isAggregateOp = true;
                }

                if (catalogeResult.getInt(1) > 0) {
                    // Select from KRAKATAU_DB2
                    FedConnection.startConnection(KRAKATAU_DB2);
                    stmt = FedConnection.connection.createStatement();
                    resultSet2 = stmt.executeQuery(sql);
                }
                // Select from PINATUBO_DB1
                FedConnection.startConnection(PINATUBO_DB1);
                stmt = FedConnection.connection.createStatement();
                resultSet1 = stmt.executeQuery(sql);

                FedConnection.startConnection(MTSTHELENS_DB3);
                if (resultSet2 != null)
                    return new FedResultSet(isAggregateOp, statement.executeQuery(sql), resultSet1, resultSet2);
                else
                    return new FedResultSet(isAggregateOp, statement.executeQuery(sql), resultSet1);
            }
            // select from MTSTHELEN, because of single table
            return new FedResultSet(false, statement.executeQuery(sql));
        } catch (SQLException e) {
            throw new FedException(e);
        }

    }

    private String catalogueDelQueryBuilder(String tableName, String columnName, String equalitySign, int lowerBound) {
        return "DELETE from " + tableName + " where " + columnName + equalitySign + lowerBound;
    }

    private String catalogueSelectQueryBuilder(String tableName) {
        return "SELECT * FROM ddbs_catalogue WHERE table_name = '" + tableName + "'";
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
