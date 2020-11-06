package com.marvastsi.ibgewrapper.gateway.feign;

import java.util.List;

import com.marvastsi.ibgewrapper.gateway.json.CidadeJson;

import feign.Param;
import feign.RequestLine;

public interface CidadeClient {

	// https://servicodados.ibge.gov.br/api/v1/localidades/estados/{UF}/municipios

	@RequestLine("GET /api/v1/localidades/estados/{UF}/municipios")
	List<CidadeJson> get(@Param("UF") String uf);
}
