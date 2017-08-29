package com.haocaipu.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.haocaipu.ContentActivity;
import com.haocaipu.GetDataRecipe;
import com.haocaipu.Interface.GetDataInterface;
import com.haocaipu.R;
import com.haocaipu.adapter.OnRecyclerViewItemClickListener;
import com.haocaipu.adapter.RecipeAdapter;
import com.haocaipu.moel.Recipe;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment9 extends Fragment implements GetDataInterface{
    private ProgressBar progressBar;
    private View view;
    private RecipeAdapter recipeAdapter;
    private RecyclerView recipeRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.context = getContext();
        view = inflater.inflate(R.layout.fragment_0, null);
        progressBar = view.findViewById(R.id.progress);
        GetDataRecipe getdata = new GetDataRecipe(progressBar,getContext(),this);
        getdata.getDatas("byclass","classid","137");
        return view;
    }

    @Override
    public void onSucess(ArrayList arrayList) {
        mLayoutManager = new LinearLayoutManager(context);
        recipeRecyclerView = view.findViewById(R.id.recipe_recycler);
        recipeRecyclerView.setLayoutManager(mLayoutManager);
        recipeAdapter = new RecipeAdapter(arrayList);
        recipeRecyclerView.setAdapter(recipeAdapter);
        recipeAdapter.setOnItemClickListener(new OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, Object object) {
                Recipe recipe = (Recipe) object;
                Intent intent = new Intent(context,ContentActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("RECIPE",recipe);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
