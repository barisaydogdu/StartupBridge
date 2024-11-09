package com.filepackage.controller;

import com.filepackage.dto.CommentsDto;
import com.filepackage.service.impl.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentsController {

    @Autowired
    private CommentsService commentsService;


    @GetMapping
    public ResponseEntity<List<CommentsDto>> getAllComments() {
        List<CommentsDto> comments = commentsService.getAll();
        return ResponseEntity.ok(comments);
    }


    @PostMapping
    public ResponseEntity<CommentsDto> addComment(@RequestBody CommentsDto commentsDto) {
        CommentsDto savedComment = commentsService.createComments(commentsDto);
        return new ResponseEntity<>(savedComment, HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<CommentsDto> getCommentById(@PathVariable("id") Long commentId) {
        CommentsDto commentsDto = commentsService.getById(commentId);
        return ResponseEntity.ok(commentsDto);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable("id") Long commentId) {
        commentsService.delete(commentId);
        return ResponseEntity.ok("Comment deleted successfully");
    }


    @PutMapping("/{id}")
    public ResponseEntity<CommentsDto> updateComment(@PathVariable("id") Long commentId, @RequestBody CommentsDto updatedComment) {
        CommentsDto commentsDto = commentsService.update(commentId, updatedComment);
        return ResponseEntity.ok(commentsDto);
    }
}
