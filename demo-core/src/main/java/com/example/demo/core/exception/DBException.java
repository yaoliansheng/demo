package com.example.demo.core.exception;

import com.example.demo.api.base.ReturnCodeEnum;
import lombok.Data;
import org.springframework.dao.DataAccessException;

@Data
public class DBException extends DataAccessException {
    private static final long serialVersionUID = 1L;

    private String code;
    private String desc;

    public DBException(String code, String desc) {
        super(desc);
        this.code = code;
        this.desc = desc;
    }

    /**
     * 默认code 8000
     *
     * @param desc
     */
    public DBException(String desc) {
        super(desc);
        this.code = ReturnCodeEnum.DB_ERROR.value();
        this.desc = desc;
    }

}
