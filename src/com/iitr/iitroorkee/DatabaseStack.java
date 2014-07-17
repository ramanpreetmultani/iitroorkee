package com.iitr.iitroorkee;

import java.util.ArrayList;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseStack extends SQLiteOpenHelper {


	public DatabaseStack(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "iit_roorkee";

	//Table Name
	private static final String TABLE_EVENT = "calender";

	// Contacts Table Columns names
	private static final String ID = "id";
	private static final String s_DAY_OF_MONTH = "s_day_of_month";
	private static final String s_MONTH = "s_month";
	private static final String s_YEAR = "s_year";
	private static final String e_DAY_OF_MONTH = "e_day_of_month";
	private static final String e_MONTH = "e_month";
	private static final String e_YEAR = "e_year";
	private static final String DETAIL = "detail";
	private static final String s_DAY = "s_day";
	private static final String e_DAY = "e_day";
	
	private static final String CREATE_DEVICE_TABLE = "CREATE TABLE " + TABLE_EVENT + "("
			+ ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + s_DAY_OF_MONTH + " INT NOT NULL,"
			+ s_MONTH + " TEXT NOT NULL," + s_YEAR + " INT NOT NULL, " + s_DAY + " TEXT NOT NULL, "
			+ e_DAY_OF_MONTH + " INT ,"
			+ e_MONTH + " TEXT ," + e_YEAR + " INT , " + e_DAY + " TEXT , "
			+ DETAIL + " TEXT NOT NULL" + ")";

	//private static final String _MODIFY = "ALTER TABLE " + TABLE_DEVICE + " ADD " + KEY_STATE + " INTEGER " ;
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		//db.execSQL("DROP TABLE devices");
		db.execSQL(CREATE_DEVICE_TABLE);

	}



	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		//db.execSQL("DROP TABLE IF EXISTS " + TABLE_DEVICE);
		//db.execSQL(_MODIFY);

		// Create tables again
		onCreate(db);

	}

	public void addEvent(int day_of_month,String month,int year,String day,String detail) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(s_DAY_OF_MONTH, day_of_month); 
        values.put(s_MONTH, month); 
        values.put(s_YEAR, year);
        values.put(s_DAY,day);
        values.put(DETAIL, detail);
        
        // Inserting Row
        db.insert(TABLE_EVENT, null, values);
        db.close(); // Closing database connection
      
    }
	
	public void addEvent(int day_of_month,String month,int year,String day,
			int e_day_of_month,String e_month,int e_year,String e_day,String detail) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(s_DAY_OF_MONTH, day_of_month); 
        values.put(s_MONTH, month); 
        values.put(s_YEAR, year);
        values.put(s_DAY,day);
        values.put(e_DAY_OF_MONTH, e_day_of_month); 
        values.put(e_MONTH, e_month); 
        values.put(e_YEAR, e_year);
        values.put(e_DAY,e_day);
        values.put(DETAIL, detail);
        
        // Inserting Row
        db.insert(TABLE_EVENT, null, values);
        db.close(); // Closing database connection
      
    }

    public int getDeviceCount() {
        String countQuery = "SELECT  * FROM " + TABLE_EVENT;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        return cursor.getCount();
    }
 

    public void deleteAll(){
    	SQLiteDatabase database = this.getReadableDatabase();
    	database.delete(TABLE_EVENT, null, null);
    	
    }
    
   /* public void delete(String mac){
    	SQLiteDatabase database = this.getReadableDatabase();
    	database.delete(TABLE_DEVICE, KEY_MAC+"="+mac, null);
    }
    
    public void toggleSwitch(Integer i, Integer WHERE_ID){
    	SQLiteDatabase database = this.getReadableDatabase();
    	database.execSQL("UPDATE " + TABLE_DEVICE + " SET " + KEY_STATE + " = " + i + " WHERE " + " KEY_ID = "+ WHERE_ID);

    }
    public void setNetworkState(Integer i, Integer WHERE_ID){
    	SQLiteDatabase database = this.getReadableDatabase();
    	//Log.d("WIFI","UPDATE " + TABLE_DEVICE + " SET " + KEY_NETWORK_STATE + " = " + i + " WHERE " +  KEY_ID +"= "+ WHERE_ID);
    	database.execSQL("UPDATE " + TABLE_DEVICE + " SET " + KEY_NETWORK_STATE + " = " + i + " WHERE " +  KEY_ID +"= "+ WHERE_ID);

    }*/
   /* public int getNetworkState(Integer id){
    	String countQuery = "SELECT  * FROM " + TABLE_DEVICE + " WHERE "+KEY_NETWORK_STATE + " = "+ id;
    	//Log.d("WIFI",countQuery);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        return cursor.getCount();
    }
    */
   /* public String getData(){
    	String col[] = new String[]{KEY_ID,KEY_NAME,KEY_LOC};
    	SQLiteDatabase database = this.getReadableDatabase();
    	Cursor c = database.query(TABLE_EVENT, col, null, null, null, null, null);
    	String result = "";
    	int iID = c.getColumnIndex(KEY_ID);
    	int iName = c.getColumnIndex(KEY_NAME);
    	int iLoc = c.getColumnIndex(KEY_LOC);
    	
    	for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
    		result = result + c.getString(iID) +" "+ c.getString(iName)+ " " + c.getString(iLoc)+ "\n";
    	}
    	
    	return result;
    }
    public ArrayList<String> getDevice(String t){
    	ArrayList<String> list = new ArrayList<String>();
    	String col[] = new String[]{KEY_ID ,KEY_NAME,KEY_MAC,KEY_TYPE};
    	SQLiteDatabase database = this.getReadableDatabase();
    	Cursor c = database.query(TABLE_DEVICE, col,KEY_LOC+"='"+t+"'", null, null, null, null);
    	
    	int iName = c.getColumnIndex(KEY_TYPE);
    	//title = new String[]{ c.getString( 0 ), c.getString( 1 ) };
    	
	    	for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
	    		list.add(c.getString(iName));
	    	}
    	
    	return list;
    	
    }
    
    
    public boolean notPresent(String mac){
    	String col[] = new String[]{KEY_ID,KEY_NAME,KEY_LOC};
    	boolean x=false;
    	SQLiteDatabase database = this.getReadableDatabase();
    	Cursor c = database.query(TABLE_DEVICE, col, KEY_MAC+"='"+mac+"'", null, null, null, null);
    	int i=c.getCount();
    	if(i==0)
    	x=true;
    	return x;
    }
    */
}
    