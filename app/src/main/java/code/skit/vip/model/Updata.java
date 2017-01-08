package code.skit.vip.model;
import cn.bmob.v3.BmobObject;

public class Updata extends BmobObject {

	// private String id;
	// private String time;

	private int Version; // 版本
	private String Message; // 内容
	private String Url;

	private String Banben; //下载地址


	public int getVersion() {
		return Version;
	}

	public void setVersion(int Version) {
		this.Version = Version;
	}
	public String getBanben() {
		return Banben;
	}
	
	public String getMessage() {
		return Message;
	}

	public void setMessage(String Message) {
		this.Message = Message;
	}

	public String getUrl() {
		return Url;
	}



	

}

