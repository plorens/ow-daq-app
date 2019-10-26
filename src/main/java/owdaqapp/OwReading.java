package owdaqapp;

import lombok.Data;
import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Table(name = "READINGS")
@Entity


public class OwReading {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    //------------------------------
    private Long SITE_ID;
    private Double VALUE;
    private Timestamp TIMESTAMP;

    //----------------------------

    public Long getID() { return ID; }

    public void setID(Long ID) { this.ID = ID; }

    public Long getSITE_ID() {
        return SITE_ID;
    }

    public void setSITE_ID(Long SITE_ID) {
        this.SITE_ID = SITE_ID;
    }

    public Double getVALUE() {
        return VALUE;
    }

    public void setVALUE(Double VALUE) { this.VALUE = VALUE; }

    public Timestamp getTIMESTAMP() {
        return TIMESTAMP;
    }

    public void setTIMESTAMP(Timestamp TIMESTAMP) {
        this.TIMESTAMP = TIMESTAMP;
    }
}
