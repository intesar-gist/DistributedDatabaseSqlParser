package sqlParser.utilities;

import java.util.HashMap;

/**
 * Created by Intesar on 8/16/2017.
 */
public class TableStruct {

    private String tableName;
    private int queryType;
    private HashMap<String,String> attributes = new HashMap<String, String> ();
    private HashMap<String,String> constraints = new HashMap<String, String> ();

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public HashMap<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(HashMap<String, String> attributes) {
        this.attributes = attributes;
    }

    public HashMap<String, String> getConstraints() {
        return constraints;
    }

    public void setConstraints(HashMap<String, String> constraints) {
        this.constraints = constraints;
    }

    public int getQueryType() {
        return queryType;
    }

    public void setQueryType(int queryType) {
        this.queryType = queryType;
    }
}
