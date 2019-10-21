/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，1995-2017，所有权利保留。
 * 项目名：gift-card
 * 文件名：ResponseResult
 * 模块说明：
 * 修改历史：
 * 2017/8/1 - liweihua - 创建
 */
package com.example.demo.api.base;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
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
