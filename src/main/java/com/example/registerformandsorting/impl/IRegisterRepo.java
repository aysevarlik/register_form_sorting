package com.example.registerformandsorting.impl;

import com.example.registerformandsorting.Dto.RegisterEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRegisterRepo extends PagingAndSortingRepository<RegisterEntity, Long> {
    List<RegisterEntity> findAll(Sort sort);
}
