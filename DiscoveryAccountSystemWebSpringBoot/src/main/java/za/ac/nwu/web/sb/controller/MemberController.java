package za.ac.nwu.web.sb.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.nwu.domain.dto.MemberDto;
import za.ac.nwu.domain.service.DiscoveryAccountSystemResponse;
import za.ac.nwu.logic.flow.MemberService;
import za.ac.nwu.logic.flow.ViewMemberService;

import java.util.List;

@RestController
@RequestMapping(path="/v1/c1")
public class MemberController {
    private final MemberService memberService;
    private final ViewMemberService viewMemberService;

    @Autowired
    public MemberController(MemberService memberService, ViewMemberService viewMemberService){
        this.memberService = memberService;
        this.viewMemberService = viewMemberService;
    }

    @GetMapping("/getAllMembers")
    @ApiOperation(value = "Gets all the Members.", notes = "Returns a list of members.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Members returned", response = DiscoveryAccountSystemResponse.class),
            @ApiResponse(code = 400, message = "Bad Request", response = DiscoveryAccountSystemResponse.class),
            @ApiResponse(code = 404, message = "Not found", response = DiscoveryAccountSystemResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = DiscoveryAccountSystemResponse.class)})
    public ResponseEntity<DiscoveryAccountSystemResponse<List<MemberDto>>> getAllMembers(){
        List<MemberDto> members = viewMemberService.getMembers();
        DiscoveryAccountSystemResponse<List<MemberDto>> response = new DiscoveryAccountSystemResponse<>(true, members);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/addMember")
    @ApiOperation(value = "Create a new Member.", notes = "Creates a new Member in the DB.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Member successfully create", response = DiscoveryAccountSystemResponse.class),
            @ApiResponse(code = 400, message = "Bad Request", response = DiscoveryAccountSystemResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = DiscoveryAccountSystemResponse.class)})
    public ResponseEntity<DiscoveryAccountSystemResponse<MemberDto>> newMember(@ApiParam(value = "Request body to create a new Member", required = true) @RequestBody MemberDto memberDto){
        MemberDto memberResponse = memberService.newMember(memberDto);
        DiscoveryAccountSystemResponse<MemberDto> response = new DiscoveryAccountSystemResponse<>(true, memberResponse);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/getMemberByEmail/{email}")
    @ApiOperation(value = "Fetches a new Member by email.", notes = "Fetches member by email from DB.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Goal Found", response = DiscoveryAccountSystemResponse.class),
            @ApiResponse(code = 400, message = "Bad Request", response = DiscoveryAccountSystemResponse.class),
            @ApiResponse(code = 404, message = "Not found", response = DiscoveryAccountSystemResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = DiscoveryAccountSystemResponse.class)})
    public ResponseEntity<DiscoveryAccountSystemResponse<MemberDto>> getMemberByEmail(@ApiParam(value = "The email that is unique to each member", example = "reynardengels@gmail.com", name = "email", required = true) @PathVariable("email") String email){
        MemberDto memberResponse = viewMemberService.getMemberByEmail(email);
        DiscoveryAccountSystemResponse<MemberDto> response = new DiscoveryAccountSystemResponse<>(true, memberResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/getMemberById/{id}")
    @ApiOperation(value = "Fetches a Member by their id.", notes = "Fetches member by id from DB.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Goal Found", response = DiscoveryAccountSystemResponse.class),
            @ApiResponse(code = 400, message = "Bad Request", response = DiscoveryAccountSystemResponse.class),
            @ApiResponse(code = 404, message = "Not found", response = DiscoveryAccountSystemResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = DiscoveryAccountSystemResponse.class)})
    public ResponseEntity<DiscoveryAccountSystemResponse<MemberDto>> getMemberById(@ApiParam(value = "The id that is unique to each member", example = "1", name = "id", required = true) @PathVariable("id") Integer id){
        MemberDto memberResponse = viewMemberService.getMemberById(id);
        DiscoveryAccountSystemResponse<MemberDto> response = new DiscoveryAccountSystemResponse<>(true, memberResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(path = "/deleteMemberByID/{id}")
    @ApiOperation(value = "Deletes a Member by their phone number.", notes = "Deletes a member by phone number from DB.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Goal Found", response = DiscoveryAccountSystemResponse.class),
            @ApiResponse(code = 400, message = "Bad Request", response = DiscoveryAccountSystemResponse.class),
            @ApiResponse(code = 404, message = "Not found", response = DiscoveryAccountSystemResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = DiscoveryAccountSystemResponse.class)})
    public ResponseEntity<DiscoveryAccountSystemResponse<MemberDto>> deleteMemberByID(@ApiParam(value = "The id is unique to each Member.", example = "1", name = "id", required = true) @PathVariable("id") Integer id){
        memberService.deleteMember(id);
        MemberDto memberDtoResponse = viewMemberService.getMemberById(id);
        DiscoveryAccountSystemResponse<MemberDto> response = new DiscoveryAccountSystemResponse<>(true, memberDtoResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
