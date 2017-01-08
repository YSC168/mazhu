package code.skit.vip;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.view.View;
import code.skit.vip.R;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.bmob.v3.BmobUser;
import android.content.Intent;
import android.widget.Toast;
import code.skit.vip.model.Updata;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import java.util.List;
import cn.bmob.v3.listener.GetListener;
import android.support.v7.app.AlertDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
public class settingActivity extends AppCompatActivity implements OnClickListener
{




	private TextView toolbar_title;

	private LinearLayout setting_tcdl;

	private LinearLayout setting_updata;

	private Updata updata;
	private String message;
	private String banben;


	private int version;

	private String url;

	private LinearLayout setting_code_theme;

	private Toolbar toolbar;
	@Override
	public void onClick(View p1)
	{
		switch (p1.getId()) {
			case R.id.setting_tcdl:
				BmobUser.logOut(settingActivity.this);
				//BmobUser currentUser = BmobUser.getCurrentUser(settingActivity.this);
				Toast.makeText(settingActivity.this, "退出登录成功", 1000).show();
				setting_tcdl.setVisibility(View.INVISIBLE);

				break;
			case R.id.code_theme:
				Intent intent=new Intent(this,setting_code_theme.class);
				startActivity(intent);
				break;
			case R.id.setting_updata:
				BmobQuery<Updata> query = new BmobQuery<Updata>();
				//query.addQueryKeys("代码",type);
				//query.addWhereEqualTo("type", "代码");
				//query.order("-updatedAt");
				query.getObject(settingActivity.this, "yiU5888p", new GetListener<Updata>(){



						@Override
						public void onFailure(int p1, String p2)
						{
							// TODO: Implement this method
						}

						@Override
						public void onSuccess(Updata p1)
						{
							message=	p1.getMessage();
							url=p1.getUrl();
							version=p1.getVersion();
							banben=p1.getBanben();
							updata();
							//Toast.makeText(settingActivity.this,version+url+message,1000).show();
						}


					});
				//	query.order("-createdAt");
				break;
			default:
				break;
		}}


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		intviews();
		if ( BmobUser.getCurrentUser(this) != null) {
			setting_tcdl.setVisibility(View.VISIBLE);
//                r0.登退显示.setText("退出帐号");

			return;
		}else{
			setting_tcdl.setVisibility(View.INVISIBLE);

		}
		setting_tcdl.setOnClickListener(this);

	}

	private void intviews()
	{
		toolbar=(Toolbar)findViewById(R.id.toolbar);
		//toolbar_title=(TextView)findViewById(R.id.toolbar_title);
		setting_tcdl=(LinearLayout)findViewById(R.id.setting_tcdl);
		setting_updata=(LinearLayout)findViewById(R.id.setting_updata);
		setting_code_theme=(LinearLayout)findViewById(R.id.code_theme);
		setting_code_theme.setOnClickListener(this);
		setting_updata.setOnClickListener(this);
		setting_tcdl.setOnClickListener(this);
		setSupportActionBar(toolbar);
		getSupportActionBar().setHomeButtonEnabled(true);
		// 默认显示左上角返回按钮
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
	//	toolbar.setTitle("设置");
		
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            settingActivity.this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
	
	
	private void updata()
	{
		if(version>1){
			AlertDialog.Builder gg=new AlertDialog.Builder(this);
			gg.setTitle("更新提示");
			gg.setMessage("版本:"+banben+"\n"+"更新内容:"+message);
			gg.setPositiveButton("更新", new DialogInterface.OnClickListener(){

					@Override
					public void onClick(DialogInterface p1, int p2)
					{
						Intent intent = new Intent();
						intent.setAction(Intent.ACTION_VIEW);
						intent.addCategory(Intent.CATEGORY_BROWSABLE);
						intent.setData(Uri.parse(url));
						startActivity(intent);
					}
				});
			//gg.setNegativeButton("检测更新",null);
			gg.setNeutralButton("取消", null);
			//gg.setCancelable(false);

			gg.show();

		}else{


		}
	}
}

