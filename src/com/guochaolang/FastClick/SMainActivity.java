package com.guochaolang.FastClick;

import com.guochaolang.model.Person;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

//�ؿ�ѡ��Activity
public class SMainActivity extends Activity{
	Button bg1=null;		//��һ�ذ�ť
	Button bg2=null;		//�ڶ��ذ�ť
	Button bg3=null;		//�����ذ�ť
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
				Toast.makeText(getApplicationContext(), "�˹ػ�δ��д���ȴ��С���",Toast.LENGTH_SHORT).show();
			}
		});
		bg3=(Button)findViewById(R.id.g3);
		bg3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "�˹ػ�δ��д���ȴ��С���",Toast.LENGTH_SHORT).show();
			}
		});
	}
}
