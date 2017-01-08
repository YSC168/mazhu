package code.skit.vip;


import android.support.v7.app.AppCompatActivity;
import android.content.Context;
import java.util.ArrayList;
import android.support.v4.view.ViewPager;
import android.view.View;
import code.skit.vip.utils.ViewFindUtils;
import code.skit.vip.fragment.fg_code_all;
import com.flyco.tablayout.SlidingTabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.support.design.widget.NavigationView;
import android.widget.ImageView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.v4.view.GravityCompat;
import android.view.WindowManager;
import cn.bmob.v3.Bmob;
import android.os.Handler;
import cn.bmob.v3.BmobUser;
import code.skit.vip.model.user;
import code.skit.vip.model.GetData;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap;
import android.view.KeyEvent;
import android.support.v7.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View.OnClickListener;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import code.skit.vip.fragment.fg_code_code;
import code.skit.vip.login.activity_login;
import android.support.design.widget.Snackbar;

public class MainActivity extends AppCompatActivity implements
OnClickListener
{

	
	
	private Context mContext = this;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private final String[] mTitles = {
		"全部", "代码", "求助"
		, "灌水", "开心一刻", "公告"
    };
    private MyPagerAdapter mAdapter;

	private Toolbar toolbar;

	private TextView toolbar_title;

	private NavigationView navigationView;

	private TextView tv;

	private ImageView tx;

	private ImageView txbz;

	private TextView qm;
	private Bitmap bitmap;
	
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
				case 0x001:

					//txbz.setImageBitmap(bitmap);
					tx.setImageBitmap(bitmap);

					//Toast.makeText(getActivity(), "图片加载完毕", Toast.LENGTH_SHORT).show();
					break;
				case 0x002:

					//txyltu.setImageBitmap(bitmap);

					break;
				default:
					break;
			}
		}};

	private FloatingActionButton fab;

	private DrawerLayout drawer;
	@Override
	public void onClick(View p1)
	{
		
		switch (p1.getId()) {
			case R.id.test_fab:
				Intent intent=new Intent(MainActivity.this,add_code.class);
				startActivity(intent);
				break;

			default:
				break;
		}
		
		
	}
	private String txdz;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		//getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		Bmob.initialize(this,"3f09752fef867559f55ad519ae0702d0");
		
        
        mFragments.add(fg_code_all.getInstance("全部"));
		mFragments.add(fg_code_code.getInstance("代码"));
		mFragments.add(fg_code_code.getInstance("求助"));
		mFragments.add(fg_code_code.getInstance("灌水"));
		mFragments.add(fg_code_code.getInstance("开心一刻"));
		mFragments.add(fg_code_code.getInstance("公告"));
		
        

intviews();
        View decorView = getWindow().getDecorView();
        ViewPager vp = ViewFindUtils.find(decorView, R.id.vp);
        mAdapter = new MyPagerAdapter(getSupportFragmentManager());
        vp.setAdapter(mAdapter);

		/** tab固定宽度 */
        SlidingTabLayout tabLayout_4 = (SlidingTabLayout)findViewById(R.id.tl_4);


        tabLayout_4.setViewPager(vp);
qm();

    }

	@Override
	protected void onResume()
	{
		// TODO: Implement this method
		super.onResume();
		qm();
	}
	
	
	private void intviews()
	{
		// TODO: Implement this method

		toolbar=(Toolbar)findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
	//	toolbar.setTitle("");
		//toolbar_title=(TextView)findViewById(R.id.toolbar_title);
 //       fManager = getSupportFragmentManager();

		navigationView = (NavigationView) findViewById(R.id.nav_view);
		navigationView.setItemIconTintList(null);
		tv=(TextView)navigationView.getHeaderView(0).findViewById(R.id.tv);
fab=(FloatingActionButton)findViewById(R.id.test_fab);
fab.setOnClickListener(this);
		tx=(ImageView) navigationView.getHeaderView(0).findViewById(R.id.my_user_tx);
		txbz=(ImageView)navigationView.getHeaderView(0).findViewById(R.id.txbz);
		qm=(TextView)navigationView.getHeaderView(0).findViewById(R.id.qm);
		//ImageView pic = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.tou_pic);
        //TextView username = (TextView) navigationView.getHeaderView(0).findViewById(R.id.nav_username);

		 drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.nav_open, R.string.nav_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
		//qm();
		navigation();
	}
	private void qm()
    {
		if(BmobUser.getCurrentUser(MainActivity.this)!=null){
			user user = BmobUser.getCurrentUser(MainActivity.this,user.class);
			txdz=user.getTxdz();
			hqtx();
			tv.setText(user.getUsername());
			qm.setText(user.getqm());
			//qm1.setText("用户名："+user.getUsername()+"\n \t \n"+"签名："+user.getqm());
		}else{
			//Toast.makeText(this,"请登录",1000).show();
			//finish();
			tv.setText("请登录");
			qm.setText("");
			tx.setImageResource(R.drawable.icon_tx);
		}
	}
	private void hqtx()
	{
		// TODO: Implement this method

		new Thread() {

			




			public void run() {
				try {
					byte[] data = GetData.getImage(txdz);
					bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
				} catch (Exception e) {
					e.printStackTrace();
				}
				handler.sendEmptyMessage(0x001);
			};
		}.start();
		//my_user_tx.setImageBitmap(bitmap);

	}
	private void navigation()
	{
		navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
				@Override
				public boolean onNavigationItemSelected(MenuItem item) {
					//在这里处理item的点击事件
					int id = item.getItemId();

					if (id == R.id.nav_yfb) {
						Intent intent=new Intent(MainActivity.this,my_user_yfb.class);
					startActivity(intent);
						}else if(id==R.id.nav_setting){
							
							Intent intent=new Intent(MainActivity.this,settingActivity.class);
							startActivity(intent);
						}else if(id==R.id.nav_me){

							Intent intent=new Intent(MainActivity.this,activity_login.class);
							startActivity(intent);
						}
						drawer.closeDrawer(GravityCompat.START);

					return true;
				}
			});
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		if (keyCode == KeyEvent.KEYCODE_BACK )
		{
			drawer.closeDrawer(GravityCompat.START);
			
			Snackbar.make(fab, "退出？", Snackbar.LENGTH_SHORT)
				.setAction("确认", new OnClickListener(){

					@Override
					public void onClick(View p1)
					{
finish();
					}
				}).show();
			
			
//			AlertDialog.Builder dialog=new AlertDialog.Builder(this);
//			dialog.setTitle("提示");
//			dialog.setMessage("确认退出？");
//			dialog.setNegativeButton("确认", new DialogInterface.OnClickListener(){
//
//					@Override
//					public void onClick(DialogInterface p1, int p2)
//					{
//						finish();
//					}
//				});
//			dialog.setNeutralButton("取消",null);
//			dialog.show();

		}

		return false;

	}

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }
}

