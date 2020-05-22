package top.faig.blog.code.article.vo;

import lombok.Data;
import top.faig.blog.code.article.entity.Article;
import top.faig.blog.code.tags.entity.Tags;

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
