package com.filepackage.service;

import com.filepackage.dto.BlogDto;
import com.filepackage.entity.Blog;

public interface IBlogService <BlogDto,Long> extends IBaseService<BlogDto,Long>{
    BlogDto createBlog(BlogDto commentDto);
}
