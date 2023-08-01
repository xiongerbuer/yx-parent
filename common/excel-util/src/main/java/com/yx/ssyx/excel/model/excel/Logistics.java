package com.yx.ssyx.excel.model.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.HeadStyle;
import com.yx.ssyx.excel.annotation.Column;
import com.yx.ssyx.excel.annotation.Table;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 物流费用单
 */
@Data
@Table(name = "logistics")
@HeadStyle(fillBackgroundColor = 9)
public class Logistics extends ExcelModel {

    @ExcelProperty("单据编号")
    @NotNull(message = "物流编号不能为空")
    @Column(primaryKey = true, keyOrder = 0)
    private String orderCode;
    @ExcelProperty("单据状态")
    private String orderStatus;
    @ExcelProperty("业务日期")
    private String bizDate;
    @ExcelProperty("来源单据类型")
    private String srcOrderType;
    @ExcelProperty("发运类型")
    private String shippingType;
    @ExcelProperty("发运组织")
    private String shippingOrg;
    @ExcelProperty("核算单位")
    private String accountingUnit;
    @ExcelProperty("物流提供商")
    private String logisticsProvider;
    @ExcelProperty("分录序号")
    @NotNull(message = "分录序号不能为空")
    @Column(primaryKey = true, keyOrder = 1)
    private String entryCode;
    @ExcelProperty("结算单位")
    private String settlementUnit;
    @ExcelProperty("发运路线")
    private String shippingRoute;
    @ExcelProperty("起运站")
    private String shippingStart;
    @ExcelProperty("到货站")
    private String shippingEnd;
    @ExcelProperty("费用项目")
    private String expenseItem;
    @ExcelProperty("运输方式")
    private String shippingMode;
    @ExcelProperty("物料编码")
    @NotNull(message = "物料编码不能为空")
    @Column(primaryKey = true, keyOrder = 2)
    private String materialCode;
    @ExcelProperty("物料名称")
    @NotNull(message = "物料名称不能为空")
    @Column(primaryKey = true, keyOrder = 3)
    private String materialName;
    @ExcelProperty("计量单位")
    @NotNull(message = "计量单位不能为空")
    @Column(primaryKey = true, keyOrder = 4)
    private String measurementUnit;
    @ExcelProperty("发运数量")
    private String shippingCount;
    @ExcelProperty("结算数量")
    private String settlementCount;
    @ExcelProperty("定额单价")
    private String quotaPrice;
    @ExcelProperty("定额金额")
    private BigDecimal quotaAmount;
    @ExcelProperty("每公里运费")
    private String shippingPrice;
    @ExcelProperty("实际里程数（公里）")
    private String actualMileage;
    @ExcelProperty("结算单价")
    private String settlementPrice;
    @ExcelProperty("结算金额")
    private BigDecimal settlementAmount;
    @ExcelProperty("累计应付数量")
    private String payableCount;
    @ExcelProperty("累计应付金额")
    private BigDecimal payableAmount;
    @ExcelProperty("未关联基本数量")
    private String unassociatedCount;
    @ExcelProperty("分录状态")
    private String entryStatus;
    @ExcelProperty("来源单据编号")
    private String srcOrderCode;
    @ExcelProperty("来源单据分录序号")
    private String srcEntryCode;
    @ExcelProperty("核心单据类型")
    private String coreOrderType;
    @ExcelProperty("核心单据号")
    private String coreOrderCode;
    @ExcelProperty("核心单据分录序号")
    private String coreEntryCode;
    @ExcelProperty("制单人")
    private String creator;
    @ExcelProperty("制单日期")
    private String createdTime;
    @ExcelProperty("修改人")
    private String modifier;
    @ExcelProperty("修改日期")
    private String modifiedTime;
    @ExcelProperty("审核人")
    private String reviewer;
    @ExcelProperty("审核日期")
    private String reviewTime;
    @ExcelProperty("摘要")
    private String summary;
    @ExcelProperty("备注")
    private String remark;
    @ExcelProperty("押运费单价")
    private String escortPrice;
    @ExcelProperty("实际押运费")
    private String escortAmount;
    @ExcelProperty("押运员")
    private String escorter;
    @ExcelProperty("是否生成凭证")
    private String hasCertificate;
    @ExcelProperty("实际发运日期")
    private String actualShippingDate;
    @ExcelProperty("车辆牌照")
    private String licensePlate;
    @ExcelProperty("当前节点")
    private String currentLocation;

}
