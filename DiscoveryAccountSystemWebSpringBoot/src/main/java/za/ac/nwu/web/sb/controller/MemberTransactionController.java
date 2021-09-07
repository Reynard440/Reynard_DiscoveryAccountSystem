package za.ac.nwu.web.sb.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.nwu.domain.dto.MemberTransactionDto;
import za.ac.nwu.domain.service.DiscoveryAccountSystemResponse;
import za.ac.nwu.logic.flow.ExchangeMediumService;
import za.ac.nwu.logic.flow.NewTransactionService;
import za.ac.nwu.logic.flow.ViewMemberTransactionService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path="/v1/c2")
public class MemberTransactionController {
    private final ViewMemberTransactionService memberTransactionService;
    private final NewTransactionService newTransactionService;
    //private final ExchangeMediumService exchangeMediumService;

    @Autowired
    public MemberTransactionController(ViewMemberTransactionService memberTransactionService, NewTransactionService newTransactionService/*, ExchangeMediumService exchangeMediumService*/){
        this.memberTransactionService = memberTransactionService;
        this.newTransactionService = newTransactionService;
        //this.exchangeMediumService = exchangeMediumService;
    }

    @GetMapping("/getAllTransactions")
    @ApiOperation(value = "Gets all the Member Transactions.", notes = "Returns a list of member transactions.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Member Transactions returned", response = DiscoveryAccountSystemResponse.class),
            @ApiResponse(code = 400, message = "Bad Request", response = DiscoveryAccountSystemResponse.class),
            @ApiResponse(code = 404, message = "Not found", response = DiscoveryAccountSystemResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = DiscoveryAccountSystemResponse.class)})
    public ResponseEntity<DiscoveryAccountSystemResponse<List<MemberTransactionDto>>> getAllMemberTransactions(){
        List<MemberTransactionDto> memberTransactions = memberTransactionService.getAllMemberTransaction();
        DiscoveryAccountSystemResponse<List<MemberTransactionDto>> response = new DiscoveryAccountSystemResponse<>(true, memberTransactions);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/newTransaction")
    @ApiOperation(value = "Create a new transaction for a specific member.", notes = "Creates a new transaction for a member in the DB.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Transaction successfully create", response = DiscoveryAccountSystemResponse.class),
            @ApiResponse(code = 400, message = "Bad Request", response = DiscoveryAccountSystemResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = DiscoveryAccountSystemResponse.class)})
    public ResponseEntity<DiscoveryAccountSystemResponse<MemberTransactionDto>> newTransaction(@ApiParam(value = "Request body to create a new Transaction", required = true) @RequestBody MemberTransactionDto memberTransactionDto){
        MemberTransactionDto memberTransactionResponse = newTransactionService.addTransactionDto(memberTransactionDto);
//        if (memberTransactionResponse.getDescription() == "Deposit"){
//            exchangeMediumService.increaseExchangeMediumTotal(id,amount);
//        }else{
//
//        }
        DiscoveryAccountSystemResponse<MemberTransactionDto> response = new DiscoveryAccountSystemResponse<>(true, memberTransactionResponse);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping(path = "/getMemberTransactionByID/{id}")
    @ApiOperation(value = "Fetches a Member's Transaction by its id.", notes = "Fetches Member's Transaction by id from DB.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Goal Found", response = DiscoveryAccountSystemResponse.class),
            @ApiResponse(code = 400, message = "Bad Request", response = DiscoveryAccountSystemResponse.class),
            @ApiResponse(code = 404, message = "Not found", response = DiscoveryAccountSystemResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = DiscoveryAccountSystemResponse.class)})
    public ResponseEntity<DiscoveryAccountSystemResponse<MemberTransactionDto>> getMemberTransactionByID(@ApiParam(value = "The id that is unique to each exchange medium.", example = "1", name = "id", required = true) @PathVariable("id") Integer id){
        MemberTransactionDto memberTransactionResponse = memberTransactionService.getMemberTransactionID(id);
        DiscoveryAccountSystemResponse<MemberTransactionDto> response = new DiscoveryAccountSystemResponse<>(true, memberTransactionResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/getTransactionByIDAndDate/{id}/{date}")
    @ApiOperation(value = "Fetches a Member's Transaction by its id and date specified.", notes = "Fetches Member's Transaction by id and date from DB.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Goal Found", response = DiscoveryAccountSystemResponse.class),
            @ApiResponse(code = 400, message = "Bad Request", response = DiscoveryAccountSystemResponse.class),
            @ApiResponse(code = 404, message = "Not found", response = DiscoveryAccountSystemResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = DiscoveryAccountSystemResponse.class)})
    public ResponseEntity<DiscoveryAccountSystemResponse<MemberTransactionDto>> getTransactionByIDAndDate(@ApiParam(value = "The id that is unique to each exchange medium.", example = "1", name = "id", required = true) @PathVariable("id") Integer id, @ApiParam(value = "The date of the transaction.", example = "2021-08-31", name = "date", required = true) @PathVariable("date") LocalDate date){
        MemberTransactionDto memberTransactionResponse = memberTransactionService.getTransactionByIdAndDate(id, date);
        DiscoveryAccountSystemResponse<MemberTransactionDto> response = new DiscoveryAccountSystemResponse<>(true, memberTransactionResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
