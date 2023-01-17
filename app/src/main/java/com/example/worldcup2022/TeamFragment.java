package com.example.worldcup2022;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.util.Size;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class TeamFragment extends Fragment {

    static interface Listener3 {
        void ViewClicked(int ID);
    }

    private Listener3 listener;
    private SQLiteDatabase db;
    private SQLiteOpenHelper worldCupOpenHelper;
    private int[][] IDsRequired;
    private HashMap<String, Integer> List;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        worldCupOpenHelper = new WorldCupDatabaseHelper(getContext());
        return inflater.inflate(R.layout.fragment_team, container, false);
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
        }

        Cursor cursor = db.query("TEAMS", new String[]{"NAME", "FLAG_ID"}, null,
                null, null, null, null);

        CircleImageView[] img = new CircleImageView[cursor.getCount()];
        TextView[] name = new TextView[cursor.getCount()];

        this.IDsRequired = new int[][]{
                {R.id.ateam1, R.id.logo1},
                {R.id.ateam2, R.id.logo2},
                {R.id.ateam3, R.id.logo3},
                {R.id.ateam4, R.id.logo4},
                {R.id.ateam5, R.id.logo5},
                {R.id.ateam6, R.id.logo6},
                {R.id.ateam7, R.id.logo7},
                {R.id.ateam8, R.id.logo8},
                {R.id.bteam1, R.id.logo9},
                {R.id.bteam2, R.id.logo10},
                {R.id.bteam3, R.id.logo11},
                {R.id.bteam4, R.id.logo12},
                {R.id.bteam5, R.id.logo13},
                {R.id.bteam6, R.id.logo14},
                {R.id.bteam7, R.id.logo15},
                {R.id.bteam8, R.id.logo16},
                {R.id.cteam1, R.id.logo17},
                {R.id.cteam2, R.id.logo18},
                {R.id.cteam3, R.id.logo19},
                {R.id.cteam4, R.id.logo20},
                {R.id.cteam5, R.id.logo21},
                {R.id.cteam6, R.id.logo22},
                {R.id.cteam7, R.id.logo23},
                {R.id.cteam8, R.id.logo24},
                {R.id.dteam1, R.id.logo25},
                {R.id.dteam2, R.id.logo26},
                {R.id.dteam3, R.id.logo27},
                {R.id.dteam4, R.id.logo28},
                {R.id.dteam5, R.id.logo29},
                {R.id.dteam6, R.id.logo30},
                {R.id.dteam7, R.id.logo31},
                {R.id.dteam8, R.id.logo32}
        };

        for (int i = 0; i < cursor.getCount(); i++) {
            if (img[i] == null && name[i] == null) {
                assert view != null;
                img[i] = view.findViewById(IDsRequired[i][1]);
                name[i] = view.findViewById(IDsRequired[i][0]);

                name[i].setOnClickListener(View -> Click(View, 1));
            }
        }

        this.List = new HashMap<>();

        int i = 0;
        while (cursor.moveToNext()) {
            int nameIndex = cursor.getColumnIndex("NAME");
            name[i].setText(getString(cursor.getInt(nameIndex)));
            int imgIndex = cursor.getColumnIndex("FLAG_ID");
            assert img[i] != null;
            img[i].setImageResource(cursor.getInt(imgIndex));

            this.List.put((String) name[i].getText(), cursor.getInt(nameIndex));
            i++;
        }
        cursor.close();
    }

    public void Click(View v, int opt) {

        TextView E = (TextView) v;

        for (int[] ints : this.IDsRequired) {
            if (opt == 1) {
                if (ints[0] == v.getId()) {
                    Log.d(TAG, "Click: " + "FOUND");

                    if (this.listener != null) {
                        this.listener.ViewClicked(this.List.get((String) E.getText()));
                    }
                }
            }
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.listener = (Listener3) context;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}