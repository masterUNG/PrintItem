package jp.co.casio.caios.sddatabasecsv;

import android.app.IntentService;
import android.content.Intent;


/**
 * Database csv convert service (background app)
 */
public class DatabaseCsvService extends IntentService {
	
	public DatabaseCsvService() {
		super("GetService");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		String bizDate  = intent.getStringExtra(BroadcastReceiver.EXTRA_BIZDATE);
		String zcounter = intent.getStringExtra(BroadcastReceiver.EXTRA_ZCOUNTER);
		
		convertCSS012( bizDate, zcounter);
		convertCSS014( bizDate, zcounter);
		convertCSS017( bizDate, zcounter);
		
	}
	
	
	/**
	 * Convert Fixed Totalizer data to CSV
	 * @param bizDate
	 * @param zcounter
	 */
	private void convertCSS012( String bizDate, String zcounter){
		final String dbName	= "SUMMARY.DB";			// Target DB
		final String table	= "CSS012";				// Fixed Totalizer
		
		// Condition (BIZDATE='20130701' and ZCOUNTER='000001')
		String condition= "BIZDATE='"+bizDate+"' and "+"ZCOUNTER='"+zcounter+"'";
		// CSV file name (CSS012_20130701_000001.CSV)
		String csvName	= table+"_"+bizDate+"_"+zcounter+".CSV";
		
		ConvertDatabaseToCsv convert = new ConvertDatabaseToCsv();
		convert.convertDatabaseToCsv(dbName,table,condition,csvName);
	}

	/**
	 * Convert PLU data to CSV
	 * @param bizDate
	 * @param zcounter
	 */
	private void convertCSS014( String bizDate, String zcounter){
		final String dbName	= "SUMMARY.DB";			// Target DB
		final String table	= "CSS014";				// PLU
		
		// Condition (BIZDATE='20130701' and ZCOUNTER='000001')
		String condition= "BIZDATE='"+bizDate+"' and "+"ZCOUNTER='"+zcounter+"'";
		// CSV file name (CSS014_20130701_000001.CSV)
		String csvName	= table+"_"+bizDate+"_"+zcounter+".CSV";
		
		ConvertDatabaseToCsv convert = new ConvertDatabaseToCsv();
		convert.convertDatabaseToCsv(dbName,table,condition,csvName);
	}
	
	/**
	 * Convert Clerk data to CSV
	 * @param bizDate
	 * @param zcounter
	 */
	private void convertCSS017( String bizDate, String zcounter){
		final String dbName	= "SUMMARY.DB";			// Target DB
		final String table	= "CSS017";				// Clerk
		
		// Condition (BIZDATE='20130701' and ZCOUNTER='000001')
		String condition= "BIZDATE='"+bizDate+"' and "+"ZCOUNTER='"+zcounter+"'";
		// CSV file name (CSS014_20130701_000001.CSV)
		String csvName	= table+"_"+bizDate+"_"+zcounter+".CSV";
		
		ConvertDatabaseToCsv convert = new ConvertDatabaseToCsv();
		convert.convertDatabaseToCsv(dbName,table,condition,csvName);
	}

	
}
