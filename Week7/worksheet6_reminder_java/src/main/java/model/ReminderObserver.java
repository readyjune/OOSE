package model;

/**
 * Represents a collection of Reminders.
 */
public interface ReminderObserver {
    public void RemindersUpdated(ReminderList reminderList);
}
