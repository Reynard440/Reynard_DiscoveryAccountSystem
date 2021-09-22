package za.ac.nwu.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import za.ac.nwu.domain.persistence.Exchange_Medium;
import za.ac.nwu.domain.persistence.Member;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@ApiModel(value = "MemberDto", description = "A DTO that represents the Member")
public class MemberDto implements Serializable {
    private static final long serialVersionUID = -8972933183152228911L;

    private Integer memId;

    private String FirstName;

    private String LastName;

    private String Email;

    private String PhoneNumber;

    @ApiModelProperty(position = 1,
            value = "Member id",
            name = "Member ID",
            notes = "Unique to each member.",
            dataType = "java.lang.Integer",
            example = "1")
    public Integer getMemId() {
        return memId;
    }

    public void setMemId(Integer memId) {
        this.memId = memId;
    }

    @ApiModelProperty(position = 2,
            value = "Member first name",
            name = "First Name",
            notes = "Might find other members with the same names.",
            dataType = "java.lang.String",
            example = "Reynard")
    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    @ApiModelProperty(position = 3,
            value = "Member last name",
            name = "Last Name",
            notes = "Might find other members with the same last names.",
            dataType = "java.lang.String",
            example = "Engels")
    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    @ApiModelProperty(position = 4,
            value = "Member email",
            name = "Email",
            notes = "This is required to be unique to each member.",
            dataType = "java.lang.String",
            example = "reynardnegels@gmail.com")
    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    @ApiModelProperty(position = 5,
            value = "Member contact phone number",
            name = "Contact phone number",
            notes = "This is also unique to each member.",
            dataType = "java.lang.String",
            example = "0723949955")
    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public MemberDto() {
    }

    public MemberDto(Integer memId) {
        this.memId = memId;
    }

    public MemberDto(String firstName, String lastName, String email, String phoneNumber) {
        this.FirstName = firstName;
        this.LastName = lastName;
        this.Email = email;
        this.PhoneNumber = phoneNumber;
    }

    public MemberDto(Integer memId, String email, String phoneNumber, String firstName, String lastName) {
        this.memId = memId;
        this.Email = email;
        this.PhoneNumber = phoneNumber;
        this.FirstName = firstName;
        this.LastName = lastName;
    }

    public MemberDto(Member member){
        this.memId = member.getId();
        this.FirstName = member.getFirstName();
        this.LastName = member.getLastName();
        this.Email = member.getEmail();
        this.PhoneNumber = member.getPhoneNumber();
    }

    @JsonIgnore
    public Member buildMember(Set<Exchange_Medium> exchange_medium) {
        return new Member(this.getFirstName(), this.getLastName(), this.getEmail(),
                this.getPhoneNumber(), exchange_medium);
    }

    @JsonIgnore
    public Member buildMember() {
        return new Member(this.getFirstName(), this.getLastName(), this.getEmail(),
                this.getPhoneNumber());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberDto memberDto = (MemberDto) o;
        return Objects.equals(memId, memberDto.memId) && Objects.equals(FirstName, memberDto.FirstName) && Objects.equals(LastName, memberDto.LastName) && Objects.equals(Email, memberDto.Email) && Objects.equals(PhoneNumber, memberDto.PhoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memId, FirstName, LastName, Email, PhoneNumber);
    }

    @Override
    public String toString() {
        return "MemberDto{" +
                "memId=" + memId +
                ", FirstName='" + FirstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", Email='" + Email + '\'' +
                ", PhoneNumber='" + PhoneNumber + '\'' +
                '}';
    }
}
