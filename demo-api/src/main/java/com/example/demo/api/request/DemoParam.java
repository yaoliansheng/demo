package com.example.demo.api.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class DemoParam {
    @ApiModelProperty("名称")
    private String name;
    @ApiModelProperty("电话")
    private String mobileNo;
}
