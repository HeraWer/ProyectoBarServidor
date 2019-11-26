package tools;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;

public class DataFormat {

	public static String getSQLTimestampFormat(Timestamp ts) {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(ts);
	}
	
	public static Timestamp addHours(Timestamp ts, int hours) {
		return Timestamp.from(ts.toInstant().plus(hours, ChronoUnit.HOURS));
	}
	
}
