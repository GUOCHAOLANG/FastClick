package com.guochaolang.FastClick;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/*��Ϸ����
 * ������ڼ�����ɫ�ı仯�У��еĵ��мӷ֣��еĵ��м���
 * */

//������
public class Block {

	PFastClickView mv;
	PMainActivity pma;
	int x,y,v;	//��������Ͻǵ����������µ��ٶ�
	int dx,dy;	//����ĳ���
	Paint paints[];
	int nblock;
	Paint paint;
	int pn;			//�ı仭������λ��
	boolean ef;		//�ж�������־
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
					y+=v;		//�ı�λ�ã����˶�
					if(y>480){		
						y=0;
						//�����ٶ�Ϊ50��100    ���ٶȵ�����ı�
						v=(int)(Math.random()*50)+50;
						//�ı���ɫ   ����ɫ������ı�
						pn=(int)(Math.random()*6);
						paint=paints[pn];
						//Paint tp=new Paint();
						//tp.setColor((Color.rgb((int)(Math.random()*250),(int)(Math.random()*250),(int)(Math.random()*250))));
						//paint=tp;
						//paint=paints[pn];
						//paint=mv.paints[pn];
						//pma.gdiao+=1;
						//pma.myHandler.sendEmptyMessage(3);	//����һ�飬������Ϣ
						bdiao+=1;
						//System.out.println("hello guochaolang");
					}
					try {
						Thread.sleep(100);	//ÿ0.1��ı�һ�Σ�������˯100����
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
	void BDraw(Canvas canvas){
		if(ef==true){	//�����ǰ��������Ϊ�����Ʒ���
			canvas.drawRect(x, y, x+dx, y+dy, paint);
		}
	}
}
