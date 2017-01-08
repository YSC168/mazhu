package code.skit.vip;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.view.View;
import android.widget.Toast;
import code.skit.vip.model.News;
import cn.bmob.v3.listener.SaveListener;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.view.KeyEvent;
import android.support.v7.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.RadioButton;
import cn.bmob.v3.BmobUser;
import android.view.MenuItem;
//import cn.bmob.v3.exception.BmobException;


//
//import android.os.Bundle;
//import android.widget.EditText;
//import android.view.View;
////import listview.study.Data.News;
//import cn.bmob.v3.listener.SaveListener;
//import android.widget.Toast;
//import android.support.v7.app.AppCompatActivity;
//import code.skit.vip.model.News;

public class add_code extends AppCompatActivity
{

	private EditText title,content;

	private Toolbar toolbar;

	private TextView toolbar_title;

	private RadioButton type_code,type_help,type_talk,type_other;



	@Override
	protected void onCreate(Bundle savedInstanceState)
	{

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_code);
		setTitle("新建");
		initview();
		//toolbar_title.setText("新建");
	}

	private void initview()
	{
		toolbar=(Toolbar)findViewById(R.id.toolbar);

		setSupportActionBar(toolbar);
		//toolbar_title=(TextView)findViewById(R.id.toolbar_title);
		getSupportActionBar().setHomeButtonEnabled(true);
		// 默认显示左上角返回按钮
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		title=(EditText)findViewById(R.id.new_codetitle);
		content=(EditText)findViewById(R.id.new_codecontent);
		type_code = (RadioButton) findViewById(R.id.new_code_type_code);
		type_help = (RadioButton) findViewById(R.id.new_code_type_help);
		type_talk = (RadioButton) findViewById(R.id.new_code_type_talk);
		type_other = (RadioButton) findViewById(R.id.new_code_type_other);

	}
	public void 新建(View view)
	{
		//Toast.makeText(this,"",1000).show();

		if(BmobUser.getCurrentUser(add_code.this)==null){

			Toast.makeText(this,"请登录",1000).show();






		}else if(title.getText().toString().length() > 15){
			Toast.makeText(this,"标题不能超过15字",1000).show();
		}else if(title.getText().toString().equals("")|content.getText().toString().equals("") ){
			Toast.makeText(this,"其中有空项",1000).show();


		}

		else{

			BmobUser user = BmobUser.getCurrentUser(add_code.this);

			String name= user.getUsername();//用户名
			String cod=(content.getText().toString());

			News news=new News();
			news.setAuthor(name);
			news.setTitle(title.getText().toString());
			news.setContent(cod);
			//news.setType("其他");
			if(type_code.isChecked()){news.setType("代码");}else if(type_talk.isChecked()){news.setType("灌水");}
			else if(type_help.isChecked()){news.setType("求助");}else if(type_other.isChecked()){news.setType("其他");}

			news.save(this,new SaveListener(){

//					@Override
//					public void done(News p1, BmobException p2)
//					{
//						// TODO: Implement this method
//						
//						if(p2==null){
//							Toast.makeText(New_Code.this,"成功"+p1,1000).show();
//						}else{
//
//							Toast.makeText(New_Code.this,"失败"+p2.getMessage(),1000).show();
//
//
//						}
//					}
//					



					@Override
					public void onSuccess()
					{
						// TODO: Implement this metho
						Toast.makeText(add_code.this,"成功",1000).show();
						finish();
					}

					@Override
					public void onFailure(int p1, String p2)
					{
						Toast.makeText(add_code.this,"失败:"+p2,1000).show();
						//content.setText(p2);
					}




				});
		}


	}
	public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            add_code.this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		if (keyCode == KeyEvent.KEYCODE_BACK )
		{
			AlertDialog.Builder dialog=new AlertDialog.Builder(this);
			dialog.setTitle("提示");
			dialog.setMessage("代码未提交，确认退出？");
			dialog.setNegativeButton("确认", new DialogInterface.OnClickListener(){

					@Override
					public void onClick(DialogInterface p1, int p2)
					{
						finish();
					}
				});
			dialog.setNeutralButton("取消",null);
			dialog.show();

		}

		return false;

	}
}

