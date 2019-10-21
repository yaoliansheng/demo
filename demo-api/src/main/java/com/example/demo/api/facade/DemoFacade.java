package com.example.demo.api.facade;

import com.example.demo.api.base.ResponseResult;
import com.example.demo.api.request.DemoParam;
import com.example.demo.api.response.DemoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/demo")
@Api(tags = "测试接口")
public interface DemoFacade {
    /**
     *
     * @param param
     * @return 主键ID
     */
    @ApiOperation("新增，返回主键ID")
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    ResponseResult<String> add(@RequestBody DemoParam param);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    @ApiOperation("根据ID查询")
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    ResponseResult<DemoVo> queryById(@ApiParam("主键ID")@PathVariable("id")String id);

}
