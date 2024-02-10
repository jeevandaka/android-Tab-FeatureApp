package com.example.incupad_task2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Fragments.Fragment1;
import Fragments.Fragment2;
import Fragments.Fragment3;

public class MainActivity extends AppCompatActivity {
    EditText searchBar;
    int tabPosition = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        ViewPager2 viewPager = findViewById(R.id.viewPager);
        searchBar = findViewById(R.id.search);

        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new Fragment1());
        fragmentList.add(new Fragment2());
        fragmentList.add(new Fragment3());

        ViewPagerAdapter adapter = new ViewPagerAdapter(this, fragmentList);
        viewPager.setAdapter(adapter);


        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Tab 1");
                    break;
                case 1:
                    tab.setText("Tab 2");
                    break;
                case 2:
                    tab.setText("Tab 3");
                    break;
            }
        }).attach();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                tabPosition = position;
                searchBar.setText("");
                Log.d("Tab position --->", String.valueOf(position));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equals("")){
                    return;
                }
                Log.d("search text --->", String.valueOf(charSequence));
                Log.d("Tab position --->", String.valueOf(tabPosition));
                Fragment f = fragmentList.get(tabPosition);
                if(tabPosition == 0){
                    Fragment1 f1 = (Fragment1) f;
                    Log.d("f1 -----> ", String.valueOf(f1));

                    Log.d("Tab Tab Adapter --->", String.valueOf(f1.getAdapter()));
                    f1.getAdapter().filter(String.valueOf(charSequence));
                }else if(tabPosition == 1){
                    Fragment2 f2 = (Fragment2) f;
                    Log.d("Tab Adapter --->", String.valueOf(f2.getAdapter()));
                    f2.getAdapter().filter(String.valueOf(charSequence));
                }
                else{
                    Fragment3 f3 = (Fragment3) f;
                    f3.getAdapter().filter(String.valueOf(charSequence));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }
}