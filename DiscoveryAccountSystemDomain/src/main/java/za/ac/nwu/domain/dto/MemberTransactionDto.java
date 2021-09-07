package za.ac.nwu.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import za.ac.nwu.domain.persistence.Exchange_Medium;
import za.ac.nwu.domain.persistence.Member_Transaction;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@ApiModel(value = "MemberTransactionDto", description = "A DTO that represents the Member transactions")
public class MemberTransactionDto implements Serializable {

    private static final long serialVersionUID = -286232694247187358L;

    private LocalDate TransactionDate;

    private String Description;

    private double Amount;

    private double Total;

    private Exchange_Medium EmId;

    @ApiModelProperty(position = 1,
            value = "Date of the transaction",
            name = "Transaction Date",
            notes = "This field will automatically stamp the exact moment the transaction took place.",
            dataType = "java.time.LocalDate",
            example = "2012-01-01")
    public LocalDate getTransactionDate() {
        return TransactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        TransactionDate = transactionDate;
    }

    @ApiModelProperty(position = 2,
            value = "Description of the transaction",
            name = "Transaction Description",
            notes = "This field will track on what happened, like was it a deposit or a withdrawal.",
            dataType = "java.lang.String",
            example = "Deposit / Withdrawal")
    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    @ApiModelProperty(position = 3,
            value = "Amount of the transaction",
            name = "Transaction Amount",
            notes = "This field records how much currency was used",
            dataType = "java.lang.Double",
            example = "2000.00")
    public double getAmount() {
        return Amount;
    }

    public void setAmount(double amount) {
        Amount = amount;
    }

    @ApiModelProperty(position = 4,
            value = "Total of the Member's account",
            name = "Transaction Total",
            notes = "This field records the total of the Member's account",
            dataType = "java.lang.Double",
            example = "2000.00")
    public double getTotal() {
        return Total;
    }

    public void setTotal(double total) {
        Total = total;
    }

//    @ApiModelProperty(position = 5,
//            value = "The referenced id to indicate which exchange medium was used in the transaction.",
//            name = "Exchange medium id.",
//            notes = "This field keeps track of the type of exchange medium used in the transaction.",
//            dataType = "java.lang.Integer",
//            example = "1")
//    public Exchange_Medium getEmId() {
//        return EmId;
//    }
//
//    public void setEmId(Exchange_Medium emId) {
//        EmId = emId;
//    }

    public MemberTransactionDto() {
    }

    public MemberTransactionDto(LocalDate transactionDate, String description, double amount, double total, Exchange_Medium emId) {
        this.TransactionDate = transactionDate;
        this.Description = description;
        this.Amount = amount;
        this.Total = total;
        this.EmId = emId;
    }

    public MemberTransactionDto(Member_Transaction member_transaction){
        this.setTransactionDate(member_transaction.getTransactionDate());
        this.setDescription(member_transaction.getDescription());
        this.setAmount(member_transaction.getAmount());
        this.setTotal(member_transaction.getTotal());
        //this.setEmId(member_transaction.getEmId());
    }

    @JsonIgnore
    public Member_Transaction getMemberTransaction() {
        return new Member_Transaction(getTransactionDate(), getDescription(), getAmount(), getTotal()/*, getEmId()*/);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberTransactionDto that = (MemberTransactionDto) o;
        return Double.compare(that.Amount, Amount) == 0 && Double.compare(that.Total, Total) == 0 && TransactionDate.equals(that.TransactionDate) && Description.equals(that.Description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(TransactionDate, Description, Amount, Total);
    }
}
