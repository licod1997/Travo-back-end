package com.travo.controller;

import com.travo.dto.CommentDTO;
import com.travo.model.Spot;
import com.travo.service.CommentService;
import com.travo.service.SpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
