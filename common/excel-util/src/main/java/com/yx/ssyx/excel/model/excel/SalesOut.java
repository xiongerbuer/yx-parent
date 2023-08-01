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
 * 销售出库单
 */
@Data
@Table(name = "salesOut")
@HeadStyle(fillBackgroundColor = 9)
public class SalesOut extends ExcelModel {

    @ExcelProperty("单据编号")
    @NotNull(message = "单据编号不能为空")
    @NotBlank(message = "销售出库单据编号不能为空")
    @Column(primaryKey = true, keyOrder = 0)
    private String orderCode;
    @ExcelProperty("单据状态")
    private String orderStatus;
    @ExcelProperty("冲销")
    private String writeOff;
    @ExcelProperty("初始化单据")
    private String initInvoice;
    @ExcelProperty("记账标志")
    private String bookMark;
    @ExcelProperty("源单据类型")
    private String srcOrderType;
    @ExcelProperty("业务日期")
    @NotEmpty(message = "业务日期不能为空")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String bizDate;
    @ExcelProperty("送货客户编码")
    private String senderCode;
    @ExcelProperty("部门")
    private String department;
    @ExcelProperty("付款方式")
    private String payMode;
    @ExcelProperty("凭证号")
    private String certNum;
    @ExcelProperty("行号")
    @NotNull(message = "行号不能为空")
    @Column(primaryKey = true, keyOrder = 1)
    private String lineNum;
    @ExcelProperty("核心单据号")
    @NotBlank(message = "核心单据号不能为空")
    private String coreOrderCode;
    @ExcelProperty("核心单据行号")
    private String coreOrderLineNum;
    @ExcelProperty("核心单据类型")
    @NotBlank(message = "核心单据类型不能为空")
    private String coreOrderType;
    @ExcelProperty("送货客户")
    @NotBlank(message = "送货客户不能为空")
    private String sender;
    @ExcelProperty("物料编码")
    @NotBlank(message = "物料编码不能为空")
    @Column(primaryKey = true, keyOrder = 2)
    private String materialCode;
    @ExcelProperty("物料名称")
    @NotBlank(message = "物料名称不能为空")
    @Column(primaryKey = true, keyOrder = 3)
    private String materialName;
    @ExcelProperty("物料简称")
    private String materialAbbr;
    @ExcelProperty("规格型号")
    private String specification;
    @ExcelProperty("应收客户")
    private String receivableCustomer;
    @ExcelProperty("订货客户")
    private String orderingCustomer;
    @ExcelProperty("计量单位")
    @NotBlank(message = "计量单位不能为空")
    @Column(primaryKey = true, keyOrder = 4)
    private String measurementUnit;
    @ExcelProperty("出库数量")
    private String outboundCount;
    @ExcelProperty("基本计量单位")
    @NotBlank(message = "基本单位不能为空")
    private String basicUnit;
    @ExcelProperty("基本数量")
    private String basicCount;
    @ExcelProperty("辅助属性")
    @Column(primaryKey = true, keyOrder = 5)
    private String extraAttr;
    @ExcelProperty("源单据编号")
    private String srcOrderCode;
    @ExcelProperty("批次")
    private String batchNum;
    @ExcelProperty("事务类型")
    private String transactionType;
    @ExcelProperty("生产日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String productDate;
    @ExcelProperty("到期日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String expireDate;
    @ExcelProperty("项目号")
    private String projectCode;
    @ExcelProperty("跟踪号")
    private String trackCode;
    @ExcelProperty("合同号")
    private String contractCode;
    @ExcelProperty("仓库")
    private String warehouse;
    @ExcelProperty("仓管员")
    private String warehouseClerk;
    @ExcelProperty("库位")
    private String warehousePosition;
    @ExcelProperty("单位标准成本")
    private String unitStdCost;
    @ExcelProperty("标准成本")
    private String stdCost;
    @ExcelProperty("单位实际成本")
    private String unitActualCost;
    @ExcelProperty("实际成本")
    private String actualCost;
    @ExcelProperty("单价")
    private String price;
    @ExcelProperty("税率")
    private String taxRate;
    @ExcelProperty("含税单价")
    private String taxPrice;
    @ExcelProperty("折扣方式")
    private String discountMode;
    @ExcelProperty("单位折扣（率）")
    private String discountRate;
    @ExcelProperty("实际单价")
    private String actualPrice;
    @ExcelProperty("实际含税单价")
    private String actualTaxPrice;
    @ExcelProperty("金额")
    private BigDecimal amount;
    @ExcelProperty("金额本位币")
    private BigDecimal amountCurrency;
    @ExcelProperty("税额")
    private String tax;
    @ExcelProperty("税额本位币")
    private String taxCurrency;
    @ExcelProperty("价税合计")
    private String priceTaxTotal;
    @ExcelProperty("价税合计本位币")
    private String priceTaxTotalCurrency;
    @ExcelProperty("折扣额")
    private String discountAmount;
    @ExcelProperty("冲销数量")
    private String writeOffCount;
    @ExcelProperty("未结算数量")
    private String unsettledCount;
    @ExcelProperty("未结算基本数量")
    private String unsettledBasicCount;
    @ExcelProperty("退货数量")
    private String returnCount;
    @ExcelProperty("客户订单号")
    private String customerOrderCode;
    @ExcelProperty("收款客户")
    private String recipientCustomer;
    @ExcelProperty("已核销数量")
    private String verificationCount;
    @ExcelProperty("已核销金额")
    private BigDecimal verificationAmount;
    @ExcelProperty("赠品")
    private String giveaway;
    @ExcelProperty("累计应收数量")
    private String totalReceivableAmount;
    @ExcelProperty("销售组织")
    private String salesOrg;
    @ExcelProperty("销售组")
    private String salesTeam;
    @ExcelProperty("销售员")
    private String seller;
    @ExcelProperty("送货地址")
    private String shippingAddress;
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
    @ExcelProperty("币别")
    private String currency;
    @ExcelProperty("汇率")
    private String exchangeRate;
    @ExcelProperty("是否含税")
    private String includeTax;
    @ExcelProperty("备注")
    private String remark;
    @ExcelProperty("未核销金额")
    private BigDecimal unverificationAmount;
    @ExcelProperty("库存组织")
    private String stockOrg;
    @ExcelProperty("未核销数量")
    private String unverificationCount;
    @ExcelProperty("协同单据编号")
    private String joinOrderCode;
    @ExcelProperty("协同单据类型")
    private String joinOrderType;
    @ExcelProperty("确认签收日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String confirmSignedDate;
    @ExcelProperty("累计入库数量")
    private String totalStorageCount;
    @ExcelProperty("摘要")
    private String summary;
    @ExcelProperty("确认签收数量")
    private String confirmSignedCount;
    @ExcelProperty("供应商")
    private String provider;
    @ExcelProperty("业务类型")
    private String bizType;
    @ExcelProperty("是否生成业务应收")
    private String generateBusinessReceivable;
    @ExcelProperty("更新类型")
    private String updateType;
    @ExcelProperty("是否完全核销")
    private String fullyWrittenOff;
    @ExcelProperty("成本中心编码")
    private String costCenterCode;
    @ExcelProperty("成本中心名称")
    private String costCenterName;
    @ExcelProperty("客户分类")
    @NotBlank(message = "客户分类不能为空")
    private String customerCategory;
    @ExcelProperty("辅助计量单位")
    private String extraUnit;
    @ExcelProperty("辅助数量")
    private String extraCount;
    @ExcelProperty("不参与核销")
    private String notInvolvedInWriteOff;
    @ExcelProperty("入库科目")
    private String storageAccount;
    @ExcelProperty("出库科目")
    private String retrievalAccount;
    @ExcelProperty("是否已下发MES")
    private String sentToMES;
    @ExcelProperty("订货客户编码")
    private String orderCustomerCode;
    @ExcelProperty("发货组织")
    private String senderOrg;
    @ExcelProperty("发货日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String senderDate;
    @ExcelProperty("内部交易价")
    private String internalTransactionPrice;
    @ExcelProperty("实际出库日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String actualRetrievalDate;
    @ExcelProperty("管理凭证记账标志")
    private String managementCertPostingFlag;
    @ExcelProperty("管理凭证号")
    private String managementCertNumber;
    @ExcelProperty("VMI未结算基本数量")
    private String vmiUnsettledBasicQuantity;
    @ExcelProperty("VMI已结算基本数量")
    private String vmiSettledBasicQuantity;
    @ExcelProperty("物料记账分类")
    private String materialBookCategory;
    @ExcelProperty("记账分类编码")
    private String bookCategoryCode;
    @ExcelProperty("当前节点")
    private String currentLocation;

}
