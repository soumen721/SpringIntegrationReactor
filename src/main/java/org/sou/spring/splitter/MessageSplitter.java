package org.sou.spring.splitter;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.sou.spring.domain.RequestObject;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.Splitter;
import org.springframework.messaging.Message;
@MessageEndpoint
public class MessageSplitter {
	private static Log logger = LogFactory.getLog(MessageSplitter.class);

    @Splitter(inputChannel = "dummyInputChannel", outputChannel = "dummyOutputChannel")
    public List<RequestObject> splitMessageList(Message<List<RequestObject>> message) {
    	logger.info("IN MessageSplitter.splitMessageList :: "+ message);
    	
    	return message.getPayload();
    }
}