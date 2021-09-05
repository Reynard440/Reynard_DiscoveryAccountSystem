package za.ac.nwu.domain.persistence;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/*@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@NonNull*/
@Entity
@Table(name = "Member_Transaction")
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="MT_ID", scope = Member.class)
public class Member_Transaction implements Serializable {

    private static final long serialVersionUID = 3177993474135305620L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MT_ID")
    private Integer MT_ID;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "MemID")
    private Member MemberID;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "EM_ID")
    private Exchange_Medium EM_ID;

    @Column(name = "MT_Transaction_Date")
    private LocalDate MT_TransactionDate;

    @Column(name = "MT_Description")
    private String MT_Description;

    @Column(name = "MT_Amount")
    private double MT_Amount;

    @Column(name = "MT_Total")
    private double MT_Total;

    public Member_Transaction() {
    }

    public Member_Transaction(Member MemberID, Exchange_Medium EM_ID, LocalDate MT_TransactionDate, String MT_Description, double MT_Amount, double MT_Total) {
        this.MemberID = MemberID;
        this.EM_ID = EM_ID;
        this.MT_TransactionDate = MT_TransactionDate;
        this.MT_Description = MT_Description;
        this.MT_Amount = MT_Amount;
        this.MT_Total = MT_Total;
    }

    public Member_Transaction(Integer MT_ID, Member MemberID, Exchange_Medium EM_ID, LocalDate MT_TransactionDate, String MT_Description, double MT_Amount, double MT_Total) {
        this.MT_ID = MT_ID;
        this.MemberID = MemberID;
        this.EM_ID = EM_ID;
        this.MT_TransactionDate = MT_TransactionDate;
        this.MT_Description = MT_Description;
        this.MT_Amount = MT_Amount;
        this.MT_Total = MT_Total;
    }

    public Member_Transaction(LocalDate mt_transactionDate, String mt_description, double mt_amount, double mt_total, Member memberID, Exchange_Medium exchange_medium) {
        this.MT_TransactionDate = mt_transactionDate;
        this.MT_Description = mt_description;
        this.MT_Amount = mt_amount;
        this.MT_Total = mt_total;
        this.MemberID = memberID;
        this.EM_ID = exchange_medium;
    }

    public Member getMemberID() {
        return MemberID;
    }

    public void setMemberID(Member memberID) {
        MemberID = memberID;
    }

    public Integer getMT_ID() {
        return MT_ID;
    }

    public Exchange_Medium getEM_ID() {
        return EM_ID;
    }

    public void setMT_ID(Integer MT_ID) {
        this.MT_ID = MT_ID;
    }

    public void setMemID(Member MemID) {
        MemID = MemID;
    }

    public void setEM_ID(Exchange_Medium EM_ID) {
        this.EM_ID = EM_ID;
    }

    public LocalDate getMT_TransactionDate() {
        return MT_TransactionDate;
    }

    public void setMT_TransactionDate(LocalDate MT_TransactionDate) {
        this.MT_TransactionDate = MT_TransactionDate;
    }

    public String getMT_Description() {
        return MT_Description;
    }

    public void setMT_Description(String MT_Description) {
        this.MT_Description = MT_Description;
    }

    public double getMT_Amount() {
        return MT_Amount;
    }

    public void setMT_Amount(double MT_Amount) {
        this.MT_Amount = MT_Amount;
    }

    public double getMT_Total() {
        return MT_Total;
    }

    public void setMT_Total(double MT_Total) {
        this.MT_Total = MT_Total;
    }

    @Override
    public String toString() {
        return "Member_Transaction{" +
                "MT_ID=" + MT_ID +
                ", MemberID=" + MemberID +
                ", EM_ID=" + EM_ID +
                ", MT_TransactionDate=" + MT_TransactionDate +
                ", MT_Description='" + MT_Description + '\'' +
                ", MT_Amount=" + MT_Amount +
                ", MT_Total=" + MT_Total +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member_Transaction that = (Member_Transaction) o;
        return Double.compare(that.MT_Amount, MT_Amount) == 0 && Double.compare(that.MT_Total, MT_Total) == 0 && Objects.equals(MT_ID, that.MT_ID) && Objects.equals(MemberID, that.MemberID) && Objects.equals(EM_ID, that.EM_ID) && Objects.equals(MT_TransactionDate, that.MT_TransactionDate) && Objects.equals(MT_Description, that.MT_Description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(MT_ID, MemberID, EM_ID, MT_TransactionDate, MT_Description, MT_Amount, MT_Total);
    }
}
