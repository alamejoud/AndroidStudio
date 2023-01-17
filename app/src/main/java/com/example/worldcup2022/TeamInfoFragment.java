package com.example.worldcup2022;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class TeamInfoFragment extends Fragment {
    private int ID;
    private TextView TeamName;
    private ImageView Lineup;

    private SQLiteDatabase db;
    private SQLiteOpenHelper worldCupOpenHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        worldCupOpenHelper = new WorldCupDatabaseHelper(getContext());
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_team_info, container, false);
    }

    @Override
    public void onStart(){
        super.onStart();
        View view = getView();

        if (view == null){
            return;
        }

        try {
            db = worldCupOpenHelper.getReadableDatabase();

        } catch (SQLException e) {
            Toast toast = Toast.makeText(getActivity(), "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }

        Cursor cursor = db.query("FORMATIONS", new String[]{"NAME", "FORMATION"}, null,
                null, null, null, null);

        TeamName = view.findViewById(R.id.Team_Name);
        Lineup = view.findViewById(R.id.Screenshot);

        TeamName.setText(this.ID);

        int i = 0;
        while (cursor.moveToNext()) {
            int nameIndex = cursor.getColumnIndex("NAME");
            int imgIndex = cursor.getColumnIndex("FORMATION");

            if (getString(cursor.getInt(nameIndex)) == TeamName.getText()){
                Lineup.setImageResource(cursor.getInt(imgIndex));
                break;
            }
            i++;
        }
        cursor.close();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public void setID(int id) {
        this.ID = id;
    }
}