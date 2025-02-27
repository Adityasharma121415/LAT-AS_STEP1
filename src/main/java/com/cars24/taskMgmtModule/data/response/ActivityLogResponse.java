package com.cars24.taskMgmtModule.data.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityLogResponse {
    private String funnel;
    private List<Map<String, Object>> tasks;
}
