package top.faig.blog.code.vo;

import lombok.Data;
import top.faig.blog.code.entity.Article;
import top.faig.blog.code.entity.Tags;

import java.io.Serializable;
import java.util.List;

@Data
public class ArticleVO extends Article implements Serializable {


    /**
     * 分类名
     */
    private String cateName;

    /**
     * 标签集合
     */
    private List<Tags> tags;
}
