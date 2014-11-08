package com.kdcm.seller.leo;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.kdcm.seller.R;

public class IntroActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_intro);
		TextView tv=(TextView)findViewById(R.id.intro_content);
		String intro="里环王收银端分为四个模块：1,购物模块，2,用户相关模块，3,流水明细模块，4,功能介绍模块。\n"
				+ "本收银端主要使用对象是里环王线下实体店中的销售人员，作用为帮助销售员为拥有线上里环王账户的到店消费者快递建立订单，"
				+ "使用线上账户余额或第三方支付进行付款。\n模块介绍：\n"
				+ "1，购物模块：使用添加按钮将用户所选物品添加的购物车中，然后点击购物车进行购物车确定商品后，点击结算后会将订单推送给消费者的里环王账户里。\n"
				+ "2，用户相关模块：分为消费用户选择和用户充值。\n"
				+ "消费用户选择：根据用户提供的注册手机号，查找用户，选择出目标用户。\n"
				+ "用户充值：进入用户充值，查看将要充值的用户信息（昵称，手机号），输入将要输入的金币数量，点击确定，充值。\n"
				+ "3，流水明细模块：本模块罗列了最近几天所有流水账单，分为所有流水账单，充值流水账单和销售流水账单。\n"
				+ "4，功能介绍模块：即为本模块，主要介绍了本软件的使用方法和操作步骤。\n"
				+ "动作1：为消费者充值\n"
				+ "1，收银员登陆成功后，进入用户相关模块-消费用户选择，输入消费者手机号，搜索出用户，选择成功，返回用户相关模块\n"
				+ "2，进入用户相关模块-用户充值，检查充值用户信息是否正确，输入将要充值的金额，点击确定;\n"
				+ "3，提醒充值成功。\n"
				+ "动作2：为消费者创建线上订单\n"
				+ "1，收银员登陆成功后，进入用户相关模块-消费用户选择，输入消费者手机号，搜索出用户，选择成功，返回购物模块\n"
				+ "2，将用户所选商品通过添加按钮添加到购物车，用户选择完毕，进入购物车，让用户进行商品确认，确认完毕，点击结算进入结算界面；\n"
				+ "3，输入小于用户拥有金币数并且低于抵扣额得金币数，确认目标用户的相关信息（昵称，金币，手机号）是否正确，\n"
				+ "4，点击创建订单，创建订单成功。\n"
				+ "动作3：收银员进行交易流水查看\n"
				+ "1，收银员登陆成功后，进入流水明细模块中选择所要查看的交易流水\n"
				+ "2，核实订单号和交易创建时间，点击交易流水，查看交易流水详情。";
		tv.setMovementMethod(ScrollingMovementMethod.getInstance()); 
		tv.setText(intro);
	}
}
