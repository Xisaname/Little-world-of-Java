package ouc.cs.course.java.musicserver.model;

public class Rank {

	private String name;
	private String md5value;
	private int times;
	
	public Rank() {
	}
	
	public Rank(String name, String md5value, int times) {
		super();
		this.name = name;
		this.md5value = md5value;
		this.times = times;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMd5value() {
		return md5value;
	}

	public void setMd5value(String md5value) {
		this.md5value = md5value;
	}

	public int getTimes() {
		return times;
	}

	public void setTimes(int times) {
		this.times = times;
	}
}
