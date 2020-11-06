package com.marvastsi.ibgeservice.gateway.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marvastsi.ibgeservice.gateway.json.CidadeList;
import com.marvastsi.ibgeservice.gateway.json.EstadoList;
import com.marvastsi.ibgeservice.services.cidade.ConsultaCidadeService;
import com.marvastsi.ibgeservice.services.estado.ConsultaEstadoService;

@RestController
@RequestMapping("/estados")
public class EstadoResource {

	private final ConsultaEstadoService consultaEstadoService;
	private final ConsultaCidadeService consultaCidadeService;

	@Autowired
	public EstadoResource(ConsultaEstadoService consultaEstadoService, ConsultaCidadeService consultarCidadeService) {
		this.consultaEstadoService = consultaEstadoService;
		this.consultaCidadeService = consultarCidadeService;
	}

	@GetMapping
	public EstadoList consultarEstados() throws Exception {
		return consultaEstadoService.execute();
	}

	@GetMapping("/{uf}/cidades")
	public CidadeList consultarCidades(@PathVariable("uf") String idUf) throws Exception {
		return consultaCidadeService.execute(idUf);
	}

}
