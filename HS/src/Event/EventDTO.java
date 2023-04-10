package Event;

import java.sql.Date;

public class EventDTO {
	private String eventName;
	private String eventPlace;
	private Date eventDate;
	private String eventTime;
	private String eventCategory;
	private int eventmaxPer;
	private String eventOrg;
	
	public String getEventTime() {
		return eventTime;
	}
	public void setEventTime(String eventTime) {
		this.eventTime = eventTime;
	}
	
	public String getEventOrg() {
		return eventOrg;
	}

	public int getEventmaxPer() {
		return eventmaxPer;
	}
	public void setEventmaxPer(int eventmaxPer) {
		this.eventmaxPer = eventmaxPer;
	}
	public void setEventOrg(String eventOrg) {
		this.eventOrg = eventOrg;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getEventPlace() {
		return eventPlace;
	}
	public void setEventPlace(String eventPlace) {
		this.eventPlace = eventPlace;
	}
	public Date getEventDate() {
		return eventDate;
	}
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}
	public String getEventCategory() {
		return eventCategory;
	}
	public void setEventCategory(String eventCategory) {
		this.eventCategory = eventCategory;
	}
	
}
