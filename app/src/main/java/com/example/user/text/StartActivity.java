package com.example.user.text;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

/**
 * @author LiXinyu
 * @date 2017/8/14 13:53.
 */

public class StartActivity extends Activity implements View.OnClickListener {

    private Button btn_1;
    private Button btn_2;
    private Button btn_3;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startlayout);
        initViews();
        initListener();
    }

    private void initViews() {
        btn_1=(Button)findViewById(R.id.button);
        btn_2=(Button)findViewById(R.id.button2);
        btn_3=(Button)findViewById(R.id.button3);
    }

    private void initListener() {
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent();
        switch (v.getId()){
            case R.id.button:
                intent.setClass(this,MainActivity.class);
                break;
            case R.id.button2:
                intent.setClass(this,AnimText.class);
                break;
            case R.id.button3:
                intent.setClass(this,RecylcerViewText.class);
                break;
        }
        startActivity(intent);
    }
}
