package com.kdcm.seller.web;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.annotation.SuppressLint;
import android.util.Log;

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

		String startTime;
		String endTime;
		Date date=new Date();
		Date time=new Date();
		Calendar calendar = Calendar.getInstance(); //得到日历
		calendar.setTime(date);//把当前时间赋给日历
		calendar.add(Calendar.DAY_OF_MONTH, +1);  //设置为前一天
		time = calendar.getTime();   //得到前一天的时间
		endTime=(new SimpleDateFormat("yyyy-MM-dd")).format(time);
		
		calendar.add(Calendar.DAY_OF_MONTH, -10);  //设置为前十天
		time = calendar.getTime();   //得到前十天的时间
		startTime=(new SimpleDateFormat("yyyy-MM-dd")).format(time); 
		
		String url=base_url+"m=user&a=getBills&type=3&start_time="+startTime+"&end_time="+endTime;
		client.post(url, res);
		Log.i("recharge-bill", url);
        
    }
	/**
	 * 获取购物流水列表
	 * @param res
	 */
	public static void getBuyBills(AsyncHttpResponseHandler res)    //用一个完整url获取一个string对象
    {
		String startTime;
		String endTime;
		Date date=new Date();
		Date time=new Date();
		Calendar calendar = Calendar.getInstance(); //得到日历
		calendar.setTime(date);//把当前时间赋给日历
		calendar.add(Calendar.DAY_OF_MONTH, +1);  //设置为前一天
		time = calendar.getTime();   //得到前一天的时间
		endTime=(new SimpleDateFormat("yyyy-MM-dd")).format(time);
		
		calendar.add(Calendar.DAY_OF_MONTH, -10);  //设置为前十天
		time = calendar.getTime();   //得到前十天的时间
		startTime=(new SimpleDateFormat("yyyy-MM-dd")).format(time); 
		
		String url=base_url+"m=user&a=getBills&type=2&start_time="+startTime+"&end_time="+endTime;
		client.post(url, res);
		
		Log.i("buy-url", url);
		

        
    }
	/**
	 * 获取所有交易流水
	 * @param res
	 */
	public static void getAllBills(AsyncHttpResponseHandler res)    //用一个完整url获取一个string对象
    {
		
		String startTime;
		String endTime;
		Date date=new Date();
		Date time=new Date();
		Calendar calendar = Calendar.getInstance(); //得到日历
		calendar.setTime(date);//把当前时间赋给日历
		calendar.add(Calendar.DAY_OF_MONTH, +1);  //设置为前一天
		time = calendar.getTime();   //得到前一天的时间
		endTime=(new SimpleDateFormat("yyyy-MM-dd")).format(time);
		
		calendar.add(Calendar.DAY_OF_MONTH, -10);  //设置为前十天
		time = calendar.getTime();   //得到前十天的时间
		startTime=(new SimpleDateFormat("yyyy-MM-dd")).format(time); 
		
		String url=base_url+"m=user&a=getBills&start_time="+startTime+"&end_time="+endTime;
		
		client.post(url,res);
		
		Log.i("all-bill-url", url);
		

		
        
    }
}
