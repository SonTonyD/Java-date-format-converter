
public class MyData {
	private int day;
	private int month;
	private int year;
	private String weekday;

	public MyData(int d, int m, int y, String weekday) {
		this.setDay(d);
		this.setMonth(m);
		this.setYear(y);
		this.setWeekday(weekday);
	}
	
	public String toString() {
		String str = this.getYear() + " " + this.getMonth() + " " + this.getDay();
		return str;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getWeekday() {
		return weekday;
	}

	public void setWeekday(String weekday) {
		this.weekday = weekday;
	}

}
