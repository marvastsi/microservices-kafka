package com.marvastsi.ibgewrapper.gateway.http;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marvastsi.ibgewrapper.gateway.json.CidadeJson;
import com.marvastsi.ibgewrapper.gateway.json.EstadoJson;
import com.marvastsi.ibgewrapper.services.cidade.ConsultaCidadeService;
import com.marvastsi.ibgewrapper.services.estado.ConsultaEstadoService;

@RestController
@RequestMapping("/estados")
public class EstadoResource {

	private final ConsultaEstadoService consultaEstadoService;
	private final ConsultaCidadeService consultaCidadeService;

	@Autowired
	public EstadoResource(ConsultaEstadoService consultaEstadoService, ConsultaCidadeService consultaCidadeService) {
		this.consultaEstadoService = consultaEstadoService;
		this.consultaCidadeService = consultaCidadeService;
	}

	@GetMapping("/")
	public List<EstadoJson> consultar() {
		return consultaEstadoService.execute();
	}

	@GetMapping("/{uf}/cidades/")
	public List<CidadeJson> consultar(@PathVariable("uf") String uf) {
		return consultaCidadeService.execute(uf);
	}

}
