package com.marvastsi.ibgewrapper.services.cidade;

import java.util.List;

import org.springframework.stereotype.Service;

import com.marvastsi.ibgewrapper.gateway.feign.CidadeClient;
import com.marvastsi.ibgewrapper.gateway.json.CidadeJson;

import feign.Feign;
import feign.gson.GsonDecoder;

@Service
public class ConsultaCidadeService {

	public List<CidadeJson> execute(String uf) {
		CidadeClient client = Feign.builder().decoder(new GsonDecoder()).target(CidadeClient.class,
				"https://servicodados.ibge.gov.br");
		return client.get(uf);
	}
}
