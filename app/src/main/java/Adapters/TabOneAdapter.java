package Adapters;

import static android.app.PendingIntent.getActivity;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.incupad_task2.MyAdapter;
import com.example.incupad_task2.R;

import java.util.ArrayList;
import java.util.List;

import Model.ItemDataModel;

public class TabOneAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    static private List<ItemDataModel> originalData;
    private static List<ItemDataModel> filteredData;

    public TabOneAdapter(Context context, List<ItemDataModel> data) {
        this.context = context;
        originalData = data;
        filteredData = new ArrayList<>(data);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_item, parent, false);

        return new TabOneAdapter.myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TabOneAdapter.myViewHolder holder1 = (TabOneAdapter.myViewHolder) holder;
        holder1.bind(filteredData.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return filteredData.size();
    }

    private static class myViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_title);
            imageView = itemView.findViewById(R.id.dots);

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int n = getAbsoluteAdapterPosition();
                    Log.d("n --------->", String.valueOf(n));
                    showPopupMenu(v , n);
                }
            });
        }

        void bind(String text) {
            textView.setText(text);
        }
    }

    public void filter(String query) {
        filteredData.clear();
        if (TextUtils.isEmpty(query)) {
            filteredData.addAll(originalData);
        } else {
            for (ItemDataModel item : originalData) {
                if (item.getName().toLowerCase().contains(query.toLowerCase())) {
                    filteredData.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }

    private static void showPopupMenu(View view , int n) {
        PopupMenu popupMenu = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            popupMenu = new PopupMenu(view.getContext(), view, Gravity.RIGHT);
        }
        popupMenu.inflate(R.menu.three_dot_menu);

        // Get the position of the clicked item in the RecyclerView
        int position = n;

        // Get the title dynamically based on the position or data associated with the item
        String title = getTitleForItem(position); // Implement this method according to your logic

        popupMenu.getMenu().getItem(0).setTitle(title);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Log.d("item title ->>", item.getTitle().toString());
                if(item.getItemId() == R.id.like_dislike){
                    if(title.equals("Like")){
                        item.setTitle("Dislike");
                        filteredData.get(position).setStatus("Dislike");
                        originalData.get(position).setStatus("Dislike");
                        Log.d("item title ->>", item.getTitle().toString());
                    }
                    else{
                        item.setTitle("Like");
                        filteredData.get(position).setStatus("Like");
                        originalData.get(position).setStatus("Like");
                        Log.d("item title ->>", item.getTitle().toString());
                    }
                    Log.d("item title ->>", item.getTitle().toString());
                    return true;
                }
                else if(item.getItemId() == R.id.fav){
                    return true;
                }
                else{
                    return false;
                }
            }
        });
        popupMenu.show();
    }

    private static String getTitleForItem(int position) {
        return filteredData.get(position).getStatus().toString();
    }


}
