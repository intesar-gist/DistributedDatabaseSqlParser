package sqlParser.fjdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Set the connection and do commits and rollbacks
 *  Created by: Touhidur Rahman
 */
public class FedConnection implements FedConnectionInterface {

    static String URL1 = "jdbc:oracle:thin:@pinatubo.informatik.hs-fulda.de:1521:oralv8a";
    static String URL2 = "jdbc:oracle:thin:@krakatau.informatik.hs-fulda.de:1521:oralv10a";
    static String URL3 = "jdbc:oracle:thin:@mtsthelens.informatik.hs-fulda.de:1521:oralv9a";
    static public String USERNAME = "VDBSA09";
    static public String PASSWORD = "VDBSA09";
    public static Connection connection = null;

    public static void startConnection (int db) throws FedException {
        try {
            switch (db) {
                case 1:
                    connection = DriverManager.getConnection(URL1, USERNAME, PASSWORD);
                    break;
                case 2:
                    connection = DriverManager.getConnection(URL2, USERNAME, PASSWORD);
                    break;
                case 3:
                    connection = DriverManager.getConnection(URL3, USERNAME, PASSWORD);
                    break;
            }
        }
        catch (SQLException e) {
            throw new FedException(e.getCause());
        }
    }

    @Override
    public void setAutoCommit(boolean autoCommit) throws FedException {
        try {
            connection.setAutoCommit(autoCommit);
        } catch (SQLException e) {
            throw new FedException(e.getCause());
        }
    }

    @Override
    public boolean getAutoCommit() throws FedException {
        try {
            return connection.getAutoCommit();
        } catch (SQLException e) {
            throw new FedException(e.getCause());
        }
    }

    @Override
    public void commit() throws FedException {
        try {
            connection.commit();
        } catch (SQLException e) {
            throw new FedException(e.getCause());
        }
    }

    @Override
    public void rollback() throws FedException {
        try {
            connection.rollback();
        } catch (SQLException e) {
            throw new FedException(e.getCause());
        }
    }

    @Override
    public void close() throws FedException {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new FedException(e.getCause());
        }
    }

    @Override
    public FedStatement getStatement() {
        try {
            return (FedStatement) connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Statement createStatement () throws FedException {
        try {
            return connection.createStatement();
        } catch (SQLException e) {
            throw new FedException(e.getCause());
        }
    }
}
