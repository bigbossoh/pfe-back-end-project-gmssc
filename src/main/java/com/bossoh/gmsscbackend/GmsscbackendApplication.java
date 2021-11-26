package com.bossoh.gmsscbackend;

//import com.bossoh.gmsscbackend.services.GssmcInit;
//import com.bossoh.gmsscbackend.services.impl.GssmcInitImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class GmsscbackendApplication implements CommandLineRunner
{
//	private final GssmcInit gssmcInit;
//	@Autowired
//	public GmsscbackendApplication(GssmcInit gssmcInit) {
//		this.gssmcInit = gssmcInit;
//	}

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
}
