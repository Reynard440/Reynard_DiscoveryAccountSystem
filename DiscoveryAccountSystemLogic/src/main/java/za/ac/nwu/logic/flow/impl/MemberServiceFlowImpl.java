package za.ac.nwu.logic.flow.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.domain.dto.MemberDto;
import za.ac.nwu.logic.flow.MemberServiceFlow;
import za.ac.nwu.repo.persistence.MemberRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class MemberServiceFlowImpl implements MemberServiceFlow {
    private final MemberRepository memRepo;

    @Autowired
    public MemberServiceFlowImpl(MemberRepository memRepo){
        this.memRepo = memRepo;
    }

    @Override
    public List<MemberDto> getMembers(){
        List<MemberDto> memberDto = new ArrayList<>();
        memberDto.add(new MemberDto(
                "kevin",
                "van staden",
                "kvs@gmail.com",
                "0723949955",
                0

        ));
        return memberDto;
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
