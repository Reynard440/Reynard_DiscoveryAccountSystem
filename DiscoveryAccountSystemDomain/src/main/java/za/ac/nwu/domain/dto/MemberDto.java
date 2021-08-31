package za.ac.nwu.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import za.ac.nwu.domain.persistence.Member;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;

@ApiModel(value = "MemberDto", description = "A DTO that represents the Member")
public class MemberDto implements Serializable {

    private Integer Mem_ID;

    private String Mem_FirstName;

    private String Mem_LastName;

    private String Mem_Email;

    private String Mem_Phone_Number;

    public MemberDto(Optional<Member> member) {
    }

    public MemberDto(Integer mem_ID, String mem_FirstName, String mem_LastName, String mem_Email, String mem_Phone_Number) {
        this.Mem_ID = mem_ID;
        this.Mem_FirstName = mem_FirstName;
        this.Mem_LastName = mem_LastName;
        this.Mem_Email = mem_Email;
        this.Mem_Phone_Number = mem_Phone_Number;
    }

    public MemberDto(Member member){
        this.setMem_ID(member.getMemID());
        this.setMem_LastName(member.getMem_LastName());
        this.setMem_FirstName(member.getMem_FirstName());
        this.setMem_Email(member.getMem_Email());
        this.setMem_Phone_Number(member.getMem_Phone_Number());
    }

    @ApiModelProperty(position = 1,
            value = "Member ID",
            name = "ID",
            notes = "This is unique identifier to each member.",
            dataType = "java.lang.Integer",
            example = "1")
    public Integer getMem_ID() {
        return Mem_ID;
    }

    public void setMem_ID(Integer mem_ID) {
        Mem_ID = mem_ID;
    }

    @ApiModelProperty(position = 2,
            value = "Member first name",
            name = "First Name",
            notes = "Might find other members with the same names.",
            dataType = "java.lang.String",
            example = "Reynard")
    public String getMem_FirstName() {
        return Mem_FirstName;
    }

    public void setMem_FirstName(String mem_FirstName) {
        Mem_FirstName = mem_FirstName;
    }

    @ApiModelProperty(position = 3,
            value = "Member last name",
            name = "Last Name",
            notes = "Might find other members with the same last names.",
            dataType = "java.lang.String",
            example = "Engels")
    public String getMem_LastName() {
        return Mem_LastName;
    }

    public void setMem_LastName(String mem_LastName) {
        Mem_LastName = mem_LastName;
    }

    @ApiModelProperty(position = 4,
            value = "Member email",
            name = "Email",
            notes = "This is required to be unique to each member.",
            dataType = "java.lang.String",
            example = "reynardnegels@gmail.com")
    public String getMem_Email() {
        return Mem_Email;
    }

    public void setMem_Email(String mem_Email) {
        Mem_Email = mem_Email;
    }

    @ApiModelProperty(position = 5,
            value = "Member contact phone number",
            name = "Contact phone number",
            notes = "This is also unique to each member.",
            dataType = "java.lang.String",
            example = "0723949955")
    public String getMem_Phone_Number() {
        return Mem_Phone_Number;
    }

    public void setMem_Phone_Number(String mem_Phone_Number) {
        Mem_Phone_Number = mem_Phone_Number;
    }

    @JsonIgnore
    public Member getMember() {
        return new Member(getMem_ID(), getMem_FirstName(), getMem_LastName(), getMem_Email(), getMem_Phone_Number());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberDto memberDto = (MemberDto) o;
        return Objects.equals(Mem_ID, memberDto.Mem_ID) && Objects.equals(Mem_FirstName, memberDto.Mem_FirstName) && Objects.equals(Mem_LastName, memberDto.Mem_LastName) && Objects.equals(Mem_Email, memberDto.Mem_Email) && Objects.equals(Mem_Phone_Number, memberDto.Mem_Phone_Number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Mem_ID, Mem_FirstName, Mem_LastName, Mem_Email, Mem_Phone_Number);
    }

    @Override
    public String toString() {
        return "MemberDto{" +
                "Mem_ID=" + Mem_ID +
                ", Mem_FirstName='" + Mem_FirstName + '\'' +
                ", Mem_LastName='" + Mem_LastName + '\'' +
                ", Mem_Email='" + Mem_Email + '\'' +
                ", Mem_Phone_Number='" + Mem_Phone_Number + '\'' +
                '}';
    }
}
