package com.travo.controller;

import java.util.Date;
import java.util.List;

import com.travo.model.User;
import com.travo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.travo.dto.CommentDTO;
import com.travo.model.Spot;
import com.travo.service.CommentService;
import com.travo.service.SpotService;

/**
 * Created by asus on 3/23/2018.
 */
@CrossOrigin
@RestController
public class CommentController {
    private CommentService commentService;
    private SpotService spotService;
    private UserService userService;
    @Autowired
    public CommentController (CommentService commentService, SpotService spotService, UserService userService) {
        this.commentService = commentService;
        this.spotService = spotService;
        this.userService = userService;
    }

    @RequestMapping(value = "/loadCommentsInSpot", method = RequestMethod.GET)
    public List<CommentDTO> getCommentsBySpot(@RequestParam(value = "spotId") Long spotId){
        Spot spot = spotService.findSpotById(spotId);
        System.out.println("Load comment in Spot");
        return commentService.findCommentsDTOBySpot(spot);
    }
    @RequestMapping(value= "/saveComment", method = RequestMethod.POST)
    public String saveComment(@RequestBody CommentDTO commentDTO, Authentication auth) {
    	    	String userName= auth.getName();
        System.out.println("Saving comment");
        User loggedUser = userService.findByUsername(userName);
        Date createdDate = new Date();
        commentDTO.setCreatedTime(createdDate);
        commentDTO.setUserDTO(userService.findUserDTOById(loggedUser.getId()));
        commentService.saveComment(commentDTO, loggedUser);
        return "Success";
    }
}
