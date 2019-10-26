package owdaqapp;

import lombok.Data;
import javax.persistence.*;

@Data
@Table(name = "SITES")
@Entity


public class OwSite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    //------------------------------
    private String DESCRIPTION;
    private String STATUS;



    //------------------------------------------
    public Long getID() {
        return ID;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }
}
