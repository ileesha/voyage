package com.example.mahe.voyage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "TravelApp.db";
    private static final String LOGIN_TABLE = "Login";
    private static final String USERS_TABLE = "Users";
    private static final String PLACE_TABLE = "Place";
    private static final String PLACE_NAME = "Pname";
    private static final String PLACE_ID = "PID";
    private static final String PLACE_DISTANCE = "Pdistance";
    private static final String PLACE_COORDINATES = "PCoordinates";
    private static final String PLACE_INFO = "Pinfo";
    private static final String LOGIN_UNAME = "Lu_name";
    private static final String LOGIN_UPASS = "Lu_pass";
    private static final String USERS_UID ="Uu_id";
    private static final String USERS_UNAME = "Uuname";
    private static final String USERS_PASS = "Uupass";
    private static final String USERS_FNAME = "Ufname";
    private static final String USERS_LNAME = "Ulname";
    private static final String USERS_PHNUM = "Uphnum";
    private static final String USERS_EMAIL = "Uemail";
    private static final String TABLE_WISH_LIST = "Wish_List";

    private int id = 1001;
    private static final int DB_VERSION = 1;

    private SQLiteDatabase sqdb;

    public MyDBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

        sqdb = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        UsersTableCreate(db);
        LoginTableCreate(db);
        palcesTableCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqdb, int oldVersion, int newVersion) {
        sqdb.execSQL("DROP TABLE IF EXISTS " + LOGIN_TABLE);
        onCreate(sqdb);
    }

    public String searchUpass(String s) {
        Cursor cursor = sqdb.rawQuery("SELECT * FROM LOGIN WHERE Lu_name=?;", new String[]{s});
        if (cursor.getCount() < 1) {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String password = cursor.getString(cursor.getColumnIndex(LOGIN_UPASS));
        cursor.close();
        return password;
    }

    public void createWshList(SQLiteDatabase db, String place)
    {
        String query = "CREATE TABLE " + TABLE_WISH_LIST + "(" +
                PLACE_NAME + " TEXT);";

        db.execSQL(query);
        insertIntoWishlist(db,place);

    }

    public void insertIntoWishlist(SQLiteDatabase db, String place)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(PLACE_NAME, 1);
        db.insert(TABLE_WISH_LIST,null,contentValues);
    }

    public String searchWishlist()
    {
        String str = "SELECT " +PLACE_NAME + " FROM " + TABLE_WISH_LIST + ");";
        Cursor cursor = sqdb.rawQuery(str,null);
        if (cursor.getCount() < 1) {
            cursor.close();
            return "NO PLACES YET!";
        }
        String name="";
        do {
            cursor.moveToFirst();
            name = name + cursor.getString(cursor.getColumnIndex((PLACE_NAME))) + "\n";
        }while(cursor.moveToNext());
        cursor.close();
        return name;
    }

    public String searchPlaces(int placeid) {
        Cursor cursor = sqdb.rawQuery("SELECT " + PLACE_NAME + " FROM " + PLACE_TABLE + " WHERE " + PLACE_ID + " =?; ", new String[]{placeid+""});
        if (cursor.getCount() < 1) {
            cursor.close();
            return "Enter a correct place";
        }
        cursor.moveToFirst();
        String name = cursor.getString(cursor.getColumnIndex((PLACE_NAME)));
        cursor.close();
        return name;

    }
    public int getDistance(int source, int destination)
    {
        String sql = "SELECT SUM("+ PLACE_DISTANCE + ") AS dist FROM " + PLACE_TABLE + " WHERE " +
                PLACE_ID + " > " + source + " and " + PLACE_ID + " <= " + destination + ";";
        Cursor cursor = sqdb.rawQuery(sql, new String[] {});

        if(cursor.getCount() < 1)
        {
            cursor.close();
            return 0;
        }
        cursor.moveToFirst();
        int ans = cursor.getInt(cursor.getColumnIndex("dist"));
        Log.i("MyDBHelperMessage", ans+" = ans");
        cursor.close();
        return ans;
    }

    public String getPlaceCoordinates(String placeName) {
    String query = "SELECT " + PLACE_COORDINATES + " FROM " + PLACE_TABLE + " WHERE " + PLACE_NAME + " =?;";
        Cursor cursor = sqdb.rawQuery(query, new String[] {placeName});

        if(cursor.getCount() < 1)
        {
            cursor.close();
            return "";
        }
        cursor.moveToFirst();
        String coordinates = cursor.getString(cursor.getColumnIndex(PLACE_COORDINATES));
        Log.i("MyDBHelperMessage", coordinates);
        cursor.close();
        return coordinates;
    }

    public String[] getPlaceInfo(String[] placeName)
    {
        String[] info=new String[5];
        Cursor cursor=null;
        //String i = "";
        int j=-1;
        for(String i:placeName)
        {
            j++;
            String sql = "SELECT " + PLACE_INFO + " FROM " + PLACE_TABLE + " WHERE " + PLACE_NAME + "=? ;";
             cursor = sqdb.rawQuery(sql, new String[]{i});
            if (cursor.getCount() < 1) {
                cursor.close();
            }
            else{
                cursor.moveToFirst();
                info[j] = cursor.getString(cursor.getColumnIndex(PLACE_INFO));
                Log.i("MyDBHelperMessage",  cursor.getString(cursor.getColumnIndex(PLACE_INFO)));
            }
        }
        for(int i = j+1;i<5;i++)
        {
            placeName[i] = ".";
        }
        cursor.close();
        return info;
    }

    public int searchID(String name)
    {  
        Cursor cursor = sqdb.rawQuery("SELECT "+PLACE_ID + " FROM " + PLACE_TABLE + " WHERE " + PLACE_NAME + " =?; ", new String[]{name});
        if (cursor.getCount() < 1) {
            cursor.close();
            return -1;
        }

        cursor.moveToFirst();
        int info = cursor.getInt(cursor.getColumnIndex((PLACE_ID)));
        cursor.close();
        return info;
    }

    public void palcesTableCreate(SQLiteDatabase db)
    {
        String Placequery = "CREATE TABLE " + PLACE_TABLE + "(" +
                PLACE_ID + " INTEGER PRIMARY KEY, "+
                PLACE_NAME + " TEXT UNIQUE, " +
                PLACE_INFO + " TEXT," +
                PLACE_DISTANCE+ " INTEGER, "+
                PLACE_COORDINATES + " TEXT );";
        db.execSQL(Placequery);
        insertIntoPlaceTable(db);
    }

    public void insertIntoPlaceTable(SQLiteDatabase db)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(PLACE_ID, 1);
        contentValues.put(PLACE_NAME, "Karwar");
        contentValues.put(PLACE_INFO, "Karwar is a city in Uttara Kannada district in the South Indian state of Karnataka and " +
                "the administrative centre of Uttara Kannada district. Karwar lies on the west coast of Southern India at the mouth " +
                "of the Kali river. Its geography creates a natural harbour with protection against monsoon weather. Being a port town, " +
                "Karwar is a centre for agriculture, manufacturing and tourism.");
        contentValues.put(PLACE_DISTANCE, 0);
        contentValues.put(PLACE_COORDINATES, "14.821217,74.152007");
        db.insert(PLACE_TABLE, null, contentValues);

        Log.i("MyDBHelperMessage", "Karwar inserted into the table");

        contentValues.put(PLACE_ID, 2);
        contentValues.put(PLACE_NAME, "Gokarna");
        contentValues.put(PLACE_INFO, "Gokarna is a small temple town on the western coast of India in the Kumta taluk of " +
                "Uttara Kannada district of the state of Karnataka. The main temple and deity is Lord Shiva, who is also known" +
                " as Mahabhaleshwara. This temple houses what is believed to be original image of Lord Shiva's lingam (Atmalinga). " +
                "There are many other temples all over this small town. Ankola and Kumta on Highway 17 are the main towns near Gokarna.");
        contentValues.put(PLACE_DISTANCE, 60.7);
        contentValues.put(PLACE_COORDINATES, "24.044112,88.114557");
        db.insert(PLACE_TABLE, null, contentValues);

        Log.i("MyDBHelperMessage", "Gokarna inserted into the table");

        contentValues.put(PLACE_ID, 3);
        contentValues.put(PLACE_NAME, "Kumta");
        contentValues.put(PLACE_INFO, "Kumta is a town and a taluk in the Uttara Kannada district of Karnataka, India. " +
                "Kumta is about 142 km south of Margao and 58 km north of Bhatkal. It is situated 72.7 km from Karwar, the district headquarters. " +
                "It is one of the important stations along the Konkan Railway line running between Mumbai and Mangalore. " +
                "Kumta is well connected through Konkan railways, which has frequent trains plying from Delhi, Mumbai, Ahmedabad, Mangalore, " +
                "Thiruvananthapuram, Margao, Bhopal, Karwar towards Mangalore, Bengaluru, Mysore, Ernakulam and also vice versa.");
        contentValues.put(PLACE_DISTANCE, 32.2);
        contentValues.put(PLACE_COORDINATES, "14.432498,74.423203");
        db.insert(PLACE_TABLE, null, contentValues);

        Log.i("MyDBHelperMessage", "Kumta inserted into the table");

        contentValues.put(PLACE_ID, 4);
        contentValues.put(PLACE_NAME, "Honnavar");
        contentValues.put(PLACE_INFO, "Honavar or Honnavar is a port town in Uttara Kannada district of Karnataka, India." +
                " The town is the headquarters of Honnavar Taluk. The Konkan Railway line passes through Honnavar, " +
                "and its longest bridge is 2.065 km in length and is in Honnavar, over the Sharavati River.");
        contentValues.put(PLACE_DISTANCE, 20.1);
        contentValues.put(PLACE_COORDINATES, "14.284808,74.443667");
        db.insert(PLACE_TABLE, null, contentValues);

        Log.i("MyDBHelperMessage", "Honnavar inserted into the table");

        contentValues.put(PLACE_ID, 5);
        contentValues.put(PLACE_NAME, "Murudeshvar");
        contentValues.put(PLACE_INFO, "Murdeshwara is a town in Bhatkal Taluk of Uttara Kannada " +
                "district in the state of Karnataka, India. Murudeshwara\"' is another name of the Hindu god Shiva. " +
                "Famous for the world's second-tallest Shiva statue, the town lies on the coast of the " +
                "Arabian Sea and is also famous for the Murdeshwar Temple. Murdeshwar has a railway station on the" +
                " Mangalore-Mumbai Konkan railway route.");
        contentValues.put(PLACE_DISTANCE, 26.4);
        contentValues.put(PLACE_COORDINATES, "14.087535,74.491403");
        db.insert(PLACE_TABLE, null, contentValues);

        Log.i("MyDBHelperMessage", "Murudeshwar inserted into the table");
    }

    private void LoginTableCreate(SQLiteDatabase db) {
        String Loginquery = "CREATE TABLE " + LOGIN_TABLE + "(" +
                LOGIN_UNAME + " TEXT PRIMARY KEY, " +
                LOGIN_UPASS + " TEXT," +
                "FOREIGN KEY(" + LOGIN_UNAME + ") REFERENCES " + USERS_TABLE + "(" + USERS_UNAME + ") );";

        db.execSQL(Loginquery);
    }

    private void loginInsert(Users users) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(LOGIN_UNAME, users.getU_name());
        contentValues.put(LOGIN_UPASS, users.getU_pass());
        sqdb.insert(LOGIN_TABLE, null, contentValues);
    }

    private void UsersTableCreate(SQLiteDatabase db) {
        String Usersquery = "CREATE TABLE " + USERS_TABLE + "(" +
                USERS_UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                USERS_FNAME + " TEXT, " +
                USERS_LNAME + " TEXT, " +
                USERS_UNAME + " TEXT, " +
                USERS_PASS + " TEXT, " +
                USERS_PHNUM + " TEXT, " +
                USERS_EMAIL + " TEXT " + ");";
        db.execSQL(Usersquery);
    }


    public void usersInsert(Users users)
    {
      String insertQuery = "INSERT INTO " + USERS_TABLE +
                " (" + USERS_FNAME + ", " + USERS_LNAME + ", " + USERS_UNAME + ", " + USERS_PASS + ", "
                + USERS_PHNUM + ", " + USERS_EMAIL +" ) VALUES ( '" + users.getF_name() + "' , '" + users.getL_name()
                + "' , '" + users.getU_name()+"' , '" + users.getU_pass() + "' , '" + users.getPhnumb() + "' , '" + users.getEmail() + "' );";
        loginInsert(users);

        sqdb.execSQL(insertQuery);
    }
}

