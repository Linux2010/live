package cn.com.myproject.api.util;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by liyang-macbook on 2017/6/27.
 */
public class Message<T> implements Serializable {
    /**
     * 请求结果，0为失败，1为成功
     */
    @ApiModelProperty(value="请求结果，0为失败，1为成功",example = "1")
    private int result;


    /**
     * 请求结果信息
     */
    @ApiModelProperty(value = "请求结果信息",example = "成功")
    private String message;


    /**
     * 请求的返回数据对象，也将被转为json格式
     */
    @ApiModelProperty("请求的返回数据对象，也将被转为json格式")
    private T data;

    public Message() {

    }

    public Message(int result,String message) {
        this.result = result;
        this.message = message;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
