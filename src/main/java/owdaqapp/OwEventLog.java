package owdaqapp;

import lombok.Data;
import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Table(name = "EVENT_LOGS")
@Entity


public class OwEventLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    //------------------------------
    private String DESCRIPTION;
    private String TYPE;
    private Timestamp TIMESTAMP;

    //----------------------------


    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    public String getTYPE() {
        return TYPE;
    }

    public void setTYPE(String TYPE) {
        this.TYPE = TYPE;
    }

    public Timestamp getTIMESTAMP() {
        return TIMESTAMP;
    }

    public void setTIMESTAMP(Timestamp TIMESTAMP) {
        this.TIMESTAMP = TIMESTAMP;
    }

}
