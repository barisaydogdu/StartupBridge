package com.filepackage.controller;

import com.filepackage.dto.BlogDto;
import com.filepackage.service.impl.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blogs")
public class  BlogController {

    @Autowired
    private BlogService blogService;


    @GetMapping
    public ResponseEntity<List<BlogDto>> getAllBlogs() {
        List<BlogDto> blogs = blogService.getAll();
        return ResponseEntity.ok(blogs);
    }


    @PostMapping
    public ResponseEntity<BlogDto> addBlog(@RequestBody BlogDto blogDto) {
        BlogDto savedBlog = blogService.createBlog(blogDto);
        return new ResponseEntity<>(savedBlog, HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<BlogDto> getBlogById(@PathVariable("id") Long blogId) {
        BlogDto blogDto = blogService.getById(blogId);
        return ResponseEntity.ok(blogDto);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBlog(@PathVariable("id") Long blogId) {
        blogService.delete(blogId);
        return ResponseEntity.ok("Blog deleted successfully");
    }


    @PutMapping("/{id}")
    public ResponseEntity<BlogDto> updateBlog(@PathVariable("id") Long blogId, @RequestBody BlogDto updatedBlog) {
        BlogDto blogDto = blogService.update(blogId, updatedBlog);
        return ResponseEntity.ok(blogDto);
    }
}
