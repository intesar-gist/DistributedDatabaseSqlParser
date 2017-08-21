package sqlParser.utilities;

/**
 * Created by Intesar on 8/21/2017.
 */
public class QueryType {

    private String tableName;
    private int queryType;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public int getQueryType() {
        return queryType;
    }

    public void setQueryType(int queryType) {
        this.queryType = queryType;
    }
}
