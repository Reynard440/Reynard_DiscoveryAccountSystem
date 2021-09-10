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
import za.ac.nwu.domain.service.DiscoveryAccountSystemResponse;
import za.ac.nwu.logic.flow.ExchangeMediumService;
import za.ac.nwu.logic.flow.NewTransactionService;
import za.ac.nwu.logic.flow.ViewExchangeMediumService;

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

    @PostMapping("/newExchangeMedium")
    @ApiOperation(value = "Create a new exchange medium.", notes = "Creates a new Exchange Medium in the DB.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Exchange Medium successfully create", response = DiscoveryAccountSystemResponse.class),
            @ApiResponse(code = 400, message = "Bad Request", response = DiscoveryAccountSystemResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = DiscoveryAccountSystemResponse.class)})
    public ResponseEntity<DiscoveryAccountSystemResponse<ExchangeMediumDto>> newExchangeMedium(@ApiParam(value = "Request body to create a new Member", required = true) @RequestBody ExchangeMediumDto exchangeMediumDto){
        ExchangeMediumDto exchangeMediumDtoResponse = exchangeMediumService.newExchangeMedium(exchangeMediumDto);
        DiscoveryAccountSystemResponse<ExchangeMediumDto> response = new DiscoveryAccountSystemResponse<>(true, exchangeMediumDtoResponse);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/getAllExchangeMediums")
    @ApiOperation(value = "Gets all the types of Exchange Mediums.", notes = "Returns a list of all the types of Exchange Mediums that exist.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "MeExchange Mediums returned", response = DiscoveryAccountSystemResponse.class),
            @ApiResponse(code = 400, message = "Bad Request", response = DiscoveryAccountSystemResponse.class),
            @ApiResponse(code = 404, message = "Not found", response = DiscoveryAccountSystemResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = DiscoveryAccountSystemResponse.class)})
    public ResponseEntity<DiscoveryAccountSystemResponse<List<ExchangeMediumDto>>> getAllExchangeMediums(){
        List<ExchangeMediumDto> exchangeMediumDtos = viewExchangeMediumService.getAllExchangeMedium();
        DiscoveryAccountSystemResponse<List<ExchangeMediumDto>> response = new DiscoveryAccountSystemResponse<>(true, exchangeMediumDtos);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/getExchangeMediumById/{id}")
    @ApiOperation(value = "Fetches a Exchange Medium by its id.", notes = "Fetches exchange medium by id from DB.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Goal Found", response = DiscoveryAccountSystemResponse.class),
            @ApiResponse(code = 400, message = "Bad Request", response = DiscoveryAccountSystemResponse.class),
            @ApiResponse(code = 404, message = "Not found", response = DiscoveryAccountSystemResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = DiscoveryAccountSystemResponse.class)})
    public ResponseEntity<DiscoveryAccountSystemResponse<ExchangeMediumDto>> getExchangeMediumById(@ApiParam(value = "The id that is unique to each exchange medium.", example = "1", name = "id", required = true) @PathVariable("id") Integer id){
        ExchangeMediumDto exchangeMediumResponse = viewExchangeMediumService.getExchangeMediumByEmID(id);
        DiscoveryAccountSystemResponse<ExchangeMediumDto> response = new DiscoveryAccountSystemResponse<>(true, exchangeMediumResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/getExchangeMediumCurrentByTypeAndID/{type}/{id}")
    @ApiOperation(value = "Fetches a Exchange Medium by its id and type.", notes = "Fetches exchange medium by id and type from DB.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Goal Found", response = DiscoveryAccountSystemResponse.class),
            @ApiResponse(code = 400, message = "Bad Request", response = DiscoveryAccountSystemResponse.class),
            @ApiResponse(code = 404, message = "Not found", response = DiscoveryAccountSystemResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = DiscoveryAccountSystemResponse.class)})
    public ResponseEntity<DiscoveryAccountSystemResponse<ExchangeMediumDto>> getExchangeMediumCurrentByTypeAndID(@ApiParam(value = "The type of the Exchange Medium.", example = "PLAYS", name = "type", required = true) @PathVariable("type") String type, @ApiParam(value = "The id of the exchange medium.", example = "1", name = "id", required = true) @PathVariable("id") Integer id){
        ExchangeMediumDto exchangeMediumResponse = viewExchangeMediumService.getExchangeMediumCurrentByTypeAndID(type, id);
        DiscoveryAccountSystemResponse<ExchangeMediumDto> response = new DiscoveryAccountSystemResponse<>(true, exchangeMediumResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/checkTypeExist/{id}/{type}")
    @ApiOperation(value = "Fetches a Exchange Medium by its id and type.", notes = "Fetches exchange medium by id and type from DB.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Goal Found", response = DiscoveryAccountSystemResponse.class),
            @ApiResponse(code = 400, message = "Bad Request", response = DiscoveryAccountSystemResponse.class),
            @ApiResponse(code = 404, message = "Not found", response = DiscoveryAccountSystemResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = DiscoveryAccountSystemResponse.class)})
    public ResponseEntity<DiscoveryAccountSystemResponse<Boolean>> checkTypeExist(@ApiParam(value = "The id that is unique to each exchange medium.", example = "1", name = "id", required = true) @PathVariable("id") Integer id, @ApiParam(value = "The type of exchange medium.", example = "Rand", name = "type", required = true) @PathVariable("type") String type){
        boolean intResponse = exchangeMediumService.checkTypeExist(id, type);
        DiscoveryAccountSystemResponse<Boolean> response = new DiscoveryAccountSystemResponse<>(true, intResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(path = "/increaseExchangeMediumTotal/{id}/{amount}")
    @ApiOperation(value = "Fetches a Exchange Medium by its id and amount to increase the type with.", notes = "Fetches exchange medium by id from the DB and then increases the type with the amount.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Goal Found", response = DiscoveryAccountSystemResponse.class),
            @ApiResponse(code = 400, message = "Bad Request", response = DiscoveryAccountSystemResponse.class),
            @ApiResponse(code = 404, message = "Not found", response = DiscoveryAccountSystemResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = DiscoveryAccountSystemResponse.class)})
    public ResponseEntity<DiscoveryAccountSystemResponse<ExchangeMediumDto>> increaseExchangeMediumTotal(@ApiParam(value = "The id that is unique to each exchange medium.", example = "1", name = "id", required = true) @PathVariable("id") Integer id, @ApiParam(value = "The type of exchange medium.", example = "100", name = "amount", required = true) @PathVariable("amount") double amount){
        exchangeMediumService.increaseExchangeMediumTotal(id,amount);
        ExchangeMediumDto exchangeMediumResponse = viewExchangeMediumService.getExchangeMediumByEmID(id);
        DiscoveryAccountSystemResponse<ExchangeMediumDto> response = new DiscoveryAccountSystemResponse<>(true, exchangeMediumResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(path = "/decreaseExchangeMediumTotal/{id}/{amount}")
    @ApiOperation(value = "Fetches a Exchange Medium by its id and amount to decrease the type with.", notes = "Fetches exchange medium by id from the DB and then decreases the type with the amount.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Goal Found", response = DiscoveryAccountSystemResponse.class),
            @ApiResponse(code = 400, message = "Bad Request", response = DiscoveryAccountSystemResponse.class),
            @ApiResponse(code = 404, message = "Not found", response = DiscoveryAccountSystemResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = DiscoveryAccountSystemResponse.class)})
    public ResponseEntity<DiscoveryAccountSystemResponse<ExchangeMediumDto>> decreaseExchangeMediumTotal(@ApiParam(value = "The id that is unique to each exchange medium.", example = "1", name = "id", required = true) @PathVariable("id") Integer id, @ApiParam(value = "The type of exchange medium.", example = "100", name = "amount", required = true) @PathVariable("amount") double amount){
        exchangeMediumService.decreaseExchangeMediumTotal(id,amount);
        ExchangeMediumDto exchangeMediumResponse = viewExchangeMediumService.getExchangeMediumByEmID(id);
        DiscoveryAccountSystemResponse<ExchangeMediumDto> response = new DiscoveryAccountSystemResponse<>(true, exchangeMediumResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
