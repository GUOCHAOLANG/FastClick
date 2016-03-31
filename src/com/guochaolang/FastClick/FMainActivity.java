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
//��¼Activity
public class FMainActivity extends Activity{
	FMainActivity fma;
	SQLiteDatabase db;
	Button b1 = null;		//ע�ᰴť
	Button b2 = null;		//��¼��ť
	String pname,ppwd;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fmain);
		//�������ݿ�
		db = SQLiteDatabase.openOrCreateDatabase(
				this.getFilesDir().toString()+"/my.db3", null);
		//ע�ᰴť������
		b1 = (Button) findViewById(R.id.ok);
		b1.setOnClickListener(new OnClickListener() {
			//ע�ᰴť�����¼����������ݴ��ھͲ��룬��ע�᣻���û�д����ݿ⣬�ʹ���һ��
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//Toast.makeText(getApplicationContext(), "hello", Toast.LENGTH_SHORT).show();
				// ��ȡ�û�����
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
				catch (SQLiteException se)	//�˴�Ϊ���ݱ����ڣ�������д�����Ȼ���ٲ��롣
				{		
					// ִ��DDL�������ݱ�
					db.execSQL("create table tb_user(_id integer"
						+ " primary key autoincrement,"
						+ " name varchar(50),"
						+ " pwd varchar(25))");
					// ִ��insert����������
					insertData(db, gname, gpwd);
					// ִ�в�ѯ
					Cursor cursor = db.rawQuery("select * from tb_user"
						, null);
					inflateList(cursor);
				}
				Toast.makeText(getApplicationContext(), "ע��ɹ�", Toast.LENGTH_SHORT).show();
				
				//Toast.makeText(getApplicationContext(), "hello", Toast.LENGTH_SHORT).show();
			}
		});
		//��¼��ť����
		b2 = (Button) findViewById(R.id.land);
		b2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// ��ȡ�û�����
				String fname = ((EditText) findViewById(
					R.id.name)).getText().toString();
				String fpwd = ((EditText) findViewById(R.id.pwd))
					.getText().toString();
				try
				{
					Cursor cursor2=db.rawQuery("select * from tb_user where name=? and pwd=?"
							, new String[]{fname,fpwd});
					int ci=0;
					//����һ�¼�¼�����������ѯ�ļ�¼������
					while(cursor2.moveToNext()){
						ci++;
					}
					if(ci>=1){		//��������һ�����û�����
						Toast.makeText(getApplicationContext(), "�ҵ��û�,��¼�ɹ�", Toast.LENGTH_SHORT).show();
						//fma.myHandler.sendEmptyMessage(1);
						Person p=new Person(fname,fpwd);
						Bundle data =new Bundle();
						data.putSerializable("person", p);
						//��¼�ɹ���Ȼ����תActivityҳ��
						Intent intent = new Intent(FMainActivity.this, 
								SMainActivity.class);
						intent.putExtras(data);
						startActivity(intent);
					}else{
						Toast.makeText(getApplicationContext(), "���û������ڣ���¼ʧ��", Toast.LENGTH_SHORT).show();
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
	//�������ݺ���
	private void insertData(SQLiteDatabase db,
			String name,String pwd){
		// ִ�в������
		db.execSQL("insert into tb_user values(null , ? , ?)"
			, new String[] {name, pwd });
	}
	private void inflateList(Cursor cursor)
	{
		// ���SimpleCursorAdapter
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(
			FMainActivity.this,
			R.layout.line, cursor,
			new String[] { "name", "pwd" }
			, new int[] {R.id.my_name, R.id.my_pwd },
			CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER); //��
		// ��ʾ����
		//listView.setAdapter(adapter);
	}

	//�ر����ݿ�����
	@Override
	public void onDestroy()
	{
		super.onDestroy();
		// �˳�����ʱ�ر�SQLiteDatabase
		if (db != null && db.isOpen())
		{
			db.close();
		}
	}

}
