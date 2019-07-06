package domain.intervention;

import domain.GeneralEntity;
import domain.tooth.Tooth;
import domain.tooth.ToothState;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ToothIntervention extends InterventionItem{

    private Tooth tooth;
    private ToothState state;

    public ToothIntervention(Intervention intervention, String note, Tooth tooth, ToothState state) {
        super(intervention, note);
        this.tooth = tooth;
        this.state = state;
    }

    public ToothIntervention(String itemID, ToothState state) {
        super(itemID);
        this.state = state;
    }

    public ToothIntervention(String itemID, ToothState state, Intervention intervention) {
        this(itemID, state);
        this.intervention = intervention;
    }
    
    public ToothIntervention() {

    }
    
    @Override
    public String getToothLabel() {
        return getTooth().getLabel().toString();
    }

    public Tooth getTooth() {
        return tooth;
    }

    public void setTooth(Tooth tooth) {
        this.tooth = tooth;
    }

    public ToothState getState() {
        return state;
    }

    public void setState(ToothState state) {
        this.state = state;
    }
    
    @Override
    public String getStateLabel() {
        return getState().getName();
    }

    @Override
    public String getItemLabel() {
        return tooth.getLabel().toString();
    }

    @Override
    public String getClassName() {
        return "tooth_intervention";
    }

    @Override
    public String getAtrValue() {
        StringBuilder sb = new StringBuilder();
        sb.append("'").append(intervention.getInterventionID()).append("',")
                .append("'").append(itemID).append("',")
                .append("'").append(tooth.getToothID()).append("',")
                .append("'").append(tooth.getPatient().getPatientID()).append("',")
                .append(state.getToothStateID());
        return sb.toString();
    }

    @Override
    public String setAtrValue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getNameByColumn(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getWhereCondition() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GeneralEntity getNewRecord(ResultSet rs) throws SQLException {
        String interventionID1 = rs.getString("interventionID");
        String itemID1 = rs.getString("itemID");
        String toothID1 = rs.getString("toothID");
        String patientID1 = rs.getString("patientID");
        int toothStateID1 = rs.getInt("toothStateID");
        return new ToothIntervention(itemID1, new ToothState(toothStateID1), new Intervention(interventionID1));
    }
    
}
