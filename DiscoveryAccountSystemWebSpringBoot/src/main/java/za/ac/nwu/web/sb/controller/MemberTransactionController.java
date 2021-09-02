package za.ac.nwu.web.sb.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.nwu.domain.dto.MemberTransactionDto;
import za.ac.nwu.domain.service.DiscoveryAccountSystemResponse;
import za.ac.nwu.logic.flow.NewTransactionService;
import za.ac.nwu.logic.flow.ViewMemberTransactionService;

import java.util.List;

@RestController
@RequestMapping(path="/v1/c2")
public class MemberTransactionController {

    private final ViewMemberTransactionService memberTransactionService;
    private final NewTransactionService newTransactionService;

    @Autowired
    public MemberTransactionController(ViewMemberTransactionService memberTransactionService, NewTransactionService newTransactionService){
        this.memberTransactionService = memberTransactionService;
        this.newTransactionService = newTransactionService;
    }

    @GetMapping("/all")
    @ApiOperation(value = "Gets all the Member Transactions.", notes = "Returns a list of member transactions.")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "Member Transactions returned", response = DiscoveryAccountSystemResponse.class),
            @io.swagger.annotations.ApiResponse(code = 400, message = "Bad Request", response = DiscoveryAccountSystemResponse.class),
            @io.swagger.annotations.ApiResponse(code = 404, message = "Not found", response = DiscoveryAccountSystemResponse.class),
            @io.swagger.annotations.ApiResponse(code = 500, message = "Internal Server Error", response = DiscoveryAccountSystemResponse.class)})
    public ResponseEntity<DiscoveryAccountSystemResponse<List<MemberTransactionDto>>> getAll(){
        List<MemberTransactionDto> memberTransactions = memberTransactionService.getAllMemberTransaction();
        DiscoveryAccountSystemResponse<List<MemberTransactionDto>> response = new DiscoveryAccountSystemResponse<>(true, memberTransactions);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("")
    @ApiOperation(value = "Create a new transaction for a specific member.", notes = "Creates a new transaction for a member in the DB.")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "Transaction successfully create", response = DiscoveryAccountSystemResponse.class),
            @io.swagger.annotations.ApiResponse(code = 400, message = "Bad Request", response = DiscoveryAccountSystemResponse.class),
            @io.swagger.annotations.ApiResponse(code = 500, message = "Internal Server Error", response = DiscoveryAccountSystemResponse.class)})
    public ResponseEntity<DiscoveryAccountSystemResponse<MemberTransactionDto>> newTransaction(@ApiParam(value = "Request body to create a new Transaction", required = true) @RequestBody MemberTransactionDto memberTransactionDto){
        MemberTransactionDto memberTransactionResponse = newTransactionService.addTransactionDto(memberTransactionDto);
        DiscoveryAccountSystemResponse<MemberTransactionDto> response = new DiscoveryAccountSystemResponse<>(true, memberTransactionResponse);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping(path = "/getMemberTransactionByID/{id}")
    @ApiOperation(value = "Fetches a Member's Transaction by its id.", notes = "Fetches Member's Transaction by id from DB.")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "Goal Found", response = DiscoveryAccountSystemResponse.class),
            @io.swagger.annotations.ApiResponse(code = 400, message = "Bad Request", response = DiscoveryAccountSystemResponse.class),
            @io.swagger.annotations.ApiResponse(code = 404, message = "Not found", response = DiscoveryAccountSystemResponse.class),
            @io.swagger.annotations.ApiResponse(code = 500, message = "Internal Server Error", response = DiscoveryAccountSystemResponse.class)})
    public ResponseEntity<DiscoveryAccountSystemResponse<MemberTransactionDto>> getMemberTransactionByID(@ApiParam(value = "The id that is unique to each exchange medium.", example = "1", name = "id", required = true) @PathVariable("id") Integer id){
        MemberTransactionDto memberTransactionResponse = memberTransactionService.getMemberTransactionID(id);
        DiscoveryAccountSystemResponse<MemberTransactionDto> response = new DiscoveryAccountSystemResponse<>(true, memberTransactionResponse);
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
