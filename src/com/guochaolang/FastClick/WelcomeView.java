package com.guochaolang.FastClick;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

public class WelcomeView extends View{

	WMainActivity wma;
	Bitmap bmpWelcome;
	Resources r;
	public WelcomeView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		this.wma=(WMainActivity)context;
		r=getResources();
		bmpWelcome=(Bitmap)BitmapFactory.decodeResource(r, R.drawable.welcome);
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		wma.myHandler.sendEmptyMessage(1);
		return super.onTouchEvent(event);
	}
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		canvas.drawBitmap(bmpWelcome, 0,0,null);
	}

}
