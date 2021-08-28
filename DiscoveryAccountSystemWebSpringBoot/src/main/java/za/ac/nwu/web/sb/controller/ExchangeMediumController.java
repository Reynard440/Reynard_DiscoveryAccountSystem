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
import za.ac.nwu.domain.dto.ExchangeMediumDto;
import za.ac.nwu.domain.dto.MemberTransactionDto;
import za.ac.nwu.domain.service.GeneralResponse;
import za.ac.nwu.logic.flow.ViewExchangeMediumService;
import za.ac.nwu.logic.flow.ViewMemberTransactionService;

import java.util.List;

@RestController
@RequestMapping(path="/discovery-account-system/mvc/v1/c3")
public class ExchangeMediumController {

    private final ViewExchangeMediumService viewExchangeMediumService;

    @Autowired
    public ExchangeMediumController(ViewExchangeMediumService viewExchangeMediumService){
        this.viewExchangeMediumService = viewExchangeMediumService;
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
