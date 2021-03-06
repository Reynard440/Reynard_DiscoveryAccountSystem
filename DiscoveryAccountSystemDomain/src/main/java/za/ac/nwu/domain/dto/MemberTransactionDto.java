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

    private Integer EmId;

    private Integer MtId;

    @ApiModelProperty(position = 1,
            value = "The id that is unique to each member transaction.",
            name = "Member Transaction id.",
            notes = "This field uniquely identifies each member transaction.",
            dataType = "java.lang.Integer",
            example = "1")
    public Integer getMtId() {
        return MtId;
    }

    public void setMtId(Integer MtId) {
        this.MtId = MtId;
    }

    @ApiModelProperty(position = 2,
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

    @ApiModelProperty(position = 3,
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

    @ApiModelProperty(position = 4,
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

    @ApiModelProperty(position = 5,
            value = "The referenced id to indicate which exchange medium was used in the transaction.",
            name = "Exchange medium id.",
            notes = "This field keeps track of the type of exchange medium used in the transaction.",
            dataType = "java.lang.Integer",
            example = "1")
    public Integer getEmId() {
        return EmId;
    }

    public void setEmId(Integer emId) {
        EmId = emId;
    }

    public MemberTransactionDto() {
    }

    public MemberTransactionDto(Integer MtId, LocalDate transactionDate, String description, double amount, Integer exchangeMedium) {
        this.MtId = MtId;
        this.TransactionDate = transactionDate;
        this.Description = description;
        this.Amount = amount;
        this.EmId = exchangeMedium;
    }

    public MemberTransactionDto(String description, LocalDate transactionDate, double amount, Integer emId, Integer mtId) {
        this.TransactionDate = transactionDate;
        this.Description = description;
        this.Amount = amount;
        this.EmId = emId;
        this.MtId = mtId;
    }

    public MemberTransactionDto(Member_Transaction member_transaction){
        this.TransactionDate = member_transaction.getTransactionDate();
        this.Description = member_transaction.getDescription();
        this.Amount = member_transaction.getAmount();
        this.MtId = member_transaction.getMtId();
        this.EmId = member_transaction.getEmId().getEmId();
    }

    @JsonIgnore
    public Member_Transaction buildMemberTransaction(Exchange_Medium exchange_medium) {
        return new Member_Transaction(exchange_medium, this.getTransactionDate(), this.getDescription(),
                this.getAmount());
    }

    @JsonIgnore
    public Member_Transaction buildMemberTransaction() {
        return new Member_Transaction(this.getEmId(), this.getTransactionDate(), this.getDescription(),
                this.getAmount());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberTransactionDto that = (MemberTransactionDto) o;
        return Double.compare(that.Amount, Amount) == 0 && Objects.equals(TransactionDate, that.TransactionDate) && Objects.equals(Description, that.Description) && Objects.equals(EmId, that.EmId) && Objects.equals(MtId, that.MtId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(TransactionDate, Description, Amount, EmId, MtId);
    }

    @Override
    public String toString() {
        return "MemberTransactionDto{" +
                "TransactionDate=" + TransactionDate +
                ", Description='" + Description + '\'' +
                ", Amount=" + Amount +
                ", EmId=" + EmId +
                ", MtId=" + MtId +
                '}';
    }
}
