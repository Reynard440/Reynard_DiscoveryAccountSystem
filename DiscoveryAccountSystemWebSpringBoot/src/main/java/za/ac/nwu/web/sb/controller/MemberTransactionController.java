package za.ac.nwu.web.sb.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.nwu.domain.dto.MemberTransactionDto;
import za.ac.nwu.domain.service.DiscoveryAccountSystemResponse;
import za.ac.nwu.logic.flow.NewTransactionService;
import za.ac.nwu.logic.flow.ViewMemberTransactionService;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path="/v1/c2")
public class MemberTransactionController {
    private final ViewMemberTransactionService memberTransactionService;
    private final NewTransactionService newTransactionService;

    @Autowired
    public MemberTransactionController(ViewMemberTransactionService memberTransactionService, NewTransactionService newTransactionService/*, ExchangeMediumService exchangeMediumService*/){
        this.memberTransactionService = memberTransactionService;
        this.newTransactionService = newTransactionService;
    }

    @Transactional
    @PostMapping("/newTransaction")
    @ApiOperation(value = "Create a new transaction for a specific member.", notes = "Creates a new transaction for a member in the DB.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Transaction successfully create", response = DiscoveryAccountSystemResponse.class),
            @ApiResponse(code = 400, message = "Bad Request: could not resolve the creation of the transaction", response = DiscoveryAccountSystemResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = DiscoveryAccountSystemResponse.class)})
    public ResponseEntity<DiscoveryAccountSystemResponse<MemberTransactionDto>> newTransaction(
            @ApiParam(value = "Request body to create a new Transaction", required = true)
            @RequestBody MemberTransactionDto memberTransactionDto) throws SQLException {
        MemberTransactionDto memberTransactionResponse = newTransactionService.addTransactionDto(memberTransactionDto);
        DiscoveryAccountSystemResponse<MemberTransactionDto> response = new DiscoveryAccountSystemResponse<>(true, memberTransactionResponse);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping(path = "/getMemberTransactionByID/{id}")
    @ApiOperation(value = "Fetches a list of a Member's Transaction by its id.", notes = "Fetches a list of Member's Transaction by id from DB.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Found the transaction by id", response = DiscoveryAccountSystemResponse.class),
            @ApiResponse(code = 400, message = "Bad Request: could not resolve the search by id", response = DiscoveryAccountSystemResponse.class),
            @ApiResponse(code = 404, message = "Could not found a transaction by this id", response = DiscoveryAccountSystemResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = DiscoveryAccountSystemResponse.class)})
    public ResponseEntity<DiscoveryAccountSystemResponse<List<MemberTransactionDto>>> getMemberTransactionByID(
            @ApiParam(value = "The id that is unique to each transaction.", example = "1", name = "id", required = true)
            @PathVariable("id") Integer id) throws SQLException {
        List<MemberTransactionDto> memberTransactionResponse = memberTransactionService.getMemberTransactionID(id);
        DiscoveryAccountSystemResponse<List<MemberTransactionDto>> response = new DiscoveryAccountSystemResponse<>(true, memberTransactionResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/getTransactionByIdAndDate/{id}/{date}")
    @ApiOperation(value = "Fetches a Member's Transaction by its id and date specified.", notes = "Fetches Member's Transaction by id and date from DB.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Found the transaction by date and id", response = DiscoveryAccountSystemResponse.class),
            @ApiResponse(code = 400, message = "Bad Request: could not resolve the search by id and date", response = DiscoveryAccountSystemResponse.class),
            @ApiResponse(code = 404, message = "Could not found a transaction by this date or id", response = DiscoveryAccountSystemResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = DiscoveryAccountSystemResponse.class)})
    public ResponseEntity<DiscoveryAccountSystemResponse<List<MemberTransactionDto>>> getTransactionByIDAndDate(
            @ApiParam(value = "The id that is unique to each transaction.", example = "1", name = "id", required = true)
            @PathVariable("id") Integer id,
            @ApiParam(value = "The date of the transaction.", example = "2021-08-31", name = "date", required = true)
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) throws SQLException {
        List<MemberTransactionDto> memberTransactionResponse = memberTransactionService.getTransactionByIdAndDate(id, date);
        DiscoveryAccountSystemResponse<List<MemberTransactionDto>> response = new DiscoveryAccountSystemResponse<>(true, memberTransactionResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
