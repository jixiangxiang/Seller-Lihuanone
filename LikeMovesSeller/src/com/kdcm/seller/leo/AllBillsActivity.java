package com.kdcm.seller.leo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import com.kdcm.seller.R;
import com.kdcm.seller.web.HttpUtil;
import com.loopj.android.http.JsonHttpResponseHandler;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SimpleAdapter;

public class AllBillsActivity extends ListActivity {
	private String[] orders;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 创建一个List集合，List集合的元素是Map
		final List<Map<String, Object>> listItems = 
				new ArrayList<Map<String, Object>>();
		// 创建一个SimpleAdapter
		final SimpleAdapter simpleAdapter = new SimpleAdapter(this, listItems,
			R.layout.bill_list_detail, 
			new String[] { "orderID"},
			new int[] { R.id.orderID });
		setListAdapter(simpleAdapter);
		
				JsonHttpResponseHandler response=new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(int statusCode, Header[] headers,
					JSONObject response) {

				super.onSuccess(statusCode, headers, response);
				Log.i("all-bills", response.toString());
				int result=response.getInt("result");
				if (statusCode==200&result==1) {
					JSONArray array=response.getJSONArray("list");
					
					for (int i = 0; i < array.length(); i++) {
						Map<String, Object> listItem = new HashMap<String, Object>();
						listItem.put("orderID", array.getJSONObject(i).getString("order_no").toString());
						listItems.add(listItem);
						Log.d("orderID",  array.getJSONObject(i).getString("order_no").toString());
					}
					
					simpleAdapter.notifyDataSetChanged();
					
				}
				
			}
			@Override
			public void onFailure(int statusCode, Header[] headers,
					Throwable throwable, JSONObject errorResponse) {
				super.onFailure(statusCode, headers, throwable, errorResponse);
			}
		};
		HttpUtil.getAllBills(response);
		
		
		
	}
	private void getBill(){
		
	}
}
