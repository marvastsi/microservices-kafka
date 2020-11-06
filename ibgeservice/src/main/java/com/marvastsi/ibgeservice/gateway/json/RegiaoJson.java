package com.marvastsi.ibgeservice.gateway.json;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegiaoJson implements Serializable {
	private static final long serialVersionUID = 6909156136189729182L;

	private Long id;
	private String sigla;
	private String nome;
}
