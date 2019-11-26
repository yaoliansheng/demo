package com.example.demo.api.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
//这个是类注解,表示该类实例化的对象里，值为null的字段不参与序列化
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseResult<T> {
    private String status = ReturnCodeEnum.SUCCESS.value();
    private String message = ReturnCodeEnum.SUCCESS.getDesc();

    private T data;

    public ResponseResult() {
    }

    public ResponseResult(T data) {
        this.data = data;
    }

    public ResponseResult(ReturnCodeEnum result) {
        if (result != null) {
            this.status = result.value();
            this.message = result.getDesc();
        }
    }

    /**
     * 成功不传数据返回
     * @return
     */
    public static ResponseResult ok() {
        return new ResponseResult();
    }

    /**
     * 成功传数据返回
     * @return
     */
    public static<T> ResponseResult ok(T data) {
        ResponseResult resp = new ResponseResult();
        resp.setData(data);
        return resp;
    }

    /**
     * 错误
     * @return
     */
    public static ResponseResult error() {
        return new ResponseResult(ReturnCodeEnum.FAIL);
    }

    /**
     * 参数错误
     * @return
     */
    public static ResponseResult paramError() {
        return new ResponseResult(ReturnCodeEnum.BAD_REQUEST);
    }

    public ResponseResult(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public ResponseResult(String status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

}
