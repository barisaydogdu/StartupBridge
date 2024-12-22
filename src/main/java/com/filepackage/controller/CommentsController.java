package com.filepackage.controller;

import com.filepackage.dto.CommentsDto;
import com.filepackage.entity.User;
import com.filepackage.repository.IUserRepository;
import com.filepackage.service.impl.CommentsService;
import com.filepackage.service.impl.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentsController {

    @Autowired
    private CommentsService commentsService;
    private IUserRepository userRepository;
    private JwtService jwtService;

    public CommentsController(IUserRepository userRepository, JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    @GetMapping
    public ResponseEntity<List<CommentsDto>> getAllComments() {
        List<CommentsDto> comments = commentsService.getAll();
        return ResponseEntity.ok(comments);
    }

    public User getAuthenticatedUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("Principal: " + principal); // Principal'i logla

        if (principal instanceof User) {
            return (User) principal; // Principal doğrudan User nesnesiyse döndür
        } else {
            throw new RuntimeException("Authenticated user is not of type User");
        }
    }

    // JWT için alternatif metod
    public User getAuthenticatedUserFromToken(String token) {
        // JWT'den user bilgisini çıkar
        String username = jwtService.extractUsername(token);  // JWT utility metodunuz
        return userRepository.findByName(username)
                .orElseThrow(() -> new RuntimeException("User not found in database"));
    }

    /* @PostMapping
    public ResponseEntity<CommentsDto> addComment(@RequestBody CommentsDto commentsDto) {
        User currentUser = getAuthenticatedUser();
        System.out.println("Current User " +currentUser.getId());
        commentsDto.setUserId(currentUser.getId());
        commentsDto.setCreatedAt(LocalDateTime.now());
        CommentsDto savedComment = commentsService.createComments(commentsDto);
        savedComment.setUserId(currentUser.getId());
        return new ResponseEntity<>(savedComment, HttpStatus.CREATED);
    }*/
    /*@PostMapping
   public ResponseEntity<CommentsDto> addComment(@RequestBody CommentsDto commentsDto) {
       try {
           // Get current user from security context
           Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
           UserDetails userDetails = (UserDetails) authentication.getPrincipal();
           User user = userRepository.findByName(userDetails.getUsername())
                   .orElseThrow(() -> new RuntimeException("User not found"));

           // Set the user_id from authenticated user
           assert user != null;
           commentsDto.setUserId(user.getId());

           CommentsDto savedComment = commentsService.createComments(commentsDto);
           savedComment.setUserId(user.getId());
           return new ResponseEntity<>(savedComment, HttpStatus.CREATED);
       } catch (Exception e) {
           System.err.println("Error creating comment: " + e.getMessage());
           e.printStackTrace();
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
       }
   }*/


 /* @PostMapping
    public ResponseEntity<CommentsDto> addComment(@RequestBody CommentsDto commentsDto) {
        User currentUser = getAuthenticatedUser();
        System.out.println("Current User " +currentUser);
        commentsDto.setUserId(currentUser.getId());
        commentsDto.setCreatedAt(LocalDateTime.now());
        CommentsDto savedComment = commentsService.createComments(commentsDto);
        savedComment.setUserId(currentUser.getId());
        return new ResponseEntity<>(savedComment, HttpStatus.CREATED);
  }*/
    @GetMapping("/{id}")
    public ResponseEntity<CommentsDto> getCommentById(@PathVariable("id") Long commentId) {
        CommentsDto commentsDto = commentsService.getById(commentId);
        return ResponseEntity.ok(commentsDto);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable("id") Long commentId) {
        // Get authenticated user
        User currentUser = getAuthenticatedUser();

        // Check if user owns the comment before deleting
        CommentsDto comment = commentsService.getById(commentId);
        if (!comment.getUserId().equals(currentUser.getId())) {
            return new ResponseEntity<>("Unauthorized to delete this comment", HttpStatus.FORBIDDEN);
        }

        commentsService.delete(commentId);
        return ResponseEntity.ok("Comment deleted successfully");
    }


    @PutMapping("/{id}")
    public ResponseEntity<CommentsDto> updateComment(@PathVariable("id") Long commentId, @RequestBody CommentsDto updatedComment) {
        CommentsDto commentsDto = commentsService.update(commentId, updatedComment);
        return ResponseEntity.ok(commentsDto);
    }


    @PostMapping
    public ResponseEntity<CommentsDto> addComment(@RequestBody CommentsDto commentsDto) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            User user = userRepository.findByName(userDetails.getUsername())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            commentsDto.setUserId(user.getId());
            commentsDto.setCreatedAt(LocalDateTime.now());

            CommentsDto savedComment = commentsService.createComments(commentsDto);
            return new ResponseEntity<>(savedComment, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

