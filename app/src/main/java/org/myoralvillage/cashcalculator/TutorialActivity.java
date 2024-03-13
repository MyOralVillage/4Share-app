package org.myoralvillage.cashcalculator;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import org.myoralvillage.cashcalculator.tutorials.AdvancedVideoActivity;
import org.myoralvillage.cashcalculator.tutorials.IntroVideoActivity;
import org.myoralvillage.cashcalculator.tutorials.NumericVideoActivity;
import org.myoralvillage.cashcalculator.tutorials.ThirdVideoActivity;
import org.myoralvillage.cashcalculator.tutorials.ZeroVideoActivity;

import androidx.appcompat.app.AppCompatActivity;



public class TutorialActivity extends AppCompatActivity implements View.OnClickListener {

    String currencyName = null;
    boolean numericMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_tutorial);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            if (extras.containsKey("currencyCode")) {
                currencyName = extras.getString("currencyCode");
            }
            if (extras.containsKey("numericMode")) {
                numericMode = extras.getBoolean("numericMode", false);
            }

        }

//        numericMode = getIntent().getBooleanExtra("numericMode", false);
//        currencyName = getIntent().getStringExtra("currencyName");
//        currencyName = "PKR";
        Button intro_video = findViewById(R.id.intro_video);
        intro_video.setOnClickListener(this);
        Button advanced_video = findViewById(R.id.advanced_video);
        advanced_video.setOnClickListener(this);
        Button numeric_video = findViewById(R.id.numeric_video);
        numeric_video.setOnClickListener(this);
//        ImageView goto_main = (ImageView) findViewById(R.id.goto_main);
//        goto_main.setOnClickListener(this);

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
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.intro_video:
                switchtointrovideo();
//                switchtozerovideo();
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
