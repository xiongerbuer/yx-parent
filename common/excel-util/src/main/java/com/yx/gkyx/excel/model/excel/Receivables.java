package com.yx.gkyx.excel.model.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.HeadStyle;
import com.yx.gkyx.excel.annotation.Column;
import com.yx.gkyx.excel.annotation.Table;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 应收单
 */
@Data
@Table(name = "receivables")
@HeadStyle(fillBackgroundColor = 9)
public class Receivables extends ExcelModel {

    @ExcelProperty("公司")
    private String company;
    @ExcelProperty("单据编号")
    @NotNull(message = "单据编号不能为空")
    @Column(primaryKey = true, keyOrder = 0)
    private String orderCode;
    @ExcelProperty("单据状态")
    private String orderStatus;
    @ExcelProperty("单据日期")
    private String orderDate;
    @ExcelProperty("业务日期")
    private String bizDate;
    @ExcelProperty("单据类型")
    private String orderType;
    @ExcelProperty("业务类型")
    private String bizType;
    @ExcelProperty("来源单据类型")
    private String srcOrderType;
    @ExcelProperty("往来类型")
    private String contactType;
    @ExcelProperty("往来户编码")
    private String contactCode;
    @ExcelProperty("往来户名称")
    private String contactName;
    @ExcelProperty("币别")
    private String currency;
    @ExcelProperty("汇率")
    private String exchangeRate;
    @ExcelProperty("部门")
    private String department;
    @ExcelProperty("成本中心")
    private String costCenter;
    @ExcelProperty("销售组织")
    private String salesOrg;
    @ExcelProperty("销售组")
    private String salesTeam;
    @ExcelProperty("人员")
    private String personnel;
    @ExcelProperty("付款方式")
    private String payMode;
    @ExcelProperty("收款条件")
    private String paymentTerms;
    @ExcelProperty("现金折扣")
    private String cashDiscount;
    @ExcelProperty("摘要")
    private String summary;
    @ExcelProperty("应收金额合计")
    private BigDecimal totalReceivableAmount;
    @ExcelProperty("金额合计")
    private BigDecimal totalAmount;
    @ExcelProperty("税额合计")
    private String totalTaxAmount;
    @ExcelProperty("已结算金额合计")
    private BigDecimal totalSettledAmount;
    @ExcelProperty("其中坏账金额合计")
    private BigDecimal totalBadDebtAmount;
    @ExcelProperty("未结算金额合计")
    private BigDecimal totalUnsettledAmount;
    @ExcelProperty("核心单据类型")
    private String coreOrderType;
    @ExcelProperty("核心单号")
    @NotNull(message = "核心单号不能为空")
    @Column(primaryKey = true, keyOrder = 1)
    private String coreOrderCode;
    @ExcelProperty("核心单行号")
    @NotNull(message = "核心单行号不能为空")
    @Column(primaryKey = true, keyOrder = 2)
    private String coreOrderLineNum;
    @ExcelProperty("项目号")
    private String projectCode;
    @ExcelProperty("跟踪号")
    private String trackCode;
    @ExcelProperty("销售合同号")
    private String saleContractCode;
    @ExcelProperty("销售合同行号")
    private String saleContractLineNum;
    @ExcelProperty("物料(费用)编码")
    @NotNull(message = "物料(费用)编码不能为空")
    @Column(primaryKey = true, keyOrder = 3)
    private String materialCode;
    @ExcelProperty("物料(费用)名称")
    @NotNull(message = "物料(费用)名称不能为空")
    @Column(primaryKey = true, keyOrder = 4)
    private String materialName;
    @ExcelProperty("规格型号")
    @Column(primaryKey = true, keyOrder = 5)
    private String specification;
    @ExcelProperty("辅助属性")
    @Column(primaryKey = true, keyOrder = 6)
    private String extraAttr;
    @ExcelProperty("费用项目编码")
    private String feeProjectCode;
    @ExcelProperty("费用项目名称")
    private String feeProjectName;
    @ExcelProperty("计量单位")
    @NotNull(message = "计量单位不能为空")
    @Column(primaryKey = true, keyOrder = 7)
    private String measurementUnit;
    @ExcelProperty("数量")
    private String count;
    @ExcelProperty("单价")
    private String price;
    @ExcelProperty("税率(%)")
    private String taxRate;
    @ExcelProperty("含税单价")
    private String taxPrice;
    @ExcelProperty("折扣方式")
    private String discountMode;
    @ExcelProperty("单位折扣(率)")
    private String discountRate;
    @ExcelProperty("实际单价")
    private String actualPrice;
    @ExcelProperty("实际含税单价")
    private String actualTaxPrice;
    @ExcelProperty("应收金额")
    private BigDecimal amountReceivable;
    @ExcelProperty("金额")
    private BigDecimal amount;
    @ExcelProperty("税额")
    private String tax;
    @ExcelProperty("折扣额")
    private String discountAmount;
    @ExcelProperty("已锁定金额")
    private BigDecimal lockAmount;
    @ExcelProperty("未锁定金额")
    private BigDecimal unLockAmount;
    @ExcelProperty("已结算金额")
    private BigDecimal settledAmount;
    @ExcelProperty("坏账金额")
    private BigDecimal badDebtAmount;
    @ExcelProperty("未结算金额")
    private BigDecimal unsettledAmount;
    @ExcelProperty("已提坏账准备金额")
    private BigDecimal badDebtReserveAmount;
    @ExcelProperty("已提坏帐准备金额本位币")
    private BigDecimal badDebtReserveAmountCurrency;
    @ExcelProperty("应收科目")
    private String receivableAccount;
    @ExcelProperty("对方科目")
    private String counterpartAccount;
    @ExcelProperty("备注")
    private String remark;
    @ExcelProperty("已生成凭证")
    private String hasGeneratedCert;
    @ExcelProperty("凭证字")
    private String certWord;
    @ExcelProperty("凭证号")
    private String certNum;
    @ExcelProperty("发票号码")
    private String invoiceNumber;
    @ExcelProperty("已开发票金额")
    private BigDecimal invoicedAmount;
    @ExcelProperty("期初单据")
    private String isInitialDocument;
    @ExcelProperty("导入单据")
    private String isImportedDocument;
    @ExcelProperty("转移生成")
    private String isTransferGenerated;
    @ExcelProperty("折让单据")
    private String isDiscountDocument;
    @ExcelProperty("已被冲销")
    private String isReversed;
    @ExcelProperty("冲销单据")
    private String isReversalDocument;
    @ExcelProperty("业务应收")
    private String businessReceivable;
    @ExcelProperty("是否完全核销")
    private String fullyWrittenOff;
    @ExcelProperty("已核销本位币金额")
    private BigDecimal verifiedAmountCurrency;
    @ExcelProperty("已核销基本数量")
    private String verifiedQuantity;
    @ExcelProperty("未核销本位币金额")
    private BigDecimal unverifiedAmountCurrency;
    @ExcelProperty("未核销基本数量")
    private String unverifiedQuantity;
    @ExcelProperty("发货组织")
    private String senderOrg;
    @ExcelProperty("订货客户编码")
    private String orderCustomerCode;
    @ExcelProperty("订货客户名称")
    private String orderCustomerName;
    @ExcelProperty("送货客户编码")
    private String senderCode;
    @ExcelProperty("送货客户名称")
    private String senderName;
    @ExcelProperty("收款客户编码")
    private String payCustomerCode;
    @ExcelProperty("收款客户名称")
    private String payCustomerName;
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
    @ExcelProperty("会计")
    private String accountant;
    @ExcelProperty("协同单据类型")
    private String joinOrderType;
    @ExcelProperty("协同单据编号")
    private String joinOrderCode;
    @ExcelProperty("生产场点")
    private String productionSite;
    @ExcelProperty("实际出库日期")
    private String actualRetrievalDate;
    @ExcelProperty("客户分类")
    private String customerCategory;
    @ExcelProperty("当前节点")
    private String currentLocation;
    @ExcelProperty("应收金额本位币合计")
    private BigDecimal totalReceivableAmountCurrency;
    @ExcelProperty("金额本位币合计")
    private BigDecimal totalAmountCurrency;
    @ExcelProperty("税额本位币合计")
    private BigDecimal totalTaxAmountCurrency;
    @ExcelProperty("结算状态")
    private String settlementStatus;
    @ExcelProperty("应收金额本位币")
    private BigDecimal receivableAmountCurrency;
    @ExcelProperty("金额本位币")
    private BigDecimal amountCurrency;
    @ExcelProperty("税额本位币")
    private String taxCurrency;
    @ExcelProperty("折扣额本位币")
    private String discountAmountCurrency;
    @ExcelProperty("已结算金额本位币")
    private BigDecimal settledAmountCurrency;
    @ExcelProperty("未结算金额本位币")
    private BigDecimal unsettledAmountCurrency;
    @ExcelProperty("不参与坏账计提")
    private String excludeBadDebtProvision;
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
    @ExcelProperty("仓库")
    private String warehouse;
    @ExcelProperty("发票开具状态")
    private String invoiceIssuanceStatus;
    @ExcelProperty("发票编号")
    private String invoiceNum;
    @ExcelProperty("开票名称")
    private String invoiceName;
    @ExcelProperty("税号")
    private String taxNum;
    @ExcelProperty("地址电话")
    private String addressPhone;
    @ExcelProperty("开户行及账号")
    private String bankAccount;
    @ExcelProperty("计划开票日期")
    private String plannedInvoiceDate;
    @ExcelProperty("发票代码")
    private String invoiceCode;
    @ExcelProperty("开票日期")
    private String invoiceDate;
    @ExcelProperty("分录成本中心")
    private String entryCostCenter;
    @ExcelProperty("零数量")
    private String zeroQuantity;
    @ExcelProperty("收票人")
    private String recipient;
    @ExcelProperty("收票电话")
    private String recipientPhone;
    @ExcelProperty("收票单位名称")
    private String recipientCompanyName;
    @ExcelProperty("收票地址")
    private String recipientAddress;
    @ExcelProperty("邮政编码")
    private String postalCode;
    @ExcelProperty("收票邮箱")
    private String recipientEmail;
    @ExcelProperty("快递单号")
    private String expressNumber;
    @ExcelProperty("邮寄日期")
    private String mailingDate;
    @ExcelProperty("发票类型")
    private String invoiceType;
    @ExcelProperty("批次")
    private String batchNum;
    @ExcelProperty("销售出库单时间")
    private String salesRetrievalOrderDate;
    @ExcelProperty("快递公司")
    private String expressCompany;
    @ExcelProperty("是否已生成开票单")
    private String generatedInvoice;
    @ExcelProperty("是否已生成开票申请单（增值税）")
    private String generatedVATInvoiceApplication;
    @ExcelProperty("是否已生成开票申请单（机动车）")
    private String generatedVehicleInvoiceApplication;
    @ExcelProperty("是否已开票")
    private String invoiced;
    @ExcelProperty("项目号名称")
    private String projectNumName;

}
