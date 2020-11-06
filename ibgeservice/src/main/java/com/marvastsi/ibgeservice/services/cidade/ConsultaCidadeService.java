package com.marvastsi.ibgeservice.services.cidade;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marvastsi.ibgeservice.gateway.json.CidadeList;
import com.marvastsi.ibgeservice.gateway.json.EstadoRequestTopicJson;

@Service
public class ConsultaCidadeService {

	private final ReplyingKafkaTemplate<String, String, String> kafkaTemplate;

	@Value("${kafka.topic.request-topic-cidade}")
	private String requestTopic;

	@Value("${kafka.topic.requestreply-topic-cidade}")
	private String requestReplyTopic;

	@Autowired
	public ConsultaCidadeService(ReplyingKafkaTemplate<String, String, String> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	@Cacheable("cidade-principal")
	public CidadeList execute(String uf) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(EstadoRequestTopicJson.builder().uf(uf).build());

		ProducerRecord<String, String> record = new ProducerRecord<>(requestTopic, jsonString);
		record.headers().add(new RecordHeader(KafkaHeaders.REPLY_TOPIC, requestReplyTopic.getBytes()));

		RequestReplyFuture<String, String, String> sendAndReceive = kafkaTemplate.sendAndReceive(record);

		SendResult<String, String> sendResult = sendAndReceive.getSendFuture().get();
		sendResult.getProducerRecord().headers()
				.forEach(header -> System.out.println(header.key() + ":" + header.value().toString()));

		ConsumerRecord<String, String> consumerRecord = sendAndReceive.get();

		CidadeList listReturn = mapper.readValue(consumerRecord.value(), CidadeList.class);

		return listReturn;

	}
}
