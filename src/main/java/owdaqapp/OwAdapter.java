package owdaqapp;

import lombok.Data;
import javax.persistence.*;

@Data
@Table(name = "ADAPTERS")
@Entity


public class OwAdapter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    //------------------------------
    private String NAME;
    private String PORT;
    private String STATUS;



    //----------------------------
    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getPORT() {
        return PORT;
    }

    public void setPORT(String PORT) {
        this.PORT = PORT;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }
}