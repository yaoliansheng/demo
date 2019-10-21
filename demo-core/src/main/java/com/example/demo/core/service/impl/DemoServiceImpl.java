package com.example.demo.core.service.impl;

import com.dangdang.ddframe.rdb.sharding.id.generator.IdGenerator;
import com.example.demo.api.base.ReturnCodeEnum;
import com.example.demo.api.request.DemoParam;
import com.example.demo.api.response.DemoVo;
import com.example.demo.core.entity.Demo;
import com.example.demo.core.exception.ParamInvalidException;
import com.example.demo.core.mapper.DemoMapper;
import com.example.demo.core.service.DemoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    private DemoMapper demoMapper;
    @Autowired
    private IdGenerator idGenerator;

    @Override
    public String add(DemoParam param) {
        verifyAddParam(param);
        Demo demo = convertDemoParamToDemo(param);
        demoMapper.insert(demo);
        return demo.getId().toString();
    }

    @Override
    public DemoVo queryById(String id) {
        long demoId;
        try {
            demoId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new ParamInvalidException(ReturnCodeEnum.BAD_REQUEST.value(),"参数ID不合法");
        }
        Demo demo = demoMapper.selectByPrimaryKey(demoId);
        if (demo == null){
            throw new ParamInvalidException(ReturnCodeEnum.BAD_REQUEST.value(),"没有找到要查询的数据");
        }
        return convertDemoToDemoVo(demo);
    }

    private DemoVo convertDemoToDemoVo(Demo demo) {
        DemoVo demoVo = new DemoVo();
        demoVo.setId(demo.getId().toString());
        demoVo.setName(demo.getName());
        demoVo.setMobileNo(demo.getMobileNo());

        return demoVo;
    }

    /**
     * 简单实现一下
     *
     * @param param
     * @throws ParamInvalidException
     */
    private void verifyAddParam(DemoParam param) {
        if (StringUtils.isBlank(param.getName())) {
            throw new ParamInvalidException(ReturnCodeEnum.BAD_REQUEST.value(), "名称不能为空");
        }
        if (StringUtils.isBlank(param.getMobileNo())) {
            throw new ParamInvalidException(ReturnCodeEnum.BAD_REQUEST.value(), "手机号码不能为空");
        }
    }

    /**
     * 对象转换
     * @param param
     * @return Demo
     */
    private Demo convertDemoParamToDemo(DemoParam param) {
        Demo demo = new Demo();
        demo.setId(idGenerator.generateId().longValue());
        demo.setName(param.getName());
        demo.setMobileNo(param.getMobileNo());
        return demo;
    }
}
