package com.kdcm.seller.leo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import com.kdcm.seller.R;
import com.kdcm.seller.R.layout;
import com.kdcm.seller.web.HttpUtil;
import com.loopj.android.http.JsonHttpResponseHandler;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SimpleAdapter;

public class RechargeBillActivity extends ListActivity {

	// 创建一个SimpleAdapter
	 SimpleAdapter simpleAdapter;
	 List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 创建一个SimpleAdapter
		 simpleAdapter = new SimpleAdapter(this, listItems,
				R.layout.bill_list_detail, new String[] { "orderNO","type","createTime","num","total","nickname" },
				new int[] { R.id.order,R.id.type,R.id.time,R.id.num,R.id.total,R.id.nickname });
		setListAdapter(simpleAdapter);
		JsonHttpResponseHandler response = new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(int statusCode, Header[] headers,
					JSONObject response) {

				super.onSuccess(statusCode, headers, response);
				Log.i("all-bills", response.toString());
				int result = response.getInt("result");
				if (statusCode == 200 & result == 1) {
					JSONArray array = response.getJSONArray("list");
					for (int i = 0; i < array.length(); i++) {
						Map<String, Object> listItem = new HashMap<String, Object>();
						listItem.put("orderNO", "订单号："+array.getJSONObject(i)
								.getString("order_no").toString());
						listItem.put("createTime", "创建时间："+array.getJSONObject(i).getString("create_time").toString());
						listItem.put("total", "总价：￥"+array.getJSONObject(i).getString("total_price").toString());
						
						String type=array.getJSONObject(i).getString("type");
						int sType=Integer.parseInt(type);
						if(sType==3){
							listItem.put("type", "充值");
							listItem.put("num", "数量："+0);
						}else{
							listItem.put("type", "购物");
							listItem.put("num", "数量:"+array.getJSONObject(i).getJSONArray("shopping_carts").length());
						}
						listItem.put("nickname", "消费者："+array.getJSONObject(i).getString("nickname").toString());
						listItems.add(listItem);
						Log.d("orderID",
								array.getJSONObject(i).getString("order_no")
										.toString());
					}
					Message msg = new Message();
					msg.what = 0x123;

					handler.sendMessage(msg);
//					simpleAdapter.notifyDataSetChanged();

				}

			}

			@Override
			public void onFailure(int statusCode, Header[] headers,
					Throwable throwable, JSONObject errorResponse) {
				super.onFailure(statusCode, headers, throwable, errorResponse);
			}
		};
		HttpUtil.getRechargeBills(response);

	}
	Handler handler = new Handler()
	{

		public void handleMessage(Message msg)
		{
			if(msg.what == 0x123)
			{
				
				// 使用adapter显示服务器响应
				simpleAdapter.notifyDataSetChanged();

			}
		}
	};
}

