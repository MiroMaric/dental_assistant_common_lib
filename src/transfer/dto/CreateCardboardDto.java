package transfer.dto;

import domain.Patient;
import domain.User;
import java.io.Serializable;

public class CreateCardboardDto implements Serializable{
    private Patient patient;
    private User user;

    public CreateCardboardDto() {
    }

    public CreateCardboardDto(Patient patient, User user) {
        this.patient = patient;
        this.user = user;
    }
    
    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
}
