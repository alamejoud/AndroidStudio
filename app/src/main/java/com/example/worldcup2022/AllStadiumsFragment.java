package com.example.worldcup2022;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cursoradapter.widget.SimpleCursorAdapter;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;

public class AllStadiumsFragment extends ListFragment {

    static interface Listener2 {
        void itemClicked(int id);
    }

    private WorldCupDatabaseHelper worldCupDatabaseHelper;
    private SQLiteDatabase db;
    private Cursor cursor;
    private Listener2 listener;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        worldCupDatabaseHelper = new WorldCupDatabaseHelper(getContext());

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_all_stadiums, container, false);
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

            cursor = db.query("STADIUMS", new String[]{"_id", "NAME"}, null, null, null, null, null);

            String[] stadiums = new String[cursor.getCount()];

            int i = 0;
            while (cursor.moveToNext()) {

                stadiums[i] = getString(cursor.getInt(cursor.getColumnIndexOrThrow("NAME")));

                i++;

            }

            ArrayAdapter<String> listAdapter = new ArrayAdapter<>(getView().getContext(),
                    R.layout.stadium_text, R.id.stadium_text, stadiums);

            setListAdapter(listAdapter);

        }

    }

    public void onAttach(Context context) {
        super.onAttach(context);
        this.listener = (Listener2) context;
    }

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        if (listener != null) {
            listener.itemClicked(position);
        }
    }
}