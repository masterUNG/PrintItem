package jp.co.casio.caios.sddatabasecsv;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;


/**
 * Database data output to CSV file
 *
 */
public class ConvertDatabaseToCsv {
	
	private final String DBFOLDER  = "/jp.co.casio.caios.framework.database/databases";
	private final String CSVFOLDER = "/csv";
	private String[] itemNameStrings, qtyStrings, amtStrings;
	
	/**
	 * Read SD database, convert to CSV and write to SD
	 * SD file: /csv/xxxx.csv
	 * @param dbName  DB file name (Ex.SUMMARY.DB)
	 * @param table   Table name (Ex.CSS012)
	 * @param condition  Search condition (Ex.BIZDATE='20130701')
	 * @param csvName CSV file name (Ex.data.csv)
	 * @return  CSV data or error message.
	 */
	public String  convertDatabaseToCsv(
			String dbName,
			String table,
			String condition,
			String csvName ){
		
		//
		// Open database on SD
		//
		File dbfile = Environment.getExternalStorageDirectory();
		String dbpath = dbfile.getPath() + DBFOLDER + "/" + dbName;
		// "/mnt/sdcard/jp.co.casio.caios.framework.database/databases/SUMMARY.DB"

		SQLiteDatabase db = null;
		try {
			db = SQLiteDatabase.openDatabase(
					dbpath,
					null, 
					SQLiteDatabase.NO_LOCALIZED_COLLATORS|SQLiteDatabase.OPEN_READONLY);
		} catch(Exception e) {
			return e.getMessage();
		}
		
		//
		// Read database from SD
		//
		Cursor cursor = null;
		String sql = "select * from '" + table + "' where " + condition;
		try {
			cursor = db.rawQuery(sql,null);
		} catch (Exception e){
			//
		}
		if ( cursor == null ){
			db.close();
			return "SQL query error:["+sql+"]";
		}
		
		//
		// Read data and convert to CSV
		//

		//*******************************************************************************

		myCursor(cursor);

		//*******************************************************************************
		StringBuilder csvData = new StringBuilder(sql+"\n");
		cursor.moveToFirst();
		final int column = cursor.getColumnCount();
		if ( cursor.getCount() > 0 ){
			do {
				for ( int c=0; c<column; c++ ){
					String val = cursor.getString(c);
					if ( c != 0 ){
						csvData.append(",");
					}
					csvData.append(val);
				}
				csvData.append("\n");
			} while (cursor.moveToNext());
		}
		cursor.close();
		db.close();
		
		//
		// Write CSV data to SD under "csv" folder.
		//
		final String folderName = 
				Environment.getExternalStorageDirectory().getAbsolutePath() + 
				CSVFOLDER;
		File csvfile = new File(folderName);
		if ( csvfile.exists() == false ) {
			csvfile.mkdir();
		}
		
		OutputStream outputStream;
		final String csvFileName = 
				Environment.getExternalStorageDirectory().getAbsolutePath() + 
				CSVFOLDER + "/" + csvName;
		try {
			csvfile = new File(csvFileName);
			outputStream = new FileOutputStream(csvfile);
		} catch(Exception e) {
			return e.getMessage();
		}

		String cvsDataString = csvData.toString();
		try {
			outputStream.write(cvsDataString.getBytes());
			outputStream.close();
		} catch(Exception e){
			return e.getMessage();
		}
		
		return cvsDataString;
	}

	private void myCursor(Cursor cursor) {

		itemNameStrings = new String[cursor.getCount()];
		cursor.moveToFirst();

		for (int i=0;i<cursor.getCount();i++) {

			itemNameStrings[i] = cursor.getString(cursor.getColumnIndex("TIEMNAME"));

			cursor.moveToNext();

		}	// for


	}	// myCursor

	public String[] shareArray(int intColumn) {

		String[] strResult = new String[itemNameStrings.length];
		for (int i=0;i<itemNameStrings.length;i++) {
			strResult[i] = itemNameStrings[i];
		}

		return strResult;
	}


}	// Main Class
