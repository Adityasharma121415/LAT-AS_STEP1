package com.cars24.taskMgmtModule.service.impl;

import com.cars24.taskMgmtModule.data.response.ActivityLogResponse;
import com.cars24.taskMgmtModule.service.ActivityLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class ActivityLogServiceImpl implements ActivityLogService {

    private final MongoTemplate mongoTemplate;

    private static final List<String> FUNNEL_ORDER = Arrays.asList(
            "SOURCING", "CREDIT", "CONVERSION", "FULFILMENT", "RTO", "RISK", "DISBURSAL"
    );

    @Override
    public List<ActivityLogResponse> getActivityLog(String applicationId) {
        log.info("Fetching activity log for applicationId: {}", applicationId);

        Aggregation aggregation = Aggregation.newAggregation(
            Aggregation.match(Criteria.where("applicationId").is(applicationId)),
            Aggregation.group("funnel")
                .push(new Document("taskId", "$taskId")
                        .append("status", "$status")
                        .append("order", "$order")
                        .append("actorId", "$actorId")
                        .append("updatedAt", "$updatedAt")
                )
                .as("tasks"),
            Aggregation.project("tasks")
                .and("_id").as("funnel")
                .andExclude("_id")
        );

        AggregationResults<Document> results = mongoTemplate.aggregate(aggregation, "task_execution", Document.class);

        List<ActivityLogResponse> activityLogs = new ArrayList<>();
        for (Document doc : results.getMappedResults()) {
            ActivityLogResponse logResponse = new ActivityLogResponse();
            logResponse.setFunnel(doc.getString("funnel"));

            List<Map<String, Object>> tasks = (List<Map<String, Object>>) doc.get("tasks");
            tasks.sort(Comparator.comparingInt(task -> (int) task.get("order")));

            logResponse.setTasks(tasks);
            activityLogs.add(logResponse);
        }

        activityLogs.sort(Comparator.comparingInt(log -> {
            String funnel = log.getFunnel();

            if (funnel == null) {
                return Integer.MAX_VALUE;
            }

            int index = FUNNEL_ORDER.indexOf(funnel.toUpperCase());
            return index == -1 ? Integer.MAX_VALUE : index;
        }));

        return activityLogs;
    }
}
