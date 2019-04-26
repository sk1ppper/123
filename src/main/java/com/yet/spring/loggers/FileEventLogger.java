package com.yet.spring.loggers;

import com.yet.spring.beans.Event;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileEventLogger implements EventLogger {
    private File file;
    private String fileName;
    public void logEvent(Event event){
        try {
            FileUtils.writeStringToFile(new File(fileName),event.toString(),true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void init() {
        try {
            this.file = new File(fileName);
            if (!file.canWrite()) {
                throw new FileNotFoundException();
            }
        }
        catch (FileNotFoundException e){
        }
    }

    public FileEventLogger(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
