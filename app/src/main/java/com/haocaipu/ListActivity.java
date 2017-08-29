package com.haocaipu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.haocaipu.adapter.ChannelAdapter;
import com.haocaipu.adapter.OnRecyclerViewItemClickListener;
import com.haocaipu.adapter.RecipeAdapter;
import com.haocaipu.basic.BasicActivity;
import com.haocaipu.moel.Process;
import com.haocaipu.moel.Recipe;
import com.lzy.okgo.OkGo;

import java.util.ArrayList;

public class ListActivity extends BasicActivity {
    private ProgressBar progressBar;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView recyclerView;
    private RecipeAdapter recipeAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        progressBar = (ProgressBar) findViewById(R.id.progress);
        Intent intent = getIntent();
        if (intent.hasExtra("CHANNEL_NAME")){
            String classid = intent.getStringExtra("CHANNEL_NAME");
            Log.d("channel_name",classid);

            GetDataRecipe getDataRecipe = new GetDataRecipe(progressBar,this,this);
            getDataRecipe.getDatas("byclass","classid",classid);
        }
    }

    @Override
    public void onSucess(ArrayList arrayList) {
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView = (RecyclerView) findViewById(R.id.list_recycler);
        recyclerView.setLayoutManager(linearLayoutManager);
        recipeAdapter = new RecipeAdapter(arrayList);
        recyclerView.setAdapter(recipeAdapter);
        recipeAdapter.setOnItemClickListener(new OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, Object object) {
                Recipe recipe = (Recipe) object;
                Intent intent = new Intent(ListActivity.this,ContentActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("RECIPE",recipe);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OkGo.getInstance().cancelTag(this);
    }
}
