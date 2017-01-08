package code.skit.vip.login;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import code.skit.vip.R;
import android.widget.EditText;
import android.text.TextWatcher;
import android.text.Editable;
import android.support.design.widget.TextInputLayout;
import android.widget.Button;
import android.view.View.OnClickListener;
import code.skit.vip.model.user;
import cn.bmob.v3.listener.SaveListener;
import android.widget.Toast;

//import com.flyco.tablayoutsamples.R;

@SuppressLint("ValidFragment")
public class activity_login_add extends Fragment {
    private String mTitle;

	private View v;

	private EditText username;

	private TextInputLayout textInputLayout;

	private TextInputLayout textInputLayout1;

	private EditText password;

	private EditText passwordals;

	private TextInputLayout textInputLayout2;

	private Button add_user;

	private String name;

	private String als;

	private String pass;

	private EditText email;

	private String emailadress;

	private TextInputLayout textInputLayout3;

    public static activity_login_add getInstance(String title) {
        activity_login_add sf = new activity_login_add();
        sf.mTitle = title;
        return sf;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         v = inflater.inflate(R.layout.activity_login_add, null);
//        TextView card_title_tv = (TextView) v.findViewById(R.id.card_title_tv);
//        card_title_tv.setText(mTitle);
intviews();
jiance();
        return v;
    }

	

	private void intviews()
	{
		textInputLayout1 = (TextInputLayout) v.findViewById(R.id.textInputLayout1);
		textInputLayout2= (TextInputLayout) v.findViewById(R.id.textInputLayout2);
		textInputLayout3= (TextInputLayout) v.findViewById(R.id.textInputLayout3);
		
		add_user=(Button)v.findViewById(R.id.add_user);
		textInputLayout = (TextInputLayout) v.findViewById(R.id.textInputLayout);
		username=(EditText)v.findViewById(R.id.username);
		password=(EditText)v.findViewById(R.id.password);
		passwordals=(EditText)v.findViewById(R.id.passwordals);
		email=(EditText)v.findViewById(R.id.email);
	}
	private void jiance()
	{
		username.addTextChangedListener(new TextWatcher() {
				@Override
				public void beforeTextChanged(CharSequence s, int start, int count, int after) {

				}

				@Override
				public void onTextChanged(CharSequence s, int start, int before, int count) {

				}

				@Override
				public void afterTextChanged(Editable s) {
					String string = s.toString();
					if (string.length() > 10) {
						textInputLayout.setErrorEnabled(true);
						textInputLayout.setError("用户名长度不能大于10！");
					}else if(string.length()<=10){
						textInputLayout.setErrorEnabled(false);

					}
				}
			});
			
		password.addTextChangedListener(new TextWatcher() {
				@Override
				public void beforeTextChanged(CharSequence s, int start, int count, int after) {

				}

				@Override
				public void onTextChanged(CharSequence s, int start, int before, int count) {

				}

				@Override
				public void afterTextChanged(Editable s) {
					String string = s.toString();
					if (string.length() < 6) {
						textInputLayout1.setErrorEnabled(true);
						textInputLayout1.setError("输入密码长度不能小于6！");
					}else if(string.length()>=6){
						textInputLayout1.setErrorEnabled(false);

					}
				}
			});
		email.addTextChangedListener(new TextWatcher() {
				@Override
				public void beforeTextChanged(CharSequence s, int start, int count, int after) {

				}

				@Override
				public void onTextChanged(CharSequence s, int start, int before, int count) {

				}

				@Override
				public void afterTextChanged(Editable s) {
					String string = s.toString();
					if(string.matches("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*")){
						
						textInputLayout3.setErrorEnabled(false);
						
						
					}else {
							textInputLayout3.setErrorEnabled(true);
							textInputLayout3.setError("输入的邮箱不合法！");
							
					}
				}
			});
//						textInputLayout2.setErrorEnabled(true);
//						textInputLayout2.setError("输入密码长度不能小于6！");
//					
			
		add_user.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					zhuce();
				}

				
			});
	}
	private void zhuce()
	{
		  name=username.getText().toString();
         pass=password.getText().toString();
         als=passwordals.getText().toString();
		 emailadress=email.getText().toString();
   //     final String rr=r.getText().toString();
	if(name.equals("")){
			textInputLayout.setError("用户名不能为空");

		}
		if(emailadress.equals("")){
			textInputLayout3.setError("邮箱不能空");
			
		}
		else if(pass.equals(als)){
			textInputLayout2.setErrorEnabled(false);
			qr();
        }
		else{
		textInputLayout2.setErrorEnabled(true);
		textInputLayout2.setError("两次密码不相同");
		}
        
        
	}

	private void qr()
	{
		user bu = new user();
		// bu.setAge(0);
		//  bu.setNick(imei);
		bu.setTxdz("https://ss0.bdstatic.com/9bA1vGfa2gU2pMbfm9GUKT-w/timg?wisealaddin&size=w350&quality=75&sec=1483371953&di=64fa5585867ce7a6dc7448e43da4d5ea&src=http%3A%2F%2Fphoto.jokeji.cn%2Fuppic%2F14-11%2F18%2F2346248575852.jpg");
		// bu.setloyer(0);
		bu.setUsername(name);
		bu.setPassword(pass);
		bu.setcodetheme(1);
		//bu.setdn(ww);
		bu.setEmail(emailadress);
		bu.setqm("这个人很懒什么都没留下");
		// bu.setcolor("");
		bu.signUp(getActivity(), new SaveListener() {
				@Override
				public void onSuccess() {
					//   toast("注册成功:");

					Toast.makeText(getActivity(),"注册成功快快登录吧",Toast.LENGTH_LONG).show();

//                        Intent f=new Intent(my_user_adduser.this,MainActivity.class);
//                        startActivity(f);
					

				}
				@Override
				public void onFailure(int code, String msg) {
					//toast("注册失败:"+msg);
					//q.setText(msg+code);
					//Toast.makeText(my_user_adduser.this,msg,Toast.LENGTH_LONG).show();

//        
					if(code==9010){
						Toast.makeText(getActivity(),"连接超时，请检查网络",Toast.LENGTH_LONG).show();

					}else if(code==9016){
						Toast.makeText(getActivity(),"无网络连接，请检查网络连接",Toast.LENGTH_LONG).show();


					}else if(code==202){
						//Toast.makeText(my_user_adduser.this,"用户名"+qq+"已被注册",Toast.LENGTH_LONG).show();
textInputLayout.setErrorEnabled(true);
textInputLayout.setError("用户名"+name+"已被注册");

					}if(code==203){
						//Toast.makeText(my_user_adduser.this,"邮箱"+rr+"已被注册",Toast.LENGTH_LONG).show();

textInputLayout3.setErrorEnabled(true);
textInputLayout3.setError("邮箱: "+emailadress+"已注册过");
					}

				}

			});

		
	}
	
}

