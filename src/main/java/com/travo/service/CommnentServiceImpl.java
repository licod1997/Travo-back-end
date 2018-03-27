package com.travo.service;

import com.travo.dto.CommentDTO;
import com.travo.model.Comment;
import com.travo.model.Spot;
import com.travo.model.User;
import com.travo.repository.CommentRepository;
import com.travo.repository.SpotRepository;
import com.travo.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class CommnentServiceImpl implements CommentService {
	private CommentRepository commentRepo;
	private UserService userService;
	private SpotRepository spotRepository;

	@Autowired
	public CommnentServiceImpl(CommentRepository commentRepo, UserService userService, SpotRepository spotService) {
		this.commentRepo = commentRepo;
		this.userService = userService;
		this.spotRepository=  spotService;
	}

	@Override
	public Integer countComment(Spot spot) {
		// System.out.println("test: "+commentRepo.findAll().size());
		List<Comment> commentList = commentRepo.findAllBySpot(spot);
		System.out.println("commentListSize: " + commentList.size());
		return commentList.size();
	}

	public ArrayList<Long> getCommentsIdArray(Spot spot) {
		List<Comment> commentList = commentRepo.findAllBySpot(spot);
		ArrayList<Long> arrayId = new ArrayList<>();
		for (Comment comment : commentList) {
			arrayId.add(comment.getId());
		}
		return arrayId;
	}

	@Override
	public List<CommentDTO> findCommentsDTOBySpot(Spot spot) {
		List<Comment> commentList = commentRepo.findAllBySpotOrderByIdDesc(spot);
		List<CommentDTO> commentDTOList = new ArrayList<>();
		for (Comment comment : commentList) {
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

	@Override
	public Comment saveComment(CommentDTO commentDTO, User loggedUser) {
		Comment comment = new Comment();
		Spot spot = spotRepository.findSpotById(commentDTO.getSpotId());
	    comment.setCreatedTime(new Date());
		comment.setContent(commentDTO.getContent());
		comment.setUser(loggedUser);
		comment.setSpot(spot);

		return commentRepo.save(comment);
	}
}
