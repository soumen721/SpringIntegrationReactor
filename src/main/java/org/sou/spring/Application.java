package org.sou.spring;

import static reactor.bus.selector.Selectors.$;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.sou.spring.processor.IntegrationConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.DirectChannel;

import reactor.Environment;
import reactor.bus.EventBus;

@SpringBootApplication
@PropertySource("classpath:message.properties")
@IntegrationComponentScan("org.sou.spring")
public class Application implements CommandLineRunner {
	private static Log logger = LogFactory.getLog(Application.class);

	public static void main(String[] args) {
		logger.info("START APPLICATION :: "+ args);
		SpringApplication.run(Application.class, args);
	}

	@Bean
	Environment env() {
		return Environment.initializeIfEmpty().assignErrorJournal();
	}

	@Bean
	EventBus createEventBus(Environment env) {
		return EventBus.create(env, Environment.THREAD_POOL);
	}

	@Bean
	public DirectChannel dummyInputChannel() {
		return new DirectChannel();
	}

	@Bean
	public DirectChannel dummyOutputChannel() {
		return new DirectChannel();
	}

	@Autowired
	private EventBus eventBus;

	@Autowired
	private IntegrationConsumer integrationConsumer;

	@Override
	public void run(String... arg0) throws Exception {
		eventBus.on($("intgConsumer"), integrationConsumer);
	}
}
