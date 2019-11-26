package com.distributedsystems.consumer.repository;

import com.distributedsystems.consumer.model.RequestLogs;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogsRepository extends CrudRepository<RequestLogs, String> {
}
