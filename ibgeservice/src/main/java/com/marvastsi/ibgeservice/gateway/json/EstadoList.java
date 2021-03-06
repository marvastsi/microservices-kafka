package com.marvastsi.ibgeservice.gateway.json;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EstadoList implements Serializable {
	
	private static final long serialVersionUID = -4902640430540783291L;
	private List<EstadoJson> list;

}
