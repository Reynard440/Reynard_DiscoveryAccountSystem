package za.ac.nwu.domain.persistence;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/*@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@NonNull*/
@Entity
@Table(name = "Exchange_Medium")
public class Exchange_Medium implements Serializable {

    private static final long serialVersionUID = 5381982572241988327L;
    private Integer EM_ID;

    private String EM_Type;

    private String EM_Description;
    private double EM_Balance;
    private Integer Mem_ID;

    public Exchange_Medium() {
    }

    public Exchange_Medium(String EM_Type, String EM_Description, double EM_Balance, Integer mem_ID) {
        this.EM_Type = EM_Type;
        this.EM_Description = EM_Description;
        this.EM_Balance = EM_Balance;
        Mem_ID = mem_ID;
    }

    public Exchange_Medium(Integer EM_ID, String EM_Type, String EM_Description, double EM_Balance, Integer mem_ID) {
        this.EM_ID = EM_ID;
        this.EM_Type = EM_Type;
        this.EM_Description = EM_Description;
        this.EM_Balance = EM_Balance;
        Mem_ID = mem_ID;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getEM_ID() {
        return EM_ID;
    }

    public void setEM_ID(Integer EM_ID) {
        this.EM_ID = EM_ID;
    }

    public String getEM_Type() {
        return EM_Type;
    }

    public void setEM_Type(String EM_Type) {
        this.EM_Type = EM_Type;
    }

    public String getEM_Description() {
        return EM_Description;
    }

    public void setEM_Description(String EM_Description) {
        this.EM_Description = EM_Description;
    }

    public double getEM_Balance() {
        return EM_Balance;
    }

    public void setEM_Balance(double EM_Balance) {
        this.EM_Balance = EM_Balance;
    }

    public Integer getMem_ID() {
        return Mem_ID;
    }

    public void setMem_ID(Integer mem_ID) {
        Mem_ID = mem_ID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exchange_Medium that = (Exchange_Medium) o;
        return Double.compare(that.EM_Balance, EM_Balance) == 0 && Objects.equals(EM_ID, that.EM_ID) && Objects.equals(EM_Type, that.EM_Type) && Objects.equals(EM_Description, that.EM_Description) && Objects.equals(Mem_ID, that.Mem_ID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(EM_ID, EM_Type, EM_Description, EM_Balance, Mem_ID);
    }

    @Override
    public String toString() {
        return "Exchange_Medium{" +
                "EM_ID=" + EM_ID +
                ", EM_Type='" + EM_Type + '\'' +
                ", EM_Description='" + EM_Description + '\'' +
                ", EM_Balance=" + EM_Balance +
                ", Mem_ID=" + Mem_ID +
                '}';
    }
}
