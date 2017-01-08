package code.skit.vip;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import code.skit.vip.*;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import android.content.Intent;
import android.widget.ListView;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import code.skit.vip.model.Comment;
import code.skit.vip.model.News;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.datatype.BmobPointer;
import cn.bmob.v3.exception.BmobException;
import java.util.List;
import android.widget.EditText;
import android.view.View;
import android.support.v7.app.AlertDialog;
import android.content.DialogInterface;
import cn.bmob.v3.BmobUser;
import android.widget.Toast;
//import android.view.MenuItem;
import code.skit.vip.model.user;
import code.skit.vip.adapter.comment_list_adapter;
import java.util.ArrayList;
import android.widget.ListAdapter;
import thereisnospon.codeview.CodeView;
import thereisnospon.codeview.CodeViewTheme;
import android.webkit.WebChromeClient;
import android.webkit.DownloadListener;
import android.util.Log;
import android.net.Uri;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.content.Context;
import android.widget.ImageView;
import android.view.WindowManager;
import android.view.MenuItem;
public class look_content extends AppCompatActivity implements
OnClickListener
{


	private ImageView userLogo;
	private ImageView myFav;
	private TextView comment;
	private TextView share;
	private TextView love;
	private TextView hate;


	private String newsID;

	private String newsTitle;

	private String newsAuthor;

	private String newsTime;

	private String newsContent;

	private TextView nr;

	private Toolbar toolbar;

	private TextView toolbar_title;

	private ListView listvie;

	private Intent intent1;
	private List<Comment> newsList = new ArrayList<Comment>();

	private comment_list_adapter newsListAdapter;

	private EditText content_pl;

	private TextView look_content_title;

	private TextView look_content_author;

	private CodeView tv_code;

	private TextView look_content_data;

	//private ListAdapter newsListAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		setContentView(R.layout.look_content);

		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

		intent1 = this.getIntent();
		getIntentData();
		nr=(TextView)findViewById(R.id.look_content_content);
		//	look_content_data=(TextView)findViewById(R.id.tv_news_date);

		//look_content_title=(TextView)findViewById(R.id.look_content_title);
		look_content_author=(TextView)findViewById(R.id.look_content_author);
		tv_code=(thereisnospon.codeview.CodeView)findViewById(R.id.codeview);
		toolbar=(Toolbar)findViewById(R.id.toolbar);
		//toolbar_title=(TextView)findViewById(R.id.toolbar_title);
		setSupportActionBar(toolbar);
		getSupportActionBar().setHomeButtonEnabled(true);
		// 默认显示左上角返回按钮
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		userLogo = (ImageView) findViewById(R.id.user_logo);
		myFav = (ImageView) findViewById(R.id.item_action_fav);
		comment = (TextView) findViewById(R.id.item_action_comment);
		share = (TextView) findViewById(R.id.item_action_share);
		love = (TextView) findViewById(R.id.item_action_love);
		hate = (TextView) findViewById(R.id.item_action_hate);
		userLogo.setOnClickListener(this);
		myFav.setOnClickListener(this);
		love.setOnClickListener(this);
		hate.setOnClickListener(this);
		share.setOnClickListener(this);
		comment.setOnClickListener(this);
		content_pl=(EditText)findViewById(R.id.code_content_pl);
		listvie=(ListView)findViewById(R.id.listll);
		toolbar.setTitle(newsTitle);
		look_content_author.setText(newsAuthor);
		//look_content_title.setText(newsTitle);
		//look_content_data.setText(newsTime);
		newsListAdapter = new comment_list_adapter(this,newsList);
		listvie.setAdapter(newsListAdapter);
//		if(newsAuthor.equals("睡客")){
//			tv_code.setTheme(CodeViewTheme.ANDROIDSTUDIO);
//			tv_code.setDownloadListener(new MyWebViewDownLoadListener()); 
//			//WebSettings settings = webview.getSettings();
//			tv_code.getSettings().setJavaScriptEnabled(true);
//			tv_code.setWebChromeClient(new WebChromeClient());
//			nr.setVisibility(View.GONE);
//			tv_code.showCodeHtmlByCssSelect(newsContent,".代码");
//		}else{
//			//nr.setText(newsContent);


		tv_code.setTheme(CodeViewTheme.ANDROIDSTUDIO);
		tv_code.setDownloadListener(new MyWebViewDownLoadListener()); 
		//WebSettings settings = webview.getSettings();
		tv_code.getSettings().setJavaScriptEnabled(true);
		String code1=newsContent.replace("[代码]","<pre class=\"代码\">");
		String code2=code1.replace("[/代码]","</pre>");
		tv_code.setWebChromeClient(new WebChromeClient());
		nr.setVisibility(View.GONE);
		tv_code.showCodeHtmlByCssSelect(code2,".代码");


		//}
		//tv_code.showCode(newsContent);
		hideSoftInput();

		shuaxin();
		//setComment();
	}


	@Override
	public void onClick(View p1)
	{
		switch (p1.getId()) {
			case R.id.user_logo:
				//onClickUserLogo();
				break;
//			case R.id.loadmore:
//				onClickLoadMore();
//				break;
//			case R.id.comment_commit:
//				onClickCommit();
				//break;
			case R.id.item_action_fav:
				//onClickFav(v);
				break;
			case R.id.item_action_love:
				//onClickLove();
				break;
			case R.id.item_action_hate:
				//onClickHate();
				break;
			case R.id.item_action_share:
				onClickShare();
				break;
			case R.id.item_action_comment:
				onClickComment();
				break;
			default:
				break;
		}}

	private void onClickComment()
	{
		// TODO: Implement this method
		content_pl.requestFocus();

		InputMethodManager imm = (InputMethodManager) this
			.getSystemService(Context.INPUT_METHOD_SERVICE);

		imm.showSoftInput(content_pl, 0);

	}
	private void hideSoftInput() {
		InputMethodManager imm = (InputMethodManager) this
			.getSystemService(Context.INPUT_METHOD_SERVICE);

		imm.hideSoftInputFromWindow(content_pl.getWindowToken(), 0);
	}
	private void onClickShare() {
		// TODO Auto-generated method stub

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


	//发表评论的方法get
	//发表评论
	private void setComment(String objectid,String content,String username){
//获得用户
		//	BmobUser user = BmobUser.getCurrentUser(user.class);
//关联数据的类
		final Comment comment = new Comment();
//帖子数据的类
		//News data=new News();
//设置关联数据的评论内容
		comment.setcontent(content);
//把data_get和Comment关联（很重要）
		//comment.setpost(data);
//设置评论者的名字
		comment.setname(username);
//设置关联的帖子的id
		//data.setObjectId(objectid);
//监听
		comment.settzid(newsID);
		comment.save(look_content.this, new SaveListener() {

				@Override
				public void onSuccess()
				{
					toast("评论成功!");
					//shuaxin1();
					shuaxin();
					hideSoftInput();

				}

				@Override
				public void onFailure(int p1, String p2)
				{
					toast("评论失败!");
				}



				private void toast(String p0)
				{
					Toast.makeText(look_content.this,p0,1000).show();
					// TODO: Implement this method
				}
			});
	}
	//封装刷新
//	private void shuaxin1()
//	{
//		Intent intent = this.getIntent();
//		String id=intent.getStringExtra("id");
//		listvie= (ListView) findViewById(R.id.listll);
//		//清除横线
//		listvie.setDividerHeight(0);
////		adapter = new SimpleAdapter(Contents.this, mData, R.layout.list_cm,
////									new String[]{"name","content","time"},
////									new int[]{R.id.name,R.id.message,R.id.time});					
//		listvie.setAdapter(adapter);
//		BmobQuery<Comment> query = new BmobQuery<Comment>();
//		query.order("createdAt");
//		query.setLimit(500);
//		News posts=new News();
////查询指定id帖子的关联评论
//		posts.setObjectId(id);
////返回数据包含
//		query.addWhereEqualTo("comment",new BmobPointer(posts));
//		query.include("content,name,creatdAt");
//		query.setCachePolicy(BmobQuery.CachePolicy./*CACHE_ELSE_NETWORK*/NETWORK_ELSE_CACHE);
//		query.findObjects(new FindListener<Comment>() {
//
//				@Override
//				public void done(List<Comment> p1, BmobException p2)
//				{
//					mData.clear();
//					for(Comment message : p1)
//					{
////设置ltem显示的内容
//						//final Map<String, Object> item = new HashMap<String, Object>();
//						final Map<String, Object> item = new HashMap<String, Object>();
//						//获取名称
//						String name1=message.getname();
//						//获取内容
//						String message1 = message.getcontent();
//						//获取发送时间
//						String time = message.getCreatedAt();
//						//设置名称
//						item.put("name",name1);
//						//设置内容
//						item.put("content", message1);
//						//设置发送时间
//						item.put("time", time);
//						//添加数据
//						mData.add(item);
//					}
//					adapter.notifyDataSetChanged();
//				}
//				public void toast(String a){
//					Toast.makeText(Contents.this,a,1000).show();
//				}});}

	public void send(View v){

		if(BmobUser.getCurrentUser(look_content.this)!=null){
			//user user = BmobUser.getCurrentUser(look_content.this,user.class);


			if (content_pl.getText().toString().length() > 35
			/*大于35位数字*/
				)
			{  
				Toast.makeText(look_content.this,"评论内容太多",1000).show();

			}else if (content_pl.getText().toString().length() <2){
				Toast.makeText(look_content.this,"最少两个字",1000).show();

			} else{
				user user = BmobUser.getCurrentUser(look_content.this,user.class);


				String username =user.getUsername();   
				String id=intent1.getStringExtra("id");
				String y="";
				y=content_pl.getText().toString();
				setComment(id,y,username); 
				shuaxin();
				content_pl.setText("");
			}
		}else{

			Toast.makeText(look_content.this,"请登录",1000).show();


		}
	}






	private void toast(String p0)
	{
		Toast.makeText(look_content.this,p0,1000).show();
		// TODO: Implement this method
	}



    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            look_content.this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

	private void shuaxin()
	{
		// TODO: Implement this method

		BmobQuery<Comment> query = new BmobQuery<Comment>();
		query.order("createdAt");
		//query.order("-createdAt");
		query.setLimit(50);
		query.setCachePolicy(BmobQuery.CachePolicy.NETWORK_ELSE_CACHE);
		//final boolean isCache = query.hasCachedResult(this News.class);

		query.addWhereEqualTo("tzid", newsID);
		query.findObjects(look_content.this,new FindListener<Comment>() {







				@Override
				public void onSuccess(List<Comment> object) {
					newsList = object;
					// 通知Adapter数据更新
					newsListAdapter.refresh((ArrayList<Comment>) newsList);
					newsListAdapter.notifyDataSetChanged();
				}

				@Override
				public void onError(int code, String arg1) {
					if(code==9010){
						Toast.makeText(look_content.this,"连接超时，请检查网络",Toast.LENGTH_LONG).show();

					}else if(code==9016){
						Toast.makeText(look_content.this,"无网络连接，请检查网络连接",Toast.LENGTH_LONG).show();


					}
				}
			});
	}


	private class MyWebViewDownLoadListener implements DownloadListener{   

		@Override  

		public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype,   

									long contentLength) {              

            Log.i("tag", "url="+url);              

            Log.i("tag", "userAgent="+userAgent);   

            Log.i("tag", "contentDisposition="+contentDisposition);            

            Log.i("tag", "mimetype="+mimetype);   

            Log.i("tag", "contentLength="+contentLength);   

            Uri uri = Uri.parse(url);   

            Intent intent = new Intent(Intent.ACTION_VIEW, uri);   

            startActivity(intent);              

        }   

    }  

	
}


