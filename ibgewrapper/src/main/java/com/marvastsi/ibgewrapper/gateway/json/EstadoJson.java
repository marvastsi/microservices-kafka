package com.marvastsi.ibgewrapper.gateway.json;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class EstadoJson implements Serializable {

	private static final long serialVersionUID = 1922113092853574088L;

	private Long id;
	private String sigla;
	private String nome;
	private RegiaoJson regiao;
}
