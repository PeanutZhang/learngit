package com.example.demo.activitys;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.effect.EffectFactory;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EdgeEffect;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.demo.R;
import com.example.demo.utils.DateUtil;
import com.example.demo.views.PullRecyclerView;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.OVER_SCROLL_IF_CONTENT_SCROLLS;

public class ConstrainsActivity extends AppCompatActivity {

    private ArrayList<String> datas;
    private ProgressBar progressBar;
    private boolean isPullDowning;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constrains);
        datas = new ArrayList<>();
        for (int i = 50; i < 80; i++) {
            datas.add(" item = "+i);
        }
        // 1585635324689 14.13
        //1554011840000 57 2019
//        1585633820000 50 20
        //1585547420000 3. 30
        // 1585375040000 3.28
        // 1553752640000 2019
        Log.e("zyh","1 "+ DateUtil.format2MsgTime(1585635324689L)+" 2  "+ DateUtil.format2MsgTime(1585633820000L)+
                " 3 "+DateUtil.format2MsgTime(1585547420000L)+ " 4 "+DateUtil.format2MsgTime(1585375040000L)+" 5  "+DateUtil.format2MsgTime(1553752640000L)
                );


        PullRecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        CMyAdapter adapter = new CMyAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.smoothScrollToPosition(datas.size()-1);
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.onDraw(c, parent, state);
                Paint paint = new Paint();
                paint.setColor(Color.BLACK);
                paint.setAntiAlias(true);
                paint.setStyle(Paint.Style.FILL);
                int childCount = parent.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    View child = parent.getChildAt(i);
                    int top = child.getBottom();
                    int bottom = top+5;
                    c.drawRect(0,top,child.getWidth(),bottom,paint);
                }
            }

            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
            }
        });
        recyclerView.setEdgeEffectFactory(new RecyclerView.EdgeEffectFactory());

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
//                Log.e("zyh","onscrolled dy "+dy);
//                if(recyclerView.canScrollVertically(-1)){//检测是否还能向下滑动
//                    Log.e("zyh","未滑动到顶"+ dy);
//                }else {
//                    Log.e("zyh","滑动到顶 "+ dy);
//                }
//                if(recyclerView.canScrollVertically(1)){//检测向上滑动
//                    Log.e("zyh","未滑动到底 "+dy);
//                }else {
//                    Log.e("zyh","滑动到底 "+dy);
//                }
            }

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
//               RecyclerView.SCROLL_STATE_IDLE 0
//                RecyclerView.SCROLL_STATE_DRAGGING 1
//                RecyclerView.SCROLL_STATE_SETTLING 2
//                Log.e("zyh","newState  "+newState+"" );
            }
        });

      findViewById(R.id.add).setOnClickListener(v->{
               datas.addAll(createD());
               adapter.notifyDataSetChanged();
               recyclerView.smoothScrollToPosition(datas.size()-1);
      });
      findViewById(R.id.add2).setOnClickListener(v->{
          datas.addAll(0 ,createH());
          adapter.notifyDataSetChanged();
      });
      findViewById(R.id.scroll).setOnClickListener(v -> {
          recyclerView.scrollToPosition(datas.size()-1);
      });
        progressBar = findViewById(R.id.progress);
        progressBar.setProgress(60);
        recyclerView.addPullListener(new PullRecyclerView.PullScrollListener() {
            @Override
            public void onPullDown() {

                if(!isPullDowning){
                    isPullDowning = true;
                    progressBar.setVisibility(View.VISIBLE);
                    //加载数据 模拟
                    new Thread(new Runnable() {
                        @Override
                        public void run() {

                            try {
                                Thread.sleep(5 * 1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            runOnUiThread(()->{
                                progressBar.setVisibility(View.GONE);
                               datas.addAll(0,createH());
                               adapter.notifyDataSetChanged();
                               isPullDowning = false;

                            });

                        }
                    }).start();

                }

            }

            @Override
            public void onPullUp() {

            }
        });
    }
    int count= 80;
    private List<String> createD(){
        ArrayList<String> datas = new ArrayList<>();
        for (int i = count; i < count+8; i++) {
            datas.add("item = "+i);
        }
        count+=7;
        return datas;

    }

    int minCount = 50;

    private List<String> createH(){
        ArrayList<String> datas = new ArrayList<>();
        for (int i = minCount - 8 ; i < minCount+1; i++) {
           datas.add("item = "+i);
        }
        minCount -=7;
        return datas;
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
//               viewHolder.textView.setOnClickListener(v -> Log.e("zyh","pos - >  "+position));
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
