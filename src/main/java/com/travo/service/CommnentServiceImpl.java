package com.travo.service;

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



    @Autowired
    public CommnentServiceImpl (CommentRepository commentRepo) {
        this.commentRepo = commentRepo;
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
}
