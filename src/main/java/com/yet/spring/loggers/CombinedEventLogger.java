package com.yet.spring.loggers;

import com.yet.spring.beans.Event;

import java.util.Collection;

public class CombinedEventLogger implements EventLogger {
    Collection<EventLogger> loggers;

    public CombinedEventLogger(Collection<EventLogger> loggers) {
        this.loggers = loggers;
    }

    @Override
    public void logEvent(Event event) {
        for (EventLogger eventLogger :
                loggers) {
            eventLogger.logEvent(event);
        }
    }
}
