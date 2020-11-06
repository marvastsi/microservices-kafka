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
import com.marvastsi.ibgewrapper.gateway.json.EstadoJson;
import com.marvastsi.ibgewrapper.gateway.json.EstadoList;
import com.marvastsi.ibgewrapper.services.estado.ConsultaEstadoService;

@Service
public class EstadoListener {

	private final ConsultaEstadoService consultaEstadoService;

	@Autowired
	public EstadoListener(ConsultaEstadoService consultaEstadoService) {
		this.consultaEstadoService = consultaEstadoService;
	}

	@SendTo
	@KafkaListener(topics = "${kafka.topic.request-topic}")
	public Message<String> execute(
			@Header(KafkaHeaders.REPLY_TOPIC) byte[] replyTo,
			@Header(KafkaHeaders.CORRELATION_ID) byte[] correlation) throws Exception {

		List<EstadoJson> listEstados = consultaEstadoService.execute();

		String json = new ObjectMapper().writeValueAsString(EstadoList.builder().list(listEstados).build());

		return MessageBuilder
				.withPayload(json)
				.setHeader(KafkaHeaders.TOPIC, replyTo)
				.setHeader(KafkaHeaders.CORRELATION_ID, correlation)
				.build();
	}

}
