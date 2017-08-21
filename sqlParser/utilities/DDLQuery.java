package sqlParser.utilities;

import java.util.HashMap;

/**
 * Created by Intesar on 8/16/2017.
 */
public class DDLQuery extends QueryType {

    private HashMap<String,String> attributes = new HashMap<String, String> ();
    private HashMap<String,String> constraints = new HashMap<String, String> ();

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
}
