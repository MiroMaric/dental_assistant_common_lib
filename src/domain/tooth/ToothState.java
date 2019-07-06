
package domain.tooth;

import java.sql.ResultSet;
import java.sql.SQLException;
import domain.GeneralEntity;


public class ToothState implements GeneralEntity{
    
    private int toothStateID;
    private String name;
    private String description;
    private int color;
    
    public ToothState() {
    }

    public ToothState(int toothStateID) {
        this.toothStateID = toothStateID;
    }
    
    public ToothState(String name) {
        this.name = name;
    }
    
    public ToothState(int toothStateID, String name, String description, int color) {
        this.toothStateID = toothStateID;
        this.name = name;
        this.description = description;
        this.color = color;
    }
    
     public int getToothStateID() {
        return toothStateID;
    }

    public void setToothStateID(int toothStateID) {
        this.toothStateID = toothStateID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
    
    @Override
    public String toString() {
        return getName();
    }

    @Override
    public String getClassName() {
        return "tooth_state";
    }

    @Override
    public String getAtrValue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String setAtrValue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getNameByColumn(int i) {
        String[] atributes = {"toothStateID", "name", "description","color"};
        return atributes[i];
    }

    @Override
    public String getWhereCondition() {
        if(getToothStateID()==0){
            return "name = '"+getName()+"'";
        }else{
            return "toothStateID = '"+getToothStateID()+"'";
        }
    }

    @Override
    public GeneralEntity getNewRecord(ResultSet rs) throws SQLException {
        int toothStateID1 = rs.getInt("toothStateID");
        String name1 = rs.getString("name");
        String description1 = rs.getString("description");
        int color1 = rs.getInt("color");
        return new ToothState(toothStateID1, name1, description1, color1);
    }

    
}
