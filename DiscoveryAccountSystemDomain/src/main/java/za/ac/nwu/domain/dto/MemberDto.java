package za.ac.nwu.domain.dto;

import za.ac.nwu.domain.persistence.Member;

import java.io.Serializable;

public class MemberDto implements Serializable {

    private String Mem_FirstName;

    private String Mem_LastName;

    private String Mem_Email;

    private String Mem_Phone_Number;

    public MemberDto() {
    }

    public MemberDto(String mem_FirstName, String mem_LastName, String mem_Email, String mem_Phone_Number) {
        Mem_FirstName = mem_FirstName;
        Mem_LastName = mem_LastName;
        Mem_Email = mem_Email;
        Mem_Phone_Number = mem_Phone_Number;
    }

    public MemberDto(Member member){
        this.setMem_LastName(member.getMem_LastName());
        this.setMem_FirstName(member.getMem_FirstName());
        this.setMem_Email(member.getMem_Email());
        this.setMem_Phone_Number(member.getMem_Phone_Number());
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
}
