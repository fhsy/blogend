package top.faig.blog.article.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author fhs
 * 2020/4/30 13:33
 * 文件说明:
 */
@Data
public class IndexTagsArticle implements Serializable{

    /**
     * 标签 ID
     */
    private Integer tagId;

    /**
     * 标签名
     */
    private String tagName;

    /**
     * 博文列表
     */
    private List<Article> articleList;

}
