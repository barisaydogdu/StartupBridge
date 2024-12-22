package com.filepackage.controller;

import com.filepackage.dto.BlogDto;
import com.filepackage.entity.Entrepreneur;
import com.filepackage.entity.User;
import com.filepackage.service.impl.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/blogs")
@CrossOrigin(
        origins = "http://localhost:3000",
        allowCredentials = "true",
        allowedHeaders = {"Authorization", "Content-Type", "Accept"},
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS}
)
public class BlogController {

    @Autowired
    private BlogService blogService;

    public User getAuthenticatedUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("Principal: " + principal); // Principal'i logla

        if (principal instanceof User) {
            return (User) principal; // Principal doğrudan User nesnesiyse döndür
        } else {
            throw new RuntimeException("Authenticated user is not of type User");
        }
    }

    @GetMapping
    public ResponseEntity<List<BlogDto>> getAllBlogs() {
        try {
            List<BlogDto> blogs = blogService.getAll();
            return ResponseEntity.ok(blogs);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<BlogDto> addBlog(@RequestBody BlogDto blogDto) {
        try {
            User user = getAuthenticatedUser();

            blogDto.setAuthor_id(user.getId());
            System.out.println("Received blog data: " + blogDto); // Debug için log
            BlogDto savedBlog = blogService.createBlog(blogDto);
            savedBlog.setAuthor_id(user.getId());
            return new ResponseEntity<>(savedBlog, HttpStatus.CREATED);
        } catch (Exception e) {
            System.err.println("Error creating blog: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlogDto> getBlogById(@PathVariable("id") Long blogId) {
        try {
            BlogDto blogDto = blogService.getById(blogId);
            return ResponseEntity.ok(blogDto);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBlog(@PathVariable("id") Long blogId) {
        try {
            blogService.delete(blogId);
            return ResponseEntity.ok("Blog deleted successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting blog: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<BlogDto> updateBlog(
            @PathVariable("id") Long blogId,
            @RequestBody BlogDto updatedBlog
    ) {
        try {
            BlogDto blogDto = blogService.update(blogId, updatedBlog);
            return ResponseEntity.ok(blogDto);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // OPTIONS metodu için explicit handler ekleyelim
    @RequestMapping(method = RequestMethod.OPTIONS)
    public ResponseEntity<?> handleOptions() {
        return ResponseEntity.ok().build();
    }
}