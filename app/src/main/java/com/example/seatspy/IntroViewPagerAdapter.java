package com.example.seatspy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class IntroViewPagerAdapter extends PagerAdapter {
    Context context;
    List<IntroScreenItem> itemList;

    public IntroViewPagerAdapter(Context context, List<IntroScreenItem> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layoutScreen = inflater.inflate(R.layout.layout_screen,null);
        ImageView img;
        TextView title,description;
        img = layoutScreen.findViewById(R.id.imgscreen);
        title  = layoutScreen.findViewById(R.id.titlescreen);
        description = layoutScreen.findViewById(R.id.descriptionscreen);

//        Toast.makeText(context, "Title"+itemList.get(position).getTitle(), Toast.LENGTH_SHORT).show();
//        Toast.makeText(context, "Description"+itemList.get(position).getDescription(), Toast.LENGTH_SHORT).show();
//        Toast.makeText(context, "Image Resource"+itemList.get(position).getScreenimage(), Toast.LENGTH_SHORT).show();
        title.setText(itemList.get(position).getTitle());
        description.setText(itemList.get(position).getDescription());
        img.setImageResource(itemList.get(position).getScreenimage());
        container.addView(layoutScreen);
        return layoutScreen;
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
