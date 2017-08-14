package com.example.user.text;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LiXinyu
 * @date 2017/8/14 14:07.
 */

public class RecylcerViewText extends Activity{

    private RecyclerView recyclerView;
    private List<String> datas;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycleview);
        initDatas();
        initViews();
        initListener();
    }

    private void initDatas() {
        datas=new ArrayList<String>();
        for(int i='A';i<'z';i++){
            datas.add(""+(char)i);
        }
    }

    private void initViews() {
        recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new RecyclerAdapter(this,datas));
    }

    private void initListener() {

    }
}
