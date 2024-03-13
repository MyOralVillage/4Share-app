package org.myoralvillage.cashcalculator.tutorials;

import android.animation.Animator;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import org.myoralvillage.cashcalculator.MainActivity;
import org.myoralvillage.cashcalculator.R;
import org.myoralvillage.cashcalculator.TutorialActivity;
import org.myoralvillage.cashcalculatormodule.fragments.CashCalculatorFragment;
import org.myoralvillage.cashcalculatormodule.models.CurrencyModel;
import org.myoralvillage.cashcalculatormodule.services.CurrencyService;
import org.myoralvillage.cashcalculatormodule.services.SettingService;
import org.myoralvillage.cashcalculatormodule.utils.SavedPreferences;
import org.myoralvillage.cashcalculatormodule.views.CountingTableView;
import org.myoralvillage.cashcalculatormodule.views.CurrencyScrollbarView;
import org.myoralvillage.cashcalculatormodule.views.NumberPadView;

import java.util.ArrayList;
import java.util.List;

public class ZeroVideoActivity extends AppCompatActivity {

    String currencyName = null;
    boolean numericMode = false;
    private static SettingService settingService;
    private ImageView pointing_hand, button;
    private View view1, view2;
    private CashCalculatorFragment fragment;

    private CountingTableView countingTable;
    private CurrencyScrollbarView currencyScrollbar;
    private NumberPadView numberPad;

    private CurrencyModel currency;
    private int numDenominations;
    private ArrayList<Animator> animations;
    private List<Integer> horizontalOffsets;
    private List<Integer> verticalOffsets;

    private int height;
    private int width;
    private int[] fingerLocation;
    private int[] scrollbarLocation;
    private int scrollbarWidth;
    private int scrollbarScrollPosition;

    private int elapsed = 0;

    private ImageView finger;
    private View black;
    private NumberPadView numberPadView;
    private View view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_zero_animation_1);
        pointing_hand = (ImageView) findViewById(R.id.pointing_hand);
        view1 = getLayoutInflater().inflate(R.layout.activity_zero_animation_1, null);
        view2 = getLayoutInflater().inflate(R.layout.activity_main, null);
//        setContentView(view1);
        settingService = new SettingService(getApplicationContext(), getResources());
        buildLayout();
    }

    public void startAnimation() {
        (new Handler()).postDelayed(()->handAnimation(pointing_hand, 350, 300, 500), 2000);
        (new Handler()).postDelayed(()->ImageAnimation(button, 0.1f, 1.0f), 3000);
        (new Handler()).postDelayed(()-> {
            switchtozerovideo1();

        }, 3500);

    }
    public void ImageAnimation(ImageView code, float x, float y) {
        Animation anim = new AlphaAnimation(x, y);
        anim.setDuration(50); //You can manage the blinking time with this parameter
        anim.setStartOffset(20);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.ABSOLUTE);
        code.startAnimation(anim);
    }
    public static SettingService getSettingService() {
        return settingService;
    }

    private void buildLayout() {
        new CurrencyService(getApplicationContext(), settingService.getDefaultOrder()).call(currencies
                -> runOnUiThread(() -> {
            LinearLayout view = findViewById(R.id.currencies);
            int width = (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    260f,
                    getResources().getDisplayMetrics()
            );
            int height = (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    130f,
                    getResources().getDisplayMetrics()
            );
            int paddingSides = (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    25f,
                    getResources().getDisplayMetrics()
            );
            int paddingTopBottom = (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    80f,
                    getResources().getDisplayMetrics()
            );

            for (int i = 0; i < currencies.length; i++) {
                String currency = currencies[i];
                currencyName = currency;
                button = new ImageView(this);
                button.setScaleType(ImageView.ScaleType.CENTER_CROP);

                LinearLayout.LayoutParams params =
                        new LinearLayout.LayoutParams(width, height);

                params.setMargins(0, 0, 0, (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP,
                        10f,
                        getResources().getDisplayMetrics()));

                Log.d("PADDING", "TOP>>>> "+paddingTopBottom+" SIDES>>>>> "+paddingSides);

                button.setLayoutParams(params);
                button.setPadding(paddingSides,
                        paddingTopBottom,
                        paddingSides,
                        paddingTopBottom);
                button.setBackgroundResource(R.drawable.white_rectangle);
                button.setImageResource(CurrencyService.getCurrencyResource(currency));
//                button.setOnClickListener(e -> switchToMainActivity(currency));
                view.addView(button);
            }
        }));
        findViewById(R.id.btn_privacy_policy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.url_privacy_policy)));
//                startActivity(browserIntent);
            }
        });
        startAnimation();
    }

    private void switchToMainActivity(String currencyCode) {
        SavedPreferences.setSelectedCurrencyCode(this,currencyCode);
//        Intent intent = new Intent(this, TutorialActivity.class);
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("currencyCode", currencyCode);
        intent.putExtra("numericMode", getIntent().getBooleanExtra("numericMode", false));
        startActivity(intent);
        finish();
    }
    private void switchtozerovideo1() {
        Intent intent = new Intent(this, ZeroVideoActivity_1.class);
        intent.putExtra("currencyName", currencyName);
        intent.putExtra("numericMode", numericMode);
        intent.putExtra("animationStage", 0);
        startActivity(intent);
        finish();
    }


    public void handAnimation(ImageView code, float x, float y, int milliSecond) {
        code.animate().x(x).y(y).setDuration(milliSecond);
    }

}
