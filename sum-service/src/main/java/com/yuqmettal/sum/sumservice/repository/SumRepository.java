package com.yuqmettal.sum.sumservice.repository;

import java.util.List;
import java.util.Optional;

import com.yuqmettal.sum.sumservice.model.Sum;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SumRepository extends CrudRepository<Sum, Long> {
    public List<Sum> findByUserName(String username);
    public Optional<Sum> findByIdAndUserName(Long id, String username);
}