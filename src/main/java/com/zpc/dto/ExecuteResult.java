package com.zpc.dto;

/**
 * Created by 和谐社会人人有责 on 2017/12/5.
 */
public class ExecuteResult {

    private boolean success;

    private String message;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ExecuteResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public ExecuteResult() {
    }
}
