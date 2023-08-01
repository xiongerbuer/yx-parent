package com.yx.ssyx.excel.model.mysql;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class ImportLog {

    /**
     * 日志表主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long logId;

    /**
     * 文件名称
     */
    private String name;

    /**
     * 运行状态，success：导入成功，fail：导入失败，running：进行中
     */
    @Enumerated(EnumType.STRING)
    private ExcelImportStatus status;

    /**
     * 模型类型
     */
    private String modelType;

    /**
     * 用户上传导入文件的url
     */
    private String fileUrl;

    /**
     * 用户上传导入文件的url
     */
    @Nullable
    private String errorFileUrl;

    /**
     * 操作用户id
     */
    private Long userId;

    /**
     * 租户id
     */
    private Long tenantId;

    /**
     * 是否删除
     */
    private Boolean deleted;

    /**
     * 提示信息
     */
    private String info;

    /**
     * 创建时间
     */
    @CreatedDate
    private LocalDateTime createAt;

    /**
     * 创建用户id
     */
    private String createBy;

    /**
     * 更新时间
     */
    @LastModifiedDate
    private LocalDateTime modifyAt;

    /**
     * 更新用户id
     */
    private String modifyBy;
}
