package com.cars24.taskMgmtModule.controller;

import com.cars24.taskMgmtModule.data.entity.ActivityEntity;
import com.cars24.taskMgmtModule.data.response.ActivityLogResponse;
import com.cars24.taskMgmtModule.service.impl.ActivityLogServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("activity")
@Slf4j
@RequiredArgsConstructor
public class ActivityLogController {

    private final ActivityLogServiceImpl activityLogService;

    @GetMapping(path = "{applicationId}")
    public List<ActivityLogResponse> getActivityLogByAppId(@PathVariable String applicationId){
        return activityLogService.getActivityLog(applicationId);
    }

}
