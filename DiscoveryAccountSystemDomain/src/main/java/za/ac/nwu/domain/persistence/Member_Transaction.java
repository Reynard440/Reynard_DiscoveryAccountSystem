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
@Table(name = "Member_Transaction", schema = "discoveryDB")
//@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="MT_ID", scope = Member.class)
public class Member_Transaction implements Serializable {

    private static final long serialVersionUID = 3177993474135305620L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MT_ID")
    private Integer MtId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "EM_ID")
    @JsonBackReference
    private Exchange_Medium EmId;

    @Column(name = "MT_Transaction_Date")
    private LocalDate TransactionDate;

    @Column(name = "MT_Description")
    private String Description;

    @Column(name = "MT_Amount")
    private double Amount;

    @Column(name = "MT_Total")
    private double Total;

    public Member_Transaction() {
    }

    public Member_Transaction(Exchange_Medium emId, LocalDate transactionDate, String description, double amount, double total) {
        this.EmId = emId;
        this.TransactionDate = transactionDate;
        this.Description = description;
        this.Amount = amount;
        this.Total = total;
    }

    public Member_Transaction(Integer mtId, Exchange_Medium emId, LocalDate transactionDate, String description, double amount, double total) {
        this.MtId = mtId;
        this.EmId = emId;
        this.TransactionDate = transactionDate;
        this.Description = description;
        this.Amount = amount;
        this.Total = total;
    }

    public Member_Transaction(LocalDate transactionDate, String description, double amount, double total) {
        this.TransactionDate = transactionDate;
        this.Description = description;
        this.Amount = amount;
        this.Total = total;
        //this.EmId = exchange_medium;
    }

    public Integer getMtId() {
        return MtId;
    }

    public void setMtId(Integer mtId) {
        MtId = mtId;
    }

    public Exchange_Medium getEmId() {
        return EmId;
    }

    public void setEmId(Exchange_Medium emId) {
        EmId = emId;
    }

    public LocalDate getTransactionDate() {
        return TransactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        TransactionDate = transactionDate;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public double getAmount() {
        return Amount;
    }

    public void setAmount(double amount) {
        Amount = amount;
    }

    public double getTotal() {
        return Total;
    }

    public void setTotal(double total) {
        Total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member_Transaction that = (Member_Transaction) o;
        return Double.compare(that.Amount, Amount) == 0 && Double.compare(that.Total, Total) == 0 && MtId.equals(that.MtId) && EmId.equals(that.EmId) && TransactionDate.equals(that.TransactionDate) && Description.equals(that.Description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(MtId, EmId, TransactionDate, Description, Amount, Total);
    }
}
