package com.marvastsi.ibgewrapper.gateway.feign;

import java.util.List;

import com.marvastsi.ibgewrapper.gateway.json.EstadoJson;

import feign.RequestLine;

public interface EstadoClient {

	// https://servicodados.ibge.gov.br/api/v1/localidades/estados

	@RequestLine("GET /api/v1/localidades/estados")
	List<EstadoJson> get();
}
