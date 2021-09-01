package za.ac.nwu.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import za.ac.nwu.domain.persistence.Exchange_Medium;
import za.ac.nwu.domain.persistence.Member;

import java.io.Serializable;
import java.util.Objects;

@ApiModel(value = "ExchangeMediumDto", description = "A DTO that represents the types of accounts")
public class ExchangeMediumDto implements Serializable {
    private static final long serialVersionUID = 5108461587671472664L;

    private Integer EM_ID;

    private String EM_Type;

    private String EM_Description;

    private double EM_Balance;

    private Member MEM_ID;

    @ApiModelProperty(position = 1,
            value = "ID of a exchange medium",
            name = "Type",
            notes = "This field uniquely identifies each exchange medium type.",
            dataType = "java.lang.Integer",
            example = "1")
    public Integer getEM_ID() {
        return EM_ID;
    }

    public void setEM_ID(Integer EM_ID) {
        this.EM_ID = EM_ID;
    }

    @ApiModelProperty(position = 2,
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

    @ApiModelProperty(position = 3,
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

    @ApiModelProperty(position = 4,
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

    @ApiModelProperty(position = 5,
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

    public ExchangeMediumDto() {
    }

    public ExchangeMediumDto(Integer EM_ID, String EM_Type, String EM_Description, double EM_Balance, Member MEM_ID) {
        this.EM_ID = EM_ID;
        this.EM_Type = EM_Type;
        this.EM_Description = EM_Description;
        this.EM_Balance = EM_Balance;
        this.MEM_ID = MEM_ID;
    }

    public ExchangeMediumDto(String EM_Type, String EM_Description, double EM_Balance, Member MEM_ID) {
        this.EM_Type = EM_Type;
        this.EM_Description = EM_Description;
        this.EM_Balance = EM_Balance;
        this.MEM_ID = MEM_ID;
    }

    public ExchangeMediumDto(Exchange_Medium exchange_medium){
        this.setEM_ID(exchange_medium.getEM_ID());
        this.setEM_Type(exchange_medium.getEM_Type());
        this.setEM_Description(exchange_medium.getEM_Description());
        this.setEM_Balance(exchange_medium.getEM_Balance());
        //this.setMEM_ID(exchange_medium.getMEM_ID());
    }

    @JsonIgnore
    public Exchange_Medium getExchangeMedium() {
        return new Exchange_Medium(getEM_ID() ,getEM_Type(), getEM_Description(), getEM_Balance(), getMEM_ID());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExchangeMediumDto that = (ExchangeMediumDto) o;
        return Double.compare(that.EM_Balance, EM_Balance) == 0 && Objects.equals(EM_ID, that.EM_ID) && Objects.equals(EM_Type, that.EM_Type) && Objects.equals(EM_Description, that.EM_Description) && Objects.equals(MEM_ID, that.MEM_ID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(EM_ID, EM_Type, EM_Description, EM_Balance, MEM_ID);
    }

    @Override
    public String toString() {
        return "ExchangeMediumDto{" +
                "EM_ID=" + EM_ID +
                ", EM_Type='" + EM_Type + '\'' +
                ", EM_Description='" + EM_Description + '\'' +
                ", EM_Balance=" + EM_Balance +
                ", MEM_ID=" + MEM_ID +
                '}';
    }
}
