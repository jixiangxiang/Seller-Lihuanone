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
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class BuyBillActivity extends ListActivity {
	JSONObject json;
	// 创建一个SimpleAdapter
	 SimpleAdapter simpleAdapter;
	 List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 创建一个SimpleAdapter
		 simpleAdapter = new SimpleAdapter(this, listItems,
				R.layout.bill_list_detail, new String[] { "orderNO","type","createTime","num","total" ,"nickname"},
				new int[] { R.id.order,R.id.type,R.id.time,R.id.num,R.id.total,R.id.nickname });
		setListAdapter(simpleAdapter);
		JsonHttpResponseHandler response = new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(int statusCode, Header[] headers,
					JSONObject response) {

				super.onSuccess(statusCode, headers, response);
				json=response;
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

		HttpUtil.getBuyBills(response);
		getListView().setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				int result = json.getInt("result");
				if (result == 1) {
					JSONArray array = json.getJSONArray("list");

					String type = array.getJSONObject(position).getString(
							"type");
					int sType = Integer.parseInt(type);
					if (sType == 2) {
						String content = "";
						JSONObject json = array.getJSONObject(position);
						JSONArray carts = json.getJSONArray("shopping_carts");
						for (int j = 0; j < carts.length(); j++) {
							JSONObject cart = carts.getJSONObject(j);
							content = content + "编号:"
									+ cart.getString("product_no").toString()
									+ "--名称:"
									+ cart.getString("name").toString()
									+ "--单价:￥"
									+ cart.getString("price").toString()
									+ "--数量:"
									+ cart.getString("number").toString()
									+ "\n";

						}

						AlertDialog.Builder builder = new AlertDialog.Builder(
								BuyBillActivity.this)
						// 设置对话框标题
								.setTitle("订单商品详情").setMessage(content);
						// 为AlertDialog.Builder添加【确定】按钮
						// 调用setPositiveButton方法添加确定按钮
						builder.setPositiveButton("确定", new OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
							}
						}).create().show();
					}

				}

			}

		});
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
