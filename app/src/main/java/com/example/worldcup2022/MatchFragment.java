package com.example.worldcup2022;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;



public class MatchFragment extends Fragment {

    private WorldCupDatabaseHelper worldCupDatabaseHelper;
    private SQLiteDatabase db;
    private Cursor cursor;
    MatchRecyclerViewAdapter mMatchAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        worldCupDatabaseHelper = new WorldCupDatabaseHelper(getContext());

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_match, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        View view = getView();
        if (view != null) {
            try {
                db = worldCupDatabaseHelper.getReadableDatabase();

            } catch (SQLException e) {
                Toast toast = Toast.makeText(getActivity(), "Database unavailable", Toast.LENGTH_SHORT);
                toast.show();
            }

            cursor = db.query("MATCHES", new String[]{"_id", "TEAM1", "TEAM1_SCORE", "TEAM1_IMAGE", "TEAM2", "TEAM2_SCORE", "TEAM2_IMAGE", "DATE", "TIME"}, null, null, null, null, null);

            mMatchAdapter = new MatchRecyclerViewAdapter(getActivity(), cursor);

            RecyclerView recyclerViewMatches = view.findViewById(R.id.RecyclerView_matches);
            RecyclerView recyclerViewMatches2 = view.findViewById(R.id.RecyclerView_matches2);

            if (recyclerViewMatches != null) {
                recyclerViewMatches.setLayoutManager(new LinearLayoutManager(getActivity()));
                recyclerViewMatches.setAdapter(mMatchAdapter);
            }

            if (recyclerViewMatches2 != null) {
                recyclerViewMatches2.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                recyclerViewMatches2.setAdapter(mMatchAdapter);
            }

        }

    }

}