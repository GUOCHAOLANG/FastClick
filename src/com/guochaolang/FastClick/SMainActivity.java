package com.guochaolang.FastClick;

import com.guochaolang.model.Person;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

//关卡选择Activity
public class SMainActivity extends Activity{
	Button bg1=null;		//第一关按钮
	Button bg2=null;		//第二关按钮
	Button bg3=null;		//第三关按钮
	String fsname,fspwd;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_smain);
		Intent intent0=getIntent();
		Person p=(Person) intent0.getSerializableExtra("person");
		fsname=p.getName();
		fspwd=p.getPass();
		bg1=(Button)findViewById(R.id.g1);
		bg1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Person s=new Person(fsname,fspwd);
				Bundle data=new Bundle();
				data.putSerializable("person", s);
				Intent intent = new Intent(SMainActivity.this, 
						PMainActivity.class);
				intent.putExtras(data);
				startActivity(intent);
			}
		});
		bg2=(Button)findViewById(R.id.g2);
		bg2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "此关还未编写，等待中。。",Toast.LENGTH_SHORT).show();
			}
		});
		bg3=(Button)findViewById(R.id.g3);
		bg3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "此关还未编写，等待中。。",Toast.LENGTH_SHORT).show();
			}
		});
	}
}
