package com.atguigu.ssyx.sys.controller;


import com.yx.ssyx.common.result.Result;
import com.atguigu.ssyx.model.sys.RegionWare;
import com.atguigu.ssyx.sys.service.RegionWareService;
import com.atguigu.ssyx.vo.sys.RegionWareQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * <p>
 * 城市仓库关联表 前端控制器
 * </p>
 *
 * @author xiongyou
 * @since 2023-04-03
 */
@RestController
@Api(tags = "开通区域接口")
@RequestMapping(value = "/admin/sys/regionWare", produces = APPLICATION_JSON_VALUE)
@AllArgsConstructor(onConstructor_ = @Autowired)
//@CrossOrigin
public class RegionWareController {

    private RegionWareService regionWareService;

    //开通区域列表
//    url: `${api_name}/${page}/${limit}`,
//    method: 'get',
//    params: searchObj
    @ApiOperation(value = "开通区域列表")
    @GetMapping("{page}/{limit}")
    public Result<IPage<RegionWare>> list(@PathVariable Long page,
                       @PathVariable Long limit,
                       RegionWareQueryVo regionWareQueryVo) {
        Page<RegionWare> pageParam = new Page<>(page,limit);
        IPage<RegionWare> pageModel = regionWareService.selectPageRegionWare(pageParam,regionWareQueryVo);
        return Result.ok(pageModel);
    }

    //添加开通区域
//    url: `${api_name}/save`,
//    method: 'post',
//    data: role
    @ApiOperation(value = "添加开通区域")
    @PostMapping("save")
    public Result<String> addRegionWare(@RequestBody RegionWare regionWare) {
        regionWareService.saveRegionWare(regionWare);
        return Result.ok("添加成功。");
    }

    //删除开通区域
//    url: `${api_name}/remove/${id}`,
//    method: 'delete'
    @ApiOperation(value = "删除开通区域")
    @DeleteMapping("remove/{id}")
    public Result<String> remove(@PathVariable Long id) {
        if (regionWareService.removeById(id)) {
            return Result.ok("删除成功。");
        }
        return Result.fail("删除失败！");
    }

    //取消开通区域
//    url: `${api_name}/updateStatus/${id}/${status}`,
//    method: 'post'
    @ApiOperation(value = "取消开通区域")
    @PostMapping("updateStatus/{id}/{status}")
    public Result<String> updateStatus(@PathVariable Long id,
                               @PathVariable Integer status) {
        regionWareService.updateStatus(id,status);
        return Result.ok("取消成功。");
    }
}

