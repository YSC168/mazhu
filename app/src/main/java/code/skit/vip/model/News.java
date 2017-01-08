package code.skit.vip.model;


import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * 首页校内新闻实体类
 * 
 * @date 2014-5-3
 * @author Stone
 */
//@SuppressWarnings("serial")
public class News extends BmobObject {

	// private String id;
	// private String time;

	private String type; // 类型
	private String title; // 标题
	private String author; // 作者
	private String content;





//	public void save(New_Code p0, Object done)
//	{
//		// TODO: Implement this method
//	} // 内容
//	//private BmobFile picNews = null;  //图片
//
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

//	public BmobFile getPicNews() {
//		return picNews;
//	}
//
//	public void setPicNews(BmobFile picNews) {
//		this.picNews = picNews;
//	}

}

