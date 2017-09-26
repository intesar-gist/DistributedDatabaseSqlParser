package ddbs.fjdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

/** Class to get and iterate a resulset object
 *  Created by: Norman Lista
 * */
public class FedResultSet implements FedResultSetInterface {
    ResultSet resultSet3, resultSet1 = null, resultSet2 = null;
    int current = 3;
    boolean ist_sucunt = false;

    public FedResultSet (boolean ist_sucunt, ResultSet resultSet3) {
        this.resultSet3 = resultSet3;
        this.ist_sucunt = ist_sucunt;
    }
    public FedResultSet (boolean ist_sucunt, ResultSet resultSet3, ResultSet resultSet1) {
        this.resultSet3 = resultSet3;
        this.resultSet1 = resultSet1;
        this.ist_sucunt = ist_sucunt;
    }
    public FedResultSet (boolean ist_sucunt, ResultSet resultSet3, ResultSet resultSet1, ResultSet resultSet2) {
        this.resultSet3 = resultSet3;
        this.resultSet1 = resultSet1;
        this.resultSet2 = resultSet2;
        this.ist_sucunt = ist_sucunt;
    }

    @Override
    public boolean next() throws FedException {
        try {
            if (resultSet3.next()) return true;
            else if (resultSet1!=null && resultSet1.next()) {
                current = 1;
                return true;
            }
            else if (resultSet2!=null && resultSet2.next()) {
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
            if (ist_sucunt) {
                int total = resultSet3.getInt(columnIndex);
                if (resultSet1 != null && resultSet1.next()) total += resultSet1.getInt(columnIndex);
                if (resultSet2 != null && resultSet2.next()) total += resultSet2.getInt(columnIndex);

                return total;
            }  else switch (current) {
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
        try {
            return resultSet3.getMetaData().getColumnCount();
        } catch (SQLException e) {
            throw new FedException(e.getCause());
        }
    }

    @Override
    public String getColumnName(int index) throws FedException {
        try {
            return resultSet3.getMetaData().getColumnName(index);
        } catch (SQLException e) {
            throw new FedException(e.getCause());
        }
    }

    @Override
    public int getColumnType(int index) throws FedException {
        try {
            return resultSet3.getMetaData().getColumnType(index);
        } catch (SQLException e) {
            throw new FedException(e.getCause());
        }
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
