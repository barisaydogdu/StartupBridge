package com.filepackage.service.impl;

import com.filepackage.Exception.ResourceNotFoundException;
import com.filepackage.dto.BlogDto;
import com.filepackage.dto.PublicUsersDto;
import com.filepackage.entity.Blog;
import com.filepackage.entity.Entrepreneur;
import com.filepackage.entity.Investors;
import com.filepackage.entity.User;
import com.filepackage.mapper.AutoMapper;
import com.filepackage.repository.IBlogRepository;
import com.filepackage.repository.IEntrepreneurRepository;
import com.filepackage.repository.IInvestorsRepository;
import com.filepackage.service.IBlogService;
import com.filepackage.service.IEntrepreneurService;
import com.filepackage.service.IInvestorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BlogService implements IBlogService<BlogDto, Long> {

    @Autowired
    AutoMapper autoMapper;

    private final IBlogRepository blogRepository;
    private final IEntrepreneurRepository entrepreneurRepository;
    private final IInvestorsRepository investorsRepository;

    @Autowired
    public BlogService(IBlogRepository blogRepository,
                       IEntrepreneurRepository entrepreneurRepository,
                       IInvestorsRepository investorsRepository) {
        this.blogRepository = blogRepository;
        this.entrepreneurRepository = entrepreneurRepository;
        this.investorsRepository = investorsRepository;
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
                .map(blog -> {
                    BlogDto blogDto = autoMapper.convertToDto(blog, BlogDto.class);
                    // Find author profile
                    User author = blog.getAuthorUser();
                    if (author != null) {
                        // Check if author is an entrepreneur
                        Optional<Entrepreneur> entrepreneur = entrepreneurRepository.findByUser(author);
                        if (entrepreneur.isPresent()) {
                            blogDto.setAuthorType("entrepreneur");
                            blogDto.setAuthorProfileId(entrepreneur.get().getEntrepreneurId());
                            blogDto.setAuthorName(entrepreneur.get().getFirstName() + " " + entrepreneur.get().getLastName());
                            blogDto.setAuthorProfilePicture(entrepreneur.get().getProfilePicture());
                        } else {
                            // Check if author is an investor
                            Optional<Investors> investor = investorsRepository.findByUser(author);
                            if (investor.isPresent()) {
                                blogDto.setAuthorType("investor");
                                blogDto.setAuthorProfileId(investor.get().getInvestor_id());
                                blogDto.setAuthorName(investor.get().getFirst_name() + " " + investor.get().getLast_name());
                                blogDto.setAuthorProfilePicture(investor.get().getProfile_picture());
                            }
                        }
                    }
                    return blogDto;
                })
                .collect(Collectors.toList());
    }

   /* @Override
    public List<BlogDto> getAll() {
        List<Blog> blogs = blogRepository.findAll();
        return blogs.stream()
                .map(blog -> autoMapper.convertToDto(blog, BlogDto.class))
                .collect(Collectors.toList());
    }*/




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

