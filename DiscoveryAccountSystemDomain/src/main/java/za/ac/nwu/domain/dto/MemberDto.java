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

    private String FirstName;

    private String LastName;

    private String Email;

    private String PhoneNumber;

    //private Set<Exchange_Medium> exchangeMedium;

    public MemberDto() {
    }

    public MemberDto(Optional<Member> member) {
    }

    public MemberDto(String firstName, String lastName, String email, String phoneNumber) {
        this.FirstName = firstName;
        this.LastName = lastName;
        this.Email = email;
        this.PhoneNumber = phoneNumber;
    }

    public MemberDto(Member member){
        this.setLastName(member.getLastName());
        this.setFirstName(member.getFirstName());
        this.setEmail(member.getEmail());
        this.setPhoneNumber(member.getPhoneNumber());
        //this.setExchangeMedium(member.getExchangeMedium());
    }

    @ApiModelProperty(position = 1,
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

    @ApiModelProperty(position = 2,
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

    @ApiModelProperty(position = 3,
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

    @ApiModelProperty(position = 4,
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

//    @ApiModelProperty(position = 5,
//            value = "Exchange Medium types owned by the member",
//            name = "Exchange Medium",
//            notes = "This is used as the foreign key .",
//            dataType = "java.lang.Set",
//            example = "{}")
//    public Set<Exchange_Medium> getExchangeMedium() {
//        return exchangeMedium;
//    }
//
//    public void setExchangeMedium(Set<Exchange_Medium> exchangeMedium) {
//        this.exchangeMedium = exchangeMedium;
//    }

    @JsonIgnore
    public Member getMember() {
        return new Member(getFirstName(), getLastName(), getEmail(), getPhoneNumber()/*, getExchangeMedium()*/);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberDto memberDto = (MemberDto) o;
        return FirstName.equals(memberDto.FirstName) && LastName.equals(memberDto.LastName) && Email.equals(memberDto.Email) && PhoneNumber.equals(memberDto.PhoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(FirstName, LastName, Email, PhoneNumber);
    }
}
