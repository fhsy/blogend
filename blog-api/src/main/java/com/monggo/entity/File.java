package com.monggo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 文件表
 * </p>
 *
 * @author fhs
 * @since 2020-04-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_file")
public class File implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 文件 ID
     */
    private Integer fileId;

    /**
     * 真实文件路径
     */
    private String path;

    /**
     * 网络路径
     */
    private String img;

    /**
     * 文件名
     */
    private String name;

    /**
     * 引用次数
     */
    private Integer quote;

    /**
     * hack mysql auto update timestamp
     */
    //出参
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 创建时间
     */
    //出参
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;


}
