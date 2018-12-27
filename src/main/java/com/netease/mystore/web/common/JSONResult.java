package com.netease.mystore.web.common;

import java.io.Serializable;

/**
 * JSON返回数据格式对象
 * Created by switch on 16/11/14.
 */
public class JSONResult implements Serializable {
    /**
     * 错误码
     */
    public final static String ERROR_CODE = "401";
    /**
     * 成功码
     */
    public final static String SUCCESS_CODE = "200";
    /**
     * 失败响应结果
     */
    public final static String ERROR_RESULT = "0";
    /**
     * 成功响应结果
     */
    public final static String SUCCESS_RESULT = "1";
    /**
     * 成功消息
     */
    public final static String SUCCESS_MESSAGE = "请求成功";
    /**
     * 响应吗
     */
    private String code;
    /**
     * 响应消息
     */
    private String message;
    /**
     * 响应结果，0失败，1成功
     */
    private String result;

    public JSONResult() {

    }

    /**
     * 设置响应数据
     *
     * @param result  响应对象
     * @param code    响应码
     * @param message 响应消息
     * @param res     响应结果，0失败，1成功
     */
    public static void setStatusCode(JSONResult result, String code, String message, String res) {
        result.setCode(code);
        result.setMessage(message);
        result.setResult(res);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "JSONResult{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}
