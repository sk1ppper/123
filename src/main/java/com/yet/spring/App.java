package com.yet.spring;

import com.yet.spring.beans.Client;
import com.yet.spring.beans.Event;
import com.yet.spring.beans.EventType;
import com.yet.spring.loggers.EventLogger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

public class App {
    private Client client;
    private EventLogger defaultLogger;
    private Map<EventType,EventLogger> loggers;

    public App(Client client, EventLogger eventLogger, Map<EventType,EventLogger> loggers) {
        this.client = client;
        this.defaultLogger = eventLogger;
        this.loggers = loggers;
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");

        App app = (App) ctx.getBean("app");
        Event event = ctx.getBean(Event.class);
        app.logEvent(EventType.INFO, event, "Some event for 1");

        event = ctx.getBean(Event.class);
        app.logEvent(EventType.ERROR, event, "Some event for 2");

        event = ctx.getBean(Event.class);
        app.logEvent(null, event, "Some event for 3");
        ctx.close();

    }
    private void logEvent(EventType eventType, Event event, String msg){
        String message = msg.replaceAll(client.getId(),client.getFullname());
        event.setMsg(message); //получаем текст

        EventLogger logger = loggers.get(eventType);
        if (logger == null){
            logger = defaultLogger;
            System.out.println(logger);
        }


        logger.logEvent(event);

    }

}
