package de.tbosch.tools.googleapps.service;

import java.util.List;

import javax.mail.MessagingException;

import org.hibernate.service.spi.ServiceException;

import de.tbosch.tools.googleapps.exception.GoogleAppsException;
import de.tbosch.tools.googleapps.model.GCalendarEvent;
import de.tbosch.tools.googleapps.model.GEmail;
import de.tbosch.tools.googleapps.model.GReminder;
import de.tbosch.tools.googleapps.service.listeners.ConnectionStatusListener;
import de.tbosch.tools.googleapps.service.listeners.UpdateListener;

public interface GoogleAppsService {

	/**
	 * Gets the calendar from google and saves all entries in local database.
	 * 
	 * @throws GoogleAppsException
	 */
	public void updateCalendar() throws GoogleAppsException;

	/**
	 * All reminders in database. In sort order.
	 * 
	 * @return The list
	 */
	public List<GReminder> getAllReminders();

	/**
	 * All calendar events. In sort order.
	 * 
	 * @return all calendar events.
	 */
	public List<GCalendarEvent> getAllCalendarEvents();

	/**
	 * All calendar events. In sort order.
	 * 
	 * @return all calendar events that start time are after or equal today.
	 */
	public List<GCalendarEvent> getCalendarEventsFromNowOn();

	/**
	 * Sets the authentication information to connect to google service.
	 * 
	 * @throws ServiceException
	 */
	public void connect() throws GoogleAppsException;

	/**
	 * Checks if service is connected to google, i.e. authentication is set.
	 * 
	 * @return connected?
	 */
	public boolean isConnected();

	/**
	 * Resets the authentication information to disconnect from google service.
	 */
	public void disconnect();

	/**
	 * Adds update listeners to the internal set.
	 * 
	 * @param updateListener
	 *            The listener.
	 */
	void addUpdateListener(UpdateListener updateListener);

	/**
	 * Adds status change listeners to the internal set.
	 * 
	 * @param statusListener
	 *            The listener.
	 */
	void addConnectionStatusListener(ConnectionStatusListener statusListener);

	/**
	 * Reads Emails an persists the inbox into DB.
	 * 
	 * @throws MessagingException
	 */
	void updateEmails() throws GoogleAppsException;

	/**
	 * Returns all Emails from Database.
	 * 
	 * @return List.
	 */
	List<GEmail> getEmails();

}