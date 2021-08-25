package za.ac.nwu.web.sb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/*import za.ac.nwu.domain.dto.MemberDto;
import za.ac.nwu.logic.flow.MemberServiceFlow;*/

import java.util.List;

@RestController
@RequestMapping(path="/discovery-account-system/mvc/c1")
public class MemberController {
    /*@GetMapping("/all")
    public GeneralResponse<String> getAll(){
        return new GeneralResponse<String>(true, "No types found");
    }*/

   @GetMapping("/all")
   public String getAll(){return "Hello World!";}

    /*private final MemberServiceFlow memberServiceFlow;

    @Autowired
    public MemberController(MemberServiceFlow memberServiceFlow) {
        this.memberServiceFlow = memberServiceFlow;
    }

    @GetMapping(path="/members")
    public List<MemberDto> getMembers(){
        return memberServiceFlow.getMembers();
    }

    /*@PostMapping(path = "{userId}")
    public void addUser(@RequestBody Member member){
        memberService.addUser((za.ac.nwu.domain.persistence.Member) member);
    }

    @DeleteMapping(path = "{userId}")
    public void deleteUser(@PathVariable("userId") Integer id){
        memberService.deleteUser(id);
    }

    @PutMapping(path = "{userId}")
    public void updateUser(@PathVariable("userId") Integer id, @RequestParam(required = false) String firstname, @RequestParam(required = false) String lastname){
        memberService.updateUser(id, firstname, lastname);
    }*/
}
