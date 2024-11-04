package com.filepackage.service;

import java.util.List;

public interface IBaseService <E,ID>{
    E getById(ID userId);
    List<E> getAll();
    void delete(ID userId);
    E update(ID userId, E updatedUser);
}
