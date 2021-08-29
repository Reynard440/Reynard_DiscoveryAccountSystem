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
import za.ac.nwu.domain.dto.MemberTransactionDto;
import za.ac.nwu.domain.service.GeneralResponse;
import za.ac.nwu.logic.flow.MemberServiceFlow;
import za.ac.nwu.logic.flow.ViewMemberTransactionService;

import java.util.List;

@RestController
@RequestMapping(path="/v1/c2")
public class MemberTransactionController {

    private final ViewMemberTransactionService memberTransactionService;

    @Autowired
    public MemberTransactionController(ViewMemberTransactionService memberTransactionService){
        this.memberTransactionService = memberTransactionService;
    }

    @GetMapping("/all")
    @ApiOperation(value = "Gets all the Member Transactions.", notes = "Returns a list of member transactions.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Member Transactions returned", response = GeneralResponse.class),
            @ApiResponse(code = 400, message = "Bad Request", response = GeneralResponse.class),
            @ApiResponse(code = 404, message = "Not found", response = GeneralResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = GeneralResponse.class)})
    public ResponseEntity<GeneralResponse<List<MemberTransactionDto>>> getAll(){
        List<MemberTransactionDto> memberTransactions = memberTransactionService.getAllMemberTransaction();
        GeneralResponse<List<MemberTransactionDto>> response = new GeneralResponse<>(true, memberTransactions);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

        /*private final UserService userService;

    @Autowired
    public MemberController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path="/users")
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @PostMapping
    public void addUser(@RequestBody User user){
        userService.addUser(user);
    }

    @DeleteMapping(path = "{userId}")
    public void deleteUser(@PathVariable("userId") Integer id){
        userService.deleteUser(id);
    }

    @PutMapping(path = "{userId}")
    public void updateUser(@PathVariable("userId") Integer id, @RequestParam(required = false) String firstname, @RequestParam(required = false) String lastname){
        userService.updateUser(id, firstname, lastname);
    }*/
}
