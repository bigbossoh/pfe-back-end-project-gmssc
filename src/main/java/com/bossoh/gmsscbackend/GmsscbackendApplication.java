package com.bossoh.gmsscbackend;

//import com.bossoh.gmsscbackend.services.GssmcInit;
//import com.bossoh.gmsscbackend.services.impl.GssmcInitImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableJpaAuditing
public class GmsscbackendApplication implements CommandLineRunner {
//	private final GssmcInit gssmcInit;
//	@Autowired
//	public GmsscbackendApplication(GssmcInit gssmcInit) {
//		this.gssmcInit = gssmcInit;
//	}
//@Bean
//public PasswordEncoder passwordEncoder() {
//	return new BCryptPasswordEncoder();
//}
	public static void main(String[] args) {
		SpringApplication.run(GmsscbackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		gssmcInit.initSociete();
//		gssmcInit.initBienImmobilier();
//		gssmcInit.initPiece();
//		gssmcInit.initEquipement();
//		gssmcInit.initPieceEquipement();
	}
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
