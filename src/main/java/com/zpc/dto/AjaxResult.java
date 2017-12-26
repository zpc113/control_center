package com.zpc.dto;

/**
 * Created by 和谐社会人人有责 on 2017/12/4.
 * 所有的ajax请求返回类型
 */
public class AjaxResult<T> {

    private boolean success;

    private T data;

    private String error;

    public AjaxResult(boolean success) {
        this.success = success;
    }

    public AjaxResult(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public AjaxResult(boolean success, String error) {
        this.success = success;
        this.error = error;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
