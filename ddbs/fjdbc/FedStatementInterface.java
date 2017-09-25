package ddbs.fjdbc;
/**
 *  Created by: Intesar Haider
 * */

public interface FedStatementInterface {
	public int executeUpdate(String sql) throws FedException;

	public FedResultSet executeQuery(String sql) throws FedException;

	public FedConnection getConnection() throws FedException;

	public void close() throws FedException;
}
