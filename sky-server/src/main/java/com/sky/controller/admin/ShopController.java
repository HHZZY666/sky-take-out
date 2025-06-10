package com.sky.controller.admin;

import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.DoubleSummaryStatistics;

@RestController("adminShopController")
@RequestMapping("/admin/shop")
@Api(tags = "店铺相关接口")
@Slf4j
public class ShopController {

    public static final String KEY = "SHOP_STATUS";

    @Autowired
    public RedisTemplate  redisTemplate;

    /**
     * 设置营业状态
     * @param status
     * @return
     */
    @PutMapping("/{status}")
    @ApiOperation("设置营业状态")
    public Result setStatues(@PathVariable Integer status){
        log.info("设置营业状态:{}",status == 1 ? "营业中" : "打烊中");
        redisTemplate.opsForValue().set("KEY",status);
        return Result.success();
    }

    /**
     * 查询营业状态
     * @return
     */
    @GetMapping("/status")
    @ApiOperation("查询营业状态")
    public Result<Integer> getStatues(){
        Integer status = (Integer) redisTemplate.opsForValue().get("KEY");
        log.info("获取营业状态:{}",status == 1 ? "营业中" : "打烊中");
        return Result.success(status);
    }
}






