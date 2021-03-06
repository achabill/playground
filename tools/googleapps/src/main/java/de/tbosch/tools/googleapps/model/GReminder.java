package de.tbosch.tools.googleapps.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.time.DateUtils;

import com.google.api.services.calendar.model.EventReminder;

@Entity
@Table(name = "reminder")
public class GReminder implements Comparable<GReminder> {

	@Id
	@GeneratedValue
	private long id;

	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private Date time;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@NotNull
	private GCalendarEvent event;

	public GReminder() {
	}

	public GReminder(EventReminder reminder, GCalendarEvent event) {
		this.event = event;
		if (reminder.getMinutes() != null) {
			this.time = DateUtils.addMinutes(event.getStartTime(), -1 * reminder.getMinutes());
		} else {
			throw new IllegalStateException("reminder must have a time");
		}
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the time
	 */
	public Date getTime() {
		return time;
	}

	/**
	 * @param time
	 *            the time to set
	 */
	public void setTime(Date time) {
		this.time = time;
	}

	/**
	 * @return the event
	 */
	public GCalendarEvent getEvent() {
		return event;
	}

	/**
	 * @param event
	 *            the event to set
	 */
	public void setEvent(GCalendarEvent event) {
		this.event = event;
	}

	/**
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(GReminder o) {
		return time.compareTo(o.getTime());
	}

}
