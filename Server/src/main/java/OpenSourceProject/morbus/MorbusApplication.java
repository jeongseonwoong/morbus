package OpenSourceProject.morbus;

import org.junit.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.function.IntPredicate;


@SpringBootApplication
public class MorbusApplication {


	public static IntPredicate main(String[] args) {
		SpringApplication.run(MorbusApplication.class, args);
		return null;
	}

}