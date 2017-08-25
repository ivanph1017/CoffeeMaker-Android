package com.eveexite.coffeemaker_android.ui.view;


import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.eveexite.coffeemaker_android.R;
import com.eveexite.coffeemaker_android.di.component.ActivityComponent;
import com.eveexite.coffeemaker_android.ui.base.BaseActivity;
import com.eveexite.coffeemaker_android.ui.presenter.MainPresenter;
import com.eveexite.coffeemaker_android.ui.widget.TitleView;
import com.eveexite.coffeemaker_android.ui.widget.WaterLevelView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements MainPresenter.View {

    @Inject
    MainPresenter mainPresenter;

    @BindView(R.id.vMain)
    LottieAnimationView vMain;
    @BindView(R.id.vTitle)
    TitleView vTitle;
    @BindView(R.id.ivTimer)
    ImageView ivTimer;
    @BindView(R.id.tvTimer)
    TextView tvTimer;
    @BindView(R.id.vWaterLevel)
    WaterLevelView vWaterLevel;
    @BindView(R.id.fabPower)
    FloatingActionButton fabPower;

    @Override
    public void initView() {
        super.initView();
        initializePresenter();
        vMain.setAnimation("coffee_maker.json");
        vMain.setScaleType(ImageView.ScaleType.FIT_XY);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPresenter.destroy();
    }

    @Override
    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_alert, null);
        ((TextView) view.findViewById(R.id.tvWindowTitle)).setText(title);
        ((TextView) view.findViewById(R.id.tvMessage)).setText(message);
        AlertDialog alertDialog = builder.setView(view).create();
        view.findViewById(R.id.btnDismiss).setOnClickListener(v -> {
            alertDialog.dismiss();
        });
        alertDialog.show();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void setupComponent(ActivityComponent component) {
        component.inject(this);
    }

    private void initializePresenter() {
        mainPresenter.setView(this);
        mainPresenter.initialize();
    }

    @Override
    public void showTitle(String title) {
        vTitle.setText(title);
        vTitle.invalidate();
    }

    @Override
    public void showWaterLevelValue(int waterLevel) {
        vWaterLevel.setWaterLevel(waterLevel);
    }

    @Override
    public void showTimerValue(String timer) {
        tvTimer.setText(timer);
    }

    @Override
    public void setAnimation(String filePath, ImageView.ScaleType scaleType) {
        vMain.clearAnimation();
        vMain.setAnimation(filePath);
        vMain.setScaleType(scaleType);
        vMain.setProgress(0.0f);
    }

    @Override
    public void playAnimation() {
        vMain.playAnimation();
    }

    @Override
    public void showMsgConfirmation(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_alert_confirmation, null);
        ((TextView) view.findViewById(R.id.tvWindowTitle)).setText(title);
        ((TextView) view.findViewById(R.id.tvMessage)).setText(message);
        AlertDialog alertDialog = builder.setView(view).create();
        view.findViewById(R.id.btnAccept).setOnClickListener(v -> {
            alertDialog.dismiss();
            mainPresenter.isCoffeeMakerReady();
        });
        view.findViewById(R.id.btnCancel).setOnClickListener(v -> {
            alertDialog.cancel();
        });
        alertDialog.show();
    }


    @OnClick(R.id.fabPower)
    public void onTurnOn(View view) {
        mainPresenter.showMsgConfirmation();
    }
}
