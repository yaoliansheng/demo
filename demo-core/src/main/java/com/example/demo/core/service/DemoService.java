package com.example.demo.core.service;

import com.example.demo.api.request.DemoParam;
import com.example.demo.api.response.DemoVo;

public interface DemoService {
    /**
     * 新增返回主键ID
     * @param param
     * @return
     */
    String add(DemoParam param);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    DemoVo queryById(String id);
}
