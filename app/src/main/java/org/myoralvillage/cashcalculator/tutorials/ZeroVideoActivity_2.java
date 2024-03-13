package org.myoralvillage.cashcalculator.tutorials;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import org.myoralvillage.cashcalculator.MainActivity;
import org.myoralvillage.cashcalculator.R;


public class ZeroVideoActivity_2 extends AppCompatActivity implements View.OnClickListener {

    String currencyName = null;
    boolean numericMode = false;
    int pageNumber;
    private ImageView finger, goto_main;
    private Button intro_video,advanced_video,numeric_video;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_zero_animation_3);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            currencyName = getIntent().getStringExtra("currencyName");
            numericMode = getIntent().getBooleanExtra("numericMode", false);
        }

        finger = findViewById(R.id.finger);

        intro_video = findViewById(R.id.intro_video);
        advanced_video = findViewById(R.id.advanced_video);
        numeric_video = findViewById(R.id.numeric_video);
//        goto_main = (ImageView) findViewById(R.id.goto_main);
        pageNumber = extras.getInt("pageNumber");
        if(pageNumber==1){
            (new Handler()).postDelayed(()->handAnimation(finger, 350, 200, 500), 2000);
            (new Handler()).postDelayed(()->ImageAnimation(intro_video, 0.1f, 1.0f), 3000);
            (new Handler()).postDelayed(()-> {
                switchtozerovideo3();
            }, 3500);
        }
        else if(pageNumber==2){
            (new Handler()).postDelayed(()->handAnimation(finger, 750, 200, 500), 2000);
            (new Handler()).postDelayed(()->ImageAnimation(advanced_video, 0.1f, 1.0f), 3000);
            (new Handler()).postDelayed(()-> {
                switchtozerovideo4();
            }, 3500);
        }
        else if(pageNumber==3){
            (new Handler()).postDelayed(()->handAnimation(finger, 1050, 200, 500), 2000);
            (new Handler()).postDelayed(()->ImageAnimation(numeric_video, 0.1f, 1.0f), 3000);
            (new Handler()).postDelayed(()-> {
                switchtozerovideo5();
            }, 3500);
        }
        else if (pageNumber==4){
//            (new Handler()).postDelayed(()->handAnimation(finger, 1130, 200, 500), 2000);
//            (new Handler()).postDelayed(()->ImageAnimation(goto_main, 0.1f, 1.0f), 3000);
            (new Handler()).postDelayed(()-> {
                switchtomain();
            }, 1500);
        }
    }

    public void handAnimation(ImageView code, float x, float y, int milliSecond) {
        code.animate().x(x).y(y).setDuration(milliSecond);
    }
    public void ImageAnimation(View code, float x, float y) {
        Animation anim = new AlphaAnimation(x, y);
        anim.setDuration(50); //You can manage the blinking time with this parameter
        anim.setStartOffset(20);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.ABSOLUTE);
        code.startAnimation(anim);
    }

    private void switchtozerovideo3() {
        Intent intent = new Intent(this, ZeroVideoActivity_3.class);
//        intent.putExtra("pageNumber", "1");
        intent.putExtra("currencyName", currencyName);
        intent.putExtra("numericMode", numericMode);
        intent.putExtra("animationStage", 0);
        startActivity(intent);
        finish();
    }
    private void switchtozerovideo4() {
        Intent intent = new Intent(this, ZeroVideoActivity_4.class);
//        intent.putExtra("pageNumber", "1");
        intent.putExtra("currencyName", currencyName);
        intent.putExtra("numericMode", numericMode);
        intent.putExtra("animationStage", 0);
        startActivity(intent);
        finish();
    }
    private void switchtozerovideo5() {
        Intent intent = new Intent(this, ZeroVideoActivity_5.class);
//        intent.putExtra("pageNumber", "1");
        intent.putExtra("currencyName", currencyName);
        intent.putExtra("numericMode", numericMode);
        intent.putExtra("animationStage", 0);
        startActivity(intent);
        finish();
    }
    private void switchtozerovideo() {
        System.out.println("Test here");
        Intent intent = new Intent(this, ZeroVideoActivity.class);
        intent.putExtra("currencyName", currencyName);
        intent.putExtra("numericMode", numericMode);
        intent.putExtra("animationStage", 0);
        startActivity(intent);
        finish();
    }
    private void switchtointrovideo() {
        System.out.println("Test here");
        Intent intent = new Intent(this, IntroVideoActivity.class);
        intent.putExtra("currencyName", currencyName);
        intent.putExtra("numericMode", numericMode);
        intent.putExtra("animationStage", 0);
        startActivity(intent);
        finish();
    }

    private void switchtoadvancedvideo() {
        Intent intent = new Intent(this, AdvancedVideoActivity.class);
        intent.putExtra("currencyName", currencyName);
        intent.putExtra("numericMode", numericMode);
        intent.putExtra("animationStage", 0);
        startActivity(intent);
        finish();
    }

    private void switchtonumericvideo() {
        Intent intent = new Intent(this, NumericVideoActivity.class);
        intent.putExtra("currencyName", currencyName);
        intent.putExtra("numericMode", numericMode);
        intent.putExtra("animationStage", 0);
        startActivity(intent);
        finish();
    }

    private void switchtothirdvideo() {
        Intent intent = new Intent(this, ThirdVideoActivity.class);
        intent.putExtra("currencyName", currencyName);
        intent.putExtra("numericMode", numericMode);
        intent.putExtra("animationStage", 0);
        startActivity(intent);
        finish();
    }


    private void switchtomain() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("currencyCode", currencyName);
        intent.putExtra("numericMode", getIntent().getBooleanExtra("numericMode", false));
//        intent.putExtra("firstComplete", true);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.intro_video:
//                switchtointrovideo();
                switchtozerovideo();
                break;

            case R.id.advanced_video:
                switchtoadvancedvideo();
                break;

            case R.id.numeric_video:
                switchtothirdvideo();
                break;

//            case R.id.goto_main:
//                switchtomain();
//                break;
        }
    }
}
