/*
 * Created by Lê Tấn Lộc on 13:05 01/03/2019
 * Copyright (c) 2019 . All rights reserved.
 * Last modified 13:05 01/03/2019
 */

package app.b1605339.letanloc.eulerian_cycle.Graphic;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class Dinh extends View {
    private Paint paint = new Paint();

    private float x;
    private float y;

    ArrayList<Float> listX = new ArrayList<>();
    ArrayList<Float> listY = new ArrayList<>();

    ArrayList<Float> listStartX = new ArrayList<>();
    ArrayList<Float> listStartY = new ArrayList<>();
    ArrayList<Float> listStopX = new ArrayList<>();
    ArrayList<Float> listStopY = new ArrayList<>();

    //0: Không làm gì
    //1: Vẽ đỉnh khi click
    //2: Di chuyển đỉnh
    //4:

    private static int actionTouch = 0;
    private static int timeTouch = 0;


    public Dinh(Context context) {
        super(context);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int widthCanvas = canvas.getWidth();
        int heightCanvas = canvas.getHeight();

        /*paint.setColor(Color.YELLOW);
        canvas.drawCircle(100, 100, 10, paint);

        paint.setColor(Color.BLUE);
        canvas.drawLine(200, 200, 400, 400, paint);*/

        paint.setColor(Color.WHITE);
        canvas.drawPaint(paint);


        paint = new Paint();
        paint.setAntiAlias(true);
        int touchVertex = -1;
        if (actionTouch == 1) {
            for (int i = 0; i < listX.size(); i++) {
                if (Math.sqrt(Math.pow((listX.get(i) - x), 2) + Math.pow((listY.get(i) - y), 2)) <= 20) {
                    paint.setColor(Color.BLUE);
                    canvas.drawText(i + "", x + 100, y, paint);
                    touchVertex = i;
                    break;
                }
            }
        }

        //Khi người dùng click
        if (actionTouch == 1) {
            //Vẽ lại đồ thị
            for (int i = 0; i < listX.size(); i++) {
                paint = new Paint();
                paint.setAntiAlias(true);
                paint.setColor(Color.BLUE);
                canvas.drawCircle(listX.get(i), listY.get(i), 20, paint);
                paint.setColor(Color.WHITE);
                paint.setTextAlign(Paint.Align.CENTER);
                paint.setTextSize(20f);
                canvas.drawText(i + "", listX.get(i), listY.get(i) + 10, paint);
            }

            if (touchVertex != -1) {
                //Nếu đã có đỉnh => đổi màu
                for (int i = 0; i < listX.size(); i++) {
                    paint = new Paint();
                    paint.setAntiAlias(true);
                    paint.setColor(Color.BLUE);

                    //Đổi màu tại đỉnh click vào
                    if (touchVertex == i) {
                        paint.setColor(Color.RED);
                    }
                    canvas.drawCircle(listX.get(i), listY.get(i), 20, paint);
                    paint.setColor(Color.WHITE);
                    paint.setTextAlign(Paint.Align.CENTER);
                    paint.setTextSize(20f);
                    canvas.drawText(i + "", listX.get(i), listY.get(i) + 10, paint);
                }
            } else {
                //Nếu chưa có đỉnh => add vào

                //Nếu quá gần thì không add
                boolean touchArea = false;
                for (int i = 0; i < listX.size(); i++) {
                    if (Math.sqrt(Math.pow((listX.get(i) - x), 2) + Math.pow((listY.get(i) - y), 2)) <= 100) {
                        touchArea = true;
                        break;
                    }
                }

                if (!touchArea) {
                    listX.add(x);
                    listY.add(y);
                    //Vẽ lại đồ thị
                    for (int i = 0; i < listX.size(); i++) {
                        paint = new Paint();
                        paint.setAntiAlias(true);
                        paint.setColor(Color.BLUE);
                        canvas.drawCircle(listX.get(i), listY.get(i), 20, paint);
                        paint.setColor(Color.WHITE);
                        paint.setTextAlign(Paint.Align.CENTER);
                        paint.setTextSize(20f);
                        canvas.drawText(i + "", listX.get(i), listY.get(i) + 10, paint);
                    }
                }
            }
            actionTouch = 0;
        }

        if (actionTouch == 2) {
            //Vẽ lại đồ thị
            for (int i = 0; i < listX.size(); i++) {
                paint = new Paint();
                paint.setAntiAlias(true);
                paint.setColor(Color.BLUE);
                canvas.drawCircle(listX.get(i), listY.get(i), 20, paint);
                paint.setColor(Color.WHITE);
                paint.setTextAlign(Paint.Align.CENTER);
                paint.setTextSize(20f);
                canvas.drawText(i + "", listX.get(i), listY.get(i) + 10, paint);
            }

            boolean touchArea = false;
            for (int i = 0; i < listX.size(); i++) {
                if (Math.sqrt(Math.pow((listX.get(i) - x), 2) + Math.pow((listY.get(i) - y), 2)) <= 100) {
                    touchArea = true;
                    break;
                }
            }

            //Di chuyển
            if (!touchArea) {
                paint = new Paint();
                paint.setAntiAlias(true);
                paint.setColor(Color.RED);
                canvas.drawCircle(x, y, 20, paint);
                paint.setColor(Color.WHITE);
                paint.setTextAlign(Paint.Align.CENTER);
                paint.setTextSize(20f);
                canvas.drawText("", x, y + 10, paint);
                actionTouch = 0;
            }
        }

        if (actionTouch == 3) {
            //Vẽ lại đồ thị
            for (int i = 0; i < listX.size(); i++) {
                paint = new Paint();
                paint.setAntiAlias(true);
                paint.setColor(Color.BLUE);
                canvas.drawCircle(listX.get(i), listY.get(i), 20, paint);
                paint.setColor(Color.WHITE);
                paint.setTextAlign(Paint.Align.CENTER);
                paint.setTextSize(20f);
                canvas.drawText(i + "", listX.get(i), listY.get(i) + 10, paint);
            }

            //Nếu quá gần thì không add
            boolean touchArea = false;
            for (int i = 0; i < listX.size(); i++) {
                if (Math.sqrt(Math.pow((listX.get(i) - x), 2) + Math.pow((listY.get(i) - y), 2)) <= 100) {
                    touchArea = true;
                    break;
                }
            }
            if(!touchArea){
                listX.add(x);
                listY.add(y);
                for (int i = 0; i < listX.size(); i++) {
                    paint = new Paint();
                    paint.setAntiAlias(true);
                    paint.setColor(Color.BLUE);
                    canvas.drawCircle(listX.get(i), listY.get(i), 20, paint);
                    paint.setColor(Color.WHITE);
                    paint.setTextAlign(Paint.Align.CENTER);
                    paint.setTextSize(20f);
                    canvas.drawText(i + "", listX.get(i), listY.get(i) + 10, paint);
                }
            }
        }

        /*for (int i = 0; i < listX.size(); i++) {
            paint = new Paint();
            paint.setColor(Color.BLUE);
            canvas.drawCircle(listX.get(i), listY.get(i), 20, paint);
            paint.setColor(Color.WHITE);
            paint.setTextAlign(Paint.Align.CENTER);
            paint.setTextSize(20f);
            canvas.drawText(i + "", listX.get(i), listY.get(i) + 10, paint);
        }

        for (int i = 0; i < listStartX.size(); i++) {
            paint.setColor(Color.BLUE);
            canvas.drawLine(listStartX.get(i), listStartY.get(i), listStopX.get(i), listStopY.get(i), paint);
        }*/
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //x = event.getX();
                //y = event.getY();
                //actionTouch = 1;
                break;
            case MotionEvent.ACTION_MOVE:
                timeTouch++;
                if (timeTouch > 5) {
                    x = event.getX();
                    y = event.getY();
                    actionTouch = 2;
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
                if (timeTouch < 5) {
                    //Click
                    x = event.getX();
                    y = event.getY();
                    actionTouch = 1;
                    invalidate();
                } else {
                    //Move
                    x = event.getX();
                    y = event.getY();
                    actionTouch = 3;
                    invalidate();
                }
                timeTouch = 0;
                break;
        }
        return true;
    }
}
