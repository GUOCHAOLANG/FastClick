package com.guochaolang.FastClick;

import java.util.LinkedList;
import java.util.List;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

//游戏界面
public class PFastClickView extends View{

	PMainActivity pma;		
	List <Block> blocks;	//方块的一个链表
	Paint paints[];			//画笔的数组
	Bitmap bmpbg;
	Resources r;
	int nblock,maxblock;	//当前方块数，和最大方块数
	int dn;					//用于统计点击掉的方块数
	int text;				//存储掉下去的块数
	public PFastClickView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		pma=(PMainActivity)context;
		r=getResources();
		bmpbg=(Bitmap)BitmapFactory.decodeResource(r, R.drawable.bg);
	
		dn=0;
		text=0;
		initBlocks();
		
		new Thread(){
			public void run(){
				while(true){
					pma.myHandler.sendEmptyMessage(1);
				}
			};
		}.start();
		//用来唤醒方块
		new Thread(){
			public void run() {
				while(true){
					pma.myHandler.sendEmptyMessage(9);
					try {
						Thread.sleep(3*1000);		//每3秒复活一个方块。
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			};
		}.start();
		//这个线程用来做时间限制器，30秒后退出程序
		new Thread(){
			public void run() {
				while(true){
					try {
						Thread.sleep(30*1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					pma.myHandler.sendEmptyMessage(10);
					for(int i=0;i<blocks.size();i++){
						text+=blocks.get(i).backBdiao();
						blocks.get(i).setBdiao(0);
					}
					//System.out.println("aaaaaa"+text);
					//pma.myHandler.sendEmptyMessage(0);	//退出
				}
			};
		}.start();
	}
	//初始化方块
	void initBlocks(){
		nblock=6;
		maxblock=12;
		paints=new Paint[nblock];
		initPaints();
		blocks=new LinkedList<Block>();
		for(int i=0;i<nblock;i++){
			//paints[i]=new Paint();
			//paints[i].setColor((Color.rgb((int)(Math.random()*250),(int)(Math.random()*250),(int)(Math.random()*250))));
			Block tb=new Block(this,i*80,0,(int)(Math.random()*100),paints[i],i);
			blocks.add(tb);
		}
	}
	//初始化颜色画笔组
	void initPaints(){
		paints=new Paint[nblock];
		for(int i=0;i<nblock;i++){
			paints[i]=new Paint();
			switch(i){
			case 0:	//黑色
				paints[i].setColor(Color.BLACK);
				break;
			case 1:	//蓝色
				paints[i].setColor(Color.BLUE);
				break;
			case 2:	//红色
				paints[i].setColor(Color.RED);
				break;
			case 3:	//白色
				paints[i].setColor(Color.WHITE);
				break;
			case 4:	//绿色
				paints[i].setColor(Color.GREEN);
				break;
			case 5:	//黄色
				paints[i].setColor(Color.YELLOW);
				break;
			}
		}
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		//监控屏幕点击事件
		// TODO Auto-generated method stub、
		//消除方块
		Block b;
		int n;
		for(int i=0;i<blocks.size();i++){
			b=blocks.get(i);
			if(event.getX()<b.x+80 && event.getX()>=b.x &&
				event.getY()>=b.y && event.getY()<b.y+120){
				b.ef=false;		//已死了
				n=b.pn;
				//pma.gdian+=1;
				switch(n){
				case 0:	//黑色
					System.out.println("点中黑色");
					break;
				case 1:	//蓝色
					System.out.println("点中蓝色");
					break;
				case 2:	//红色
					System.out.println("点中红色");
					break;
				case 3:	//白色
					System.out.println("点中白色");
					break;
				case 4:	//绿色
					System.out.println("点中绿色");
					break;
				case 5:	//黄色
					System.out.println("点中黄色");
					break;
				}
				b.pn=(int)(Math.random()*6);
				b.y=0;			//死了之后从最上面开始
				pma.myHandler.sendEmptyMessage(2);	//点中了发一个消息到Activity
				dn++;
				//System.out.println(pma.gdian);
			}
		}
		if(dn>=10){
			dn=0;
			pma.myHandler.sendEmptyMessage(11);
		}
		return super.onTouchEvent(event);
	}
	@Override
	protected void onDraw(Canvas canvas) {
		//绘制
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		String s1,s2;
				s1=pma.fspname;
				s2=pma.fsppwd;
		canvas.drawBitmap(bmpbg, 0,0,null);
		canvas.drawText(s1+"的得分是："+dn, 0, 475, paints[0]);
		for(int i1=0;i1<blocks.size();i1++){
			if(blocks.get(i1).ef==true){
				blocks.get(i1).BDraw(canvas);
			}
		}
	}
	int backText(){
		return text;
	}
}
