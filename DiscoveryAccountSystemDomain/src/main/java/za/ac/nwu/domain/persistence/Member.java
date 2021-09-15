package za.ac.nwu.domain.persistence;

//import lombok.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import za.ac.nwu.domain.dto.ExchangeMediumDto;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/*@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@NonNull*/
@Entity
@Table(name = "Member", schema = "discoveryDB")
public class Member implements Serializable {
    private static final long serialVersionUID = -6965549404196897257L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MemID")
    private Integer Id;

    @Column(name = "Mem_First_Name")
    private String FirstName;

    @Column(name = "Mem_Last_Name")
    private String LastName;

    @Column(name = "Mem_Email")
    private String Email;

    @Column(name = "Mem_Phone_Number")
    private String PhoneNumber;

    @OneToMany(targetEntity = Exchange_Medium.class, fetch = FetchType.LAZY, mappedBy = "MemID"/*, orphanRemoval = true, cascade = CascadeType.ALL*/)
    @JsonManagedReference
    private Set<Exchange_Medium> exchangeMedium;

    public Member() {
    }

    public Member(Integer id) {
        this.Id = id;
    }

    public Member(String firstName, String lastName, String email, String phoneNumber) {
        this.FirstName = firstName;
        this.LastName = lastName;
        this.Email = email;
        this.PhoneNumber = phoneNumber;
    }

    public Member(String firstName, String lastName, String email, String phoneNumber, Set<Exchange_Medium> exchangeMedium) {
        this.FirstName = firstName;
        this.LastName = lastName;
        this.Email = email;
        this.PhoneNumber = phoneNumber;
        this.exchangeMedium = exchangeMedium;
    }

    public Member(Integer id, String firstName, String lastName, String email, String phoneNumber, Set<Exchange_Medium> exchangeMedium) {
        this.Id = id;
        this.FirstName = firstName;
        this.LastName = lastName;
        this.Email = email;
        this.PhoneNumber = phoneNumber;
        this.exchangeMedium = exchangeMedium;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public Set<Exchange_Medium> getExchangeMedium() {
        return exchangeMedium;
    }

    public void setExchangeMedium(Set<Exchange_Medium> exchangeMedium) {
        this.exchangeMedium = exchangeMedium;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return Objects.equals(Id, member.Id) && Objects.equals(FirstName, member.FirstName) && Objects.equals(LastName, member.LastName) && Objects.equals(Email, member.Email) && Objects.equals(PhoneNumber, member.PhoneNumber) && Objects.equals(exchangeMedium, member.exchangeMedium);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, FirstName, LastName, Email, PhoneNumber, exchangeMedium);
    }
}
