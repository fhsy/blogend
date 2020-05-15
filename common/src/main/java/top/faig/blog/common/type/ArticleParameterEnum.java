package top.faig.blog.common.type;

/**
 * @author fhs
 * 2020/4/29 17:57
 * 文件说明:
 */
public enum ArticleParameterEnum {

    DATE(1, "时间顺序"),
    TAGS(2, "标签展示"),
    CATEGORY(3, "分类展示"),
    TITLE(4, "标题搜索"),
    CONTEXT(5, "内容搜索");

    private Integer code;
    private String name;
    ArticleParameterEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
    public Integer getCode() {
        return code;
    }
    public String getName() {
        return name;
    }
}
