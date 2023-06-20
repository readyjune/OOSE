package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a collection of Reminders.
 */
public class ReminderList {
    private List<Reminder> reminders;
    private List<ReminderObserver> obs = new ArrayList<>();

    public ReminderList() {
        reminders = new ArrayList<>();
    }

    /** Add a single reminder to the list. */
    public void addReminder(Reminder rem) {
        reminders.add(rem);
        notifyObservers();

    }

    /** Add a complete list of reminders to the existing list. */
    public void addReminders(List<Reminder> newReminders) {
        reminders.addAll(newReminders);
        notifyObservers();
    }

    /** Remove a reminder by index (i.e. 0 to #reminders-1) */
    public void removeReminder(int index) {
        reminders.remove(index);
        notifyObservers();
    }

    /** Retrieve a copy of the reminder list. */
    public List<Reminder> getReminders() {
        return Collections.unmodifiableList(reminders);
    }

    public void addObserver(ReminderObserver ob) {
        obs.add(ob);
    }

    public void removeObserver(ReminderObserver ob) {
        obs.remove(ob);
    }

    public void notifyObservers() {
        for (ReminderObserver ob : obs) {
            ob.RemindersUpdated(this);
        }
    }
}
