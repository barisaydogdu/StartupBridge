package com.filepackage.service;

import com.filepackage.dto.CommentsDto;
import com.filepackage.entity.Comments;

public interface ICommentsService <CommentsDto,Long> extends IBaseService<CommentsDto,Long>{
   CommentsDto createComments(CommentsDto commentDto);
}
