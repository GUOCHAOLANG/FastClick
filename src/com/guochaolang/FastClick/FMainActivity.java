package com.guochaolang.FastClick;


import com.guochaolang.model.Person;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.Toast;
//登录Activity
public class FMainActivity extends Activity{
	FMainActivity fma;
	SQLiteDatabase db;
	Button b1 = null;		//注册按钮
	Button b2 = null;		//登录按钮
	String pname,ppwd;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fmain);
		//连接数据库
		db = SQLiteDatabase.openOrCreateDatabase(
				this.getFilesDir().toString()+"/my.db3", null);
		//注册按钮的设置
		b1 = (Button) findViewById(R.id.ok);
		b1.setOnClickListener(new OnClickListener() {
			//注册按钮触发事件，若果数据存在就插入，即注册；如果没有此数据库，就创建一个
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//Toast.makeText(getApplicationContext(), "hello", Toast.LENGTH_SHORT).show();
				// 获取用户输入
				String gname = ((EditText) findViewById(
					R.id.name)).getText().toString();
				String gpwd = ((EditText) findViewById(R.id.pwd))
					.getText().toString();
				//pname=gname;
				//ppwd=gpwd;
				try
				{
					insertData(db, gname, gpwd);
					Cursor cursor = db.rawQuery("select * from tb_user"
						, null);
					inflateList(cursor);
				}
				catch (SQLiteException se)	//此处为数据表不存在，这里进行创建，然后再插入。
				{		
					// 执行DDL创建数据表
					db.execSQL("create table tb_user(_id integer"
						+ " primary key autoincrement,"
						+ " name varchar(50),"
						+ " pwd varchar(25))");
					// 执行insert语句插入数据
					insertData(db, gname, gpwd);
					// 执行查询
					Cursor cursor = db.rawQuery("select * from tb_user"
						, null);
					inflateList(cursor);
				}
				Toast.makeText(getApplicationContext(), "注册成功", Toast.LENGTH_SHORT).show();
				
				//Toast.makeText(getApplicationContext(), "hello", Toast.LENGTH_SHORT).show();
			}
		});
		//登录按钮设置
		b2 = (Button) findViewById(R.id.land);
		b2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// 获取用户输入
				String fname = ((EditText) findViewById(
					R.id.name)).getText().toString();
				String fpwd = ((EditText) findViewById(R.id.pwd))
					.getText().toString();
				try
				{
					Cursor cursor2=db.rawQuery("select * from tb_user where name=? and pwd=?"
							, new String[]{fname,fpwd});
					int ci=0;
					//遍历一下记录集，来计算查询的记录的条数
					while(cursor2.moveToNext()){
						ci++;
					}
					if(ci>=1){		//条数大于一就是用户存在
						Toast.makeText(getApplicationContext(), "找到用户,登录成功", Toast.LENGTH_SHORT).show();
						//fma.myHandler.sendEmptyMessage(1);
						Person p=new Person(fname,fpwd);
						Bundle data =new Bundle();
						data.putSerializable("person", p);
						//登录成功，然后跳转Activity页面
						Intent intent = new Intent(FMainActivity.this, 
								SMainActivity.class);
						intent.putExtras(data);
						startActivity(intent);
					}else{
						Toast.makeText(getApplicationContext(), "此用户不存在，登录失败", Toast.LENGTH_SHORT).show();
					}
					//inflateList(cursor);
				}
				catch (SQLiteException se)
				{
					se.getMessage();
				}
			}
		});
	}
	//插入数据函数
	private void insertData(SQLiteDatabase db,
			String name,String pwd){
		// 执行插入语句
		db.execSQL("insert into tb_user values(null , ? , ?)"
			, new String[] {name, pwd });
	}
	private void inflateList(Cursor cursor)
	{
		// 填充SimpleCursorAdapter
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(
			FMainActivity.this,
			R.layout.line, cursor,
			new String[] { "name", "pwd" }
			, new int[] {R.id.my_name, R.id.my_pwd },
			CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER); //③
		// 显示数据
		//listView.setAdapter(adapter);
	}

	//关闭数据库连接
	@Override
	public void onDestroy()
	{
		super.onDestroy();
		// 退出程序时关闭SQLiteDatabase
		if (db != null && db.isOpen())
		{
			db.close();
		}
	}

}
