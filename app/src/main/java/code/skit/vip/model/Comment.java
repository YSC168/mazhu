package code.skit.vip.model;

import cn.bmob.v3.BmobObject;
//import com.fly.community.data.user;
public class Comment extends BmobObject {
//这个是你帖子数据的类
    //private News comment;
//评论内容
    private String content;
//评论的用户
    private user commentuser;
//这个是我方便查看评论者名字的变量
    public String name;
//这里就是各种set get 我不作解释
	public String tzid;
    public void setuser(user user){
        this.commentuser=user;
    }
    public String getcontent(){
        return content;
    }
    public void setcontent(String content){
        this.content=content;
    }
	
	public String gettzid(){
        return content;
    }
    public void settzid(String tzid){
        this.tzid=tzid;
    }
	
	
//post 用来关联两个数据
//    public void setpost(News comment){
//        this.comment=comment;
//    }
    public void setname(String name){
        this.name=name;
    }
    public String getname(){
        return name;
    }
}

