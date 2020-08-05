package com.mimacom.infrastructure.configuration;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@RefreshScope //nos da la posibilidad de cambiar en caliente el valor de las propiedades sin necesidad
//de volver a generar la imagen de nuestro ms y tener que desplegarlo de nuevo en el entorno
public class ThingsToDoConfig{
	
	@Value("${mimacom.status.list}")
	private List<String> listStatusTask;
	
	@Value("${mimacom.status.finish}")
	private String finishString;
	
	@Value("${mimacom.status.pending}")
	private String pendingString;

	@Value("${mimacom.max.size.description}")
	private Integer maxSizeDescription;
}
