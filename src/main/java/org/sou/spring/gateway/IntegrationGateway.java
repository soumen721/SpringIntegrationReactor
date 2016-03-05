package org.sou.spring.gateway;

import org.sou.spring.domain.RequestObject;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway()
public interface IntegrationGateway {

	@Gateway(requestChannel = "dummyInputChannel")
	public void processIntgRequest(RequestObject requestObject);

	@Gateway(requestChannel = "dummyOutputChannel")
	public void processIntgResponse(RequestObject requestObject);

}