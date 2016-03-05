package org.sou.spring.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.sou.spring.domain.RequestObject;
import org.sou.spring.gateway.IntegrationGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IntegrationRestController {
	private static Log logger = LogFactory.getLog(IntegrationRestController.class);

	@Autowired
	private IntegrationGateway integrationGateway;
	
	@RequestMapping(value = "/springIntg", method = RequestMethod.GET)
	public ResponseEntity<Object> integrationTest() throws Exception {
		logger.info("IN integration integrationTest Controller : ");	
		
		RequestObject requestObject=new RequestObject();
		requestObject.setId("3512");
		requestObject.setName("Soumen Choudhury");
		requestObject.setDesc("Sr. Software Eng.");
		
		//Call Integration Channel
		integrationGateway.processIntgRequest(requestObject);
		return new ResponseEntity<Object>(requestObject, HttpStatus.OK);
	}
}
