package za.ac.nwu.domain.persistence;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EM_ID")
    private Integer EM_ID;

    @Column(name = "EM_Type")
    private String EM_Type;

    @Column(name = "EM_Description")
    private String EM_Description;

    @Column(name = "EM_Balance")
    private double EM_Balance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MemID")
    private Member MEM_ID;

    @OneToMany(targetEntity = Member_Transaction.class, fetch = FetchType.LAZY, mappedBy = "EM_ID", orphanRemoval = true, cascade = CascadeType.PERSIST)
    private Set<Member_Transaction> member_transactions;

    public Exchange_Medium() {
    }

    public Exchange_Medium(String EM_Type, String EM_Description, double EM_Balance, Member MEM_ID) {
        this.EM_Type = EM_Type;
        this.EM_Description = EM_Description;
        this.EM_Balance = EM_Balance;
        this.MEM_ID = MEM_ID;
    }

    public Exchange_Medium(Integer EM_ID, String EM_Type, String EM_Description, double EM_Balance, Member MEM_ID, Set<Member_Transaction> member_transactions) {
        this.EM_ID = EM_ID;
        this.EM_Type = EM_Type;
        this.EM_Description = EM_Description;
        this.EM_Balance = EM_Balance;
        this.MEM_ID = MEM_ID;
        this.member_transactions = member_transactions;
    }

    public Exchange_Medium(Integer EM_ID) {
        this.EM_ID = EM_ID;
    }

    public Integer getEM_ID() {
        return EM_ID;
    }

    public void setEM_ID(Integer EM_ID) {
        this.EM_ID = EM_ID;
    }

    public Set<Member_Transaction> getMember_transactions() {
        return member_transactions;
    }

    public void setMember_transactions(Set<Member_Transaction> member_transactions) {
        this.member_transactions = member_transactions;
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

    public Member getMEM_ID() {
        return MEM_ID;
    }

    public void setMEM_ID(Member MEM_ID) {
        this.MEM_ID = MEM_ID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exchange_Medium that = (Exchange_Medium) o;
        return Double.compare(that.EM_Balance, EM_Balance) == 0 && Objects.equals(EM_ID, that.EM_ID) && Objects.equals(EM_Type, that.EM_Type) && Objects.equals(EM_Description, that.EM_Description) && Objects.equals(MEM_ID, that.MEM_ID) && Objects.equals(member_transactions, that.member_transactions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(EM_ID, EM_Type, EM_Description, EM_Balance, MEM_ID, member_transactions);
    }

    @Override
    public String toString() {
        return "Exchange_Medium{" +
                "EM_ID=" + EM_ID +
                ", EM_Type='" + EM_Type + '\'' +
                ", EM_Description='" + EM_Description + '\'' +
                ", EM_Balance=" + EM_Balance +
                ", MEM_ID=" + MEM_ID +
                ", member_transactions=" + member_transactions +
                '}';
    }
}
