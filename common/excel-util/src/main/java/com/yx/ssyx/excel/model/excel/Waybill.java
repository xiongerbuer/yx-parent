package com.yx.ssyx.excel.model.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.HeadStyle;
import com.yx.ssyx.excel.annotation.Column;
import com.yx.ssyx.excel.annotation.Table;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 发运单
 */
@Data
@Table(name = "waybill")
@HeadStyle(fillBackgroundColor = 9)
public class Waybill extends ExcelModel {

    @ExcelProperty("单据编号")
    @NotNull(message = "单据编号不能为空")
    @Column(primaryKey = true, keyOrder = 0)
    private String orderCode;
    @ExcelProperty("业务日期")
    @NotEmpty(message = "业务日期不能为空")
    @DateTimeFormat(pattern = "yy-MM--dd")
    private String bizDate;
    @ExcelProperty("单据状态")
    private String orderStatus;
    @ExcelProperty("来源单据类型")
    private String srcOrderType;
    @ExcelProperty("发运类型")
    private String shippingType;
    @ExcelProperty("发运组织")
    private String organization;
    @ExcelProperty("主承运方")
    private String carrier;
    @ExcelProperty("发运总路线")
    private String route;
    @ExcelProperty("运输方式")
    private String shippingMode;
    @ExcelProperty("行号")
    @NotNull(message = "行号不能为空")
    @Column(primaryKey = true, keyOrder = 1)
    private String lineNum;
    @ExcelProperty("物料编码")
    @NotNull(message = "物料编码不能为空")
    @Column(primaryKey = true, keyOrder = 2)
    @NotBlank(message = "物料编码不能为空")
    private String materialCode;
    @ExcelProperty("物料名称")
    @NotNull(message = "物料名称不能为空")
    @Column(primaryKey = true, keyOrder = 3)
    @NotBlank(message = "物料名称不能为空")
    private String materialName;
    @ExcelProperty("规格型号")
    @Column(primaryKey = true, keyOrder = 4)
    private String specification;
    @ExcelProperty("辅助属性")
    private String extraAttr;
    @ExcelProperty("批次")
    private String batchNum;
    @ExcelProperty("项目号")
    private String projectCode;
    @ExcelProperty("跟踪号")
    private String trackCode;
    @ExcelProperty("车辆牌照")
    private String licensePlate;
    @ExcelProperty("司机")
    private String driver;
    @ExcelProperty("计量单位")
    @NotNull(message = "计量单位不能为空")
    @Column(primaryKey = true, keyOrder = 6)
    private String measurementUnit;
    @ExcelProperty("数量")
    private String count;
    @ExcelProperty("合理损耗率(%)")
    private String attritionRate;
    @ExcelProperty("合理损耗数量")
    private String attritionCount;
    @ExcelProperty("合理损耗基本数量")
    private String basicAttritionCount;
    @ExcelProperty("实际发货/提货数量")
    private String shipmentPickupRate;
    @ExcelProperty("签收数量")
    private String signedCount;
    @ExcelProperty("签收途损数量")
    private String transitLoss;
    @ExcelProperty("拒收数量")
    private String refuseCount;
    @ExcelProperty("基本计量单位")
    private String basicUnit;
    @ExcelProperty("基本数量")
    private String basicCount;
    @ExcelProperty("辅助计量单位")
    private String extraUnit;
    @ExcelProperty("辅助数量")
    private String extraCount;
    @ExcelProperty("实收数量")
    private String actualReceived;
    @ExcelProperty("实收途损数量")
    private String actualTransitLoss;
    @ExcelProperty("收货未关联基本数量")
    private String receiveUnassociatedCount;
    @ExcelProperty("已应收数量")
    private String receivableCount;
    @ExcelProperty("已应收金额")
    private BigDecimal receivableAmount;
    @ExcelProperty("发货方")
    private String sender;
    @ExcelProperty("发货组织")
    private String senderOrg;
    @ExcelProperty("发货仓库")
    private String senderWarehouse;
    @ExcelProperty("收货方")
    private String receiver;
    @ExcelProperty("收货组织")
    private String receiverOrg;
    @ExcelProperty("收货仓库")
    private String receiverWarehouse;
    @ExcelProperty("发运路线")
    private String shippingRoute;
    @ExcelProperty("起运站")
    private String shippingStart;
    @ExcelProperty("到货站")
    private String shippingEnd;
    @ExcelProperty("承运方")
    private String shipper;
    @ExcelProperty("车船号")
    private String shipNumber;
    @ExcelProperty("计划提货时间")
    @DateTimeFormat(pattern = "yy-MM-dd")
    private String plannedPickupTime;
    @ExcelProperty("计划交货时间")
    @DateTimeFormat(pattern = "yy-MM-dd")
    private String plannedDeliveryTime;
    @ExcelProperty("未关联基本数量")
    private String unassociatedCount;
    @ExcelProperty("分录状态")
    private String entryStatus;
    @ExcelProperty("来源单据编号")
    private String srcOrderCode;
    @ExcelProperty("来源单据分录序号")
    private String srcEntryCode;
    @ExcelProperty("核心单据类型")
    @NotBlank(message = "核心单据类型不能为空")
    private String coreOrderType;
    @ExcelProperty("核心单据号")
    @NotBlank(message = "核心单据号不能为空")
    private String coreOrderCode;
    @ExcelProperty("核心单据分录序号")
    private String coreEntryCode;
    @ExcelProperty("制单人")
    @NotBlank(message = "制单人不能为空")
    private String creator;
    @ExcelProperty("制单日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "制单日期不能为空")
    private String createdTime;
    @ExcelProperty("修改人")
    private String modifier;
    @ExcelProperty("修改日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String modifiedTime;
    @ExcelProperty("审核人")
    private String reviewer;
    @ExcelProperty("审核日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String reviewTime;
    @ExcelProperty("摘要")
    private String summary;
    @ExcelProperty("备注")
    private String remark;
    @ExcelProperty("签收日期")
    @DateTimeFormat(pattern = "yy-MM-dd")
    private String signedDate;
    @ExcelProperty("协同单据类型")
    private String joinOrderType;
    @ExcelProperty("协同单据编号")
    private String joinOrderCode;
    @ExcelProperty("集中结算")
    private String settlementSum;
    @ExcelProperty("实际发运日期")
//    @NotNull(message = "实际发运日期不能为空")
    @DateTimeFormat(pattern = "yy-MM-dd")
    private String actualShippingDate;
    @ExcelProperty("当前节点")
    private String currentLocation;

}
