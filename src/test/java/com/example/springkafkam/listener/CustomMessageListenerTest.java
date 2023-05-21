package com.example.springkafkam.listener;

import com.example.springkafkam.SpringkafkamApplication;
import com.example.springkafkam.config.KafkaConsumerConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.kafka.event.ConsumerStartedEvent;
import org.springframework.kafka.event.ConsumerStartingEvent;
import org.springframework.kafka.event.ConsumerStoppedEvent;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.test.context.BootstrapWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomMessageListenerTest {

  @Autowired
  CustomMessageListener customMessageListener;

  @Autowired
  KafkaMessageListenerContainer  kmlc;

  @Test
  void test_onApplicationEventStarting() {
    customMessageListener.onApplicationEvent(new ConsumerStartingEvent(kmlc, kmlc));
    boolean isConnected = customMessageListener.isConnected();
    assertFalse(isConnected, "Should be False");
  }

  @Test
  void test_onApplicationEventStarted() {
    customMessageListener.onApplicationEvent(new ConsumerStartedEvent(kmlc, kmlc));
    boolean isConnected = customMessageListener.isConnected();
    assertTrue(isConnected, "Should be True");
  }

  @Test
  void test_onApplicationEventStopped() {
    customMessageListener.onApplicationEvent(new ConsumerStoppedEvent(kmlc, kmlc, ConsumerStoppedEvent.Reason.ERROR));
    boolean isConnected = customMessageListener.isConnected();
    assertFalse(isConnected, "Should be False");
  }
}
