package ddbs.fjdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Set the connection and do commits and rollbacks
 *  Created by: Noor Ali Jafri
 */
public class FedConnection implements FedConnectionInterface {

    static String URL1 = "jdbc:oracle:thin:@pinatubo.informatik.hs-fulda.de:1521:oralv8a";
    static String URL2 = "jdbc:oracle:thin:@krakatau.informatik.hs-fulda.de:1521:oralv10a";
    static String URL3 = "jdbc:oracle:thin:@mtsthelens.informatik.hs-fulda.de:1521:oralv9a";
    static public String USERNAME = "VDBSA09";
    static public String PASSWORD = "VDBSA09";
    public static Connection connection = null;

    private static Connection connection_1 = null;
    private static Connection connection_2 = null;
    private static Connection connection_3 = null;

    public FedConnection() throws FedException{
        try {
            connection_1 = DriverManager.getConnection(URL1, USERNAME, PASSWORD);
            connection_2 = DriverManager.getConnection(URL2, USERNAME, PASSWORD);
            connection_3 = DriverManager.getConnection(URL3, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new FedException(e);
        }
    }

    public static void startConnection (int db) throws FedException {
        switch (db) {
            case 1:
                connection = connection_1;
                break;
            case 2:
                connection = connection_2;
                break;
            case 3:
                connection = connection_3;
                break;
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
