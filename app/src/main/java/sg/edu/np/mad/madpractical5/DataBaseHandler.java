package sg.edu.np.mad.madpractical5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataBaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "UserDB.db";
    public static final String TABLE_USERS = "users";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_FOLLOWED = "followed";

    public DataBaseHandler(Context context, String database_name, SQLiteDatabase.CursorFactory factory, int database_version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE =
                "CREATE TABLE " + TABLE_USERS + "(" +
                        COLUMN_NAME + " TEXT," +
                        COLUMN_DESCRIPTION + " TEXT," +
                        COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        COLUMN_FOLLOWED + " BOOLEAN" + ")";

        db.execSQL(CREATE_USERS_TABLE);
    }

    public void onReset(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    public ArrayList<User> getUsers() {
        String query =
                "SELECT * FROM " + TABLE_USERS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        ArrayList<User> userList = new ArrayList<>();

        while (cursor.moveToNext()) {
            User user = new User();
            user.setName(cursor.getString(0));
            user.setDescription(cursor.getString(1));
            user.setId(cursor.getInt(2));
            if (cursor.getInt(3) == 1) {
                user.setFollowed(true);
            } else user.setFollowed(false);

            userList.add(user);
        }
        cursor.close();

        db.close();
        return userList;
    }

    public void addUser(User user) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, user.getName());
        values.put(COLUMN_DESCRIPTION, user.getDescription());
        values.put(COLUMN_FOLLOWED, user.getFollowed());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_USERS, null, values);
        db.close();
    }

    public void updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_FOLLOWED, user.getFollowed() ? 1:0);

        db.update(TABLE_USERS, values, COLUMN_ID + " = ?", new String[] {String.valueOf(user.getId())});
        db.close();
    }

    public User findUser(String userName) {
        String query =
                "SELECT * FROM " + TABLE_USERS +
                        "WHERE" +
                        COLUMN_NAME + " = \"" + userName + "\"";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        User user = new User();

        if (cursor.moveToFirst()) {
            user.setName(cursor.getString(0));
            user.setDescription(cursor.getString(1));
            user.setId(Integer.parseInt(cursor.getString(2)));
            user.setFollowed(Boolean.parseBoolean(cursor.getString(3)));

            cursor.close();
        } else {
            user = null;
        }
        db.close();
        return user;
    }

    public boolean deleteUser(String userName) {
        boolean result = false;

        String query =
                "SELECT * FROM " + TABLE_USERS +
                        "WHERE" +
                        COLUMN_NAME + " = \"" + userName + "\"";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        User user = new User();

        if (cursor.moveToFirst()) {
            user.setId(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_USERS, COLUMN_ID + " = ?", new String[] { String.valueOf(user.getId()) });
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }

    public void createNewUserData() {
        for (int i = 0; i < 20; i++) {
            String name = String.format("Name%s", new Random().nextInt(1000000));
            String description = String.format("Description %s", new Random().nextInt(100000000));
            boolean followed = new Random().nextBoolean();

            User user = new User(name, description, followed);
            addUser(user);
        }
    }
}