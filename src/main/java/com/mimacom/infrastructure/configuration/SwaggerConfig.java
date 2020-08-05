package com.mimacom.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {
		
	@Bean
    public Docket productApi() {
        
    	return new Docket(DocumentationType.SWAGGER_2)
	        .select()
	        .apis(RequestHandlerSelectors.basePackage("com.mimacom.infrastructure"))
	        .paths(PathSelectors.any()) 
	        .build()
	        .apiInfo(metaData());
    }
	      
    private ApiInfo metaData() {
        
    	return new ApiInfoBuilder()
    			.title("Microservicio para el manejo de una lista de tareas personalizada.")
    			.description("Esta prueba técnica consistirá en crear una pequeña aplicación backend que exponga una API " + 
    					"REST que permita a un usuario gestionar una lista de tareas personalizada. \n\n" + 
    					"La aplicación debe permitir tanto la creación de tareas nuevas, como el borrado y la edición " + 
    					"de tareas existentes. Asímismo, una tarea ya realizada debe poder marcarse como finalizada.\n\n" + 
    					
    					"El candidato tendrá que desarrollar este ejercicio utilizando Java y Spring Framework, " + 
    					"dejando a su libre elección tanto las versiones a utilizar como el resto de tecnologías que " + 
    					"puedan necesitarse para completar la funcionalidad, como por ejemplo, la tecnología con la " + 
    					"que se va a construir el proyecto (Maven, Gradle, Ant, etc)." + 
    					"\n\n" + 
    					"Como entregable final, se facilitará el acceso al código fuente original, en el formato elegido por " + 
    					"el candidato, así como los pasos a seguir para poner la aplicación en marcha.")
                .version("1.0.0")
                .license("2020 rojorubi for MIMACOM")
                .contact(new Contact("Rojorubi", "https://www.linkedin.com/in/rubirincon/", "rojorubi@gmail.com"))
                .build();
    }
    
    /*
     * To configure support for Swagger UI with Spring Boot 
     */
	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html")
	            .addResourceLocations("classpath:/META-INF/resources/");
	
	    registry.addResourceHandler("/webjars/**")
	            .addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

	
}
