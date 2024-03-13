package org.myoralvillage.cashcalculator.tutorials;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import org.myoralvillage.cashcalculator.R;
import org.myoralvillage.cashcalculatormodule.fragments.CashCalculatorFragment;

import android.annotation.SuppressLint;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class IntroActivity extends AppCompatActivity {

    private ViewGroup mainLayout;
    private ImageView image, currency10000, currency5000, currency1000, currency500, currency100, currency50, currency20, currency10,currency5,currency2,currency1;
    private ImageView currency_cal_temp1,currency_cal_temp2,currency10000_cal,currency5000_cal,currency1000_cal,currency500_cal;
    private ImageView memory, leftRotate, clear, operator;
    private TextView total;
    private int totalPrice=0, animateSecond=0,waitAnimateSecond=0;
    private static String currencyCode;
    private static boolean numericMode;
    private int xDelta;
    private int yDelta;

    String [] labels;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_animation);

        mainLayout = (RelativeLayout) findViewById(R.id.main);
        image = (ImageView) findViewById(R.id.image);
        currency_cal_temp1 =(ImageView) findViewById(R.id.currency_cal_temp1);
        currency_cal_temp2 =(ImageView) findViewById(R.id.currency_cal_temp2);
        currency10000 = (ImageView) findViewById(R.id.currency10000);
        currency5000 = (ImageView) findViewById(R.id.currency5000);
        currency1000 = (ImageView) findViewById(R.id.currency1000);
        currency500 = (ImageView) findViewById(R.id.currency500);
        currency100 = (ImageView) findViewById(R.id.currency100);
        currency50 = (ImageView) findViewById(R.id.currency50);
        currency20 = (ImageView) findViewById(R.id.currency20);
        currency10 = (ImageView) findViewById(R.id.currency10);
        currency5 = (ImageView) findViewById(R.id.currency5);
        currency2 = (ImageView) findViewById(R.id.currency2);
        currency1 = (ImageView) findViewById(R.id.currency1);
        currency10000_cal = (ImageView) findViewById(R.id.currency10000_cal);
        currency5000_cal = (ImageView) findViewById(R.id.currency5000_cal);
        currency1000_cal = (ImageView) findViewById(R.id.currency1000_cal);
        currency500_cal = (ImageView) findViewById(R.id.currency500_cal);

        memory = (ImageView) findViewById(R.id.memory);
        leftRotate = (ImageView) findViewById(R.id.leftRotate);
        clear = (ImageView) findViewById(R.id.clear);
        operator = (ImageView) findViewById(R.id.operator);

        total = (TextView) findViewById(R.id.total);

        startAnimation();

//        image.setOnTouchListener(onTouchListener());
    }


    public void makeOperation(int amount, char oper){
        if(oper == '+'){
            totalPrice += amount;
            total.setText("Rs: "+totalPrice);
        }
        else if(oper == '-'){
            totalPrice -= amount;
            total.setText("Rs: "+totalPrice);
        }
        else if(oper == '*'){
            totalPrice *= amount;
            total.setText("Rs: "+totalPrice);
        }
        else {
            totalPrice = amount;
            total.setText("Rs: "+totalPrice);
        }
    }
    public void makeCurrencyVisible(ImageView code, int status){
        code.setVisibility(status);
        clear.setVisibility(View.VISIBLE);
    }
    public void makeCurrencyChange(ImageView code, ImageView change){
        if(R.id.currency5 == change.getId()){
            code.setVisibility(View.VISIBLE);
            code.setImageResource(R.drawable.currency_pkr_5);
//            clear.setVisibility(View.VISIBLE);
        }
        else if(R.id.currency10 == change.getId()){
            code.setVisibility(View.VISIBLE);
            code.setImageResource(R.drawable.currency_pkr_10);
//            clear.setVisibility(View.VISIBLE);
        }
        else if(R.id.currency2 == change.getId()){
            code.setVisibility(View.VISIBLE);
            code.setImageResource(R.drawable.currency_pkr_2);
//            clear.setVisibility(View.VISIBLE);
        }
        //makeOperation(amount, oper);

    }
    public void currencyAnimation(ImageView code, float x, float y) {
        Animation anim = new AlphaAnimation(x, y);
        anim.setDuration(50); //You can manage the blinking time with this parameter
        anim.setStartOffset(20);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.ABSOLUTE);
        code.startAnimation(anim);
    }
    public void handAnimation(ImageView code, float x, float y, int milliSecond) {
        code.animate().x(x).y(y).setDuration(milliSecond);
    }
    public void layoutAnimation(ViewGroup code, float x, float y) {
        Animation anim = new AlphaAnimation(x, y);
        anim.setDuration(50); //You can manage the blinking time with this parameter
        anim.setStartOffset(20);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.ABSOLUTE);
        code.startAnimation(anim);
    }
    public void scrollerAnimation(int second, int direction){
        final HorizontalScrollView hsv = (HorizontalScrollView) findViewById(R.id.horizontalScrollView);
        hsv.postDelayed(new Runnable() {
            @Override
            public void run() {
                hsv.fullScroll(direction);
            }
        },second);
    }
    public void autoSmoothScroll(int second, int x, int y) {

        final HorizontalScrollView hsv = (HorizontalScrollView) findViewById(R.id.horizontalScrollView);
        hsv.postDelayed(new Runnable() {
            @Override
            public void run() {
                //hsv.fullScroll(HorizontalScrollView.FOCUS_RIGHT);
//                hsv.setOnTouchListener(null);
                hsv.smoothScrollBy(x, y);
//                hsv.setHorizontalScrollBarEnabled(false);
//                hsv.setVerticalScrollBarEnabled(false);
//                hsv.setEnabled(false);

            }
        },second);
        hsv.setOnTouchListener(new OnTouch());
    }
    private class OnTouch implements OnTouchListener
    {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            return true;
        }
    }

    public void displayRotation(ImageView code, float x, float y, int status){
        code.setVisibility(status);
        Animation anim = new AlphaAnimation(x, y);
        anim.setDuration(50); //You can manage the blinking time with this parameter
        anim.setStartOffset(20);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.ABSOLUTE);
        code.startAnimation(anim);
    }
    public void clearCurrencyAndAddOperator(char oper){
        if(oper=='+'){
            currency_cal_temp1.setVisibility(View.INVISIBLE);
            operator.setVisibility(View.VISIBLE);
            memory.setVisibility(View.VISIBLE);
            clear.setVisibility(View.VISIBLE);
            leftRotate.setVisibility(View.INVISIBLE);
        }
        else if (oper == '-'){
            currency_cal_temp1.setVisibility(View.INVISIBLE);
            operator.setVisibility(View.VISIBLE);
            operator.setImageResource(R.drawable.operator_minus);
            memory.setVisibility(View.VISIBLE);
            clear.setVisibility(View.VISIBLE);
            leftRotate.setVisibility(View.INVISIBLE);        }
        else{
            currency_cal_temp1.setVisibility(View.INVISIBLE);
            operator.setVisibility(View.VISIBLE);
            operator.setImageResource(R.drawable.operator_times);
            memory.setVisibility(View.VISIBLE);
            clear.setVisibility(View.VISIBLE);
            leftRotate.setVisibility(View.INVISIBLE);
        }
    }

    public void clearResult(){
        currency_cal_temp1.setVisibility(View.INVISIBLE);
        currency_cal_temp2.setVisibility(View.INVISIBLE);
        memory.setVisibility(View.INVISIBLE);
        totalPrice= 0;
        total.setText("Rs: "+totalPrice);
        clear.setVisibility(View.INVISIBLE);

    }
    public void startAnimation(){
        animateSecond=0;waitAnimateSecond+=animateSecond;
        scrollerAnimation(waitAnimateSecond, HorizontalScrollView.FOCUS_RIGHT);
        animateSecond=2000;waitAnimateSecond+=animateSecond;
        (new Handler()).postDelayed(()->handAnimation(image, 450, 512, animateSecond), waitAnimateSecond);
        animateSecond=2000;waitAnimateSecond+=(animateSecond + 500);
        (new Handler()).postDelayed(()->handAnimation(image, 550, 512, animateSecond), waitAnimateSecond);
        animateSecond=200;waitAnimateSecond+=(animateSecond + 1000);
        scrollerAnimation(waitAnimateSecond,HorizontalScrollView.FOCUS_BACKWARD);
        animateSecond=2000;waitAnimateSecond+=(animateSecond + 500);
        (new Handler()).postDelayed(()->handAnimation(image, 450, 512, animateSecond), waitAnimateSecond);
        animateSecond=200;waitAnimateSecond+=(animateSecond + 1000);
        scrollerAnimation(waitAnimateSecond, HorizontalScrollView.FOCUS_RIGHT);
        animateSecond=200;waitAnimateSecond+=(animateSecond + 100);
        autoSmoothScroll(animateSecond, 450, 0);
        animateSecond=2000;waitAnimateSecond+=(animateSecond + 500);
        (new Handler()).postDelayed(()->handAnimation(image, 650, 512, animateSecond), waitAnimateSecond);
        animateSecond=100;waitAnimateSecond+=(animateSecond + 500);
        (new Handler()).postDelayed(()->currencyAnimation(currency10, 0.1f, 1.0f), waitAnimateSecond);
        animateSecond=500;waitAnimateSecond+=(animateSecond + 500);
        (new Handler()).postDelayed(()->makeCurrencyVisible(currency_cal_temp1, View.VISIBLE), waitAnimateSecond);
        //make operation
        animateSecond=10;waitAnimateSecond+=(animateSecond + 10);
        (new Handler()).postDelayed(()->makeOperation(10,'='), waitAnimateSecond);

        animateSecond=2000;waitAnimateSecond+=(animateSecond + 500);
        (new Handler()).postDelayed(()->handAnimation(image, 700, 350, animateSecond), waitAnimateSecond);

        // Rotation
        animateSecond=500;waitAnimateSecond+=(animateSecond + 500);
        (new Handler()).postDelayed(()->displayRotation(leftRotate,0.1f, 1.0f, View.VISIBLE), waitAnimateSecond);

        animateSecond=2000;waitAnimateSecond+=(animateSecond + 500);
        (new Handler()).postDelayed(()->handAnimation(image, 550, 350, animateSecond), waitAnimateSecond);

        // layout animation
        animateSecond=500;waitAnimateSecond+=(animateSecond + 500);
        (new Handler()).postDelayed(()->layoutAnimation(mainLayout, 0.1f, 1.0f), waitAnimateSecond);

        //clear and add operator
        animateSecond=50;waitAnimateSecond+=(animateSecond + 500);
        (new Handler()).postDelayed(()->clearCurrencyAndAddOperator('+'), waitAnimateSecond);

        // select coin
        animateSecond=2000;waitAnimateSecond+=(animateSecond + 500);
        (new Handler()).postDelayed(()->handAnimation(image, 820, 512, animateSecond), waitAnimateSecond);

        animateSecond=100;waitAnimateSecond+=(animateSecond + 500);
        (new Handler()).postDelayed(()->currencyAnimation(currency5, 0.1f, 1.0f), waitAnimateSecond);
        animateSecond=500;waitAnimateSecond+=(animateSecond + 500);
        (new Handler()).postDelayed(()->makeCurrencyChange(currency_cal_temp1,currency5), waitAnimateSecond);

        //move to operator symbol
        animateSecond=2000;waitAnimateSecond+=(animateSecond + 500);
        (new Handler()).postDelayed(()->handAnimation(image, 10, 30, animateSecond), waitAnimateSecond);

        animateSecond=100;waitAnimateSecond+=(animateSecond + 500);
        (new Handler()).postDelayed(()->currencyAnimation(operator, 0.1f, 1.0f), waitAnimateSecond);

        //hide operators
        animateSecond=500;waitAnimateSecond+=(animateSecond + 500);
        (new Handler()).postDelayed(()->makeCurrencyVisible(operator, View.INVISIBLE), waitAnimateSecond);

        //make operation
        animateSecond=10;waitAnimateSecond+=(animateSecond + 10);
        (new Handler()).postDelayed(()->makeOperation(5,'+'), waitAnimateSecond);

        animateSecond=10;waitAnimateSecond+=(animateSecond + 10);
        (new Handler()).postDelayed(()->makeCurrencyChange(currency_cal_temp1,currency10), waitAnimateSecond);

        animateSecond=10;waitAnimateSecond+=(animateSecond + 10);
        (new Handler()).postDelayed(()->makeCurrencyChange(currency_cal_temp2,currency5), waitAnimateSecond);

        // move to the clear button
        animateSecond=2000;waitAnimateSecond+=(animateSecond + 500);
        (new Handler()).postDelayed(()->handAnimation(image, 1200, 300, animateSecond), waitAnimateSecond);

        // clear button animation
        animateSecond=100;waitAnimateSecond+=(animateSecond + 500);
        (new Handler()).postDelayed(()->currencyAnimation(clear, 0.1f, 1.0f), waitAnimateSecond);
        // Clear the result
        animateSecond=10;waitAnimateSecond+=(animateSecond + 10);
        (new Handler()).postDelayed(()->clearResult(), waitAnimateSecond);


//         Multiplication
//         select coin
        animateSecond=2000;waitAnimateSecond+=(animateSecond + 500);
        (new Handler()).postDelayed(()->handAnimation(image, 820, 512, animateSecond), waitAnimateSecond);

        animateSecond=100;waitAnimateSecond+=(animateSecond + 500);
        (new Handler()).postDelayed(()->currencyAnimation(currency5, 0.1f, 1.0f), waitAnimateSecond);
        animateSecond=500;waitAnimateSecond+=(animateSecond + 500);
        (new Handler()).postDelayed(()->makeCurrencyChange(currency_cal_temp1,currency5), waitAnimateSecond);

        //make operation
        animateSecond=10;waitAnimateSecond+=(animateSecond + 10);
        (new Handler()).postDelayed(()->makeOperation(5,'+'), waitAnimateSecond);

        animateSecond=2000;waitAnimateSecond+=(animateSecond + 500);
        (new Handler()).postDelayed(()->handAnimation(image, 700, 150, animateSecond), waitAnimateSecond);

        // Rotation
        animateSecond=500;waitAnimateSecond+=(animateSecond + 500);
        (new Handler()).postDelayed(()->displayRotation(leftRotate,0.1f, 1.0f, View.VISIBLE), waitAnimateSecond);

        animateSecond=2000;waitAnimateSecond+=(animateSecond + 500);
        (new Handler()).postDelayed(()->handAnimation(image, 700, 450, animateSecond), waitAnimateSecond);

        // layout animation
        animateSecond=500;waitAnimateSecond+=(animateSecond + 500);
        (new Handler()).postDelayed(()->layoutAnimation(mainLayout, 0.1f, 1.0f), waitAnimateSecond);

        //clear and add operator
        animateSecond=50;waitAnimateSecond+=(animateSecond + 500);
        (new Handler()).postDelayed(()->clearCurrencyAndAddOperator('*'), waitAnimateSecond);

        // select coin
        animateSecond=2000;waitAnimateSecond+=(animateSecond + 500);
        (new Handler()).postDelayed(()->handAnimation(image, 950, 512, animateSecond), waitAnimateSecond);

        animateSecond=100;waitAnimateSecond+=(animateSecond + 500);
        (new Handler()).postDelayed(()->currencyAnimation(currency2, 0.1f, 1.0f), waitAnimateSecond);
        animateSecond=500;waitAnimateSecond+=(animateSecond + 500);
        (new Handler()).postDelayed(()->makeCurrencyChange(currency_cal_temp1,currency2), waitAnimateSecond);

        //move to operator symbol
        animateSecond=2000;waitAnimateSecond+=(animateSecond + 500);
        (new Handler()).postDelayed(()->handAnimation(image, 10, 30, animateSecond), waitAnimateSecond);

        animateSecond=100;waitAnimateSecond+=(animateSecond + 500);
        (new Handler()).postDelayed(()->currencyAnimation(operator, 0.1f, 1.0f), waitAnimateSecond);

        //hide operators
        animateSecond=500;waitAnimateSecond+=(animateSecond + 500);
        (new Handler()).postDelayed(()->makeCurrencyVisible(operator, View.INVISIBLE), waitAnimateSecond);

        animateSecond=10;waitAnimateSecond+=(animateSecond + 50);
        (new Handler()).postDelayed(()->makeCurrencyChange(currency_cal_temp1,currency10), waitAnimateSecond);

        //make operation
        animateSecond=10;waitAnimateSecond+=(animateSecond + 10);
        (new Handler()).postDelayed(()->makeOperation(5,'+'), waitAnimateSecond);

        // move to the clear button
        animateSecond=2000;waitAnimateSecond+=(animateSecond + 500);
        (new Handler()).postDelayed(()->handAnimation(image, 1200, 300, animateSecond), waitAnimateSecond);

        // clear button animation
        animateSecond=100;waitAnimateSecond+=(animateSecond + 500);
        (new Handler()).postDelayed(()->currencyAnimation(clear, 0.1f, 1.0f), waitAnimateSecond);
        // Clear the result
        animateSecond=10;waitAnimateSecond+=(animateSecond + 10);
        (new Handler()).postDelayed(()->clearResult(), waitAnimateSecond);


        // Substraction
        // select note-10
        animateSecond=2000;waitAnimateSecond+=(animateSecond + 500);
        (new Handler()).postDelayed(()->handAnimation(image, 650, 512, animateSecond), waitAnimateSecond);

        animateSecond=100;waitAnimateSecond+=(animateSecond + 500);
        (new Handler()).postDelayed(()->currencyAnimation(currency10, 0.1f, 1.0f), waitAnimateSecond);
        animateSecond=500;waitAnimateSecond+=(animateSecond + 500);
        (new Handler()).postDelayed(()->makeCurrencyChange(currency_cal_temp1,currency10), waitAnimateSecond);

        //make operation
        animateSecond=10;waitAnimateSecond+=(animateSecond + 10);
        (new Handler()).postDelayed(()->makeOperation(10,'+'), waitAnimateSecond);

        animateSecond=2000;waitAnimateSecond+=(animateSecond + 500);
        (new Handler()).postDelayed(()->handAnimation(image, 500, 150, animateSecond), waitAnimateSecond);

        // Rotation
        animateSecond=500;waitAnimateSecond+=(animateSecond + 500);
        (new Handler()).postDelayed(()->displayRotation(leftRotate,0.1f, 1.0f, View.VISIBLE), waitAnimateSecond);

        animateSecond=2000;waitAnimateSecond+=(animateSecond + 500);
        (new Handler()).postDelayed(()->handAnimation(image, 700, 150, animateSecond), waitAnimateSecond);

        // layout animation
        animateSecond=500;waitAnimateSecond+=(animateSecond + 500);
        (new Handler()).postDelayed(()->layoutAnimation(mainLayout, 0.1f, 1.0f), waitAnimateSecond);

        //clear and add operator
        animateSecond=50;waitAnimateSecond+=(animateSecond + 500);
        (new Handler()).postDelayed(()->clearCurrencyAndAddOperator('-'), waitAnimateSecond);

        // select coin
        animateSecond=2000;waitAnimateSecond+=(animateSecond + 500);
        (new Handler()).postDelayed(()->handAnimation(image, 850, 512, animateSecond), waitAnimateSecond);

        animateSecond=100;waitAnimateSecond+=(animateSecond + 500);
        (new Handler()).postDelayed(()->currencyAnimation(currency5, 0.1f, 1.0f), waitAnimateSecond);
        animateSecond=500;waitAnimateSecond+=(animateSecond + 500);
        (new Handler()).postDelayed(()->makeCurrencyChange(currency_cal_temp1,currency5), waitAnimateSecond);

        //move to operator symbol
        animateSecond=2000;waitAnimateSecond+=(animateSecond + 500);
        (new Handler()).postDelayed(()->handAnimation(image, 10, 30, animateSecond), waitAnimateSecond);

        animateSecond=100;waitAnimateSecond+=(animateSecond + 500);
        (new Handler()).postDelayed(()->currencyAnimation(operator, 0.1f, 1.0f), waitAnimateSecond);

        //hide operators
        animateSecond=500;waitAnimateSecond+=(animateSecond + 500);
        (new Handler()).postDelayed(()->makeCurrencyVisible(operator, View.INVISIBLE), waitAnimateSecond);

        animateSecond=10;waitAnimateSecond+=(animateSecond + 50);
        (new Handler()).postDelayed(()->makeCurrencyChange(currency_cal_temp1,currency5), waitAnimateSecond);

        //make operation
        animateSecond=10;waitAnimateSecond+=(animateSecond + 10);
        (new Handler()).postDelayed(()->makeOperation(5,'-'), waitAnimateSecond);

        // move to the clear button
        animateSecond=2000;waitAnimateSecond+=(animateSecond + 500);
        (new Handler()).postDelayed(()->handAnimation(image, 1200, 300, animateSecond), waitAnimateSecond);

        // clear button animation
        animateSecond=100;waitAnimateSecond+=(animateSecond + 500);
        (new Handler()).postDelayed(()->currencyAnimation(clear, 0.1f, 1.0f), waitAnimateSecond);
        // Clear the result
        animateSecond=10;waitAnimateSecond+=(animateSecond + 10);
        (new Handler()).postDelayed(()->clearResult(), waitAnimateSecond);


    }

}

//(new Handler()).postDelayed(()->handAnimation(image, 900, 512, 1000), 3700);
////        (new Handler()).postDelayed(()->currencyAnimation(currency5000, 0.1f, 1.0f), 5200);
////        (new Handler()).postDelayed(()->makeCurrencyVisible(currency5000_cal, View.VISIBLE, 5000), 5700);
//
////        Handler handler = new Handler();
////        handler.postDelayed(new Runnable() {
////            public void run() {
////                // yourMethod();
////                image.animate().x(300).y(212).setDuration(2000);
////            }
////        }, 2500);   //5 seconds



//    private OnTouchListener onTouchListener() {
//        return new OnTouchListener() {
//
//            @SuppressLint("ClickableViewAccessibility")
//            @Override
//            public boolean onTouch(View view, MotionEvent event) {
//
//                final int x = (int) event.getRawX();
//                final int y = (int) event.getRawY();
//
//                switch (event.getAction() & MotionEvent.ACTION_MASK) {
//
//                    case MotionEvent.ACTION_DOWN:
//                        RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams)
//                                view.getLayoutParams();
//
//                        xDelta = x - lParams.leftMargin;
//                        yDelta = y - lParams.topMargin;
//                        break;
//
//                    case MotionEvent.ACTION_UP:
//                        Toast.makeText(IntroActivity.this,
//                                        "I'm here!", Toast.LENGTH_SHORT)
//                                .show();
//                        break;
//
//                    case MotionEvent.ACTION_MOVE:
//                        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view
//                                .getLayoutParams();
//                        layoutParams.leftMargin = x - xDelta;
//                        layoutParams.topMargin = y - yDelta;
//                        layoutParams.rightMargin = 0;
//                        layoutParams.bottomMargin = 0;
//                        view.setLayoutParams(layoutParams);
//                        break;
//                }
//
//                mainLayout.invalidate();
//                return true;
//            }
//        };
//    }
