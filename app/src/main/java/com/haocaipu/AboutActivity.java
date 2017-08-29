package com.haocaipu;

import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class AboutActivity extends AppCompatActivity {
    private TextView aboutTextView;
    private TextView versionTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        aboutTextView = (TextView) findViewById(R.id.about_tv);
        versionTextView = (TextView) findViewById(R.id.version_tv);

        String versionName = null;

        try {
            versionName = this.getPackageManager().getPackageInfo(this.getPackageName(),0).versionName;
        } catch (PackageManager.NameNotFoundException e) {

        }

        StringBuilder text = new StringBuilder();
        versionTextView.setText("版本号:"+versionName+".");
        text.append("更新日期:2017-08-29\n");
        text.append("官网:www.haocaipu.com\n");
        text.append("开发者:好菜谱\n");
        text.append("微信:好菜谱\n");
        text.append("微信公众号:好菜谱\n");
        aboutTextView.setText(text.toString());
    }

    public void about_bt(View view){
        Toast.makeText(this,"您的版本为最新哦",Toast.LENGTH_SHORT).show();
    }
}
