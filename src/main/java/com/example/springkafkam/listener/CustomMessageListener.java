package com.example.springkafkam.listener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.kafka.event.ConsumerStartedEvent;
import org.springframework.kafka.event.ConsumerStartingEvent;
import org.springframework.kafka.event.ConsumerStoppedEvent;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.stereotype.Component;

import java.util.logging.Level;
import java.util.logging.Logger;


@Component
public class CustomMessageListener implements MessageListener, ApplicationListener {

  private Logger logger = Logger.getLogger(CustomMessageListener.class.getName());
  Boolean connected=false;

  @Override
  public void onMessage(Object o) {
  }

  @Override
  public void onApplicationEvent(ApplicationEvent event) {

    if (event instanceof ConsumerStartingEvent){
      connected=false;
      logger.log(Level.FINE,"Starting");
    }
    else if (event instanceof ConsumerStoppedEvent){
      connected=false;
      logger.log(Level.FINE, "Stopped");
    }
    else if (event instanceof ConsumerStartedEvent){
      connected=true;
      logger.log(Level.FINE, "##########################################");
      logger.log(Level.FINE, "Consumer Started Event: " + event);
      logger.log(Level.FINE, "##########################################");
    }
    else {
      logger.log(Level.FINE, "Instance of unhandled eventEvent");
    }
    logger.log(Level.ALL, "Event: " + event);
  }

  Boolean isConnected(){
    return connected;
  }
}


