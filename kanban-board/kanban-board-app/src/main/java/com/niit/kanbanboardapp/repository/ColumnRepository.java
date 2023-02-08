package com.niit.kanbanboardapp.repository;

import com.niit.kanbanboardapp.domain.Column;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ColumnRepository extends MongoRepository<Column, String> { }
