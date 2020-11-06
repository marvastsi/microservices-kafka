package com.marvastsi.ibgewrapper.gateway.listener;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marvastsi.ibgewrapper.gateway.json.CidadeJson;
import com.marvastsi.ibgewrapper.gateway.json.CidadeList;
import com.marvastsi.ibgewrapper.gateway.json.EstadoRequestTopicJson;
import com.marvastsi.ibgewrapper.services.cidade.ConsultaCidadeService;

@Service
public class CidadeListener {

	private final ConsultaCidadeService consultaCidadeService;

	@Autowired
	public CidadeListener(ConsultaCidadeService consultaCidadeService) {
		this.consultaCidadeService = consultaCidadeService;
	}

	@SendTo
	@KafkaListener(topics = "${kafka.topic.request-topic-cidade}")
	public Message<String> execute(String in,
			@Header(KafkaHeaders.REPLY_TOPIC) byte[] replyTo,
			@Header(KafkaHeaders.CORRELATION_ID) byte[] correlation) throws Exception {

		ObjectMapper mapper = new ObjectMapper();
		EstadoRequestTopicJson json = mapper.readValue(in, EstadoRequestTopicJson.class);

		List<CidadeJson> listCidades = consultaCidadeService.execute(json.getUf());

		String jsonReturn = mapper.writeValueAsString(CidadeList.builder().list(listCidades).build());

		return MessageBuilder
				.withPayload(jsonReturn)
				.setHeader(KafkaHeaders.TOPIC, replyTo)
				.setHeader(KafkaHeaders.CORRELATION_ID, correlation)
				.build();
	}

}
