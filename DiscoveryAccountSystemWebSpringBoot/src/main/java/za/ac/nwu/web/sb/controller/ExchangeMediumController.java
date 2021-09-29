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

import javax.transaction.Transactional;
import java.sql.SQLException;
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

    @Transactional
    @PostMapping("/new")
    @ApiOperation(value = "Create a new exchange medium.", notes = "Creates a new Exchange Medium in the DB.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Exchange Medium successfully create", response = DiscoveryAccountSystemResponse.class),
            @ApiResponse(code = 400, message = "Bad Request", response = DiscoveryAccountSystemResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = DiscoveryAccountSystemResponse.class)})
    public ResponseEntity<DiscoveryAccountSystemResponse<ExchangeMediumDto>> newExchangeMedium(
            @ApiParam(value = "Request body to create a new Member", required = true)
            @RequestBody ExchangeMediumDto exchangeMediumDto) throws SQLException {
        ExchangeMediumDto exchangeMediumDtoResponse = exchangeMediumService.newExchangeMedium(exchangeMediumDto);
        DiscoveryAccountSystemResponse<ExchangeMediumDto> response = new DiscoveryAccountSystemResponse<>(true, exchangeMediumDtoResponse);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping(path = "/getExchangeMediumById/{id}")
    @ApiOperation(value = "Fetches a list of Exchange Mediums via a member's id.", notes = "Fetches exchange medium by a member's id from DB.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Found exchange medium(s) by the member's id", response = DiscoveryAccountSystemResponse.class),
            @ApiResponse(code = 400, message = "Bad Request", response = DiscoveryAccountSystemResponse.class),
            @ApiResponse(code = 404, message = "Not found", response = DiscoveryAccountSystemResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = DiscoveryAccountSystemResponse.class)})
    public ResponseEntity<DiscoveryAccountSystemResponse<List<ExchangeMediumDto>>> getExchangeMediumById(
            @ApiParam(value = "The id that is unique to each member.", example = "1", name = "id", required = true)
            @PathVariable("id") Integer id) throws SQLException {
        List<ExchangeMediumDto> exchangeMediumResponse = viewExchangeMediumService.getExchangeMediumByMemID(id);
        DiscoveryAccountSystemResponse<List<ExchangeMediumDto>> response = new DiscoveryAccountSystemResponse<>(true, exchangeMediumResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/getExchangeMediumByEmId/{id}")
    @ApiOperation(value = "Fetches a Exchange Medium by its id.", notes = "Fetches exchange medium by id from DB.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Found exchange medium(s) by the member's id", response = DiscoveryAccountSystemResponse.class),
            @ApiResponse(code = 400, message = "Bad Request", response = DiscoveryAccountSystemResponse.class),
            @ApiResponse(code = 404, message = "Not found", response = DiscoveryAccountSystemResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = DiscoveryAccountSystemResponse.class)})
    public ResponseEntity<DiscoveryAccountSystemResponse<ExchangeMediumDto>> getExchangeMediumByEmId(
            @ApiParam(value = "The id that is unique to each exchange medium.", example = "1", name = "id", required = true)
            @PathVariable("id") Integer id) throws SQLException {
        ExchangeMediumDto exchangeMediumResponse = viewExchangeMediumService.getExchangeMediumByEmID(id);
        DiscoveryAccountSystemResponse<ExchangeMediumDto> response = new DiscoveryAccountSystemResponse<>(true, exchangeMediumResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/getExchangeMediumCurrentByTypeAndID/{type}/{id}")
    @ApiOperation(value = "Fetches a Exchange Medium by its id and type.", notes = "Fetches exchange medium by id and type from DB.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Current balance for the exchange medium found", response = DiscoveryAccountSystemResponse.class),
            @ApiResponse(code = 400, message = "Bad Request", response = DiscoveryAccountSystemResponse.class),
            @ApiResponse(code = 404, message = "Not found", response = DiscoveryAccountSystemResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = DiscoveryAccountSystemResponse.class)})
    public ResponseEntity<DiscoveryAccountSystemResponse<ExchangeMediumDto>> getExchangeMediumCurrentByTypeAndID(
            @ApiParam(value = "The type of the Exchange Medium.", example = "Dollars", name = "type", required = true)
            @PathVariable("type") String type, @ApiParam(value = "The id of the exchange medium.", example = "1", name = "id", required = true)
            @PathVariable("id") Integer id) throws SQLException {
        ExchangeMediumDto exchangeMediumResponse = viewExchangeMediumService.getExchangeMediumCurrentByTypeAndID(type, id);
        DiscoveryAccountSystemResponse<ExchangeMediumDto> response = new DiscoveryAccountSystemResponse<>(true, exchangeMediumResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Transactional
    @PutMapping(path = "/switchToExchangeMedium/{name}/{id}")
    @ApiOperation(value = "Configures one Exchange Medium to another.", notes = "Configures to another exchange medium.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Exchange medium has been updated", response = DiscoveryAccountSystemResponse.class),
            @ApiResponse(code = 400, message = "Bad Request", response = DiscoveryAccountSystemResponse.class),
            @ApiResponse(code = 404, message = "Not found", response = DiscoveryAccountSystemResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = DiscoveryAccountSystemResponse.class)})
    public ResponseEntity<DiscoveryAccountSystemResponse<ExchangeMediumDto>> configureExchangeMedium(
            @ApiParam(value = "The type of exchange medium.", example = "Miles", name = "name", required = true)
            @PathVariable("name") String name,
            @ApiParam(value = "The id that is unique to each exchange medium.", example = "1", name = "id", required = true)
            @PathVariable("id") Integer id,
            @ApiParam(value = "The new type of exchange medium the member wants to convert to.", example = "Dollars", name = "newType", required = true)
            @RequestParam("newType") String newType,
            @ApiParam(value = "The amount the old exchange medium should be adjusted with.", example = "0.5", name = "adjust", required = true)
            @RequestParam("adjust") double adjust,
            @ApiParam(value = "The id of the member for the specified exchange medium.", example = "1", name = "member", required = true)
            @RequestParam("member") Integer member) throws SQLException {
        exchangeMediumService.configureExchangeMedium(name, newType, adjust, member, id);
        ExchangeMediumDto exchangeMediumResponse = viewExchangeMediumService.getExchangeMediumByEmID(id);
        DiscoveryAccountSystemResponse<ExchangeMediumDto> response = new DiscoveryAccountSystemResponse<>(true, exchangeMediumResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
