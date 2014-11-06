package com.kdcm.seller.web;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.annotation.SuppressLint;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

@SuppressLint("SimpleDateFormat")
public class HttpUtil {
	public static final String base_url="http://www.haoapp123.com/app/localuser/aidongdong/api.php?";
	private static     AsyncHttpClient client =new AsyncHttpClient();    //实例话对象
	static
    {
        client.setTimeout(10);   //设置链接超时，如果不设置，默认为10s
    }
	public static AsyncHttpClient getClient()
    {
        return client;
    }
	/**
	 * 进行登录
	 * @param loginName
	 * @param pwd
	 * @param res
	 */
	public static void login(String loginName,String pwd,AsyncHttpResponseHandler res)    //用一个完整url获取一个string对象
    {
		
		String url=base_url+"m=user&a=login&login_name="+loginName+"&login_password="+pwd;
		System.out.println(url);
		client.post(url, res);
    }
	/**
	 * 获取充值流水列表
	 * @param res
	 */
	public static void getRechargeBills(AsyncHttpResponseHandler res)    //用一个完整url获取一个string对象
    {
		String sdate;  
		Date ddate = new Date();
		sdate=(new SimpleDateFormat("yyyy-MM-dd")).format(ddate); 
		String url=base_url+"m=user&a=getBills&date="+sdate+"&type=3";
		client.post(url, res);
		
		
		
		
		RequestParams params = new RequestParams();
		params.put("m", "user");
		params.put("a", "getBills");
		params.put("date", sdate);
		params.put("type", "3");
        
    }
	/**
	 * 获取购物流水列表
	 * @param res
	 */
	public static void getBuyBills(AsyncHttpResponseHandler res)    //用一个完整url获取一个string对象
    {
		String sdate;  
		Date ddate=new Date();
		sdate=(new SimpleDateFormat("yyyy-MM-dd")).format(ddate);  
		String url=base_url+"m=user&a=getBills&type=2&date="+sdate;
		client.post(url, res);
		
		
		
		RequestParams params = new RequestParams();
		params.put("m", "user");
		params.put("a", "getBills");
		params.put("date", sdate);
		params.put("type", "2");
        
    }
	/**
	 * 获取所有交易流水
	 * @param res
	 */
	public static void getAllBills(AsyncHttpResponseHandler res)    //用一个完整url获取一个string对象
    {
		String sdate;  
		Date ddate=new Date();
		sdate=(new SimpleDateFormat("yyyy-MM-dd")).format(ddate);
		
		String url=base_url+"m=user&a=getBills&date="+sdate;
		client.post(url,res);
		
		
		
		RequestParams params = new RequestParams();
		params.put("m", "user");
		params.put("a", "getBills");
		params.put("date", sdate);
		
        
    }
}
