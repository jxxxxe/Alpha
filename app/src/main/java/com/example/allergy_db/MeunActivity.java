package com.example.allergy_db;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.example.allergy_db.category.MainActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MeunActivity extends AppCompatActivity {

    Intent intent;
    private Long restaurantId;
    private String restaurantName;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Menu> arrayList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    private static final int MY_PERMISSION_REQUEST_LOCATION = 0;
    private WebView webView;
    private Button button1;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        intent = getIntent();/***/
        restaurantId = intent.getLongExtra("restaurantId", 0); /**인텐트에서 넘겨준 레스토랑id 받기*/
        restaurantName = intent.getStringExtra("restaurantName");

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<>();
        button1=(Button)findViewById(R.id.button1);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(webView.getVisibility() == View.VISIBLE){
                    button1.setText("주변 가게 검색(펼치기)");
                    webView.setVisibility(View.GONE);
                }else{
                    webView.setVisibility(View.VISIBLE);
                    button1.setText("주변 가게 검색(접기)");
                }
            }
        });

        webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true); // 자바스크립트 사용을 허용한다.
        webView.setWebViewClient(new WebViewClient());  // 새로운 창을 띄우지 않고 내부에서 웹뷰를 실행시킨다.
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
                super.onGeolocationPermissionsShowPrompt(origin, callback);
                callback.invoke(origin, true, false);
            }
        });
        webView.loadUrl("https://m.map.kakao.com/actions/searchView?q=" + restaurantName);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Menu");
        Query query = databaseReference.orderByChild("restId").equalTo(restaurantId); /**레스토랑id를 갖고있는 메뉴를 찾는 쿼리*/
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {//반복문으로 데이터 리스트 추출
                    Menu menu = snapshot.getValue(Menu.class);
                    arrayList.add(menu);
                }
                adapter.notifyDataSetChanged();//리스트 저장 및 새로고침
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Log.e("MainActivity", String.valueOf(databaseError.toException()));//에러문 출력
            }
        });

        adapter = new CustomAdapter(arrayList, this);
        recyclerView.setAdapter(adapter);//리사이클러뷰에 어댑터 연결

    }

}