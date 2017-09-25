package ddbs.fjdbc;

/**
 * Created By: Noor Ali Jafri
 */
public interface FedConnectionInterface {
	public void setAutoCommit(boolean autoCommit) throws FedException;

	public boolean getAutoCommit() throws FedException;

	public void commit() throws FedException;

	public void rollback() throws FedException;

	public void close() throws FedException;

	public FedStatement getStatement();
}
