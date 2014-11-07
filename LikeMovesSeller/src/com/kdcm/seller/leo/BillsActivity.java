package com.kdcm.seller.leo;


import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;

public class BillsActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_bills);
		String[] string={"全部订单","充值订单","消费订单"};
		ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_single_choice,string);
		setListAdapter(adapter);
		getListView().setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				switch (position) {
				case 0:
					Intent all=new Intent(BillsActivity.this,AllBillsActivity.class);
					startActivity(all);
					break;
				case 1:
					Intent recharge=new Intent(BillsActivity.this,RechargeBillActivity.class);
					startActivity(recharge);
					break;
				case 2:
					Intent buy=new Intent(BillsActivity.this,BuyBillActivity.class);
					startActivity(buy);
					break;

				default:
					break;
				}
				
			}
			
		});
	}
}
