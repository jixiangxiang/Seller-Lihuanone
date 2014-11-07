package com.kdcm.seller.leo;


import org.apache.http.Header;
import org.json.JSONObject;

import com.kdcm.seller.MainActivity;
import com.kdcm.seller.R;
import com.kdcm.seller.web.HttpUtil;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends Activity implements OnClickListener{
	private EditText username, password;
	private Button loginBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
     // 获取控件对象  
        username = (EditText) findViewById(R.id.username); //用户名控件  
        password = (EditText) findViewById(R.id.password);//密码控件  
        loginBtn=(Button)findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(this);
        
        AsyncHttpClient request=HttpUtil.getClient();
        PersistentCookieStore myCookieStore = new PersistentCookieStore(this);
        request.setCookieStore(myCookieStore);
//        request.addHeader("Cookie", null);
        
    }
	@Override
	public void onClick(View v) {
		JsonHttpResponseHandler res=new JsonHttpResponseHandler() {
        	public void onFinish() { // 完成后调用，失败，成功，都要调用
            }

        	@Override
        	public void onSuccess(int statusCode, Header[] headers,
        			JSONObject response) {
        		super.onSuccess(statusCode, headers, response);
        		Intent intent=new Intent(LoginActivity.this,MainActivity.class);
        		int result=Integer.valueOf(response.getString("result"));
        		if (statusCode==200&result==1) {
					startActivity(intent);
				}

        	}
        	@Override
        	public void onFailure(int statusCode, Header[] headers,
        			Throwable throwable, JSONObject errorResponse) {
        		// TODO Auto-generated method stub
        		super.onFailure(statusCode, headers, throwable, errorResponse);
        	}
        };
		HttpUtil.login(username.getText().toString(), password.getText().toString(), res);
//		HttpUtil.getAllBills(res);
	}
}
