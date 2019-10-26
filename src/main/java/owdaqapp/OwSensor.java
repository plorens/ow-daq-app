package owdaqapp;

import lombok.Data;
import javax.persistence.*;

@Data
@Table(name = "SENSORS")
@Entity


public class OwSensor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    //------------------------------

    private String ADDRESS;
    private Long SITE_ID;
    private Long ADAPTER_ID;
    private String STATUS;

    //----------------------------


    public String getADDRESS() {
        return ADDRESS;
    }

    public void setADDRESS(String ADDRESS) {
        this.ADDRESS = ADDRESS;
    }

    public Long getSITE_ID() {
        return SITE_ID;
    }

    public void setSITE_ID(Long SITE_ID) {
        this.SITE_ID = SITE_ID;
    }

    public Long getADAPTER_ID() {
        return ADAPTER_ID;
    }

    public void setADAPTER_ID(Long ADAPTER_ID) {
        this.ADAPTER_ID = ADAPTER_ID;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

}
