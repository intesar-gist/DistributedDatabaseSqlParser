package ddbs.fjdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

/** Class to get and iterate a resulset object
 *  Created by: Norman Lista
 * */
public class FedResultSet implements FedResultSetInterface {
    ResultSet resultSet3, resultSet1 = null, resultSet2 = null;
    int current = 3;

    public FedResultSet (ResultSet resultSet3) {
        this.resultSet3 = resultSet3;
    }
    public FedResultSet (ResultSet resultSet3, ResultSet resultSet1) {
        this.resultSet3 = resultSet3;
        this.resultSet1 = resultSet1;
    }
    public FedResultSet (ResultSet resultSet3, ResultSet resultSet1, ResultSet resultSet2) {
        this.resultSet3 = resultSet3;
        this.resultSet1 = resultSet1;
        this.resultSet2 = resultSet2;
    }

    @Override
    public boolean next() throws FedException {
        try {
            if (resultSet3.next()) return true;
            else if (resultSet1 !=null && resultSet1.next()) {
                current = 1;
                return true;
            }
            else if (resultSet2 !=null && resultSet2.next()) {
                current = 2;
                return true;
            }
            else {current = 3;}
        } catch (SQLException e) {
            throw new FedException(e.getCause());
        }

        return false;
    }

    @Override
    public String getString(int columnIndex) throws FedException {
        try {
            switch (current) {
                case 3: return resultSet3.getString(columnIndex);
                case 1: return resultSet1.getString(columnIndex);
                case 2: return resultSet2.getString(columnIndex);
            }
        } catch (SQLException e) {
            throw new FedException(e.getCause());
        }

        return null;
    }

    @Override
    public int getInt(int columnIndex) throws FedException {
        try {
            switch (current) {
                case 3: return resultSet3.getInt(columnIndex);
                case 1: return resultSet1.getInt(columnIndex);
                case 2: return resultSet2.getInt(columnIndex);
            }
        } catch (SQLException e) {
            throw new FedException(e.getCause());
        }

        return 0;
    }

    @Override
    public int getColumnCount() throws FedException {
        // Not part of ResultSet class and no interface provided, ergo not implemented
        return 0;
    }

    @Override
    public String getColumnName(int index) throws FedException {
        // Not part of ResultSet class and no interface provided, ergo not implemented
        return null;
    }

    @Override
    public int getColumnType(int index) throws FedException {
        // Not part of ResultSet class and no interface provided, ergo not implemented
        return 0;
    }

    @Override
    public void close() throws FedException {
        try {
            resultSet3.close();
            if (resultSet1 != null) resultSet1.close();
            if (resultSet2 != null) resultSet2.close();
        } catch (SQLException e) {
            throw new FedException(e.getCause());
        }
    }
}
