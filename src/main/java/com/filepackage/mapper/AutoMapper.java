package com.filepackage.mapper;

public class AutoMapper {
    @Service
    public class AutoMapper {
        private final ModelMapper modelMapper;

        public  AutoMapper()
        {
            this.modelMapper= new ModelMapper();
        }
        public <D, T> D convertToDto(T entity, Class<D> dtoClass) {
            return modelMapper.map(entity, dtoClass);
        }

        public <T, D> T convertToEntity(D dto, Class<T> entityClass) {
            return modelMapper.map(dto, entityClass);
        }
}
