package com.example.springkafkam.listener;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.kafka.event.ConsumerStartedEvent;
import org.springframework.kafka.event.ConsumerStartingEvent;
import org.springframework.kafka.event.ConsumerStoppedEvent;
import org.springframework.kafka.listener.ConsumerAwareMessageListener;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.stereotype.Component;


@Component
public class CustomMessageListener implements MessageListener, ApplicationListener {

  Boolean connected=false;

  @Override
  public void onMessage(Object o) {
  }

  @Override
  public void onApplicationEvent(ApplicationEvent event) {

    if (event instanceof ConsumerStartingEvent){
      connected=false;
      System.out.println("Starting");
    }
    else if (event instanceof ConsumerStoppedEvent){
      connected=false;
      System.out.println("Stopped");
    }
    else if (event instanceof ConsumerStartedEvent){
      connected=true;
      System.out.println("##########################################");
      System.out.println("Consumer Started Event: " + event);
      System.out.println("##########################################");
    }
    else {
      System.out.println("EVENT: " + event);
    }
  }

  Boolean isConnected(){
    return connected;
  }
}


