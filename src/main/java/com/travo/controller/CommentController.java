package com.travo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    @Autowired
    public CommentController (CommentService commentService, SpotService spotService) {
        this.commentService = commentService;
        this.spotService = spotService;
    }

    @RequestMapping(value = "/loadCommentsInSpot", method = RequestMethod.GET)
    public List<CommentDTO> getCommentsBySpot(@RequestParam(value = "spotId") Long spotId){
        Spot spot = spotService.findSpotById(spotId);
        return commentService.findCommentsDTOBySpot(spot);
    }
    @PostMapping(value= "/saveComment")
    public ResponseEntity saveComment(@RequestBody CommentDTO commentDTO, Authentication auth) {
    	    	String userName= auth.getName();
    	commentService.saveComment(commentDTO, userName);
    	
    	return  ResponseEntity.status(HttpStatus.OK).body("Save Success");
    }
}
