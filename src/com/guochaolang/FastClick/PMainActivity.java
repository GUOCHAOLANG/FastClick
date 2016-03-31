package com.guochaolang.FastClick;

import com.guochaolang.model.Person;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
//��һ����ϷActivity
public class PMainActivity extends Activity{
	
	PMainActivity pma;
	PFastClickView pfv;
	//WelcomeView wv;
	View currentView;
	private static final int ITEM_RESTART=Menu.FIRST;	//���¿�ʼ
	private static final int ITEM_EXIT=Menu.FIRST+1;	//�˳���Ϸ
	private static final int ITEM_PAUSE=Menu.FIRST+2;	//��ͣ��Ϸ
	String fspname,fsppwd;
	int gdian;	//��¼���еĿ���
	int gdiao;	//��¼����ȥ�Ŀ���
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//���ò���ʾ����
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//����ȫ����ʾ
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		pma=this;
		gdian=0;
		gdiao=0;
		Intent intent1=getIntent();
		Person p=(Person) intent1.getSerializableExtra("person");
		fspname=p.getName();
		fsppwd=p.getPass();
		pfv=new PFastClickView(this);
		setContentView(pfv);
		//currentView=pfv;
		System.out.println("�û���������"+fspname+fsppwd);
		
	}
	//��Ϣ����
	Handler myHandler=new Handler(){
		public void handleMessage(Message msg){
			switch(msg.what){
			case 0:		//�˳�����
				System.exit(1);
				break;
			case 1:		//ˢ��
				pfv.invalidate();
				break;
			case 2:		//��������Ϣ
				gdian+=1;
				System.out.println("�����˵�"+gdian);
				//for(int i=0;i<pv.blocks.size();i++){
				//	Block bb=pv.blocks.get(i);
				//	bb.v=(int)(Math.random()*100);
				//}
				break;
			case 3:		//����ȥһ��	
				//gdiao+=1;
				gdiao=pfv.backText();
				System.out.println("����ȥ�ķ�����"+gdiao);
				break;
			case 9:	//����һ������
				for(int i=0;i<pfv.blocks.size();i++){
					Block bb=pfv.blocks.get(i);
					if(bb.ef==false){
						bb.ef=true;
						break;
					}
				}
				break;
			case 10:
				Toast.makeText(getApplicationContext(), "ʱ�䵽��!����Ϸ����",Toast.LENGTH_SHORT).show();
				myHandler.sendEmptyMessage(3);
				break;
			case 11:
				Toast.makeText(getApplicationContext(), "�������10���ˣ�over!",Toast.LENGTH_SHORT).show();
				break;
			}
		}
	};
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		menu.add(0,ITEM_RESTART,0,"���¿�ʼ");
		menu.add(0,ITEM_PAUSE,0,"��ͣ��Ϸ");
		menu.add(0,ITEM_EXIT,0,"�˳���Ϸ");

		return true;
	};
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()){
		case ITEM_RESTART:
			//myHandler.sendEmptyMessage(31);
			break;
		case ITEM_PAUSE:
			break;
		case ITEM_EXIT:
			myHandler.sendEmptyMessage(0);//������Ϸ
			//wma.myHandler.sendEmptyMessage(0);	//������������
			break;
		}

		return super.onOptionsItemSelected(item);
	}
}
