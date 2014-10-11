package com.example.smartcommunity;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.smartcommunity.ui.DragGridAdapter;
import com.example.smartcommunity.ui.DragGridView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

/**
 * @blog http://blog.csdn.net/xiaanming 
 * @author xiaanming
 */
public class MainActivity extends Activity {
	
	private List<HashMap<String, Object>> dataSourceList = new ArrayList<HashMap<String, Object>>();
	private static String[] labels = {"邻里中心", "智能家居", "社区送餐", "便民缴费", 
										"邻里团购", "邻里活动", "快递信息", "费用充值", 
										"彩票资讯", "紧急呼救", "一键家政", "老年服务",
										"医院服务", "干洗服务", "一键打车", "格瓦拉",
										"自助银行", "家庭理财", "携程票务", "费用充值",
										"网上购物", "人寿保险", "亲子资讯", "家装服务"
										};
	private static String[] images = {"neighbourhood", "pic", "eleme", "pic", 
										"meituan", "tongquwang", "kuaidi100", "pic", 
										"pic", "pic", "pic", "pic",
										"pic", "pic", "dididache", "damai",
										"pic", "pic", "pic", "pic",
										"pic", "pic", "pic", "pic"
										};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		DragGridView mDragGridView = (DragGridView) findViewById(R.id.dragGridView);
		for (int i = 0; i < labels.length; i++) {
			HashMap<String, Object> itemHashMap = new HashMap<String, Object>();
			itemHashMap.put("item_image", getDrawableRes(images[i]));
			itemHashMap.put("item_text", labels[i]);
			dataSourceList.add(itemHashMap);
		}
		
		final DragGridAdapter mDragAdapter = new DragGridAdapter(this, dataSourceList);
		
		mDragGridView.setAdapter(mDragAdapter);
		mDragGridView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long rowid) {
				HashMap<String, Object> item = (HashMap<String, Object>)parent.getItemAtPosition(position);
				//String text = (String)item.get("item_text");
				int image = (Integer)item.get("item_image");
				//Toast.makeText(MainActivity.this, "未安装此程序", Toast.LENGTH_LONG).show();
				switch (image) {
				case R.drawable.eleme:
					Intent eleme=new Intent();    
					eleme.setClassName("me.ele", "me.ele.activity.SplashActivity");  
					startActivity(eleme);
					break;
				case R.drawable.meituan:
					Intent meituan=new Intent();    
					meituan.setClassName("com.sankuai.meituan", "com.sankuai.meituan.activity.Welcome");  
					startActivity(meituan);
					break;
				case R.drawable.tongquwang:
					Intent tongquwang=new Intent();    
					tongquwang.setClassName("com.tongqu", "com.tongqu.welcome.WelcomeActivity");  
					startActivity(tongquwang);
					break;
				case R.drawable.kuaidi100:
					Intent intent=new Intent();    
					intent.setClassName("com.Kingdee.Express", "com.Express.Activity.Splash");  
					startActivity(intent);
					break;
				case R.drawable.dididache:
					Intent dididache=new Intent();    
					dididache.setClassName("com.sdu.didi.psnger", "com.didi.frame.MainActivity");  
					startActivity(dididache);
					break;
				case R.drawable.damai:
					Intent damai=new Intent();    
					damai.setClassName("cn.damai", "cn.damai.activity.MainSplashActivity");  
					startActivity(damai);
					break;
					
				case R.drawable.neighbourhood:
					Intent neighCenterIntent = new Intent(MainActivity.this, NeighCenter.class);
					startActivity(neighCenterIntent);
				default:
					break;
				}
			}
		});
	}
	
	private int getDrawableRes(String s){
		try {
			Field field = R.drawable.class.getField(s);
			int i = field.getInt(new R.drawable());
			return i;
		} catch (Exception e) {
			return -1;
		}
	}

}

