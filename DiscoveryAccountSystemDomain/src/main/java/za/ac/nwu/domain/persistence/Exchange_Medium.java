package za.ac.nwu.domain.persistence;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
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
@Table(name = "Exchange_Medium", schema = "discoveryDB")
public class Exchange_Medium implements Serializable {
    private static final long serialVersionUID = 5381982572241988327L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EM_ID")
    private Integer EmId;

    @Column(name = "EM_Type")
    private String Type;

    @Column(name = "EM_Description")
    private String Description;

    @Column(name = "EM_Balance")
    private double Balance;

    @Column(name = "EM_Date")
    private LocalDate Date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MemID")
    @JsonBackReference
    private Member MemID;

    @OneToMany(targetEntity = Member_Transaction.class, mappedBy = "EmId")
    @JsonManagedReference
    private Set<Member_Transaction> memberTransactions;

    public Exchange_Medium() {
    }

    public Exchange_Medium(String type, String description, double balance, LocalDate date) {
        this.Type = type;
        this.Description = description;
        this.Balance = balance;
        this.Date = date;
    }

    public Exchange_Medium(String type, String description, double balance, LocalDate date, Member memID) {
        Type = type;
        Description = description;
        Balance = balance;
        Date = date;
        MemID = memID;
    }

    public Exchange_Medium(String type, String Description, double balance, LocalDate date, Member memID, Set<Member_Transaction> memberTransactions) {
        this.Type = type;
        this.Description = Description;
        this.Balance = balance;
        this.Date = date;
        this.MemID = memID;
        this.memberTransactions = memberTransactions;
    }

    public Exchange_Medium(Integer emId, String type, String Description, double balance, LocalDate date, Member memID, Set<Member_Transaction> memberTransactions) {
        this.EmId = emId;
        this.Type = type;
        this.Description = Description;
        this.Balance = balance;
        this.Date = date;
        this.MemID = memID;
        this.memberTransactions = memberTransactions;
    }

    public Integer getEmId() {
        return EmId;
    }

    public void setEmId(Integer emId) {
        EmId = emId;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public double getBalance() {
        return Balance;
    }

    public void setBalance(double balance) {
        Balance = balance;
    }

    public LocalDate getDate() {
        return Date;
    }

    public void setDate(LocalDate date) {
        Date = date;
    }

    public Member getMemID() {
        return MemID;
    }

    public void setMemID(Member memID) {
        MemID = memID;
    }

    public Set<Member_Transaction> getMemberTransactions() {
        return memberTransactions;
    }

    public void setMemberTransactions(Set<Member_Transaction> memberTransactions) {
        this.memberTransactions = memberTransactions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exchange_Medium that = (Exchange_Medium) o;
        return Double.compare(that.Balance, Balance) == 0 && EmId.equals(that.EmId) && Type.equals(that.Type) && Description.equals(that.Description) && Date.equals(that.Date) && MemID.equals(that.MemID) && memberTransactions.equals(that.memberTransactions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(EmId, Type, Description, Balance, Date, MemID, memberTransactions);
    }
}
