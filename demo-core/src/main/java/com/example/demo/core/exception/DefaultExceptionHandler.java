package com.example.demo.core.exception;

import com.example.demo.api.base.ResponseResult;
import com.example.demo.api.base.ReturnCodeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;
import java.util.List;

@ControllerAdvice
@ResponseBody
public class DefaultExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(ExceptionHandler.class);

    /**
     * 验证框架@NotNull等注解验证时抛这个异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseResult<String> processValidError(MethodArgumentNotValidException ex) {
        ResponseResult<String> resp = new ResponseResult<>();
        BindingResult bindingResult = ex.getBindingResult();//绑定错误返回很多错误，是一个错误列表，只需要第一个错误
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        ObjectError error = allErrors.get(0);
        String msg = error.getDefaultMessage();
        resp.setStatus(ReturnCodeEnum.BAD_REQUEST.value());
        resp.setMessage(msg);//给状态码填充参数
        return resp;
    }

    /**
     * 参数异常
     *
     * @param pe
     * @return
     */
    @ExceptionHandler(value = {IllegalArgumentException.class, ParamInvalidException.class})
    public ResponseResult<String> processParamInvalidError(Exception pe) {
        log.error("error msg:{}, exception:{}", pe.getMessage(), pe);
        ResponseResult<String> resp = new ResponseResult<String>();
        resp.setStatus(ReturnCodeEnum.BAD_REQUEST.value());
        resp.setMessage(pe.getMessage());
        return resp;
    }

    /**
     * JSON参数异常拦截
     * @param e
     * @return
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseResult<String> processJsonError(HttpMessageNotReadableException e) {
        ResponseResult<String> resp = new ResponseResult<>();
        log.error("异常堆栈信息：{}", e);
        log.error("traceId:{}, exception:{}",  e.getMessage(), e);
        resp.setStatus(ReturnCodeEnum.BAD_REQUEST.value());
        resp.setMessage("JSON解析异常");
        return resp;
    }

    /**
     * 数据库异常拦截
     * @param e
     * @return
     */
    @ExceptionHandler(value = {DataAccessException.class, SQLException.class})
    public ResponseResult<String> processSQLError(Exception e) {
        //默认提示
        String desc = "数据库表操作出错";
        //如果是DBException 抛出异常信息
        if (e instanceof DBException){
            desc = e.getMessage();
        }else if(e.getMessage().contains("Duplicate entry")){
            desc = e.getMessage();
        }
        ResponseResult<String> resp = new ResponseResult<>();
        log.error("数据库表操作异常:{}",  e.getMessage(), e);
        resp.setStatus(ReturnCodeEnum.DB_ERROR.value());
        resp.setMessage(desc);
        return resp;
    }

    /**
     * 异常拦截
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResponseResult<String> processError(Exception e) {
        ResponseResult<String> resp = new ResponseResult<>();
        log.error("异常堆栈信息：{}", e);
        log.error(e.getMessage(), e);
        resp.setStatus(ReturnCodeEnum.FAIL.value());
        resp.setMessage(ReturnCodeEnum.FAIL.getDesc());
        return resp;
    }


}
