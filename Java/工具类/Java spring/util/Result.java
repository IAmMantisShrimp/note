package com.example.backend.util;

import lombok.Data;

/**
 * @author HuaRunSheng
 * @date 2022/10/9 11:48
 * @description :响应工具类
 */
@Data
public class Result {
    // 响应给前端是否成功的标志
    private boolean flag;
    // 响应信息
    private String message;
    // 响应数据
    private Object data;
    public Result(boolean flag, String message, Object data){
        this.flag=flag;
        this.message=message;
        this.data=data;
    }
    public Result(boolean flag, String message){
        this.flag=flag;
        this.message=message;
    }

    public Result() {
    }

    // 响应成功
    public static Result success(String message, Object data){
         return new Result(true,message,data);
    }
    public static Result success(){
        return new Result(true,"返回成功",null);
    }
    // 响应失败
    public static Result fail(String message){
        return new Result(false, message);
    }
    public static Result fail(){
        return new Result(false, "返回失败");
    }
}
