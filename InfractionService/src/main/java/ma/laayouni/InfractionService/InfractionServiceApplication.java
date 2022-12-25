package ma.laayouni.InfractionService;

import com.thoughtworks.xstream.XStream;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class 	InfractionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InfractionServiceApplication.class, args);
	}


	@Bean
	public XStream xStream() {
		XStream xStream = new XStream();

		xStream.allowTypesByWildcard(new String[] { "**" });
		return xStream;
	}
}
