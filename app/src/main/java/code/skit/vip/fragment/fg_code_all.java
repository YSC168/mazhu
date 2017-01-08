package code.skit.vip.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import code.skit.vip.R;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.Adapter;
import android.widget.ListView;
import code.skit.vip.adapter.NewsListAdapter;
import code.skit.vip.model.News;
import java.util.List;
import java.util.ArrayList;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import android.content.Intent;
import android.widget.Toast;
import code.skit.vip.look_content;
import code.skit.vip.LookCodeActivity;

//import com.flyco.tablayoutsamples.R;

@SuppressLint("ValidFragment")
public class fg_code_all extends Fragment implements OnClickListener,
OnItemClickListener
{

	

    private String mTitle;

	private View v;
	private NewsListAdapter newsListAdapter;
	private List<News> newsList = new ArrayList<News>();
	
	private ListView lvNewsList;

    public static fg_code_all getInstance(String title) {
        fg_code_all sf = new fg_code_all();
        sf.mTitle = title;
        return sf;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         v = inflater.inflate(R.layout.fg_code_all, null);
//        TextView card_title_tv = (TextView) v.findViewById(R.id.card_title_tv);
//        card_title_tv.setText(mTitle);
intviews();
getNewsData();
        return v;
    }

	private void intviews()
	{
		lvNewsList=(ListView)v.findViewById(R.id.lv_news);
		//toolbar=(Toolbar)view.findViewById(R.id.toolbar);
		//setSupportActionBar(toolbar);
		// 新闻
		newsListAdapter = new NewsListAdapter(getActivity(), newsList);
		lvNewsList.setAdapter(newsListAdapter);
		lvNewsList.setOnItemClickListener(this);
		
	}
	
	
	


	@Override
	public void onClick(View p1)
	{
		// TODO: Implement this method
	}
	public void getNewsData() {
		BmobQuery<News> query = new BmobQuery<News>();
		query.order("-updatedAt");
		//	query.order("-createdAt");
		query.setLimit(50);
		query.setCachePolicy(BmobQuery.CachePolicy.NETWORK_ELSE_CACHE);
		final boolean isCache = query.hasCachedResult(getActivity(), News.class);
		query.findObjects(getActivity(), new FindListener<News>() {







				@Override
				public void onSuccess(List<News> object) {
					newsList = object;
					// 通知Adapter数据更新
					newsListAdapter.refresh((ArrayList<News>) newsList);
					newsListAdapter.notifyDataSetChanged();
				}

				@Override
				public void onError(int code, String arg1) {
					if(code==9010){
						Toast.makeText(getActivity(),"连接超时，请检查网络",Toast.LENGTH_LONG).show();

					}else if(code==9016){
						Toast.makeText(getActivity(),"无网络连接，请检查网络连接",Toast.LENGTH_LONG).show();


					}
				}
			});
	}



	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
							long id) {

		String g=newsList.get(position).getType();

		if(g.equals("代码")){
			Intent toNewsDetail = new Intent(getActivity(), LookCodeActivity.class);
			toNewsDetail.putExtra("NewsID", newsList.get(position).getObjectId());
			toNewsDetail.putExtra("NewsTitle", newsList.get(position).getTitle());
			toNewsDetail.putExtra("NewsAuthor", newsList.get(position).getAuthor());
			toNewsDetail.putExtra("NewsTime", newsList.get(position).getCreatedAt());
			toNewsDetail.putExtra("NewsContent", newsList.get(position).getContent());
			startActivity(toNewsDetail);
		}else{

			Intent toNewsDetail = new Intent(getActivity(), look_content.class);
			toNewsDetail.putExtra("NewsID", newsList.get(position).getObjectId());
			toNewsDetail.putExtra("NewsTitle", newsList.get(position).getTitle());
			toNewsDetail.putExtra("NewsAuthor", newsList.get(position).getAuthor());
			toNewsDetail.putExtra("NewsTime", newsList.get(position).getCreatedAt());
			toNewsDetail.putExtra("NewsContent", newsList.get(position).getContent());
			startActivity(toNewsDetail);


		}
	} 

	
}

