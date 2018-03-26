package com.travo.service;

import com.travo.dto.CommentDTO;
import com.travo.model.Comment;
import com.travo.model.Spot;

import java.util.ArrayList;
import java.util.List;

public interface CommentService {
    Integer countComment(Spot spot);

    ArrayList<Long> getCommentsIdArray(Spot spot);

    List<CommentDTO> findCommentsDTOBySpot(Spot spot);
    Comment saveComment(CommentDTO commentDTO, String userName);
}
