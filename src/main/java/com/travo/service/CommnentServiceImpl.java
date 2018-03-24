package com.travo.service;

import com.travo.dto.CommentDTO;
import com.travo.model.Comment;
import com.travo.model.Spot;
import com.travo.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CommnentServiceImpl implements CommentService {
    private CommentRepository commentRepo;
    private UserService userService;



    @Autowired
    public CommnentServiceImpl (CommentRepository commentRepo, UserService userService) {
        this.commentRepo = commentRepo;
        this.userService = userService;
    }

    @Override
    public Integer countComment(Spot spot) {
//        System.out.println("test: "+commentRepo.findAll().size());
        List<Comment> commentList = commentRepo.findAllBySpot(spot);
        System.out.println("commentListSize: "+commentList.size());
        return commentList.size();
    }

    public ArrayList<Long> getCommentsIdArray(Spot spot) {
        List<Comment> commentList = commentRepo.findAllBySpot(spot);
        ArrayList<Long> arrayId = new ArrayList<>();
        for (Comment comment: commentList) {
            arrayId.add(comment.getId());
        }
        return arrayId;
    }

    @Override
    public List<CommentDTO> findCommentsDTOBySpot(Spot spot) {
        List<Comment> commentList = commentRepo.findAllBySpot(spot);
        List<CommentDTO> commentDTOList = new ArrayList<>();
        for (Comment comment:commentList) {
            CommentDTO dto = new CommentDTO();
            dto.setId(comment.getId());
            dto.setContent(comment.getContent());
            dto.setCreatedTime(comment.getCreatedTime());
            dto.setSpotId(comment.getSpot().getId());
            dto.setUserDTO(userService.findUserDTOById(comment.getUser().getId()));
            dto.setSpotId(comment.getSpot().getId());
            commentDTOList.add(dto);
        }
        return commentDTOList;
    }

}
