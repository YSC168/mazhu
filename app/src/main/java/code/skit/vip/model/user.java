package code.skit.vip.model;

import cn.bmob.v3.*;
import cn.bmob.v3.datatype.BmobFile;

public class user extends BmobUser
{

	private String txdz;//状态
    private String nick;//imei
	//  private Integer age;//关卡
	//   private Integer loyer;
	// private String color;
    private String dn;
	private int codetheme;
    private String qm;

	private BmobFile icon;
    public String getdn(){

        return this.dn;
    }
    public void setdn(String h){
        this.dn=h;
    }
	public int getcodetheme(){

        return this.codetheme;
    }
    public void setcodetheme(int codetheme){
        this.codetheme=codetheme;
    }

    public String getTxdz() {
        return this.txdz;
    }

    public void setTxdz(String txdz) {
        this.txdz = txdz;
    }

    public String getNick() {
        return this.nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }
	public BmobFile getIcon() {
		return icon;
	}

	public void setIcon(BmobFile icon) {
		this.icon = icon;
	}
//    public Integer getAge() {
//        return age;
//    }
//
//    public void setAge(Integer age) {
//        this.age = age;
//    }
//    public Integer getloyer() {
//        return loyer;
//    }
//
//    public void setloyer(Integer age) {
//        this.loyer = age;
//    }
//    public String getcolor() {
//        return this.color;
//    }
//
//    public void setcolor(String nick) {
//        this.color = nick;
//    }
	public String getqm() {
        return this.qm;
    }

    public void setqm(String qm) {
        this.qm = qm;
    }

}




