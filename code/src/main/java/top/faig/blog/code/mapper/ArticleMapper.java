package top.faig.blog.code.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import top.faig.blog.code.entity.Article;
import top.faig.blog.code.vo.ArticleVO;


import java.util.List;

/**
 * <p>
 * 博文表 Mapper 接口
 * </p>
 *
 * @author fhs
 * @since 2020-04-27
 */
public interface ArticleMapper extends BaseMapper<Article> {


    /**
     *
     * @param page 翻页对象，可以作为 xml 参数直接使用，传递参数 Page 即自动分页
     * @return
     */
    List<ArticleVO> selectPageVo(Page page, @Param("searchValue") String searchValue
            , @Param("cateId")  Integer cateId
            , @Param("tagId")  Integer tagId);


    /**
     *
     * @return
     */
    List<ArticleVO> selectAll(@Param(value = "state") String state);

    /**
     *
     * @return
     */
    ArticleVO selectById(Integer articleId);



}
