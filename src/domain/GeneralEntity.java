
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface GeneralEntity extends Serializable{
    
    String getClassName();
    String getAtrValue();
    String setAtrValue();
    String getNameByColumn(int i);
    String getWhereCondition();
    GeneralEntity getNewRecord(ResultSet rs) throws SQLException;
    
}
