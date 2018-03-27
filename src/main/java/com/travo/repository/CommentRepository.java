package com.travo.repository;

import com.travo.model.Comment;
import com.travo.model.Spot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long>{
    long countAllBySpot(Spot spot);

    List<Comment> findAllBySpot(Spot spot);
    List<Comment> findAllBySpotOrderByIdDesc(Spot spot);
}
