package com.guochaolang.FastClick;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/*游戏规则
 * 方块会在几种颜色的变化中，有的点中加分，有的点中减分
 * */

//方块类
public class Block {

	PFastClickView mv;
	PMainActivity pma;
	int x,y,v;	//方块的左上角点的坐标和向下的速度
	int dx,dy;	//方块的长宽
	Paint paints[];
	int nblock;
	Paint paint;
	int pn;			//改变画笔数组位置
	boolean ef;		//判断生死标志
	int bdiao;
	Block(PFastClickView mv,int x1,int y1,int v1,Paint pp,int n){
		this.mv=mv;
		x=x1;
		y=y1;
		v=v1;
		paint=pp;
		pn=n;
		nblock=6;
		ef=true;
		dx=80;		
		dy=120;
		bdiao=0;
		initPaints();
		new Thread(){
			public void run(){
				while(true){
					y+=v;		//改变位置，即运动
					if(y>480){		
						y=0;
						//更改速度为50到100    对速度的随机改变
						v=(int)(Math.random()*50)+50;
						//改变颜色   对颜色的随机改变
						pn=(int)(Math.random()*6);
						paint=paints[pn];
						//Paint tp=new Paint();
						//tp.setColor((Color.rgb((int)(Math.random()*250),(int)(Math.random()*250),(int)(Math.random()*250))));
						//paint=tp;
						//paint=paints[pn];
						//paint=mv.paints[pn];
						//pma.gdiao+=1;
						//pma.myHandler.sendEmptyMessage(3);	//掉了一块，发个消息
						bdiao+=1;
						//System.out.println("hello guochaolang");
					}
					try {
						Thread.sleep(100);	//每0.1秒改变一次，这里是睡100毫秒
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			};
		}.start();
	}
	int backBdiao(){
		
		return bdiao;
	}
	void setBdiao(int a){
		bdiao=a;
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
	void BDraw(Canvas canvas){
		if(ef==true){	//如果当前方块生，为它绘制方块
			canvas.drawRect(x, y, x+dx, y+dy, paint);
		}
	}
}
