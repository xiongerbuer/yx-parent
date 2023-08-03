package com.yx.gkyx.activity.controller;


import com.yx.gkyx.activity.service.ActivityInfoService;
import com.yx.gkyx.common.result.Result;
import com.yx.gkyx.model.activity.ActivityInfo;
import com.yx.gkyx.model.product.SkuInfo;
import com.yx.gkyx.vo.activity.ActivityRuleVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * <p>
 * 活动表 前端控制器
 * </p>
 */
@RestController
@Api(tags = "活动相关信息接口")
@RequestMapping(value = "/admin/activity/activityInfo", produces = APPLICATION_JSON_VALUE)
@AllArgsConstructor(onConstructor_ = @Autowired)
//@CrossOrigin
public class ActivityInfoController {

    private ActivityInfoService activityInfoService;

    //列表
//    url: `${api_name}/${page}/${limit}`,
//    method: 'get'
    @GetMapping("{page}/{limit}")
    @ApiOperation(value = "活动列表")
    public Result<IPage<ActivityInfo>> list(@PathVariable Long page,
                                            @PathVariable Long limit) {
        Page<ActivityInfo> pageParam = new Page<>(page, limit);
        IPage<ActivityInfo> pageModel = activityInfoService.selectPage(pageParam);
        return Result.ok(pageModel);
    }

    //    url: `${api_name}/get/${id}`,
//    method: 'get'
    @GetMapping("get/{id}")
    @ApiOperation(value = "活动详情")
    public Result<ActivityInfo> get(@PathVariable Long id) {
        ActivityInfo activityInfo = activityInfoService.getById(id);
        activityInfo.setActivityTypeString(activityInfo.getActivityType().getComment());
        return Result.ok(activityInfo);
    }

    //添加活动
//    url: `${api_name}/save`,
//    method: 'post',
//    data: role
    @PostMapping("save")
    @ApiOperation(value = "添加活动")
    public Result<String> save(@RequestBody ActivityInfo activityInfo) {
        if (activityInfoService.save(activityInfo)) {
            return Result.ok("成功添加活动。");
        }
        return Result.fail("添加活动失败！");
    }

    //营销活动规则相关接口
    //1 根据活动id获取活动规则数据
//    url: `${api_name}/findActivityRuleList/${id}`,
//    method: 'get'
    @GetMapping("findActivityRuleList/{id}")
    @ApiOperation(value = "根据活动id获取活动规则数据")
    public Result<Map<String, Object>> findActivityRuleList(@PathVariable Long id) {
        Map<String, Object> activityRuleMap = activityInfoService.findActivityRuleList(id);
        return Result.ok(activityRuleMap);
    }

    //2 在活动里面添加规则数据
//    url: `${api_name}/saveActivityRule`,
//    method: 'post',
//    data: rule
    @PostMapping("saveActivityRule")
    @ApiOperation(value = "在活动里面添加规则数据")
    public Result<Boolean> saveActivityRule(@RequestBody ActivityRuleVo activityRuleVo) {
        activityInfoService.saveActivityRule(activityRuleVo);
        return Result.ok(true);
    }

    //3 根据关键字查询匹配sku信息
//    url: `${api_name}/findSkuInfoByKeyword/${keyword}`,
//    method: 'get'
    @GetMapping("findSkuInfoByKeyword/{keyword}")
    @ApiOperation(value = "根据关键字查询匹配sku信息")
    public Result<List<SkuInfo>> findSkuInfoByKeyword(@PathVariable("keyword") String keyword) {
        List<SkuInfo> list = activityInfoService.findSkuInfoByKeyword(keyword);
        return Result.ok(list);
    }

}

