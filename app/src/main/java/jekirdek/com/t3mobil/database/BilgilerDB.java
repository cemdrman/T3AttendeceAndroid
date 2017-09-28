package jekirdek.com.t3mobil.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashMap;

import jekirdek.com.t3mobil.model.User;

/**
 * Created by cem
 */
public class BilgilerDB extends SQLiteOpenHelper {

    private static final int dbVersion = 1;
    private static final String dbName = "bilgiler_db";
    private static final String tblName = "Bilgiler";

    public BilgilerDB(Context context) {
        super(context, dbName, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("db","creating db");
        db.execSQL("CREATE TABLE " + tblName +" (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "userId Integer,name TEXT, surname TEXT, citizenId TEXT, gender TEXT, email TEXT," +
                "password TEXT, phoneNumber TEXT, userType TEXT," +
                "schoolName TEXT, section TEXT, class TEXT, " +
                "grade TEXT)");
        Log.d("db","created db");
    }

    public void saveUser(User user){
        Log.d("db","user saving");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("userId",user.getId());
        values.put("name",user.getName());
        values.put("surname",user.getSurname());
        values.put("citizenId",user.getCitizenId());
        values.put("gender",user.getGender());
        values.put("email",user.getEmail());
        values.put("password",user.getPassword());
        values.put("phoneNumber",user.getPhoneNumber());
        values.put("userType",user.getUserType());
        values.put("schoolName",user.getSchoolName());
        values.put("section",user.getSection());
        values.put("class",user.getClas());
        values.put("grade",user.getGrade());
        db.insert(tblName, null, values);
        db.close();
        Log.d("db","user saved");
    }

    public User getUser(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + tblName;
        Cursor cursor = db.rawQuery(query,null);
        User user = new User();
        cursor.moveToFirst();
        user.setId(cursor.getInt(1));
        user.setName(cursor.getString(2));
        user.setSurname(cursor.getString(3));
        user.setCitizenId(cursor.getString(4));
        user.setGender(cursor.getString(5));
        user.setEmail(cursor.getString(6));
        user.setPassword(cursor.getString(7));
        user.setPhoneNumber(cursor.getString(8));
        user.setUserType(cursor.getString(9));
        user.setSchoolName(cursor.getString(10));
        user.setSection(cursor.getString(11));
        user.setClas(cursor.getString(12));
        user.setGrade(cursor.getString(13));

        return user;
    }

    public int getRowCount(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + tblName;
        Cursor cursor = db.rawQuery(query,null);
        int rowCount = cursor.getCount();
        cursor.close();
        return rowCount;
    }

    public void resetTable(){
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete(tblName,null,null);
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}