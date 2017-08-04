package com.example.user.text;

        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.ImageView;

        import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btn_1;
    private Button btn_2;
    private ImageView imageView_1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        initview();
    }
    private void init(){
        btn_1=(Button)findViewById(R.id.button_1);
        btn_2=(Button)findViewById(R.id.button_2);
        imageView_1=(ImageView)findViewById(R.id.iv_1);
    }
    private void initview(){

        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
    }

    /**
    *
    * @author lixinyu
    * @date 2017/8/3 17:01
    */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_1:
                imageload(1);
                Log.e("123", "onClick:1 ");
                break;
            case R.id.button_2:
                imageload(2);
                Log.e("123", "onClick:2 ");
                break;
        }
    }

    private void imageload(int num){
        switch(num){
            case 1:
                String url="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1501836662916&di=bab4ed919c671bf5267ab207d18740f6&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2Fb03533fa828ba61e5e6d4c0d4b34970a304e5915.jpg";
                Glide.with(this).load(url).placeholder(R.drawable.photo).into(imageView_1);
                break;
            case 2:
                Glide.with(this).load(R.drawable.photo).into(imageView_1);
        }
    }
}
