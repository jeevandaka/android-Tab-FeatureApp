package Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.incupad_task2.R;

import java.util.ArrayList;
import java.util.Arrays;

import Adapters.TabOneAdapter;
import Model.ItemDataModel;

public class Fragment1 extends Fragment {
    TabOneAdapter adapter;
    private static final int MENU_LIKE_DISLIKE = 1;
    private static final int MENU_FAV = 2;
    public Fragment1() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_1, container, false);
        ArrayList<ItemDataModel> namesList1 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            // Generating random name
            String name = "Name" + (i + 1);
            // Creating MyObject instance with fixed properties and random name
            ItemDataModel obj = new ItemDataModel("Like", true, name);
            namesList1.add(obj);
        }
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new TabOneAdapter(getContext(),namesList1);
        recyclerView.setAdapter(adapter);
        registerForContextMenu(recyclerView);
        return view;
    }

    @Override
    public boolean onContextItemSelected(MenuItem item){
        if(item.getItemId()==R.id.like_dislike){
            //handle click here
            return true; //handled
        }
        return false; // not handled
    }

    public TabOneAdapter getAdapter(){
        return adapter;
    }
}