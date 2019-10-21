package com.example.demo.api.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class DemoVo {
    @ApiModelProperty("主键id")
    private String id;
    @ApiModelProperty("名称")
    private String name;
    @ApiModelProperty("电话")
    private String mobileNo;
}
