package ddbs.fjdbc;

/**
 *  Created by: Norman Lista
 * */
public interface FedResultSetInterface {
	public boolean next() throws FedException;

	public String getString(int columnIndex) throws FedException;

	public int getInt(int columnIndex) throws FedException;

	public int getColumnCount() throws FedException;

	public String getColumnName(int index) throws FedException;

	public int getColumnType(int index) throws FedException;

	public void close() throws FedException;
}
