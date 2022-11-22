package io.github.PatrickRiibeio.SpringExpert;

import org.springframework.beans.factory.annotation.Value;

import io.github.PatrickRiibeio.SpringExpert.annotations.Animal;
import io.github.PatrickRiibeio.SpringExpert.annotations.Cachorro;
import io.github.PatrickRiibeio.SpringExpert.annotations.Hml;

@Hml
public class Config {

	//@Autowired
	//@Qualifier(value = "applicationName")
	@Value("${application.name}")
	private String applicaStringName;

	@Cachorro// interface anotação
	private Animal animal;

	/*@Bean(name = "executarAnimal")
	public CommandLineRunner executarAnimal() {
		return args -> {
			//this.animal.fazerBarulho();
		};
	}*/

	

}