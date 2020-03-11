package com.example.demo.activitys;

import android.media.effect.EffectFactory;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EdgeEffect;
import android.widget.TextView;

import com.example.demo.R;

import java.util.ArrayList;

public class ConstrainsActivity extends AppCompatActivity {

    private ArrayList<String> datas;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constrains);

        datas = new ArrayList<>();
        for (int i = 0; i < 38; i++) {
            datas.add(" item = "+i);
        }

       RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        CMyAdapter adapter = new CMyAdapter();
        recyclerView.setAdapter(adapter);
        RecyclerView.EdgeEffectFactory edgeEffectFactory = new RecyclerView.EdgeEffectFactory();

        recyclerView.setEdgeEffectFactory(new RecyclerView.EdgeEffectFactory());
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.e("rec","-scrolllistener->   " +" dy = "+ dy);
            }
        });
//        recyclerView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
//            @Override
//            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//                Log.e("rec","onScrollChange-- "+" scrollY= "+scrollY + " oldScrollY+ "+oldScrollY);
//            }
//        });

    }


    class CMyAdapter extends RecyclerView.Adapter<ViewHolder>{

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View itemv = LayoutInflater.from(ConstrainsActivity.this).inflate(R.layout.layout_item,viewGroup,false);
            return new ViewHolder(itemv);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
               viewHolder.textView.setText(datas.get(position));
        }

        @Override
        public int getItemCount() {
            return datas.size();
        }
    }


    class  ViewHolder extends RecyclerView.ViewHolder{
      TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView  = itemView.findViewById(R.id.tv);
        }
    }


}
