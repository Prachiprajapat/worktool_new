package com.example.worktool_new.demo;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.worktool_new.R;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MyActivity extends AppCompatActivity {
    MyAdapter adapter;
    List<Item> items = new ArrayList();

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_my);
        random10Data();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MyAdapter myAdapter = new MyAdapter(recyclerView, this, this.items);
        this.adapter = myAdapter;
        recyclerView.setAdapter(myAdapter);
        this.adapter.setLoadmore(new Loadmore() {
            public void onloadmore() {
                if (MyActivity.this.items.size() <= 50) {
                    MyActivity.this.items.add(null);
                    MyActivity.this.adapter.notifyItemInserted(MyActivity.this.items.size() - 1);
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            MyActivity.this.items.remove(MyActivity.this.items.size() - 1);
                            MyActivity.this.adapter.notifyItemRemoved(MyActivity.this.items.size());
                            int index = MyActivity.this.items.size();
                            int end = index + 10;
                            for (int i = index; i < end; i++) {
                                MyActivity.this.items.add(new Item(UUID.randomUUID().toString(), i));
                            }
                            MyActivity.this.adapter.notifyDataSetChanged();
                            MyActivity.this.adapter.setLoaded();
                        }
                    }, 5000);
                    return;
                }
                Toast.makeText(MyActivity.this.getApplicationContext(), "end", 1).show();
            }
        });
    }

    private void random10Data() {
        for (int i = 10; i <= 10; i++) {
            this.items.add(new Item(UUID.randomUUID().toString(), i));
        }
    }
}
