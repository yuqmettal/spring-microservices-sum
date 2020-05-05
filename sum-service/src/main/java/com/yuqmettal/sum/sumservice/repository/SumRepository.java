package com.yuqmettal.sum.sumservice.repository;

import com.yuqmettal.sum.sumservice.model.Sum;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SumRepository extends CrudRepository<Sum, Long> {

}