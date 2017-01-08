package code.skit.vip;


import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import code.skit.vip.model.News;
import android.widget.EditText;
import android.support.v7.app.AppCompatActivity;
import thereisnospon.codeview.CodeView;
import thereisnospon.codeview.CodeViewTheme;
import android.support.v7.widget.Toolbar;
import cn.bmob.v3.BmobUser;
import code.skit.vip.model.user;
import android.view.MenuItem;


/**
 * 新闻内容显示界面
 * 
 * @date 2014-5-8
 * @author Stone
 */
public class LookCodeActivity extends AppCompatActivity {

	@SuppressWarnings("unused")
	private static final String TAG = "NewsActivity";

	private TextView tvNewsTitle;
	private TextView tvNewsAuthor;
	private TextView tvNewsTime;

	//private ImageView imgNews;

	private String newsID;
	private String newsTitle;
	private String newsAuthor;
	private String newsTime;
	private String newsContent;



	private CodeView codeView;

	private Toolbar toolbar;

	private TextView toolbar_title;
//	Handler mHandler = new Handler() {
//
//		@Override
//		public void handleMessage(Message msg) {
//			if (msg.what == 0) {
//				// 加载图片
//				loadImage();
//			}
//		}
//	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_code);

		getIntentData();
		//setTitle(newsTitle);

		//getNewsByID();
		initView();
		

	}

	private void initView() {
		toolbar=(Toolbar)findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		getSupportActionBar().setHomeButtonEnabled(true);
		// 默认显示左上角返回按钮
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		toolbar.setTitle(newsTitle);
		//toolbar_title=(TextView)findViewById(R.id.toolbar_title);
		tvNewsTitle = (TextView) findViewById(R.id.tv_news_title);
		tvNewsAuthor = (TextView) findViewById(R.id.tv_news_author);
		tvNewsTime = (TextView) findViewById(R.id.tv_news_time);
		//	tvNewsContent = (EditText) findViewById(R.id.tv_news_content);
		//imgNews = (ImageView) findViewById(R.id.img_news);
		//int theme=getIntent().getIntExtra("theme",0);
        codeView=(CodeView)findViewById(R.id.codeview);

		if(BmobUser.getCurrentUser(LookCodeActivity.this)!=null){
			user user = BmobUser.getCurrentUser(LookCodeActivity.this,user.class);
			int u=user.getcodetheme();
			codeView.setTheme(CodeViewTheme.listThemes()[u]);

		}else{
			codeView.setTheme(CodeViewTheme.ARDUINO_LIGHT);
			//Toast.makeText(NewsActivity.this,"请登录",1000).show();
		}


		// codeView.setTheme(CodeViewTheme.ANDROIDSTUDIO);
        codeView.fillColor();

        codeView.showCode("作者: " + newsAuthor+"\n"+"发布日期 : " + newsTime+"\n\n\n"+ newsContent);
		tvNewsTitle.setText(newsTitle);
		tvNewsAuthor.setText("作者: " + newsAuthor);
		tvNewsTime.setText("发布日期 : " + newsTime);
		//tvNewsContent.setText(newsContent);
	}

	// 获取Intent中传入的新闻数据
	private void getIntentData() {
		newsID = getIntent().getStringExtra("NewsID");
		newsTitle = getIntent().getStringExtra("NewsTitle");
		newsAuthor = getIntent().getStringExtra("NewsAuthor");
		newsTime = getIntent().getStringExtra("NewsTime");
		newsContent = getIntent().getStringExtra("NewsContent");

		newsTitle = splitString(newsTitle); // 拆分字符串, 将新闻标题设置为 "】" 后面的内容
	}

	private String splitString(String str) {
		String[] strs = null;
		if (str.equals("")) {
			return "";
		} else if (!(str.contains("【") || str.contains("】"))) {
			return str;
		}
		strs = str.split("】");
		return strs[1];
	}
//
//	/**
//	 * 根据ID查找新闻
//	 * 
//	 * @date 2014-9-16
//	 * @author Stone
//	 */
//	private void getNewsByID() {
//		news = new News();
//		BmobQuery<News> query = new BmobQuery<News>();
//		query.addWhereEqualTo("objectId", newsID);
//		query.findObjects(this, new FindListener<News>() {
//
//				@Override
//				public void onSuccess(List<News> object) {
//					if (object != null)
//					{
//						news = object.get(0);
//						//发送消息开始加载图片
//						Message msg = new Message();  
//						msg.what = 0;  
//						mHandler.sendMessage(msg);
//					}
//				}
//
//				@Override
//				public void onError(int arg0, String arg1) {
//					toast("都怪小菜我, 获取数据失败了");
//				}
//			});
//	}
//
//
//	/**
//	 * 加载图片显示
//	 * @author Stone
//	 * @date 2014-9-16
//	 */
//	private void loadImage() {
//		//只加载图片,后面两个参数是图片的大小
//		if (news.getPicNews() != null)
//			news.getPicNews().loadImage(this, imgNews, 300, 180);
//	}
//
	public void toast(String toast) {
		Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
	}
	public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

