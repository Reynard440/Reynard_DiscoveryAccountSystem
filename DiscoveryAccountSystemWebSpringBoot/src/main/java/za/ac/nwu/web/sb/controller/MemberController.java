package za.ac.nwu.web.sb.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.ac.nwu.domain.dto.MemberDto;
import za.ac.nwu.domain.service.GeneralResponse;
import za.ac.nwu.logic.flow.MemberServiceFlow;

import java.util.List;

@RestController
@RequestMapping(path="/discovery-account-system/mvc")
public class MemberController {
    private final MemberServiceFlow memberServiceFlow;

    @Autowired
    public MemberController(MemberServiceFlow memberServiceFlow){
        this.memberServiceFlow = memberServiceFlow;
    }

    @GetMapping("/all")
    @ApiOperation(value = "Gets all the configure Members.", notes = "Returns a list of members.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Members returned", response = GeneralResponse.class),
            @ApiResponse(code = 400, message = "Bad Request", response = GeneralResponse.class),
            @ApiResponse(code = 404, message = "Not found", response = GeneralResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = GeneralResponse.class)})
    public ResponseEntity<GeneralResponse<List<MemberDto>>> getAll(){
        List<MemberDto> members = memberServiceFlow.getMembers();
        GeneralResponse<List<MemberDto>> response = new GeneralResponse<>(true, members);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /*public GeneralResponse<String> getAll(){
        return new GeneralResponse<String>(true, "No types found");
    }*/

   /*@GetMapping("/all")
   public String getAll(){return "Hello World!";}*/

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
