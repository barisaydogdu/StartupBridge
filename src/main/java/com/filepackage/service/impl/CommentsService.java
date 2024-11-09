package com.filepackage.service.impl;

import com.filepackage.Exception.ResourceNotFoundException;
import com.filepackage.dto.CommentsDto;
import com.filepackage.entity.Comments;
import com.filepackage.mapper.AutoMapper;
import com.filepackage.repository.ICommentsRepository;
import com.filepackage.service.ICommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentsService implements ICommentsService<CommentsDto, Long> {

    @Autowired
    AutoMapper autoMapper;

    private final ICommentsRepository commentsRepository;

    @Autowired
    public CommentsService(ICommentsRepository commentsRepository) {
        this.commentsRepository = commentsRepository;
    }

    @Override
    public CommentsDto getById(Long commentsId) {
        Comments comments = commentsRepository.findById(commentsId)
                .orElseThrow(() -> new ResourceNotFoundException("Comments does not exist with given id: " + commentsId));
        return autoMapper.convertToDto(comments, CommentsDto.class);
    }

    @Override
    public List<CommentsDto> getAll() {
        List<Comments> commentsList = commentsRepository.findAll();
        return commentsList.stream()
                .map(comments -> autoMapper.convertToDto(comments, CommentsDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long commentsId) {
        commentsRepository.findById(commentsId)
                .orElseThrow(() -> new ResourceNotFoundException("Comments does not exist with given id: " + commentsId));
        commentsRepository.deleteById(commentsId);
    }

    @Override
    public CommentsDto update(Long commentsId, CommentsDto updatedComments) {
        Comments comments = commentsRepository.findById(commentsId)
                .orElseThrow(() -> new ResourceNotFoundException("Comments does not exist with given id: " + commentsId));

        comments.setComments_id(comments.getComments_id());
        comments.setComment_text(updatedComments.getComment_text());
        comments.setBlog_id(comments.getBlog_id());
        comments.setUser_id(comments.getUser_id());
        comments.setCreated_at(updatedComments.getCreated_at());

        Comments updatedEntity = commentsRepository.save(comments);
        return autoMapper.convertToDto(updatedEntity, CommentsDto.class);
    }

    @Override
    public CommentsDto createComments(CommentsDto commentsDto) {
        Comments comments = autoMapper.convertToEntity(commentsDto, Comments.class);
        Comments savedComments = commentsRepository.save(comments);
        return autoMapper.convertToDto(savedComments, CommentsDto.class);
    }
}
