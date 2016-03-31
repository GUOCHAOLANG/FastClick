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
//第一关游戏Activity
public class PMainActivity extends Activity{
	
	PMainActivity pma;
	PFastClickView pfv;
	//WelcomeView wv;
	View currentView;
	private static final int ITEM_RESTART=Menu.FIRST;	//重新开始
	private static final int ITEM_EXIT=Menu.FIRST+1;	//退出游戏
	private static final int ITEM_PAUSE=Menu.FIRST+2;	//暂停游戏
	String fspname,fsppwd;
	int gdian;	//记录点中的块数
	int gdiao;	//记录掉下去的块数
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//设置不显示标题
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//设置全屏显示
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
		System.out.println("用户名和密码"+fspname+fsppwd);
		
	}
	//消息处理
	Handler myHandler=new Handler(){
		public void handleMessage(Message msg){
			switch(msg.what){
			case 0:		//退出程序
				System.exit(1);
				break;
			case 1:		//刷新
				pfv.invalidate();
				break;
			case 2:		//点中了消息
				gdian+=1;
				System.out.println("点中了第"+gdian);
				//for(int i=0;i<pv.blocks.size();i++){
				//	Block bb=pv.blocks.get(i);
				//	bb.v=(int)(Math.random()*100);
				//}
				break;
			case 3:		//掉下去一块	
				//gdiao+=1;
				gdiao=pfv.backText();
				System.out.println("掉下去的方块数"+gdiao);
				break;
			case 9:	//复活一个方块
				for(int i=0;i<pfv.blocks.size();i++){
					Block bb=pfv.blocks.get(i);
					if(bb.ef==false){
						bb.ef=true;
						break;
					}
				}
				break;
			case 10:
				Toast.makeText(getApplicationContext(), "时间到了!，游戏结束",Toast.LENGTH_SHORT).show();
				myHandler.sendEmptyMessage(3);
				break;
			case 11:
				Toast.makeText(getApplicationContext(), "点击超过10个了，over!",Toast.LENGTH_SHORT).show();
				break;
			}
		}
	};
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		menu.add(0,ITEM_RESTART,0,"重新开始");
		menu.add(0,ITEM_PAUSE,0,"暂停游戏");
		menu.add(0,ITEM_EXIT,0,"退出游戏");

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
			myHandler.sendEmptyMessage(0);//结束游戏
			//wma.myHandler.sendEmptyMessage(0);	//结束整个程序
			break;
		}

		return super.onOptionsItemSelected(item);
	}
}
