package com.example.demo.core.facadeImpl;

import com.example.demo.api.base.ResponseResult;
import com.example.demo.api.facade.DemoFacade;
import com.example.demo.api.request.DemoParam;
import com.example.demo.api.response.DemoVo;
import com.example.demo.core.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoFacadeImpl implements DemoFacade {
    @Autowired
    private DemoService demoService;

    @Override
    public ResponseResult<String> add(DemoParam param) {
        ResponseResult<String> resp = new ResponseResult<>();
        resp.setData(demoService.add(param));
        return resp;
    }

    @Override
    public ResponseResult<DemoVo> queryById(String id) {
        ResponseResult<DemoVo> resp = new ResponseResult<>();
        resp.setData(demoService.queryById(id));
        return resp;
    }


}
