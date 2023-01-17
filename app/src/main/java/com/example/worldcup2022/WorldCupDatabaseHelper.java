package com.example.worldcup2022;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class WorldCupDatabaseHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "World Cup";
    public static final int DB_VERION = 1;

    public WorldCupDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE TEAMS (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NAME INTEGER," +
                "FLAG_ID INTEGER," +
                "GROUP_NAME INTEGER," +
                "POINTS INTEGER);");

        insertTeam(db, R.string.Netherlands, R.drawable.netherlands_flag, R.string.Group_A, 7);
        insertTeam(db, R.string.Senegal, R.drawable.senegal_flag, R.string.Group_A, 6);
        insertTeam(db, R.string.Ecuador, R.drawable.ecuador_flag, R.string.Group_A, 4);
        insertTeam(db, R.string.Qatar, R.drawable.qatar_flag, R.string.Group_A, 0);

        insertTeam(db, R.string.England, R.drawable.england_flag, R.string.Group_B, 7);
        insertTeam(db, R.string.United_States, R.drawable.united_states_flag, R.string.Group_B, 5);
        insertTeam(db, R.string.Iran, R.drawable.iran_flag, R.string.Group_B, 3);
        insertTeam(db, R.string.Wales, R.drawable.wales_flag, R.string.Group_B, 1);

        insertTeam(db, R.string.Argentina, R.drawable.argentina_flag, R.string.Group_C, 6);
        insertTeam(db, R.string.Poland, R.drawable.poland_flag, R.string.Group_C, 4);
        insertTeam(db, R.string.Mexico, R.drawable.mexico_flag, R.string.Group_C, 4);
        insertTeam(db, R.string.Saudi_Arabia, R.drawable.saudi_arabia_flag, R.string.Group_C, 3);

        insertTeam(db, R.string.France, R.drawable.france_flag, R.string.Group_D, 6);
        insertTeam(db, R.string.Australia, R.drawable.australia_flag, R.string.Group_D, 6);
        insertTeam(db, R.string.Tunisia, R.drawable.tunisia_flag, R.string.Group_D, 4);
        insertTeam(db, R.string.Denmark, R.drawable.denmark_flag, R.string.Group_D, 1);

        insertTeam(db, R.string.Japan, R.drawable.japan_flag, R.string.Group_E, 6);
        insertTeam(db, R.string.Spain, R.drawable.spain_flag, R.string.Group_E, 4);
        insertTeam(db, R.string.Germany, R.drawable.germany_flag, R.string.Group_E, 4);
        insertTeam(db, R.string.Costa_Rica, R.drawable.costa_rica_flag, R.string.Group_E, 3);

        insertTeam(db, R.string.Morocco, R.drawable.morocco_flag, R.string.Group_F, 7);
        insertTeam(db, R.string.Croatia, R.drawable.croatia_flag, R.string.Group_F, 5);
        insertTeam(db, R.string.Belgium, R.drawable.belgium_flag, R.string.Group_F, 4);
        insertTeam(db, R.string.Canada, R.drawable.canada_flag, R.string.Group_F, 0);

        insertTeam(db, R.string.Brazil, R.drawable.brazil_flag, R.string.Group_G, 6);
        insertTeam(db, R.string.Switzerland, R.drawable.switzerland_flag, R.string.Group_G, 6);
        insertTeam(db, R.string.Cameroon, R.drawable.cameroon_flag, R.string.Group_G, 4);
        insertTeam(db, R.string.Serbia, R.drawable.serbia_flag, R.string.Group_G, 1);

        insertTeam(db, R.string.Portugal, R.drawable.portugal_flag, R.string.Group_H, 6);
        insertTeam(db, R.string.South_Korea, R.drawable.south_korea_flag, R.string.Group_H, 4);
        insertTeam(db, R.string.Uruguay, R.drawable.uruguay_flag, R.string.Group_H, 4);
        insertTeam(db, R.string.Ghana, R.drawable.ghana_flag, R.string.Group_H, 3);

        db.execSQL("CREATE TABLE FORMATIONS (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NAME INTEGER," +
                "FORMATION INTEGER)"
        );
        insertFormation(db, R.string.Netherlands, R.drawable.netheralandsformat);
        insertFormation(db, R.string.Senegal, R.drawable.senegalformat);
        insertFormation(db, R.string.Ecuador, R.drawable.ecuadorformat);
        insertFormation(db, R.string.Qatar, R.drawable.qatarformat);

        insertFormation(db, R.string.England, R.drawable.englandformat);
        insertFormation(db, R.string.United_States, R.drawable.usaformat);
        insertFormation(db, R.string.Iran, R.drawable.iranformat);
        insertFormation(db, R.string.Wales, R.drawable.walesformat);

        insertFormation(db, R.string.Argentina, R.drawable.argentinaformat);
        insertFormation(db, R.string.Poland, R.drawable.polandformat);
        insertFormation(db, R.string.Mexico, R.drawable.mexicoformat);
        insertFormation(db, R.string.Saudi_Arabia, R.drawable.saudiarabiaformat);

        insertFormation(db, R.string.France, R.drawable.franceformat);
        insertFormation(db, R.string.Australia, R.drawable.australiaformat);
        insertFormation(db, R.string.Tunisia, R.drawable.tunisiaformat);
        insertFormation(db, R.string.Denmark, R.drawable.denmarkformat);

        insertFormation(db, R.string.Japan, R.drawable.japanformat);
        insertFormation(db, R.string.Spain, R.drawable.spainformat);
        insertFormation(db, R.string.Germany, R.drawable.germanyformat);
        insertFormation(db, R.string.Costa_Rica, R.drawable.costaricaformat);

        insertFormation(db, R.string.Morocco, R.drawable.morocooformat);
        insertFormation(db, R.string.Croatia, R.drawable.croatiaformat);
        insertFormation(db, R.string.Belgium, R.drawable.belgiumformat);
        insertFormation(db, R.string.Canada, R.drawable.canadaformation);

        insertFormation(db, R.string.Brazil, R.drawable.brazilformat);
        insertFormation(db, R.string.Switzerland, R.drawable.switzerlandformat);
        insertFormation(db, R.string.Cameroon, R.drawable.cameroonformat);
        insertFormation(db, R.string.Serbia, R.drawable.serbiaformat);

        insertFormation(db, R.string.Portugal, R.drawable.portugalformat);
        insertFormation(db, R.string.South_Korea, R.drawable.southkoreaformat);
        insertFormation(db, R.string.Uruguay, R.drawable.uruguayformat);
        insertFormation(db, R.string.Ghana, R.drawable.ghanaformat);

        db.execSQL("CREATE TABLE STADIUMS (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NAME INTEGER," +
                "IMAGE INTEGER," +
                "LOCATION INTEGER, " +
                "LAT REAl," +
                "LONG REAL)");

        insertStadium(db, R.string.AL_Bayt, R.drawable.al_bayt, R.string.location1, 25.65240911560781, 51.487803514846306);
        insertStadium(db, R.string.Lusail, R.drawable.lusail, R.string.location2, 25.420986355148777, 51.490719569052764);
        insertStadium(db, R.string.Ahmad_Bin_Ali, R.drawable.ahmad_bin_ali, R.string.location3, 25.329835471734665, 51.34243641212336);
        insertStadium(db, R.string.Al_Janoub, R.drawable.al_janoub, R.string.location4, 25.159994073085876, 51.57430015443111);
        insertStadium(db, R.string.Al_Thumama, R.drawable.al_thumama, R.string.location5, 25.235421106143395, 51.5324750986176);
        insertStadium(db, R.string.Education_City, R.drawable.education_city, R.string.location6, 25.310904769359375, 51.42447581322827);
        insertStadium(db, R.string.Khalifa_International, R.drawable.khalifa_international, R.string.location7, 25.263785839676952, 51.44835016905064);
        insertStadium(db, R.string.Stadium_974, R.drawable.stadium_974, R.string.location1, 25.289119497769384, 51.566670284392565);

        db.execSQL("CREATE TABLE MATCHES (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "TEAM1 INTEGER," +
                "TEAM1_IMAGE INTEGER," +
                "TEAM2 INTEGER," +
                "TEAM2_IMAGE INTEGER," +
                "DATE TEXT," +
                "TIME TEXT," +
                "PLAYED INTEGER," +
                "TEAM1_SCORE INTEGER," +
                "TEAM2_SCORE INTEGER," +
                "STADIUM_NAME INTEGER," +
                "FOREIGN KEY(TEAM1) REFERENCES TEAMS(NAME)," +
                "FOREIGN KEY(TEAM2) REFERENCES TEAMS(NAME)," +
                "FOREIGN KEY(STADIUM_NAME) REFERENCES STADIUMS(NAME))");

        insertMatch(db, R.string.Netherlands, R.drawable.netherlands_flag, R.string.United_States, R.drawable.united_states_flag, "2022-12-3", "17:00:00", 1, 3, 1, R.string.Khalifa_International);
        insertMatch(db, R.string.Argentina, R.drawable.argentina_flag, R.string.Australia, R.drawable.australia_flag, "2022-12-3", "21:00:00", 1, 2, 1, R.string.Ahmad_Bin_Ali);

        insertMatch(db, R.string.France, R.drawable.france_flag, R.string.Poland, R.drawable.poland_flag, "2022-12-4", "17:00:00", 1, 3, 1, R.string.Al_Thumama);
        insertMatch(db, R.string.England, R.drawable.england_flag, R.string.Senegal, R.drawable.senegal_flag, "2022-12-4", "21:00:00", 1, 3, 0, R.string.AL_Bayt);

        insertMatch(db, R.string.Japan, R.drawable.japan_flag, R.string.Croatia, R.drawable.croatia_flag, "2022-12-5", "17:00:00", 1, 0, 0, R.string.Al_Janoub);
        insertMatch(db, R.string.Brazil, R.drawable.brazil_flag, R.string.South_Korea, R.drawable.south_korea_flag, "2022-12-5", "21:00:00", 1, 4, 1, R.string.Stadium_974);

        insertMatch(db, R.string.Morocco, R.drawable.morocco_flag, R.string.Spain, R.drawable.spain_flag, "2022-12-6", "17:00:00", 1, 0, 0, R.string.Education_City);
        insertMatch(db, R.string.Portugal, R.drawable.portugal_flag, R.string.Switzerland, R.drawable.switzerland_flag, "2022-12-6", "21:00:00", 1, 6, 1, R.string.Lusail);

        insertMatch(db, R.string.Croatia, R.drawable.croatia_flag, R.string.Brazil, R.drawable.brazil_flag, "2022-12-9", "17:00:00", 0, 0, 0, R.string.Education_City);
        insertMatch(db, R.string.Netherlands, R.drawable.netherlands_flag, R.string.Argentina, R.drawable.argentina_flag, "2022-12-9", "21:00:00", 0, 0, 0, R.string.Lusail);

        insertMatch(db, R.string.Morocco, R.drawable.morocco_flag, R.string.Portugal, R.drawable.portugal_flag, "2022-12-10", "17:00:00", 0, 0, 0, R.string.Al_Thumama);
        insertMatch(db, R.string.England, R.drawable.england_flag, R.string.France, R.drawable.france_flag, "2022-12-10", "21:00:00", 0, 0, 0, R.string.AL_Bayt);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    private static void insertFormation(SQLiteDatabase db, int name,  int format) {
        ContentValues teamValues = new ContentValues();
        teamValues.put("NAME", name);
        teamValues.put("FORMATION", format);
        db.insert("FORMATIONS", null, teamValues);
    }

    private static void insertTeam(SQLiteDatabase db, int name,  int flag_id, int group_name, int points) {
        ContentValues teamValues = new ContentValues();
        teamValues.put("NAME", name);
        teamValues.put("FLAG_ID", flag_id);
        teamValues.put("GROUP_NAME", group_name);
        teamValues.put("POINTS", points);
        db.insert("TEAMS", null, teamValues);
    }

    private static void insertMatch(SQLiteDatabase db, int team1, int t1image,  int team2, int t2image, String date, String time, int played, int team1_score, int team2_score, int stadium_name) {
        ContentValues matchValues = new ContentValues();
        matchValues.put("TEAM1", team1);
        matchValues.put("TEAM1_IMAGE", t1image);
        matchValues.put("TEAM2", team2);
        matchValues.put("TEAM2_IMAGE", t2image);
        matchValues.put("DATE", date);
        matchValues.put("TIME", time);
        matchValues.put("PLAYED", played);
        matchValues.put("TEAM1_SCORE", team1_score);
        matchValues.put("TEAM2_SCORE", team2_score);
        matchValues.put("STADIUM_NAME", stadium_name);
        db.insert("MATCHES", null, matchValues);
    }

    private static void insertStadium(SQLiteDatabase db, int name,  int image, int location, double lat, double lng) {
        ContentValues matchValues = new ContentValues();
        matchValues.put("NAME", name);
        matchValues.put("IMAGE", image);
        matchValues.put("LOCATION", location);
        matchValues.put("LAT", lat);
        matchValues.put("LONG", lng);
        db.insert("STADIUMS", null, matchValues);
    }
}
