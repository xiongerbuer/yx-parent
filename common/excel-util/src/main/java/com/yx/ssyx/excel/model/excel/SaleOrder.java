package com.yx.ssyx.excel.model.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.HeadStyle;
import com.yx.ssyx.excel.annotation.Column;
import com.yx.ssyx.excel.annotation.Table;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 销售单
 */
@Data
@Table(name = "saleOrder")
@HeadStyle(fillBackgroundColor = 9)
public class SaleOrder extends ExcelModel {

    @ExcelProperty("订单号")
    @NotNull(message = "订单号不能为空")
    @Column(primaryKey = true, keyOrder = 0)
    private String saleOrderCode;
    @ExcelProperty("订单日期")
    @NotNull(message = "订单日期不能为空")
    private String saleOrderDate;
    @ExcelProperty("单据状态")
    private String orderStatus;
    @ExcelProperty("业务类型")
    private String bizType;
    @ExcelProperty("客户")
    private String customer;
    @ExcelProperty("客户编码")
    private String customerCode;
    @ExcelProperty("客户订单号")
    private String customerOrderCode;
    @ExcelProperty("销售组织")
    private String salesOrg;
    @ExcelProperty("销售方仓库编码")
    private String salesWarehouseCode;
    @ExcelProperty("销售方仓库名称")
    private String salesWarehouseName;
    @ExcelProperty("结算方仓库编码")
    private String settleWarehouseCode;
    @ExcelProperty("结算方仓库名称")
    private String settleWarehouseName;
    @ExcelProperty("收款条件")
    private String paymentTerms;
    @ExcelProperty("币别")
    private String currency;
    @ExcelProperty("汇率")
    private String exchangeRate;
    @ExcelProperty("摘要")
    private String summary;
    @ExcelProperty("行号")
    @NotNull(message = "行号不能为空")
    @Column(primaryKey = true, keyOrder = 1)
    private String lineNum;
    @ExcelProperty("行状态")
    private String rowStatus;
    @ExcelProperty("关闭应收")
    private String closeReceivable;
    @ExcelProperty("物料编码")
    @NotNull(message = "物料编码不能为空")
    @Column(primaryKey = true, keyOrder = 2)
    private String materialCode;
    @ExcelProperty("物料名称")
    @NotNull(message = "物料名称不能为空")
    @Column(primaryKey = true, keyOrder = 3)
    private String materialName;
    @ExcelProperty("规格型号")
    @Column(primaryKey = true, keyOrder = 4)
    private String specification;
    @ExcelProperty("辅助属性")
    @Column(primaryKey = true, keyOrder = 5)
    private String extraAttr;
    @ExcelProperty("计量单位")
    @NotNull(message = "计量单位不能为空")
    @Column(primaryKey = true, keyOrder = 6)
    private String measurementUnit;
    @ExcelProperty("数量")
    private String count;
    @ExcelProperty("基本计量单位")
    private String basicUnit;
    @ExcelProperty("基本数量")
    private String basicCount;
    @ExcelProperty("辅助计量单位")
    private String extraUnit;
    @ExcelProperty("辅助数量")
    private String extraCount;
    @ExcelProperty("单价")
    private String price;
    @ExcelProperty("实际单价")
    private String actualPrice;
    @ExcelProperty("实际含税单价")
    private String actualTaxPrice;
    @ExcelProperty("折扣方式")
    private String discountMode;
    @ExcelProperty("单位折扣（率）")
    private String discountRate;
    @ExcelProperty("金额")
    private BigDecimal amount;
    @ExcelProperty("金额本位币")
    private BigDecimal amountCurrency;
    @ExcelProperty("税率")
    private String taxRate;
    @ExcelProperty("含税单价")
    private String taxPrice;
    @ExcelProperty("减价比例")
    private String reductionRate;
    @ExcelProperty("税额")
    private BigDecimal tax;
    @ExcelProperty("价税合计")
    private BigDecimal priceTaxTotal;
    @ExcelProperty("发货日期")
    private String senderDate;
    @ExcelProperty("交货日期")
    private String deliveryDate;
    @ExcelProperty("赠品")
    private String giveaway;
    @ExcelProperty("原因代码")
    private String reasonCode;
    @ExcelProperty("跨公司发货")
    private String crossCompanySend;
    @ExcelProperty("发货组织")
    private String senderOrg;
    @ExcelProperty("项目号")
    private String projectCode;
    @ExcelProperty("跟踪号")
    private String trackCode;
    @ExcelProperty("仓库")
    private String warehouse;
    @ExcelProperty("送货客户")
    private String sender;
    @ExcelProperty("收款客户")
    private String recipientCustomer;
    @ExcelProperty("应收客户")
    private String receivableCustomer;
    @ExcelProperty("送货地址")
    private String shippingAddress;
    @ExcelProperty("来源单据类型")
    private String srcOrderType;
    @ExcelProperty("来源单据编号")
    private String srcOrderCode;
    @ExcelProperty("安排发运数量")
    private String shippingQuantity;
    @ExcelProperty("累计出库数量")
    private String totalOutboundQuantity;
    @ExcelProperty("累计退货申请数量")
    private String totalReturnRequestQuantity;
    @ExcelProperty("累计退库数量")
    private String totalReturnQuantity;
    @ExcelProperty("累计退库需补货数量")
    private String totalReturnReplenishQuantity;
    @ExcelProperty("累计应收数量")
    private String totalReceivableAmount;
    @ExcelProperty("累计应收金额")
    private BigDecimal totalReceivableSum;
    @ExcelProperty("累计通知发货数量")
    private String totalDeliveryNotificationQuantity;
    @ExcelProperty("累计收款金额")
    private BigDecimal totalPayAmount;
    @ExcelProperty("结算方式")
    private String settlementMethod;
    @ExcelProperty("付款方式")
    private String payMode;
    @ExcelProperty("现金折扣")
    private String cashDiscount;
    @ExcelProperty("备注")
    private String remarks;
    @ExcelProperty("系统单据")
    private String systemDocument;
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
    @ExcelProperty("销售组")
    private String salesTeam;
    @ExcelProperty("销售员")
    private String seller;
    @ExcelProperty("累计生产数量")
    private String totalProductionQuantity;
    @ExcelProperty("累计未生产数量")
    private String totalNotYetProducedQuantity;
    @ExcelProperty("退货安排发运数量")
    private String returnShippingQuantity;
    @ExcelProperty("退货安排发运基本数量")
    private String returnShippingBaseQuantity;
    @ExcelProperty("已匹配预收")
    private String matchedAdvance;
    @ExcelProperty("协同单据类型")
    private String joinOrderType;
    @ExcelProperty("协同单据编号")
    private String joinOrderCode;
    @ExcelProperty("发票金额")
    private BigDecimal invoiceAmount;
    @ExcelProperty("物料组")
    private String materialGroup;
    @ExcelProperty("本位币税额")
    private BigDecimal baseCurrencyTaxAmount;
    @ExcelProperty("价税合计本位币")
    private BigDecimal priceTaxTotalCurrency;
    @ExcelProperty("供货组织")
    private String supplyOrg;
    @ExcelProperty("供货方式")
    private String supplyMethod;
    @ExcelProperty("需求传递")
    private String requestTransfer;
    @ExcelProperty("已订货数量")
    private String orderedQuantity;
    @ExcelProperty("未订货数量")
    private String notYetOrderedQuantity;
    @ExcelProperty("内部交易价")
    private String internalTransactionPrice;
    @ExcelProperty("可调拨数量")
    private String transferQuantity;
    @ExcelProperty("可调拨基本数量")
    private String transferBaseQuantity;
    @ExcelProperty("累计调拨数量")
    private String totalTransferQuantity;
    @ExcelProperty("累计调拨基本数量")
    private String totalTransferBaseQuantity;
    @ExcelProperty("已开票申请数量")
    private String invoicedApplyQuantity;
    @ExcelProperty("已开票申请基本数量")
    private String invoicedApplyQuantityBase;
    @ExcelProperty("未开票申请数量")
    private String unInvoicedApplyQuantity;
    @ExcelProperty("未开票申请基本数量")
    private String unInvoicedQuantityBase;
    @ExcelProperty("已开票申请金额")
    private BigDecimal invoicedApplyAmount;
    @ExcelProperty("已开票申请金额本位币")
    private BigDecimal invoicedApplyAmountCurrency;
    @ExcelProperty("未开票申请金额")
    private BigDecimal unInvoicedApplyAmount;
    @ExcelProperty("未开票申请金额本位币")
    private BigDecimal unInvoicedApplyAmountCurrency;
    @ExcelProperty("是否下发MES")
    private String sentToMES;
    @ExcelProperty("交货方式")
    private String deliveryMethod;
    @ExcelProperty("价格来源类型")
    private String priceSourceType;
    @ExcelProperty("价格来源编码")
    private String priceSourceCode;
    @ExcelProperty("匹配促销政策")
    private String matchedPromotePolicy;
    @ExcelProperty("促销政策编码")
    private String promotePolicyCode;
    @ExcelProperty("促销政策组号")
    private String promotePolicyGroupNum;
    @ExcelProperty("预收比率")
    private String advanceRatio;
    @ExcelProperty("当前节点")
    private String currentLocation;
    @ExcelProperty("手机下单")
    private String mobileOrder;

}
