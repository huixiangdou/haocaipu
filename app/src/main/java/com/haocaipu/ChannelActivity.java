package com.haocaipu;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.haocaipu.adapter.ChannelAdapter;
import com.haocaipu.adapter.OnRecyclerViewItemClickListener;
import com.haocaipu.basic.BasicActivity;
import com.haocaipu.moel.Channel;
import com.lzy.okgo.OkGo;

import java.util.ArrayList;

public class ChannelActivity extends BasicActivity {
    private ProgressBar progressBar;
    private Context context;
    private StaggeredGridLayoutManager gridLayoutManager;
    private ArrayList<Channel> channelArrayList;
    private ChannelAdapter channelAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel);
        progressBar = (ProgressBar) findViewById(R.id.progress);

        GetDataChannel getDataChannel = new GetDataChannel(progressBar,context,this);
        getDataChannel.getDataClass();
    }

    @Override
    public void onSucess(ArrayList arrayList) {
        gridLayoutManager = new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL);
        recyclerView = (RecyclerView) findViewById(R.id.channel_recycler);
        recyclerView.setLayoutManager(gridLayoutManager);
        channelAdapter = new ChannelAdapter(arrayList);
        recyclerView.setAdapter(channelAdapter);
        channelAdapter.setOnRecyclerViewItemClickListener(new OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, Object object) {
                Channel channel = (Channel) object;
                Intent intent = new Intent(ChannelActivity.this,ListActivity.class);
                intent.putExtra("CHANNEL_NAME",channel.getClssId());
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
