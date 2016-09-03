package com.example.chenyunpeng.youhuo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import com.example.chenyunpeng.youhuo.MainActivity;
import com.example.chenyunpeng.youhuo.R;
public class ChooseActivity extends BaseActivity implements View.OnClickListener {

    private ImageButton boys;
    private ImageButton girls;
    private ImageButton kids;
    private ImageButton lifestyle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        init();

    }

    private void init() {
        this.lifestyle = (ImageButton) findViewById(R.id.lifestyle);
        this.kids = (ImageButton) findViewById(R.id.kids);
        this.girls = (ImageButton) findViewById(R.id.girls);
        this.boys = (ImageButton) findViewById(R.id.boys);
        boys.setOnClickListener(this);
        girls.setOnClickListener(this);
        kids.setOnClickListener(this);
        lifestyle.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.boys:
            case R.id.girls:
            case R.id.kids:
            case R.id.lifestyle:
                startActivity(new Intent(ChooseActivity.this,MainActivity.class));
                overridePendingTransition(R.anim.fade_in_boys,R.anim.fade_out_choose);
                break;
        }
    }
}
