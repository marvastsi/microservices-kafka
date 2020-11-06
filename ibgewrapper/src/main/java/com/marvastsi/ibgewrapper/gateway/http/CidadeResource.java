package com.marvastsi.ibgewrapper.gateway.http;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marvastsi.ibgewrapper.gateway.json.EstadoJson;
import com.marvastsi.ibgewrapper.services.estado.ConsultaEstadoService;

@RestController
@RequestMapping("/cidades")
public class CidadeResource {

	private final ConsultaEstadoService consultaEstadoService;

	@Autowired
	public CidadeResource(ConsultaEstadoService consultaEstadoService) {
		this.consultaEstadoService = consultaEstadoService;
	}

	@GetMapping("/{uf}")
	public List<EstadoJson> consultar(@PathVariable("uf") String uf) {
		return consultaEstadoService.execute();
	}

}
