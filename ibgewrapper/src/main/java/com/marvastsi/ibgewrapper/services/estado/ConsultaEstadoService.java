package com.marvastsi.ibgewrapper.services.estado;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.marvastsi.ibgewrapper.gateway.feign.EstadoClient;
import com.marvastsi.ibgewrapper.gateway.json.EstadoJson;

import feign.Feign;
import feign.gson.GsonDecoder;

@Service
public class ConsultaEstadoService {

	@Cacheable("estado")
	public List<EstadoJson> execute() {
		EstadoClient client = Feign.builder()
				.decoder(new GsonDecoder())
				.target(EstadoClient.class, "https://servicodados.ibge.gov.br");

		return client.get();
	}
}
