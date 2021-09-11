package za.ac.nwu.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import za.ac.nwu.domain.persistence.Exchange_Medium;
import za.ac.nwu.domain.persistence.Member;
import za.ac.nwu.domain.persistence.Member_Transaction;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@ApiModel(value = "ExchangeMediumDto", description = "A DTO that represents the types of accounts")
public class ExchangeMediumDto implements Serializable {
    private static final long serialVersionUID = 5108461587671472664L;

    private Integer exchangeMediumID;

    private String Type;

    private String Description;

    private double Balance;

    private LocalDate Date;

    private MemberDto MemID;

    @ApiModelProperty(position = 1,
            value = "Type of exchange medium",
            name = "Type",
            notes = "This field stores the name of the exchanges medium.",
            dataType = "java.lang.String",
            example = "MILES")
    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    @ApiModelProperty(position = 2,
            value = "Description of exchange medium",
            name = "Description",
            notes = "This field stores a short description about the type of exchange medium.",
            dataType = "java.lang.String",
            example = "This is a new Discovery currency type that keeps track of all your MILES")
    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    @ApiModelProperty(position = 3,
            value = "Balance of Exchange Medium",
            name = "Balance",
            notes = "This field stores balance of each member's various Exchange_Mediums.",
            dataType = "java.lang.Double",
            example = "40")
    public double getBalance() {
        return Balance;
    }

    public void setBalance(double balance) {
        Balance = balance;
    }

    @ApiModelProperty(position = 4,
            value = "The date the specific exchange medium type was created for a member.",
            name = "Creation date",
            notes = "This field keeps track of when the type was initiated by a member.",
            dataType = "java.lang.LocalDate",
            example = "2021-08-05")
    public LocalDate getDate() {
        return Date;
    }

    public void setDate(LocalDate date) {
        Date = date;
    }

    @ApiModelProperty(position = 5,
            value = "The referenced id of the member of the exchange medium type.",
            name = "Member id",
            notes = "This field keeps track of the member of the exchange medium type.",
            dataType = "java.lang.Integer",
            example = "1")
    public MemberDto getMemID() {
        return MemID;
    }

    public void setMemID(MemberDto memID) {
        MemID = memID;
    }

    public Integer getExchangeMediumID() {
        return exchangeMediumID;
    }

    public void setExchangeMediumID(Integer exchangeMediumID) {
        this.exchangeMediumID = exchangeMediumID;
    }

    public ExchangeMediumDto() {
    }

    public ExchangeMediumDto(Integer exchangeMediumID, String type, String description, double balance, LocalDate date) {
        this.exchangeMediumID = exchangeMediumID;
        this.Type = type;
        this.Description = description;
        this.Balance = balance;
        this.Date = date;
    }

    public ExchangeMediumDto(Exchange_Medium exchange_medium){
        this.exchangeMediumID = exchange_medium.getEmId();
        this.Type = exchange_medium.getType();
        this.Description = exchange_medium.getDescription();
        this.Balance = exchange_medium.getBalance();
        this.Date = exchange_medium.getDate();
        if (null != exchange_medium.getMemID()) {
            this.MemID = new MemberDto(exchange_medium.getMemID());
        }
    }

    @JsonIgnore
    public Exchange_Medium buildExchangeMedium(Member member) {
        return new Exchange_Medium(this.getType(), this.getDescription(), this.getBalance(), this.getDate(),
                member/*, this.getMemberTransactions()*/);
    }

    @JsonIgnore
    public Exchange_Medium buildExchangeMedium() {
        return new Exchange_Medium(this.getType(), this.getDescription(), this.getBalance(), this.getDate());
    }

    @JsonIgnore
    public Exchange_Medium getExchangeMedium() {
        return new Exchange_Medium(getType(), getDescription(), getBalance(), getDate()/*, getMemID(), getMemberTransactions()*/);
    }

    @JsonIgnore
    public Exchange_Medium getExchangeMediumId() {
        return new Exchange_Medium(getExchangeMediumID());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExchangeMediumDto that = (ExchangeMediumDto) o;
        return Double.compare(that.Balance, Balance) == 0 && Objects.equals(Type, that.Type) && Objects.equals(Description, that.Description) && Objects.equals(Date, that.Date) && Objects.equals(MemID, that.MemID);/* && Objects.equals(memberTransactions, that.memberTransactions);*/
    }

    @Override
    public int hashCode() {
        return Objects.hash(Type, Description, Balance, Date, MemID/*, memberTransactions*/);
    }
}
