package com.yx.ssyx.common.jpa;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * @ClassName 基础字段赋值
 * @Description 基础字段自动赋值
 * @Author tuzhen@isiiot.com
 * @Date 2023-04-23 16:31
 * @see SpringSecurityAuditorAware
 */
@Getter
@Setter
@MappedSuperclass
@Accessors(chain = true)
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity<T> {


    /**
     * 创建人id
     */
    @CreatedBy
    @Column(name = "create_by", updatable = false)
    @ApiModelProperty(value = "创建人", hidden = true)
    protected T createBy;

    /**
     * 修改人id
     */
    @LastModifiedBy
    @Column(name = "modify_by")
    @ApiModelProperty(value = "修改人", hidden = true)
    protected T modifyBy;

    /**
     * 创建时间
     */
    @CreationTimestamp
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_at", updatable = false)
    @ApiModelProperty(value = "创建时间", example = "2023-04-23 00:00:12", hidden = true)
    protected LocalDateTime createAt;

    /**
     * 更新时间
     */
    @UpdateTimestamp
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "modify_at")
    @ApiModelProperty(value = "更新时间", example = "2023-04-23 00:00:12", hidden = true)
    protected LocalDateTime modifyAt;


    @Override
    public String toString() {
        return "BaseAudiTable{" +
                "createBy=" + createBy +
                ", modifyBy=" + modifyBy +
                ", createAt=" + createAt +
                ", modifyAt=" + modifyAt +
                '}';
    }
}
