package ddbs.fjdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

/** Class to get and iterate a resulset object
 *  Created by: Norman Lista
 * */
public class FedResultSet implements FedResultSetInterface, FJDBCConstants {
    ResultSet db3ResultSet, db1ResultSet = null, db2ResultSet = null;
    int selectedDB = MTSTHELENS_DB3;
    boolean isAggregateOp = false;

    public FedResultSet (boolean isAggregateOp, ResultSet db3ResultSet) {
        this.db3ResultSet = db3ResultSet;
        this.isAggregateOp = isAggregateOp;
    }
    public FedResultSet (boolean isAggregateOp, ResultSet db3ResultSet, ResultSet db1ResultSet) {
        this.db3ResultSet = db3ResultSet;
        this.db1ResultSet = db1ResultSet;
        this.isAggregateOp = isAggregateOp;
    }
    public FedResultSet (boolean isAggregateOp, ResultSet db3ResultSet, ResultSet db1ResultSet, ResultSet db2ResultSet) {
        this.db3ResultSet = db3ResultSet;
        this.db1ResultSet = db1ResultSet;
        this.db2ResultSet = db2ResultSet;
        this.isAggregateOp = isAggregateOp;
    }

    @Override
    public boolean next() throws FedException {
        try {
            if (db3ResultSet.next()) {
                return true;
            } else if (db1ResultSet !=null && db1ResultSet.next()) {
                selectedDB = PINATUBO_DB1;
                return true;
            } else if (db2ResultSet !=null && db2ResultSet.next()) {
                selectedDB = KRAKATAU_DB2;
                return true;
            } else {
                selectedDB = MTSTHELENS_DB3;
            }
        } catch (SQLException e) {
            throw new FedException(e);
        }

        return false;
    }

    @Override
    public String getString(int columnIndex) throws FedException {
        try {
            switch (selectedDB) {
                case PINATUBO_DB1: return db1ResultSet.getString(columnIndex);
                case KRAKATAU_DB2: return db2ResultSet.getString(columnIndex);
                case MTSTHELENS_DB3: return db3ResultSet.getString(columnIndex);
            }
        } catch (SQLException e) {
            throw new FedException(e);
        }

        return null;
    }

    @Override
    public int getInt(int columnIndex) throws FedException {
        try {
            if (isAggregateOp) {
                int total = db3ResultSet.getInt(columnIndex);
                if (db1ResultSet != null && db1ResultSet.next()) total += db1ResultSet.getInt(columnIndex);
                if (db2ResultSet != null && db2ResultSet.next()) total += db2ResultSet.getInt(columnIndex);

                return total;
            }  else switch (selectedDB) {
                case MTSTHELENS_DB3: return db3ResultSet.getInt(columnIndex);
                case PINATUBO_DB1: return db1ResultSet.getInt(columnIndex);
                case KRAKATAU_DB2: return db2ResultSet.getInt(columnIndex);
            }
        } catch (SQLException e) {
            throw new FedException(e.getCause());
        }

        return 0;
    }

    @Override
    public int getColumnCount() throws FedException {
        try {
            return db3ResultSet.getMetaData().getColumnCount();
        } catch (SQLException e) {
            throw new FedException(e.getCause());
        }
    }

    @Override
    public String getColumnName(int index) throws FedException {
        try {
            return db3ResultSet.getMetaData().getColumnName(index);
        } catch (SQLException e) {
            throw new FedException(e.getCause());
        }
    }

    @Override
    public int getColumnType(int index) throws FedException {
        try {
            return db3ResultSet.getMetaData().getColumnType(index);
        } catch (SQLException e) {
            throw new FedException(e.getCause());
        }
    }

    @Override
    public void close() throws FedException {
        try {
            db3ResultSet.close();
            if (db1ResultSet != null) db1ResultSet.close();
            if (db2ResultSet != null) db2ResultSet.close();
        } catch (SQLException e) {
            throw new FedException(e.getCause());
        }
    }
}
