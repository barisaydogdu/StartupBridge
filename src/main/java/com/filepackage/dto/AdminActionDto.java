package com.filepackage.dto;

public class AdminActionDto {
    private Long actionId;
    private Integer adminId;
    private String actionType;
    private Integer targetId;
    private String actionDescription;
    private String actionTimestamp;


    public AdminActionDto() {}

    public AdminActionDto(Long actionId, Integer adminId, String actionType, Integer targetId, String actionDescription, String actionTimestamp) {
        this.actionId = actionId;
        this.adminId = adminId;
        this.actionType = actionType;
        this.targetId = targetId;
        this.actionDescription = actionDescription;
        this.actionTimestamp = actionTimestamp;
    }

    public Long getActionId() {
        return actionId;
    }

    public void setActionId(Long actionId) {
        this.actionId = actionId;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public Integer getTargetId() {
        return targetId;
    }

    public void setTargetId(Integer targetId) {
        this.targetId = targetId;
    }

    public String getActionDescription() {
        return actionDescription;
    }

    public void setActionDescription(String actionDescription) {
        this.actionDescription = actionDescription;
    }

    public String getActionTimestamp() {
        return actionTimestamp;
    }

    public void setActionTimestamp(String actionTimestamp) {
        this.actionTimestamp = actionTimestamp;
    }
}
