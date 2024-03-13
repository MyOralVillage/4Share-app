package org.myoralvillage.cashcalculator;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import org.myoralvillage.cashcalculator.tutorials.IntroVideoActivity;
import org.myoralvillage.cashcalculator.tutorials.ZeroVideoActivity;
import org.myoralvillage.cashcalculatormodule.fragments.CashCalculatorFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static String currencyCode;
    private static boolean numericMode;
    private ImageView gototutorial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);
        CashCalculatorFragment fragment = (CashCalculatorFragment) getSupportFragmentManager()
                .findFragmentById(R.id.CountingTableFragment);

        numericMode = false;
        Bundle extras = getIntent().getExtras();

        boolean firstComplete = false;
        //extras.getBoolean("firstComplete");

        if (extras != null) {
            if (extras.containsKey("currencyCode")) {
                currencyCode = extras.getString("currencyCode");
            }
            if (extras.containsKey("numericMode")) {
                numericMode = extras.getBoolean("numericMode", false);
            }
            if (extras.containsKey("firstComplete")) {
                firstComplete = extras.getBoolean("firstComplete");
            }

        }

        if (fragment != null) {
            fragment.initialize(currencyCode);
            if (numericMode) {
                fragment.switchToNumericMode();
            }
        }
        if(firstComplete){
            // play video 0
            (new Handler()).postDelayed(()-> {
                switchtozerovideo();
            }, 1000);
        }
        gototutorial = (ImageView) findViewById(R.id.gototutorial);
        gototutorial.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.gototutorial:
                switchtoTutorial();
                break;
        }
    }

    private void switchtoTutorial() {
        System.out.println("Test here");
        Intent intent = new Intent(this, TutorialActivity.class);
        intent.putExtra("currencyCode", currencyCode);
        intent.putExtra("numericMode", getIntent().getBooleanExtra("numericMode", false));
        startActivity(intent);
        finish();
    }
    private void switchtozerovideo() {
        System.out.println("Test here");
        Intent intent = new Intent(this, ZeroVideoActivity.class);
        intent.putExtra("currencyName", currencyCode);
        intent.putExtra("numericMode", numericMode);
        intent.putExtra("animationStage", 0);
        startActivity(intent);
        finish();
    }
}