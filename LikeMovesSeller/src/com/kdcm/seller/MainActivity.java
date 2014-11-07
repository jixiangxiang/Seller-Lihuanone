package com.kdcm.seller;



import com.kdcm.seller.R;
import com.kdcm.seller.leo.BillsActivity;
import com.kdcm.seller.leo.LoginActivity;

import android.app.ListActivity;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.TabHost;


public class MainActivity extends TabActivity  {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a {@link FragmentPagerAdapter}
	 * derivative, which will keep every loaded fragment in memory. If this
	 * becomes too memory intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TabHost tabHost=getTabHost();
		tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("商品选购").setContent(new Intent(this,LoginActivity.class)));
		tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("用户相关").setContent(new Intent(this,LoginActivity.class)));
		tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("流水明细").setContent(new Intent(this,BillsActivity.class)));
		tabHost.addTab(tabHost.newTabSpec("tab4").setIndicator("操作说明").setContent(new Intent(this,LoginActivity.class)));
//		setContentView(R.layout.activity_main);	
//		String[] arr={"商品选购","用户相关","流水明细","操作说明"};
//		ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr);
//		setListAdapter(adapter);
		}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}


