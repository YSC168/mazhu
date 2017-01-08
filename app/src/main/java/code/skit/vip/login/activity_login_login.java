package code.skit.vip.login;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import code.skit.vip.R;
import android.support.design.widget.TextInputLayout;
import android.widget.Button;
import android.widget.EditText;
import android.view.View.OnClickListener;
import code.skit.vip.model.user;
import cn.bmob.v3.listener.SaveListener;
import android.widget.Toast;

//import com.flyco.tablayoutsamples.R;

@SuppressLint("ValidFragment")
public class activity_login_login extends Fragment {
    private String mTitle;

	private View v;

	private TextInputLayout textInputLayout1;

	private TextInputLayout textInputLayout;

	private Button user_login;

	private EditText username;

	private EditText password;

    public static activity_login_login getInstance(String title) {
        activity_login_login sf = new activity_login_login();
        sf.mTitle = title;
        return sf;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         v = inflater.inflate(R.layout.activity_login_login, null);
//        TextView card_title_tv = (TextView) v.findViewById(R.id.card_title_tv);
//        card_title_tv.setText(mTitle);
intviews();
onclick();
        return v;
    }

	

	private void intviews()
	{
		textInputLayout1 = (TextInputLayout) v.findViewById(R.id.textInputLayout1);
		
		user_login=(Button)v.findViewById(R.id.user_login);
		textInputLayout = (TextInputLayout) v.findViewById(R.id.textInputLayout);
		username=(EditText)v.findViewById(R.id.username);
		password=(EditText)v.findViewById(R.id.password);
		
	}
	
	private void onclick()
	{
		user_login.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					if(username.getText().toString().equals("")){
						textInputLayout.setErrorEnabled(true);
						textInputLayout.setError("用户名不能为空");
						
					}else if(password.getText().toString().equals("")){
						textInputLayout1.setErrorEnabled(true);
						textInputLayout1.setError("密码不能为空");

					}else{
					textInputLayout1.setErrorEnabled(false);
					textInputLayout.setErrorEnabled(false);
					login();
					}
				}

				


			});
	}
	private void login()
	{
		user bu2 = new user();
        bu2.setUsername(username.getText().toString());
        bu2.setPassword(password.getText().toString());
        bu2.login(getActivity(), new SaveListener() {




                @Override
                public void onSuccess() {


                    Toast.makeText(getActivity(),"登录成功",Toast.LENGTH_LONG).show();
					//activity_login..finish();
//					Intent i=new Intent(my_user_login.this,jm.class);
//					startActivity(i);
//					MainActivity.this.finish();
				}   
				/*
				 s();
				 */
				//通过BmobUser user = BmobUser.getCurrentUser(context)获取登录成功后的本地用户信息
				//如果是自定义用户对象MyUser，可通过MyUser user = BmobUser.getCurrentUser(context,MyUser.class)获取自定义用户信息

				@Override
				public void onFailure(int code, String msg) {
					if(code==101){
						textInputLayout.setErrorEnabled(true);
						textInputLayout.setError("用户名或密码错误");
						
					}
					if(code==9010){
						Toast.makeText(getActivity(),"连接超时，请检查网络",Toast.LENGTH_LONG).show();

					}else if(code==9016){
						Toast.makeText(getActivity(),"无网络连接，请检查网络连接",Toast.LENGTH_LONG).show();


					}

				}
			});
		
		}
	
}

