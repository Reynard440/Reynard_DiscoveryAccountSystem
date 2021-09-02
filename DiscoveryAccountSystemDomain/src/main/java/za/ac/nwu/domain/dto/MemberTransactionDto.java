package za.ac.nwu.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import za.ac.nwu.domain.persistence.Exchange_Medium;
import za.ac.nwu.domain.persistence.Member;
import za.ac.nwu.domain.persistence.Member_Transaction;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@ApiModel(value = "MemberTransactionDto", description = "A DTO that represents the Member transactions")
public class MemberTransactionDto implements Serializable {

    private static final long serialVersionUID = -286232694247187358L;

    private LocalDate MT_TransactionDate;

    private String MT_Description;

    private double MT_Amount;

    private double MT_Total;

    private Member MemberID;

    private Exchange_Medium EM_ID;

    @ApiModelProperty(position = 1,
            value = "Date of the transaction",
            name = "Transaction Date",
            notes = "This field will automatically stamp the exact moment the transaction took place.",
            dataType = "java.time.LocalDate",
            example = "2012-01-01")
    public LocalDate getMT_TransactionDate() {
        return MT_TransactionDate;
    }

    public void setMT_TransactionDate(LocalDate MT_TransactionDate) {
        this.MT_TransactionDate = MT_TransactionDate;
    }

    @ApiModelProperty(position = 2,
            value = "Description of the transaction",
            name = "Transaction Description",
            notes = "This field will track on what happened, like was it a deposit or a withdrawal.",
            dataType = "java.lang.String",
            example = "Deposit / Withdrawal")
    public String getMT_Description() {
        return MT_Description;
    }

    public void setMT_Description(String MT_Description) {
        this.MT_Description = MT_Description;
    }

    @ApiModelProperty(position = 3,
            value = "Amount of the transaction",
            name = "Transaction Amount",
            notes = "This field records how much currency was used",
            dataType = "java.lang.Double",
            example = "2000.00")
    public double getMT_Amount() {
        return MT_Amount;
    }

    public void setMT_Amount(double MT_Amount) {
        this.MT_Amount = MT_Amount;
    }

    @ApiModelProperty(position = 4,
            value = "Total of the Member's account",
            name = "Transaction Total",
            notes = "This field records the total of the Member's account",
            dataType = "java.lang.Double",
            example = "2000.00")
    public double getMT_Total() {
        return MT_Total;
    }

    public void setMT_Total(double MT_Total) {
        this.MT_Total = MT_Total;
    }

    @ApiModelProperty(position = 5,
            value = "The referenced id to indicate which member performed the transaction.",
            name = "Member id",
            notes = "This field keeps track of the member of the transaction.",
            dataType = "java.lang.Integer",
            example = "1")
    public Member getMemberID() {
        return MemberID;
    }

    public void setMemberID(Member memberID) {
        MemberID = memberID;
    }

    @ApiModelProperty(position = 6,
            value = "The referenced id to indicate which exchange medium was used in the transaction.",
            name = "Exchange medium id.",
            notes = "This field keeps track of the type of exchange medium used in the transaction.",
            dataType = "java.lang.Integer",
            example = "1")
    public Exchange_Medium getEM_ID() {
        return EM_ID;
    }

    public void setEM_ID(Exchange_Medium EM_ID) {
        this.EM_ID = EM_ID;
    }

    public MemberTransactionDto() {
    }

    public MemberTransactionDto(LocalDate MT_TransactionDate, String MT_Description, double MT_Amount, double MT_Total, Exchange_Medium EM_ID, Member memberID) {
        this.MT_TransactionDate = MT_TransactionDate;
        this.MT_Description = MT_Description;
        this.MT_Amount = MT_Amount;
        this.MT_Total = MT_Total;
        this.MemberID = memberID;
        this.EM_ID = EM_ID;
    }

    public MemberTransactionDto(LocalDate MT_TransactionDate, String MT_Description, double MT_Amount, double MT_Total, Member memberID, Exchange_Medium EM_ID) {
        this.MT_TransactionDate = MT_TransactionDate;
        this.MT_Description = MT_Description;
        this.MT_Amount = MT_Amount;
        this.MT_Total = MT_Total;
        this.MemberID = memberID;
        this.EM_ID = EM_ID;
    }

    public MemberTransactionDto(Member_Transaction member_transaction){
        this.setMT_TransactionDate(member_transaction.getMT_TransactionDate());
        this.setMT_Description(member_transaction.getMT_Description());
        this.setMT_Amount(member_transaction.getMT_Amount());
        this.setMT_Total(member_transaction.getMT_Total());
        this.setMemberID(member_transaction.getMemberID());
        this.setEM_ID(member_transaction.getEM_ID());
    }

    @JsonIgnore
    public Member_Transaction getMemberTransaction() {
        return new Member_Transaction(getMT_TransactionDate(), getMT_Description(), getMT_Amount(), getMT_Total(), getMemberID(), getEM_ID());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberTransactionDto that = (MemberTransactionDto) o;
        return Double.compare(that.MT_Amount, MT_Amount) == 0 && Double.compare(that.MT_Total, MT_Total) == 0 && Objects.equals(MT_TransactionDate, that.MT_TransactionDate) && Objects.equals(MT_Description, that.MT_Description) && Objects.equals(MemberID, that.MemberID) && Objects.equals(EM_ID, that.EM_ID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(MT_TransactionDate, MT_Description, MT_Amount, MT_Total, MemberID, EM_ID);
    }

    @Override
    public String toString() {
        return "MemberTransactionDto{" +
                "MT_TransactionDate=" + MT_TransactionDate +
                ", MT_Description='" + MT_Description + '\'' +
                ", MT_Amount=" + MT_Amount +
                ", MT_Total=" + MT_Total +
                ", MemberID=" + MemberID +
                ", EM_ID=" + EM_ID +
                '}';
    }
}
