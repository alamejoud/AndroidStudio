package com.example.worldcup2022;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import de.hdodenhof.circleimageview.CircleImageView;

public class MatchRecyclerViewAdapter extends RecyclerView.Adapter<MatchRecyclerViewAdapter.MatchesViewHolder> {
    private static final String TAG = "DrinkCategoriesRecycler";
    private Context mContext;
    private Cursor mCursor;

    public MatchRecyclerViewAdapter(Context context, Cursor cursor) {
        mContext = context;
        mCursor = cursor;
    }

    public class MatchesViewHolder extends RecyclerView.ViewHolder {
        public TextView Home;
        public TextView Home_Score;
        public TextView Away;
        public TextView Away_Score;
        public TextView date;
        public TextView time;
        public CircleImageView Img1;
        public CircleImageView Img2;

        public MatchesViewHolder(View itemView) {
            super(itemView);
            Home = itemView.findViewById(R.id.home);
            Home_Score = itemView.findViewById(R.id.home_score);
            Away = itemView.findViewById(R.id.away);
            Away_Score = itemView.findViewById(R.id.away_score);
            date = itemView.findViewById(R.id.date);
            time = itemView.findViewById(R.id.time);
            Img1 = itemView.findViewById(R.id.img1);
            Img2 = itemView.findViewById(R.id.img2);

        }

    }
    @Override
    public MatchesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.match_list_view, parent, false);
        return new MatchesViewHolder(view);
    }
    @Override
    public void onBindViewHolder(MatchesViewHolder holder, int position) {
        if (!mCursor.moveToPosition(position)) {
            return;
        }

        int home = mCursor.getInt(mCursor.getColumnIndexOrThrow("TEAM1"));
        int home_score = mCursor.getInt(mCursor.getColumnIndexOrThrow("TEAM1_SCORE"));
        int away = mCursor.getInt(mCursor.getColumnIndexOrThrow("TEAM2"));
        int away_score = mCursor.getInt(mCursor.getColumnIndexOrThrow("TEAM2_SCORE"));
        String date1 = mCursor.getString(mCursor.getColumnIndexOrThrow("DATE"));
        String time1 = mCursor.getString(mCursor.getColumnIndexOrThrow("TIME"));
        int img1 = mCursor.getInt(mCursor.getColumnIndexOrThrow("TEAM1_IMAGE"));
        int img2 = mCursor.getInt(mCursor.getColumnIndexOrThrow("TEAM2_IMAGE"));

        holder.Home.setText(mContext.getString(home));
        holder.Home_Score.setText(Integer.toString(home_score));
        holder.Away.setText(mContext.getString(away));
        holder.Away_Score.setText(Integer.toString(away_score));
        holder.date.setText(date1);
        holder.time.setText(time1);
        holder.Img1.setImageResource(img1);
        holder.Img2.setImageResource(img2);

    }
    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    //You can use this method whenever you want to insert or delete data & refresh content
    //NotifyDataSetChanged: Notify any registered observers of the recyclerview that the data set has changed.
    public void swapCursor(Cursor newCursor) {
        if (mCursor != null) {
            mCursor.close();
        }
        mCursor = newCursor;
        if (newCursor != null) {
            notifyDataSetChanged();
        }
    }
}

