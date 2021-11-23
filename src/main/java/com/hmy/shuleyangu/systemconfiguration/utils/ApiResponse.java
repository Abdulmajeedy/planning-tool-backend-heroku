package com.hmy.shuleyangu.systemconfiguration.utils;

import java.util.List;

public class ApiResponse<T> {
    public boolean status;
    public String message;
    public T data;
    public List<String> error;
    public int pageNumber;
    public int pageSize;

    public ApiResponse(boolean status, String message, T data, List<String> error) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.error = error;
    }
    public ApiResponse(boolean status, String message, T data, List<String> error, int pageNumber, int pageSize) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.error = error;
//        this.pageNumber = pageNumber;
//        this.pageSize = pageSize;
    }
    public ApiResponse(boolean status, String message){
        this.status = status;
        this.message = message;

    }

    public static <Data> ApiResponse<Data> error(String message, List<String> error) {
        return new ApiResponse<>(false, message, null, error);
    }

    public static <Data> ApiResponse<Data> success(Data data) {
        return new ApiResponse<>(true, "Success", data, null);
    }

    public static ApiResponse ok(String message) {
        return new ApiResponse(true, message);
    }
}
