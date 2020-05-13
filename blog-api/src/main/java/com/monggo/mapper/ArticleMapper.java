package com.monggo.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.monggo.entity.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.monggo.entity.IndexArticleVO;
import org.apache.ibatis.annotations.Param;


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
    List<IndexArticleVO> getIndexArticlePage(Page page,@Param("searchValue") String searchValue
            ,@Param("cateId")  Integer cateId
            ,@Param("tagId")  Integer tagId);

}
