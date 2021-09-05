package za.ac.nwu.domain.persistence;

//import lombok.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

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
@Table(name = "Member")
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="MemID", scope = Member_Transaction.class)
public class Member implements Serializable {
    private static final long serialVersionUID = -6965549404196897257L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MemID")
    private Integer MemID;

    @Column(name = "Mem_First_Name")
    private String Mem_FirstName;

    @Column(name = "Mem_Last_Name")
    private String Mem_LastName;

    @Column(name = "Mem_Email")
    private String Mem_Email;

    @Column(name = "Mem_Phone_Number")
    private String Mem_Phone_Number;

    @OneToMany(targetEntity = Member_Transaction.class, fetch = FetchType.LAZY, mappedBy = "MemberID", orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<Member_Transaction> member_transactions;

    @OneToMany(targetEntity = Exchange_Medium.class, fetch = FetchType.LAZY, mappedBy = "MEM_ID", orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<Exchange_Medium> exchange_medium;

    public Member() {
    }

    public Member(String mem_FirstName, String mem_LastName, String mem_Email, String mem_Phone_Number, Set<Exchange_Medium> exchange_medium) {
        this.Mem_FirstName = mem_FirstName;
        this.Mem_Phone_Number = mem_Phone_Number;
        this.Mem_LastName = mem_LastName;
        this.Mem_Email = mem_Email;
        this.exchange_medium = exchange_medium;
    }

    public Member(Integer memID, String mem_FirstName, String mem_LastName, String mem_Email, String mem_Phone_Number, Set<Exchange_Medium> exchange_medium) {
        this.MemID = memID;
        this.Mem_FirstName = mem_FirstName;
        this.Mem_LastName = mem_LastName;
        this.Mem_Email = mem_Email;
        this.Mem_Phone_Number = mem_Phone_Number;
        this.exchange_medium = exchange_medium;
    }

    public Member(String mem_firstName, String mem_lastName, String mem_email, String mem_phone_number) {
        this.Mem_FirstName = mem_firstName;
        this.Mem_LastName = mem_lastName;
        this.Mem_Email = mem_email;
        this.Mem_Phone_Number = mem_phone_number;
    }

    public Member(Integer memID, String mem_FirstName, String mem_LastName) {
        MemID = memID;
        this.Mem_FirstName = mem_FirstName;
        this.Mem_LastName = mem_LastName;
    }

    public Integer getMemID() {
        return MemID;
    }

    public void setMemID(Integer memID) {
        MemID = memID;
    }

    public Set<Member_Transaction> getMember_transactions() {
        return member_transactions;
    }

    public void setMember_transactions(Set<Member_Transaction> member_transactions) {
        this.member_transactions = member_transactions;
    }

    public String getMem_FirstName() {
        return Mem_FirstName;
    }

    public void setMem_FirstName(String mem_FirstName) {
        Mem_FirstName = mem_FirstName;
    }

    public String getMem_LastName() {
        return Mem_LastName;
    }

    public void setMem_LastName(String mem_LastName) {
        Mem_LastName = mem_LastName;
    }

    public String getMem_Email() {
        return Mem_Email;
    }

    public void setMem_Email(String mem_Email) {
        Mem_Email = mem_Email;
    }

    public String getMem_Phone_Number() {
        return Mem_Phone_Number;
    }

    public void setMem_Phone_Number(String mem_Phone_Number) {
        Mem_Phone_Number = mem_Phone_Number;
    }

    public Set<Exchange_Medium> getExchange_medium() {
        return exchange_medium;
    }

    public void setExchange_medium(Set<Exchange_Medium> exchange_medium) {
        this.exchange_medium = exchange_medium;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return Objects.equals(MemID, member.MemID) && Objects.equals(Mem_FirstName, member.Mem_FirstName) && Objects.equals(Mem_LastName, member.Mem_LastName) && Objects.equals(Mem_Email, member.Mem_Email) && Objects.equals(Mem_Phone_Number, member.Mem_Phone_Number) && Objects.equals(member_transactions, member.member_transactions) && Objects.equals(exchange_medium, member.exchange_medium);
    }

    @Override
    public int hashCode() {
        return Objects.hash(MemID, Mem_FirstName, Mem_LastName, Mem_Email, Mem_Phone_Number, member_transactions, exchange_medium);
    }

    @Override
    public String toString() {
        return "Member{" +
                "MemID=" + MemID +
                ", Mem_FirstName='" + Mem_FirstName + '\'' +
                ", Mem_LastName='" + Mem_LastName + '\'' +
                ", Mem_Email='" + Mem_Email + '\'' +
                ", Mem_Phone_Number='" + Mem_Phone_Number + '\'' +
                ", member_transactions=" + member_transactions +
                ", exchange_medium=" + exchange_medium +
                '}';
    }
}
