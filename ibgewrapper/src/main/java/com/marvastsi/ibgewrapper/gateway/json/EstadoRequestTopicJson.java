package com.marvastsi.ibgewrapper.gateway.json;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EstadoRequestTopicJson implements Serializable {
	private static final long serialVersionUID = 2015192489259739538L;
	private String uf;
}
