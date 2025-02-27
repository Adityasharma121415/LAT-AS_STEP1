package com.cars24.taskMgmtModule.repository;

import com.cars24.taskMgmtModule.data.entity.ActivityEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityLogRepository extends MongoRepository<ActivityEntity, String> {

}
