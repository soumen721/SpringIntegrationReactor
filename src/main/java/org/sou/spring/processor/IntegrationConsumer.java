package org.sou.spring.processor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.sou.spring.domain.RequestObject;
import org.springframework.stereotype.Service;

import reactor.bus.Event;
import reactor.fn.Consumer;

@Service
public class IntegrationConsumer implements Consumer<Event<RequestObject>> {
	private static Log logger = LogFactory.getLog(IntegrationConsumer.class);

	@Override
	public void accept(Event<RequestObject> event) {
		RequestObject reqObject= event.getData();
		
		logger.info("IntegrationConsumer.accept Received notification in EVENT BUS: Name : "+reqObject.getName());
	}
}
