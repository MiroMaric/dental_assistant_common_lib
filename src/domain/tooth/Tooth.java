package domain.tooth;

import domain.Patient;
import domain.intervention.InterventionItem;
import domain.intervention.RootIntervention;
import domain.intervention.SideIntervention;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import domain.GeneralEntity;
import domain.intervention.ToothIntervention;

public class Tooth implements GeneralEntity {

    private String toothID;
    private Patient patient;
    private ToothLabel label;
    private List<ToothSide> sides;
    private List<ToothRoot> roots;
    private List<ToothIntervention> toothInterventions;

    public Tooth() {
    }

    public Tooth(String toothID, Patient patient, ToothLabel label, List<ToothSide> sides, List<ToothRoot> roots, List<ToothIntervention> toothInterventions) {
        this.toothID = toothID;
        this.patient = patient;
        this.label = label;
        this.sides = sides;
        this.roots = roots;
        this.toothInterventions = toothInterventions;
    }

    public Tooth(Patient patient, ToothLabel label) {
        this.toothID = UUID.randomUUID().toString();
        this.patient = patient;
        this.label = label;
        this.sides = new LinkedList<>();
        this.roots = new LinkedList<>();
        this.toothInterventions = new LinkedList<>();
    }

    public Tooth(ToothLabel label) {
        this.label = label;
        this.sides = new LinkedList<>();
        this.roots = new LinkedList<>();
        this.toothInterventions = new LinkedList<>();
    }

    public Tooth(String toothID, Patient patient, ToothLabel label) {
        this.patient = patient;
        this.toothID = toothID;
        this.label = label;
    }

    public Tooth(String toothID1, Patient patient) {
        this.toothID = toothID1;
        this.patient = patient;
    }

    public ToothLabel getLabel() {
        return label;
    }

    public void setLabel(ToothLabel label) {
        this.label = label;
    }

    public List<ToothSide> getSides() {
        return sides;
    }

    public void setSides(List<ToothSide> sides) {
        this.sides = sides;
    }

    public List<ToothRoot> getRoots() {
        return roots;
    }

    public void setRoots(List<ToothRoot> roots) {
        this.roots = roots;
    }

    public List<ToothIntervention> getToothInterventions() {
        return toothInterventions;
    }

    public void setToothInterventions(List<ToothIntervention> toothInterventions) {
        this.toothInterventions = toothInterventions;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getToothID() {
        return toothID;
    }

    public void setToothID(String toothID) {
        this.toothID = toothID;
    }

    public List<InterventionItem> getAllSortedInterventions() {
        List<InterventionItem> interventions = new ArrayList<>();
        sides.forEach(side -> {
            interventions.addAll(side.getSideInterventions());
        });
        roots.forEach(root -> {
            interventions.addAll(root.getRootInterventions());
        });
        interventions.addAll(toothInterventions);
        interventions.sort((o1, o2) -> o1.getIntervention().getDate().compareTo(o2.getIntervention().getDate()));
        return interventions;
    }

    public List<SideIntervention> getAllSideInterventions() {
        List<SideIntervention> interventions = new LinkedList<>();
        sides.forEach(side -> {
            side.getSideInterventions().forEach(i -> {
                interventions.add(i);
            });
        });
        return interventions;
    }

    public HashMap<ToothSideLabel, ToothSideState> getCurrentStatesOfSides() {
        HashMap<ToothSideLabel, ToothSideState> states = new HashMap<>();
        sides.forEach((side) -> {
            states.put(side.getLabel(), side.getSideInterventions().stream().max((o1, o2) -> o1.getIntervention().getDate().compareTo(o2.getIntervention().getDate())).get().getState());
        });
        return states;
    }

    public HashMap<ToothRootLabel, ToothRootState> getCurrentStatesOfRoots() {
        HashMap<ToothRootLabel, ToothRootState> states = new HashMap<>();
        roots.forEach((root) -> {
            states.put(root.getLabel(), root.getRootInterventions().stream().max((o1, o2) -> o1.getIntervention().getDate().compareTo(o2.getIntervention().getDate())).get().getToothRootState());
        });
        return states;
    }

    public ToothState getCurrentState() {
        return toothInterventions.get(toothInterventions.size()-1).getState();
    }

    public ToothSide getToothSide(int indexOf) {
        System.out.println(label.toString());
        System.out.println(sides.get(indexOf).getTooth().getLabel().toString());
        return sides.get(indexOf);
    }

    public ToothSideState getSideStateAtDate(int i, Date date) {
        SideIntervention result = null;
        Date tempDate = new GregorianCalendar(1, 0, 0).getTime();
        for (SideIntervention si : sides.get(i).getSideInterventions()) {
            if (si.getIntervention().getDate().getTime() <= date.getTime()
                    && tempDate.getTime() <= si.getIntervention().getDate().getTime()) {
                result = si;
                tempDate = si.getIntervention().getDate();
            }
        }
        if (result == null) {
            throw new RuntimeException("Logicka greska!");
        }
        return result.getState();
    }

    public ToothRootState getRootStateAtDate(int index, Date date) {
        List<RootIntervention> interventions = roots.get(index).getRootInterventions();
        for (int i = interventions.size() - 1; i >= 0; i--) {
            if (interventions.get(i).getIntervention().getDate().getTime() <= date.getTime()) {
                return interventions.get(i).getToothRootState();
            }
        }
        return interventions.get(0).getToothRootState();
    }

    @Override
    public String getClassName() {
        return "tooth";
    }

    @Override
    public String getAtrValue() {
        StringBuilder sb = new StringBuilder();
        sb.append("'").append(toothID).append("',")
                .append("'").append(patient.getPatientID()).append("',")
                .append("'").append(label.getToothLabelID()).append("'");
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
        return "patientID='" + patient.getPatientID() + "' and toothID='" + toothID + "'";
    }

    @Override
    public GeneralEntity getNewRecord(ResultSet rs) throws SQLException {
        String toothID1 = rs.getString("toothID");
        String patientID1 = rs.getString("patientID");
        String toothLabelID1 = rs.getString("toothLabelID");
        return new Tooth(toothID1, new Patient(patientID1), new ToothLabel(toothLabelID1));
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.toothID);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Tooth other = (Tooth) obj;
        return Objects.equals(this.toothID, other.toothID);
    }

}
