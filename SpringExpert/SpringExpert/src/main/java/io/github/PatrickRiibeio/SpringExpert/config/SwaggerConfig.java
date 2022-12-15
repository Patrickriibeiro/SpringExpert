package io.github.PatrickRiibeio.SpringExpert.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
public class SwaggerConfig {

	//Link do swagger http://localhost:8080/swagger-ui/index.html
	
	@Bean
	public Docket docket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.useDefaultResponseMessages(false)
				.select()
				.apis(RequestHandlerSelectors.basePackage("io.github.PatrickRiibeio.SpringExpert.rest.controller"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(this.apiInfo());
		
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Vendas Api")
				.description("Api do projeto de vendas")
				.version("1.0")
				.contact(this.contact())
				.build();
	}
	
	private Contact contact() {
		return new Contact("Patrick Ribeiro Oliveira", "https://github.com/Patrickriibeiro", "patrickribeiro1198@gmail.com");
	}
}
