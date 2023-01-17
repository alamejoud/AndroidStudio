package com.example.worldcup2022;

import android.content.Context;
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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;


public class HomeFragment extends Fragment {

    static interface Listener {
        void buttonClicked(int id);
    }

    private Listener listener;
    private Button[] buttons;
    private SQLiteOpenHelper worldCupOpenHelper;
    private SQLiteDatabase db;
    private Cursor cursor;

    private TextView t1a;
    private TextView t2a;
    private TextView sa;
    private CircleImageView i1a;
    private CircleImageView i2a;

    private TextView t1b;
    private TextView t2b;
    private TextView sb;
    private CircleImageView i1b;
    private CircleImageView i2b;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        buttons = new Button[8];

        worldCupOpenHelper = new WorldCupDatabaseHelper(getContext());

        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        View view = getView();
        if (view != null) {

            buttons[0] = view.findViewById(R.id.group_a_button);
            buttons[1] = view.findViewById(R.id.group_b_button);
            buttons[2] = view.findViewById(R.id.group_c_button);
            buttons[3] = view.findViewById(R.id.group_d_button);
            buttons[4] = view.findViewById(R.id.group_e_button);
            buttons[5] = view.findViewById(R.id.group_f_button);
            buttons[6] = view.findViewById(R.id.group_g_button);
            buttons[7] = view.findViewById(R.id.group_h_button);

            try {
                db = worldCupOpenHelper.getReadableDatabase();

            } catch (SQLException e) {
                Toast toast = Toast.makeText(getActivity(), "Database unavailable", Toast.LENGTH_SHORT);
                toast.show();
            }

            Calendar now = Calendar.getInstance();

            String time = now.get(Calendar.YEAR) + "-" + (now.get(Calendar.MONTH) + 1) + "-" + now.get(Calendar.DAY_OF_MONTH);


            cursor = db.rawQuery("SELECT TEAM1, TEAM1_IMAGE, TEAM2, TEAM2_IMAGE, TEAM1_SCORE, TEAM2_SCORE, DATE, TIME FROM MATCHES WHERE DATE = ?" , new String[] {time});

            t1a = view.findViewById(R.id.team1a);
            t2a = view.findViewById(R.id.team2a);
            sa = view.findViewById(R.id.scoreA);
            i1a = view.findViewById(R.id.img1a);
            i2a = view.findViewById(R.id.img2a);

            t1b = view.findViewById(R.id.team1b);
            t2b = view.findViewById(R.id.team2b);
            sb = view.findViewById(R.id.scoreB);
            i1b = view.findViewById(R.id.img1b);
            i2b = view.findViewById(R.id.img2b);

            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();

                t1a.setText(cursor.getInt(cursor.getColumnIndexOrThrow("TEAM1")));
                t2a.setText(cursor.getInt(cursor.getColumnIndexOrThrow("TEAM2")));
                String[] time1 = cursor.getString(cursor.getColumnIndexOrThrow("TIME")).split(":");
                if (Integer.parseInt(time1[0]) < now.get(Calendar.HOUR_OF_DAY) + 1) {
                    sa.setText(cursor.getInt(cursor.getColumnIndexOrThrow("TEAM1_SCORE")) + ":" + cursor.getInt(cursor.getColumnIndexOrThrow("TEAM2_SCORE")));
                }
                else {
                    sa.setText(time1[0] + ":" + time1[1]);
                }
                i1a.setImageResource(cursor.getInt(cursor.getColumnIndexOrThrow("TEAM1_IMAGE")));
                i2a.setImageResource(cursor.getInt(cursor.getColumnIndexOrThrow("TEAM2_IMAGE")));

                cursor.moveToNext();

                t1b.setText(cursor.getInt(cursor.getColumnIndexOrThrow("TEAM1")));
                t2b.setText(cursor.getInt(cursor.getColumnIndexOrThrow("TEAM2")));
                String[] time2 = cursor.getString(cursor.getColumnIndexOrThrow("TIME")).split(":");
                if (Integer.parseInt(time2[0]) < now.get(Calendar.HOUR_OF_DAY) + 1) {
                    sb.setText(cursor.getInt(cursor.getColumnIndexOrThrow("TEAM1_SCORE")) + ":" + cursor.getInt(cursor.getColumnIndexOrThrow("TEAM2_SCORE")));
                }
                else {
                    sb.setText(time2[0] + ":" + time2[1]);
                }
                i1b.setImageResource(cursor.getInt(cursor.getColumnIndexOrThrow("TEAM1_IMAGE")));
                i2b.setImageResource(cursor.getInt(cursor.getColumnIndexOrThrow("TEAM2_IMAGE")));

            }

            else {
                t1a.setText(null);
                t2a.setText(null);
                t1b.setText(null);
                t2b.setText(null);
                i1a.setImageDrawable(null);
                i2a.setImageDrawable(null);
                i1b.setImageDrawable(null);
                i2b.setImageDrawable(null);
                sa.setText(getString(R.string.No_Matches));
                sb.setText(getString(R.string.No_Matches));
            }

            buttons[0].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onButtonClick(R.string.Group_A);
                }
            });

            buttons[1].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onButtonClick(R.string.Group_B);
                }
            });

            buttons[2].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onButtonClick(R.string.Group_C);
                }
            });

            buttons[3].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onButtonClick(R.string.Group_D);
                }
            });

            buttons[4].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onButtonClick(R.string.Group_E);
                }
            });

            buttons[5].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onButtonClick(R.string.Group_F);
                }
            });

            buttons[6].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onButtonClick(R.string.Group_G);
                }
            });

            buttons[7].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onButtonClick(R.string.Group_H);
                }
            });

        }

    }

    private void onButtonClick(int id) {
        if (listener != null) {
            listener.buttonClicked(id);
        }
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        this.listener = (Listener) context;
    }

}