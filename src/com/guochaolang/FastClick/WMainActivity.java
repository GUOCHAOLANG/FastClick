package com.guochaolang.FastClick;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MotionEvent;
//»¶Ó­Ò³Activity
public class WMainActivity extends Activity {

	WelcomeView we;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		we=new WelcomeView(this);
		setContentView(we);
	}
	Handler myHandler=new Handler(){
		public void handleMessage(Message msg){
			switch(msg.what){
			case 0:
				System.exit(1);
				break;
			case 1:
				Intent intent = new Intent(WMainActivity.this, 
						FMainActivity.class);
				startActivity(intent);
				break;
			case 2:
				break;
			}
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.wmain, menu);
		return true;
	}
	
}
