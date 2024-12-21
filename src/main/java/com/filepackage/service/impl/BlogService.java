package com.filepackage.service.impl;

import com.filepackage.Exception.ResourceNotFoundException;
import com.filepackage.dto.BlogDto;
import com.filepackage.dto.PublicUsersDto;
import com.filepackage.entity.Blog;
import com.filepackage.mapper.AutoMapper;
import com.filepackage.repository.IBlogRepository;
import com.filepackage.service.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlogService implements IBlogService<BlogDto, Long> {

    @Autowired
    AutoMapper autoMapper;

    private final IBlogRepository blogRepository;

    @Autowired
    public BlogService(IBlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @Override
    public BlogDto getById(Long blogId) {
        Blog blog = blogRepository.findById(blogId)
                .orElseThrow(() -> new ResourceNotFoundException("Blog does not exist with given id: " + blogId));
        return autoMapper.convertToDto(blog, BlogDto.class);
    }

    @Override
    public List<BlogDto> getAll() {
        List<Blog> blogs = blogRepository.findAll();
        return blogs.stream()
                .map(blog -> autoMapper.convertToDto(blog, BlogDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long blogId) {
        blogRepository.findById(blogId)
                .orElseThrow(() -> new ResourceNotFoundException("Blog does not exist with given id: " + blogId));
        blogRepository.deleteById(blogId);
    }

    @Override
    public BlogDto update(Long blogId, BlogDto updatedBlog) {
        Blog blog = blogRepository.findById(blogId)
                .orElseThrow(() -> new ResourceNotFoundException("Blog does not exist with given id: " + blogId));

        blog.setTitle(updatedBlog.getTitle());
        blog.setContent(updatedBlog.getContent());
        blog.setCategory(updatedBlog.getCategory());
        blog.setAuthor_id(updatedBlog.getAuthor_id());

        Blog savedBlog = blogRepository.save(blog);
        return autoMapper.convertToDto(savedBlog, BlogDto.class);
    }

    @Override
    public BlogDto createBlog(BlogDto blogDto) {
        Blog blog = autoMapper.convertToEntity(blogDto, Blog.class);
        Blog savedBlog = blogRepository.save(blog);
        return autoMapper.convertToDto(savedBlog, BlogDto.class);
    }
}

