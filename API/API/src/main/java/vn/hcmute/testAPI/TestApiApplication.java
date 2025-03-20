package vn.hcmute.testAPI;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import vn.hcmute.testAPI.config.StorageProperties;
import vn.hcmute.testAPI.service.IStorageService;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class) // them cau hinh storage
public class TestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestApiApplication.class, args);
	}

	// them cau hinh storage
	@Bean
	CommandLineRunner init(IStorageService storageService) {
		return (args -> {
			storageService.init();
		});
	}

}
