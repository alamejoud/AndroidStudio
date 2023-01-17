package com.example.worldcup2022;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import de.hdodenhof.circleimageview.CircleImageView;

public class GroupFragment extends Fragment {

    private int groupId;
    private SQLiteDatabase db;
    private Cursor cursor;
    private SQLiteOpenHelper worldCupOpenHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        worldCupOpenHelper = new WorldCupDatabaseHelper(getContext());

        if (savedInstanceState != null) {
            groupId = savedInstanceState.getInt("GROUP_ID");
        }

        return inflater.inflate(R.layout.fragment_group, container, false);

    }

    @Override
    public void onStart() {
        super.onStart();
        View view = getView();
        if (view != null) {
            try {
                db = worldCupOpenHelper.getReadableDatabase();

            } catch (SQLException e) {
                Toast toast = Toast.makeText(getActivity(), "Database unavailable", Toast.LENGTH_SHORT);
                toast.show();
            }
            cursor = db.query("TEAMS", new String[] {"NAME", "FLAG_ID", "POINTS"}, "GROUP_NAME = ?" ,
                    new String[]{Integer.toString(groupId)}, null, null, null);

            CircleImageView[] img = new CircleImageView[4];

            img[0] = view.findViewById(R.id.img1);
            img[1] = view.findViewById(R.id.img2);
            img[2] = view.findViewById(R.id.img3);
            img[3] = view.findViewById(R.id.img4);

            TextView[] name = new TextView[4];

            name[0] = view.findViewById(R.id.name1);
            name[1] = view.findViewById(R.id.name2);
            name[2] = view.findViewById(R.id.name3);
            name[3] = view.findViewById(R.id.name4);

            TextView[] score = new TextView[4];

            score[0] = view.findViewById(R.id.score1);
            score[1] = view.findViewById(R.id.score2);
            score[2] = view.findViewById(R.id.score3);
            score[3] = view.findViewById(R.id.score4);

            int i = 0;
            while(cursor.moveToNext()) {

                int imgIndex = cursor.getColumnIndexOrThrow("FLAG_ID");
                img[i].setImageResource(cursor.getInt(imgIndex));

                int nameIndex = cursor.getColumnIndexOrThrow("NAME");
                name[i].setText(getString(cursor.getInt(nameIndex)));

                int scoreIndex = cursor.getColumnIndexOrThrow("POINTS");
                score[i].setText(Integer.toString(cursor.getInt(scoreIndex)));

                i ++;

            }

        }

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("GROUP_ID", groupId);
    }

    public void setGroupId(int id) {
        this.groupId = id;
    }
}