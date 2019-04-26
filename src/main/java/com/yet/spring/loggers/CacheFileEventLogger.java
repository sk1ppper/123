package com.yet.spring.loggers;

import com.yet.spring.beans.Event;

import java.util.ArrayList;
import java.util.List;

public class CacheFileEventLogger extends FileEventLogger {
    private int cacheSize;
    private List<Event> cache;

    public CacheFileEventLogger(String fileName, int cacheSize) {
        super(fileName);

        this.cacheSize=cacheSize;
        this.cache = new ArrayList<Event>(cacheSize);
    }


    public void logEvent(Event event) {
        cache.add(event);
        if (cache.size()>=cacheSize){
            writeEvents();
            cache.clear();
        }
    }
    private void writeEvents(){
        this.cache.forEach(super::logEvent);
    }
    public void destroy(){
        if (!cache.isEmpty())
            this.cache.forEach(super::logEvent);
    }
}
