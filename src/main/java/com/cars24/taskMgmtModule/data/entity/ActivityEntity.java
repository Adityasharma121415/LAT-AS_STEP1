package com.cars24.taskMgmtModule.data.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "task_execution")
public class ActivityEntity {

    @Id
    private String id;
    private String applicationId;
    private String taskId;
    private String version;
    private int order;
    private String templateId;
    private String templateVersion;
    private String funnel;
    private String channel;
    private String productType;
    private String entityIdentifier;
    private String entityType;
    private String actorType;
    private String actorId;
    private String status;
    private String executionType;
    private boolean automationSupported;
    private boolean optional;
    private Date createdAt;
    private Date updatedAt;
    private String handledBy;

}
