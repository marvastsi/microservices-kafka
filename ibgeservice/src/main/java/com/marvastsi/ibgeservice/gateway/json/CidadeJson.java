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
public class CidadeJson implements Serializable {

	private static final long serialVersionUID = 4498376253552438660L;
	private Long id;
	private String nome;
}
