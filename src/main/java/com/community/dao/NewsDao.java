package com.community.dao;

import com.community.domain.News;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 新闻dao接口
 */
@Repository
public interface NewsDao {

	//查询所有新闻
	@Select("select * from news")
	public List<News> findAll();

	//查询所有新闻,支持分页,支持模糊查询(去除学校公告)
	@Select("SELECT nnumber,ntitle,nauthor,ntime FROM news WHERE ntitle LIKE '%${ntitle}%' AND nnumber != '10000' ")
	List<News> findAllNews(@Param("ntitle") String ntitle);

	//新建一条新闻
	@Insert("insert into news (nnumber,nauthor,ncontent) values (#{nnumber},#{nauthor},#{ncontent})")
	public void saveNews(News news);

	//查询第一条新闻(学校公告)
	@Select("select ncontent from news where nnumber ='10000' ")
	public News noticeOne();

	//查询最新六条新闻(去除学校公告)
	@Select("SELECT nnumber,ntitle,ntime FROM news WHERE nnumber !='10000' ORDER BY nid DESC LIMIT 6")
	public List<News> latestNewsData();

	//查询一条新闻(详情,通过nnumber)
	@Select("select * from news where nnumber !='10000' and nnumber=#{nnumber}")
	News findOneNewsByNnumber(@Param("nnumber") String nnumber);

	//查询所有新闻计算总和数量(不包括第一条10001)
	@Select("SELECT COUNT(*) FROM news WHERE nnumber !='10000'")
	Integer findAllCountNews();

	//查询所有新闻
	@Select("SELECT * FROM news WHERE ntitle LIKE CONCAT('%',#{ntitle},'%') AND nnumber !='10000'")
	List<News> findAllNewsList(@Param("ntitle")String ntitle);

	//发布新闻
	@Insert("insert into news (nnumber,ntitle,nauthor,ncontent) values (#{nnumber},#{ntitle},#{nauthor},#{ncontent})")
	void createNewOnenews(News news);

	//通过新闻编号更新新闻信息
	@Update("UPDATE news SET ntitle = #{ntitle},ncontent = #{ncontent}, nauthor = #{nauthor} WHERE nnumber= ${nnumber} ")
	void changeNewsByNnumber(News news);

	//更改轮播公告
	@Update("UPDATE news SET ncontent = #{ncontent} WHERE nnumber='10000' ")
	void changeNoticeMain(@Param("ncontent") String ncontent);
}
