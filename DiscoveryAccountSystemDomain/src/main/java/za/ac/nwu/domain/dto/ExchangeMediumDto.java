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
import java.util.Set;

@ApiModel(value = "ExchangeMediumDto", description = "A DTO that represents the types of accounts")
public class ExchangeMediumDto implements Serializable {
    private static final long serialVersionUID = 5108461587671472664L;

    private String EM_Type;

    private String EM_Description;

    private double EM_Balance;

    private LocalDate EM_Date;

    private Member MEM_ID;

    @ApiModelProperty(position = 1,
            value = "Type of exchange medium",
            name = "Type",
            notes = "This field stores the name of the exchanges medium.",
            dataType = "java.lang.String",
            example = "MILES")
    public String getEM_Type() {
        return EM_Type;
    }

    public void setEM_Type(String EM_Type) {
        this.EM_Type = EM_Type;
    }

    @ApiModelProperty(position = 2,
            value = "Description of exchange medium",
            name = "Description",
            notes = "This field stores a short description about the type of exchange medium.",
            dataType = "java.lang.String",
            example = "This is a new Discovery currency type that keeps track of all your MILES")
    public String getEM_Description() {
        return EM_Description;
    }

    public void setEM_Description(String EM_Description) {
        this.EM_Description = EM_Description;
    }

    @ApiModelProperty(position = 3,
            value = "Balance of Exchange Medium",
            name = "Balance",
            notes = "This field stores balance of each member's various Exchange_Mediums.",
            dataType = "java.lang.Double",
            example = "40")
    public double getEM_Balance() {
        return EM_Balance;
    }

    public void setEM_Balance(double EM_Balance) {
        this.EM_Balance = EM_Balance;
    }

    @ApiModelProperty(position = 4,
            value = "The referenced id of the member of the exchange medium type.",
            name = "Member id",
            notes = "This field keeps track of the member of the exchange medium type.",
            dataType = "java.lang.Integer",
            example = "1")
    public Member getMEM_ID() {
        return MEM_ID;
    }

    public void setMEM_ID(Member MEM_ID) {
        this.MEM_ID = MEM_ID;
    }

    @ApiModelProperty(position = 5,
            value = "The date the specific exchange medium type was created for a member.",
            name = "Creation date",
            notes = "This field keeps track of when the type was initiated by a member.",
            dataType = "java.lang.LocalDate",
            example = "2021-08-05")
    public LocalDate getEM_Date() {
        return EM_Date;
    }

    public void setEM_Date(LocalDate EM_Date) {
        this.EM_Date = EM_Date;
    }

    public ExchangeMediumDto() {
    }

    public ExchangeMediumDto(String EM_Type, String EM_Description, double EM_Balance, LocalDate EM_Date, Member MEM_ID) {
        this.EM_Type = EM_Type;
        this.EM_Description = EM_Description;
        this.EM_Balance = EM_Balance;
        this.EM_Date = EM_Date;
        this.MEM_ID = MEM_ID;
    }

    public ExchangeMediumDto(Exchange_Medium exchange_medium){
        this.setEM_Type(exchange_medium.getEM_Type());
        this.setEM_Description(exchange_medium.getEM_Description());
        this.setEM_Balance(exchange_medium.getEM_Balance());
        this.setEM_Date(exchange_medium.getEM_Date());
        this.setMEM_ID(exchange_medium.getMEM_ID());
    }

    @JsonIgnore
    public Exchange_Medium getExchangeMedium() {
        return new Exchange_Medium(getEM_Type(), getEM_Description(), getEM_Balance(), getEM_Date(), getMEM_ID());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExchangeMediumDto that = (ExchangeMediumDto) o;
        return Double.compare(that.EM_Balance, EM_Balance) == 0 && Objects.equals(EM_Type, that.EM_Type) && Objects.equals(EM_Description, that.EM_Description) && Objects.equals(EM_Date, that.EM_Date) && Objects.equals(MEM_ID, that.MEM_ID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(EM_Type, EM_Description, EM_Balance, EM_Date, MEM_ID);
    }

    @Override
    public String toString() {
        return "ExchangeMediumDto{" +
                "EM_Type='" + EM_Type + '\'' +
                ", EM_Description='" + EM_Description + '\'' +
                ", EM_Balance=" + EM_Balance +
                ", EM_Date=" + EM_Date +
                ", MEM_ID=" + MEM_ID +
                '}';
    }
}
