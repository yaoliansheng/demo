package com.example.demo.core.exception;

import com.example.demo.api.base.ResponseResult;
import com.example.demo.api.base.ReturnCodeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
public class DefaultExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(ExceptionHandler.class);

    /**
     * 自定义参数异常拦截
     * @param pe
     * @return
     */
    @ExceptionHandler(ParamInvalidException.class)
    public ResponseResult<String> processParamInvalidError(ParamInvalidException pe) {
        ResponseResult<String> resp = new ResponseResult<>();
        log.error("参数检查异常：{}", pe);
        resp.setStatus(pe.getCode());
        resp.setMessage(pe.getDesc());
        return resp;
    }


    /**
     * 异常拦截
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResponseResult<String> processError(Exception e) {
        ResponseResult<String> resp = new ResponseResult<>();
        log.error("异常堆栈信息：{}", e);
        resp.setStatus(ReturnCodeEnum.FAIL.value());
        resp.setMessage(ReturnCodeEnum.FAIL.getDesc());
        return resp;
    }


}
