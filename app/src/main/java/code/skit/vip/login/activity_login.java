package code.skit.vip.login;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import code.skit.vip.R;
import code.skit.vip.utils.ViewFindUtils;
import android.view.View;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentManager;
import com.flyco.tablayout.SlidingTabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.app.Fragment;
import java.util.ArrayList;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class activity_login extends AppCompatActivity
{
	private ArrayList<Fragment> mFragments = new ArrayList<>();
    private final String[] mTitles = {
		"登录", "注册"
    };
    private MyPagerAdapter mAdapter;

	private Toolbar toolbar;
	
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
	//	getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		//getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		intviews();

        mFragments.add(activity_login_login.getInstance("登录"));
		mFragments.add(activity_login_add.getInstance("注册"));
		


		
        View decorView = getWindow().getDecorView();
        ViewPager vp = ViewFindUtils.find(decorView, R.id.vp);
        mAdapter = new MyPagerAdapter(getSupportFragmentManager());
        vp.setAdapter(mAdapter);

		/** tab固定宽度 */
        SlidingTabLayout tabLayout_4 = (SlidingTabLayout)findViewById(R.id.tl_4);


        tabLayout_4.setViewPager(vp);
		//qm();

    }
	public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            activity_login.this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
	
	private void intviews()
	{
		toolbar=(Toolbar)findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		getSupportActionBar().setHomeButtonEnabled(true);
		// 默认显示左上角返回按钮
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
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
