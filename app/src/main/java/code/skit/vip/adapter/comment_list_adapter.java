package code.skit.vip.adapter;
import android.content.Context;
import android.widget.BaseAdapter;
import android.view.LayoutInflater;
import java.util.List;
import android.view.View;
import java.util.ArrayList;
import code.skit.vip.model.News;
import code.skit.vip.R;
import android.widget.TextView;
import android.view.ViewGroup;
import code.skit.vip.model.Comment;

public class comment_list_adapter extends BaseAdapter {

	@SuppressWarnings("unused")
	private Context mContext;
	//private List<News> newsList = new ArrayList<News>();
	
	private LayoutInflater mInflater = null;
	private List<Comment> mNewsList = null;

	private TextView type; // 所选分类下的所有店铺列表

	public comment_list_adapter(Context context, List<Comment> newsList) {
		mContext = context;
		mNewsList = newsList;
		mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return mNewsList.size();
	}

	@Override
	public Object getItem(int position) {
		return mNewsList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	// 刷新列表中的数据
	public void refresh(ArrayList<Comment> list) {
		mNewsList = list;
		notifyDataSetChanged();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		NewsHolder newsHolder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.look_contemt_item, null);
			newsHolder = new NewsHolder();
			newsHolder.tvNewsType = (TextView) convertView
				.findViewById(R.id.tv_news_type);
		//	newsHolder.tvNewsCode=(thereisnospon.codeview.CodeView)convertView.findViewById(R.id.codeview);
			newsHolder.tvNewsTitle = (TextView) convertView
				.findViewById(R.id.tv_news_title);
			newsHolder.tvNewsDate = (TextView) convertView
				.findViewById(R.id.tv_news_date);
			newsHolder.tvNewsAuthor=(TextView)convertView.findViewById(R.id.tv_news_author);
			convertView.setTag(newsHolder);
		} else {
			newsHolder = (NewsHolder) convertView.getTag();
		}
		//拆分字符串，只取年月日
		String[] ss = new String[3];
		ss = mNewsList.get(position).getCreatedAt().split(" ");
		//newsHolder.tvNewsType.setText(mNewsList.get(position).getname());	//新闻类型
		newsHolder.tvNewsTitle.setText(mNewsList.get(position).getcontent());	//新闻标题
		//newsHolder.tvNewsCode.showCode(mNewsList.get(position).getcontent());
		newsHolder.tvNewsAuthor.setText(mNewsList.get(position).getname());	//新闻标题
type= (TextView) convertView
.findViewById(R.id.tv_news_type);
	type.setVisibility(View.GONE);
		newsHolder.tvNewsDate.setText((position+1)+"楼");//新闻发布日期
		return convertView;
	}

}

