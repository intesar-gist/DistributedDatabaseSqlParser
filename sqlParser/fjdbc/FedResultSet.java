package sqlParser.fjdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

/** Class to get and iterate a resulset object
 *  Created by: Norman Lista
 * */
public class FedResultSet implements FedResultSetInterface {
    ResultSet rs3, rs1 = null, rs2 = null;
    int current = 3;

    public FedResultSet (ResultSet rs3) {
        this.rs3 = rs3;
    }
    public FedResultSet (ResultSet rs3, ResultSet rs1) {
        this.rs3 = rs3;
        this.rs1 = rs1;
    }
    public FedResultSet (ResultSet rs3, ResultSet rs1, ResultSet rs2) {
        this.rs3 = rs3;
        this.rs1 = rs1;
        this.rs2 = rs2;
    }

    @Override
    public boolean next() throws FedException {
        try {
            if (rs3.next()) return true;
            else if (rs1 !=null && rs1.next()) {
                current = 1;
                return true;
            }
            else if (rs2 !=null && rs2.next()) {
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
                case 3: return rs3.getString(columnIndex);
                case 1: return rs1.getString(columnIndex);
                case 2: return rs2.getString(columnIndex);
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
                case 3: return rs3.getInt(columnIndex);
                case 1: return rs1.getInt(columnIndex);
                case 2: return rs2.getInt(columnIndex);
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
            rs3.close();
            if (rs1 != null) rs1.close();
            if (rs2 != null) rs2.close();
        } catch (SQLException e) {
            throw new FedException(e.getCause());
        }
    }
}
