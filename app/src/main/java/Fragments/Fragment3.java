package Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.incupad_task2.R;

import java.util.ArrayList;
import java.util.Arrays;

import Adapters.TabOneAdapter;
import Adapters.TabThreeAdapter;
import Adapters.TabTwoAdapter;
import Model.ItemDataModel;

public class Fragment3 extends Fragment {

    TabThreeAdapter adapter;
    public Fragment3() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_1, container, false);
        ArrayList<ItemDataModel> namesList3 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            // Generating random name
            String name = "Name" + (i + 1);
            // Creating MyObject instance with fixed properties and random name
            ItemDataModel obj = new ItemDataModel("Like", true, name);
            namesList3.add(obj);
        }
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new TabThreeAdapter(getContext(),namesList3);
        recyclerView.setAdapter(adapter);

        return view;
    }

    public TabThreeAdapter getAdapter(){
        return adapter;
    }
}