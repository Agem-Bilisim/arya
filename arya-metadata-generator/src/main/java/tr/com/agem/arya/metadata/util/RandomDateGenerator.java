package tr.com.agem.arya.metadata.util;

import java.util.GregorianCalendar;

public class RandomDateGenerator {
	
	private int yearBegin;
	private int yearEnd;
	
	public RandomDateGenerator(int yearBegin, int yearEnd) {
		this.yearBegin = yearBegin;
		this.yearEnd = yearEnd;
	}
	
	public String nextDate() {
		
		GregorianCalendar gc = new GregorianCalendar();
		
        int year = randBetween(yearBegin, yearEnd);
        gc.set(GregorianCalendar.YEAR, year);
        
        int dayOfYear = randBetween(1, gc.getActualMaximum(GregorianCalendar.DAY_OF_YEAR));
        gc.set(GregorianCalendar.DAY_OF_YEAR, dayOfYear);
        
        return gc.get(GregorianCalendar.YEAR) + "-" + gc.get(GregorianCalendar.MONTH) + "-" + gc.get(GregorianCalendar.DAY_OF_MONTH);
	}
	
	private int randBetween(int start, int end) {
        return start + (int) Math.round(Math.random() * (end - start));
    }

}
