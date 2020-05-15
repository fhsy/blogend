package top.faig.blog.common.type;

/**
 * @author fhs
 * 2020/4/27 15:03
 * 文件说明:
 */
public enum ArticleType {

    RELEASE("RELEASE", "发布"),
    DRAFT("DRAFT", "草稿"),
    RECOVERY("RECOVERY", "回收");
    private String type;
    private String name;
    ArticleType(String type, String name) {
        this.type = type;
        this.name = name;
    }
    public String getType() {
        return type;
    }
    public String getName() {
        return name;
    }
}
