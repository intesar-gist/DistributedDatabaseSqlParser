package sqlParser.fjdbc;

public interface FedStatementInterface {
	public int executeUpdate(String sql) throws FedException;

	public FedResultSet executeQuery(String sql) throws FedException;

	public FedConnection getConnection() throws FedException;

	public void close() throws FedException;
}
