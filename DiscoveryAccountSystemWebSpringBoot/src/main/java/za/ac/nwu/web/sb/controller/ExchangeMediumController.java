package za.ac.nwu.web.sb.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.nwu.domain.dto.ExchangeMediumDto;
import za.ac.nwu.domain.dto.MemberDto;
import za.ac.nwu.domain.dto.MemberTransactionDto;
import za.ac.nwu.domain.service.GeneralResponse;
import za.ac.nwu.logic.flow.ExchangeMediumService;
import za.ac.nwu.logic.flow.ViewExchangeMediumService;
import za.ac.nwu.logic.flow.ViewMemberTransactionService;

import java.util.List;

@RestController
@RequestMapping(path="/v1/c3")
public class ExchangeMediumController {

    private final ViewExchangeMediumService viewExchangeMediumService;
    private final ExchangeMediumService exchangeMediumService;

    @Autowired
    public ExchangeMediumController(ViewExchangeMediumService viewExchangeMediumService, ExchangeMediumService exchangeMediumService){
        this.viewExchangeMediumService = viewExchangeMediumService;
        this.exchangeMediumService = exchangeMediumService;
    }

    @GetMapping("/all")
    @ApiOperation(value = "Gets all the types of Exchange Mediums.", notes = "Returns a list of all the types of Exchange Mediums that exist.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "MeExchange Mediums returned", response = GeneralResponse.class),
            @ApiResponse(code = 400, message = "Bad Request", response = GeneralResponse.class),
            @ApiResponse(code = 404, message = "Not found", response = GeneralResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = GeneralResponse.class)})
    public ResponseEntity<GeneralResponse<List<ExchangeMediumDto>>> getAll(){
        List<ExchangeMediumDto> exchangeMediumDtos = viewExchangeMediumService.getAllExchangeMedium();
        GeneralResponse<List<ExchangeMediumDto>> response = new GeneralResponse<>(true, exchangeMediumDtos);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/getExchangeMediumById/{id}")
    @ApiOperation(value = "Fetches a Exchange Medium by its id.", notes = "Fetches exchange medium by id from DB.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Goal Found", response = GeneralResponse.class),
            @ApiResponse(code = 400, message = "Bad Request", response = GeneralResponse.class),
            @ApiResponse(code = 404, message = "Not found", response = GeneralResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = GeneralResponse.class)})
    public ResponseEntity<GeneralResponse<ExchangeMediumDto>> getExchangeMediumById(@ApiParam(value = "The id that is unique to each exchange medium.", example = "1", name = "id", required = true) @PathVariable("id") Integer id){
        ExchangeMediumDto exchangeMediumResponse = exchangeMediumService.getExchangeMediumByEmID(id);
        GeneralResponse<ExchangeMediumDto> response = new GeneralResponse<>(true, exchangeMediumResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/getExchangeMediumByTypeAndId/{id}/{type}")
    @ApiOperation(value = "Fetches a Exchange Medium by its id and type.", notes = "Fetches exchange medium by id and type from DB.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Goal Found", response = GeneralResponse.class),
            @ApiResponse(code = 400, message = "Bad Request", response = GeneralResponse.class),
            @ApiResponse(code = 404, message = "Not found", response = GeneralResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = GeneralResponse.class)})
    public ResponseEntity<GeneralResponse<ExchangeMediumDto>> getExchangeMediumByTypeAndId(@ApiParam(value = "The id that is unique to each exchange medium.", example = "1", name = "id", required = true) @PathVariable("id") Integer id, @ApiParam(value = "The type of exchange medium.", example = "Rand", name = "type", required = true) @PathVariable("type") String type){
        ExchangeMediumDto exchangeMediumResponse = exchangeMediumService.getExchangeMediumByTypeAndID(type,id);
        GeneralResponse<ExchangeMediumDto> response = new GeneralResponse<>(true, exchangeMediumResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(path = "/increaseExchangeMediumTotal/{id}/{amount}")
    @ApiOperation(value = "Fetches a Exchange Medium by its id and amount to increase the type with.", notes = "Fetches exchange medium by id from the DB and then increases the type with the amount.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Goal Found", response = GeneralResponse.class),
            @ApiResponse(code = 400, message = "Bad Request", response = GeneralResponse.class),
            @ApiResponse(code = 404, message = "Not found", response = GeneralResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = GeneralResponse.class)})
    public ResponseEntity<GeneralResponse<ExchangeMediumDto>> increaseExchangeMediumTotal(@ApiParam(value = "The id that is unique to each exchange medium.", example = "1", name = "id", required = true) @PathVariable("id") Integer id, @ApiParam(value = "The type of exchange medium.", example = "100", name = "amount", required = true) @PathVariable("amount") Double amount){
        ExchangeMediumDto exchangeMediumResponse = exchangeMediumService.increaseExchangeMediumTotal(id,amount);
        GeneralResponse<ExchangeMediumDto> response = new GeneralResponse<>(true, exchangeMediumResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(path = "/decreaseExchangeMediumTotal/{id}/{amount}")
    @ApiOperation(value = "Fetches a Exchange Medium by its id and amount to decrease the type with.", notes = "Fetches exchange medium by id from the DB and then decreases the type with the amount.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Goal Found", response = GeneralResponse.class),
            @ApiResponse(code = 400, message = "Bad Request", response = GeneralResponse.class),
            @ApiResponse(code = 404, message = "Not found", response = GeneralResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = GeneralResponse.class)})
    public ResponseEntity<GeneralResponse<ExchangeMediumDto>> decreaseExchangeMediumTotal(@ApiParam(value = "The id that is unique to each exchange medium.", example = "1", name = "id", required = true) @PathVariable("id") Integer id, @ApiParam(value = "The type of exchange medium.", example = "100", name = "amount", required = true) @PathVariable("amount") Double amount){
        ExchangeMediumDto exchangeMediumResponse = exchangeMediumService.decreaseExchangeMediumTotal(id,amount);
        GeneralResponse<ExchangeMediumDto> response = new GeneralResponse<>(true, exchangeMediumResponse);
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
