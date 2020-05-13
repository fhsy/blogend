package com.monggo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author fhs
 * 2020/5/13 11:30
 * 文件说明: 分页方法返回对象
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class IndexArticleVO  extends  Article implements Serializable{


    private List<String> tagNames;


}
