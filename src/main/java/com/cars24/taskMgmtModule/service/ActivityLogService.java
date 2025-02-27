package com.cars24.taskMgmtModule.service;

import com.cars24.taskMgmtModule.data.entity.ActivityEntity;
import com.cars24.taskMgmtModule.data.response.ActivityLogResponse;

import java.util.List;

public interface ActivityLogService {

    List<ActivityLogResponse> getActivityLog(String applicationId);

}