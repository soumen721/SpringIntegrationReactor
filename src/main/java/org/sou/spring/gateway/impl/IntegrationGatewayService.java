package org.sou.spring.gateway.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.sou.spring.controller.IntegrationRestController;
import org.sou.spring.domain.RequestObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Component;

import reactor.bus.Event;
import reactor.bus.EventBus;

@Component
public class IntegrationGatewayService {
	private static Log logger = LogFactory.getLog(IntegrationRestController.class);
	
	@Autowired
	private EventBus eventBus;

	@ServiceActivator(inputChannel = "dummyInputChannel")
	public void processIntgRequest(RequestObject requestObject) {
		logger.info("IN IntegrationGatewayService.processIntgRequest id : "+ requestObject.getId());

		eventBus.notify("intgConsumer", Event.wrap(requestObject));
	}

	@ServiceActivator(inputChannel = "dummyOutputChannel")
	public void processIntgResponse(RequestObject requestObject) {
		logger.info("IN IntegrationGatewayService.processIntgResponse id : "+ requestObject.getId());

		eventBus.notify("intgConsumer", Event.wrap(requestObject));
	}

}
