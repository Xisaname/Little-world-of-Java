package ouc.cs.course.java.musicserver.model;

public class Collections {

	private String uuid;
	private String md5value;
	private String userId;
	private String musicname;
	private String sheetname;
	private String creatorname;
	private String datecreated;
	
	public Collections() {
	}
	
	public Collections(String uuid, String md5value, String userId) {
		super();
		this.uuid = uuid;
		this.md5value = md5value;
		this.userId = userId;
	}

	public Collections(String uuid, String md5value, String userId, String musicname, String sheetname,
			String creatorname, String datecreated) {
		super();
		this.uuid = uuid;
		this.md5value = md5value;
		this.userId = userId;
		this.musicname = musicname;
		this.sheetname = sheetname;
		this.creatorname = creatorname;
		this.datecreated = datecreated;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getMd5value() {
		return md5value;
	}

	public void setMd5value(String md5value) {
		this.md5value = md5value;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMusicname() {
		return musicname;
	}

	public void setMusicname(String musicname) {
		this.musicname = musicname;
	}

	public String getSheetname() {
		return sheetname;
	}

	public void setSheetname(String sheetname) {
		this.sheetname = sheetname;
	}

	public String getCreatorname() {
		return creatorname;
	}

	public void setCreatorname(String creatorname) {
		this.creatorname = creatorname;
	}

	public String getDatecreated() {
		return datecreated;
	}

	public void setDatecreated(String datecreated) {
		this.datecreated = datecreated;
	}
	
	
	
}
