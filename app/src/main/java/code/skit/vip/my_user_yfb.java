package code.skit.vip;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import code.skit.vip.*;
import code.skit.vip.adapter.NewsListAdapter;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.Adapter;
import android.view.View;
import android.content.Intent;
import cn.bmob.v3.BmobQuery;
import code.skit.vip.model.News;
import cn.bmob.v3.listener.FindListener;
import java.util.List;
import java.util.ArrayList;
import cn.bmob.v3.BmobUser;
import code.skit.vip.model.user;
import android.widget.Toast;
import android.widget.TextView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.support.v7.app.AlertDialog;
import android.content.DialogInterface;
import cn.bmob.v3.listener.DeleteListener;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class my_user_yfb extends AppCompatActivity implements OnClickListener,
OnItemClickListener,OnItemLongClickListener
{



	private ListView lvNewsList;

	private NewsListAdapter newsListAdapter;
	private List<News> newsList = new ArrayList<News>();

	private TextView toolbar_title;

	private Toolbar toolbar;

	@Override
	public void onClick(View p1)
	{
		// TODO: Implement this method
	}




	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_user_yfb);
		intviews();
		getNewsData();
	}
	public void intviews()
	{
		//toolbar_title=(TextView)findViewById(R.id.toolbar_title);

		//toolbar_title.setText("已发布");
		// TODO: Implement this method
		lvNewsList=(ListView)findViewById(R.id.lv_news);
		toolbar=(Toolbar)findViewById(R.id.toolbar);
		
		setSupportActionBar(toolbar);
		getSupportActionBar().setHomeButtonEnabled(true);
		// 默认显示左上角返回按钮
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		// 新闻
		//fab=(FloatingActionButton)view.findViewById(R.id.test_fab);
		newsListAdapter = new NewsListAdapter(this, newsList);
		lvNewsList.setAdapter(newsListAdapter);
		lvNewsList.setOnItemClickListener(this);
		lvNewsList.setOnItemLongClickListener(this);

	}

	/**
	 * 初始化列表数据
	 * @date 2014-5-3
	 * @author Stone
	 */
	public void getNewsData() {
		if(BmobUser.getCurrentUser(this)!=null){
			user user = BmobUser.getCurrentUser(this,user.class);
			String use=user.getUsername();
			BmobQuery<News> query = new BmobQuery<News>();

			//query.addQueryKeys("代码",type);
			query.addWhereEqualTo("author",use);
			query.order("-updatedAt");
			query.setLimit(50);
			query.setCachePolicy(BmobQuery.CachePolicy.NETWORK_ELSE_CACHE);
			final boolean isCache = query.hasCachedResult(my_user_yfb.this, News.class);

			//	query.order("-createdAt");
			query.findObjects(this, new FindListener<News>() {





					@Override
					public void onSuccess(List<News> object) {
						if(object.toString().equals("[]")){
							Snackbar.make(lvNewsList,"暂无数据",1000).show();

						}else{
							Snackbar.make(lvNewsList,"长按可删除",1000).show();

							newsList = object;
							// 通知Adapter数据更新
							newsListAdapter.refresh((ArrayList<News>) newsList);
							newsListAdapter.notifyDataSetChanged();
						}
					}

					@Override
					public void onError(int code, String arg1) {
						//Snackbar.make(lvNewsList,code,1000).show();

						if(code==9010){
							Snackbar.make(lvNewsList,"连接超时，请检查网络",1000).show();

							//Toast.makeText(my_user_yfb.this,"",Toast.LENGTH_LONG).show();

						}else if(code==9016){
							Snackbar.make(lvNewsList,"网络连接，请检查网络连接",1000).show();

							//Toast.makeText(my_user_yfb.this,"无网络连接，请检查网络连接",Toast.LENGTH_LONG).show();


						}

						//toast(" 获取数据失败了"+arg1);
						//Toast.makeText(my_user_yfb.this,arg1,1000).show();
					}
				});
		}else{
			finish();
			Toast.makeText(my_user_yfb.this,"请登录",1000).show();
		}
	}
	@Override
	public boolean onItemLongClick(AdapterView<?> p1, View p2, final int p3, long p4)
	{

		//final String g=newsList.get(p3).getObjectId();

		// TODO: Implement this method
		AlertDialog.Builder gg=new AlertDialog.Builder(this);
		gg.setTitle("提示");
		gg.setMessage("确认删除？");
		gg.setPositiveButton("确认", new DialogInterface.OnClickListener(){

				@Override
				public void onClick(DialogInterface p1, int p2)
				{
					News news=new News();
					//news.setObjectId(g);
					news.delete(my_user_yfb.this,newsList.get(p3).getObjectId(),new DeleteListener(){

							@Override
							public void onSuccess()
							{
								Toast.makeText(my_user_yfb.this,"删除成功",1000).show();

								// TODO: Implement this method
								getNewsData();
							}

							@Override
							public void onFailure(int p1, String p2)
							{
								// TODO: Implement this method
								Toast.makeText(my_user_yfb.this,"删除失败",1000).show();
							}
						});

				}
			});
		//gg.setNegativeButton("检测更新",null);
		gg.setNeutralButton("取消", null);
		gg.setCancelable(false);

		gg.show();

		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            my_user_yfb.this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
							long id) {
		String g=newsList.get(position).getType();

		if(g.equals("代码")){
			Intent toNewsDetail = new Intent(this, LookCodeActivity.class);
			toNewsDetail.putExtra("NewsID", newsList.get(position).getObjectId());
			toNewsDetail.putExtra("NewsTitle", newsList.get(position).getTitle());
			toNewsDetail.putExtra("NewsAuthor", newsList.get(position).getAuthor());
			toNewsDetail.putExtra("NewsTime", newsList.get(position).getCreatedAt());
			toNewsDetail.putExtra("NewsContent", newsList.get(position).getContent());
			startActivity(toNewsDetail);
		}else{

			Intent toNewsDetail = new Intent(this, look_content.class);
			toNewsDetail.putExtra("NewsID", newsList.get(position).getObjectId());
			toNewsDetail.putExtra("NewsTitle", newsList.get(position).getTitle());
			toNewsDetail.putExtra("NewsAuthor", newsList.get(position).getAuthor());
			toNewsDetail.putExtra("NewsTime", newsList.get(position).getCreatedAt());
			toNewsDetail.putExtra("NewsContent", newsList.get(position).getContent());
			startActivity(toNewsDetail);


		}
	}
}

