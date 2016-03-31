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

//��Ϸ����
public class PFastClickView extends View{

	PMainActivity pma;		
	List <Block> blocks;	//�����һ������
	Paint paints[];			//���ʵ�����
	Bitmap bmpbg;
	Resources r;
	int nblock,maxblock;	//��ǰ������������󷽿���
	int dn;					//����ͳ�Ƶ�����ķ�����
	int text;				//�洢����ȥ�Ŀ���
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
		//�������ѷ���
		new Thread(){
			public void run() {
				while(true){
					pma.myHandler.sendEmptyMessage(9);
					try {
						Thread.sleep(3*1000);		//ÿ3�븴��һ�����顣
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			};
		}.start();
		//����߳�������ʱ����������30����˳�����
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
					//pma.myHandler.sendEmptyMessage(0);	//�˳�
				}
			};
		}.start();
	}
	//��ʼ������
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
	//��ʼ����ɫ������
	void initPaints(){
		paints=new Paint[nblock];
		for(int i=0;i<nblock;i++){
			paints[i]=new Paint();
			switch(i){
			case 0:	//��ɫ
				paints[i].setColor(Color.BLACK);
				break;
			case 1:	//��ɫ
				paints[i].setColor(Color.BLUE);
				break;
			case 2:	//��ɫ
				paints[i].setColor(Color.RED);
				break;
			case 3:	//��ɫ
				paints[i].setColor(Color.WHITE);
				break;
			case 4:	//��ɫ
				paints[i].setColor(Color.GREEN);
				break;
			case 5:	//��ɫ
				paints[i].setColor(Color.YELLOW);
				break;
			}
		}
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		//�����Ļ����¼�
		// TODO Auto-generated method stub��
		//��������
		Block b;
		int n;
		for(int i=0;i<blocks.size();i++){
			b=blocks.get(i);
			if(event.getX()<b.x+80 && event.getX()>=b.x &&
				event.getY()>=b.y && event.getY()<b.y+120){
				b.ef=false;		//������
				n=b.pn;
				//pma.gdian+=1;
				switch(n){
				case 0:	//��ɫ
					System.out.println("���к�ɫ");
					break;
				case 1:	//��ɫ
					System.out.println("������ɫ");
					break;
				case 2:	//��ɫ
					System.out.println("���к�ɫ");
					break;
				case 3:	//��ɫ
					System.out.println("���а�ɫ");
					break;
				case 4:	//��ɫ
					System.out.println("������ɫ");
					break;
				case 5:	//��ɫ
					System.out.println("���л�ɫ");
					break;
				}
				b.pn=(int)(Math.random()*6);
				b.y=0;			//����֮��������濪ʼ
				pma.myHandler.sendEmptyMessage(2);	//�����˷�һ����Ϣ��Activity
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
		//����
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		String s1,s2;
				s1=pma.fspname;
				s2=pma.fsppwd;
		canvas.drawBitmap(bmpbg, 0,0,null);
		canvas.drawText(s1+"�ĵ÷��ǣ�"+dn, 0, 475, paints[0]);
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
