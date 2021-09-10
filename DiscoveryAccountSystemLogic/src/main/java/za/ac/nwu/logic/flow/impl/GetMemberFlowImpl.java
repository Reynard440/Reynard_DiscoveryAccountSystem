package za.ac.nwu.logic.flow.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import za.ac.nwu.domain.dto.MemberDto;
import za.ac.nwu.domain.dto.MemberTransactionDto;
import za.ac.nwu.domain.persistence.Member;
import za.ac.nwu.domain.persistence.Member_Transaction;
import za.ac.nwu.logic.flow.MemberServiceFlow;
import za.ac.nwu.translator.MemberTranslator;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Component
public class GetMemberFlowImpl implements MemberServiceFlow {

    private final MemberTranslator memberTranslator;

    @Autowired
    public GetMemberFlowImpl(MemberTranslator memberTranslator){
        this.memberTranslator = memberTranslator;
    }

    @Override
    public List<MemberDto> getMembers(){
        List<MemberDto> memberDtos = new ArrayList<>();
        for (Member member : memberTranslator.getAllMembers()) {
            memberDtos.add(new MemberDto(member));
        }
        return memberDtos;
    }

    @Override
    public MemberDto getMemberByEmail(String email) {
        return new MemberDto(memberTranslator.getMemberByEmail(email));
    }

    @Override
    public MemberDto getMemberById(Integer id) {
        return new MemberDto(memberTranslator.getOneMember(id));
    }

    public boolean methodToTest(){
        return true;
    }

    /*public void addUser(Member member) {
        Optional<Member> userOptional = memRepo.findMemberByEmail(member.getMem_Email());
        if(userOptional.isPresent()){
            throw new IllegalStateException("email already in use!");
        }
        memRepo.save(member);
    }

    public void deleteUser(Integer id) {
        boolean user = memRepo.existsById(id);
        if(!user){
            throw new IllegalStateException("user with id " + id + " does not exist!");
        }
        memRepo.deleteById(id);
    }

    @Transactional
    public void updateUser(Integer id, String firstname, String lastname) {
        Member member = memRepo.findById(id).orElseThrow(() -> new IllegalStateException("user with id " + id + " does not exist!"));
        if (firstname != null && (firstname.length() > 0) && !Objects.equals(member.getMem_FirstName(), firstname)) {
            member.setMem_FirstName(firstname);
        }

        if (lastname != null && lastname.length() > 0 && !Objects.equals(member.getMem_LastName(), lastname)){
            member.setMem_LastName(lastname);
        }
    }*/
}
