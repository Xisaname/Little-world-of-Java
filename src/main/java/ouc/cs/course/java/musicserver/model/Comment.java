package ouc.cs.course.java.musicserver.model;

public class Comment {

	private int id;
	private int userid;
	private String uuid;
	private String date;
	private String username;
	private String content;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Comment(int userid, String uuid, String date, String username, String content) {
		super();
		this.userid = userid;
		this.uuid = uuid;
		this.date = date;
		this.username = username;
		this.content = content;
	}
	public Comment() {};
	
}
