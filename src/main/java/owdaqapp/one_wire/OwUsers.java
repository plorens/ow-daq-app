package owdaqapp.one_wire;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "USERS")
@Entity


public class OwUsers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    //------------------------------
    private String LOGIN;
    private String PASSWORD;



    //------------------------------------------
    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getLOGIN() {
        return LOGIN;
    }

    public void setLOGIN(String LOGIN) {
        this.LOGIN = LOGIN;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }
}
