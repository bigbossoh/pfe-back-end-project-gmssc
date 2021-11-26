//package com.bossoh.gmsscbackend.services.impl;
//import com.bossoh.gmsscbackend.Validator.PieceEquipementValidator;
//import com.bossoh.gmsscbackend.utils.UtilRandom;
//import com.bossoh.gmsscbackend.entities.*;
//import com.bossoh.gmsscbackend.repositories.*;
//import com.bossoh.gmsscbackend.services.GssmcInit;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDate;
//import java.util.List;
//import java.util.Random;
//import java.util.stream.Stream;
//
//@Service
//@Transactional
//@Slf4j
//@RequiredArgsConstructor
//public class GssmcInitImpl implements GssmcInit {
//
//     private final SocieteRepository societeRepository;
//    private final BienImmobilierRepository bienImmobilierRepository;
//    private final PieceRepository pieceRepository;
//    private final EquipementRepository equipementRepository;
//    private final PieceEquipementRepository pieceEquipementRepository;
//    private final UtilRandom utilRandom;
//
//
//    Random randomGenerator=new Random();
//
//
//    @Override
//    public void initSociete() {
//        log.info("We are going to create dummy data into the data");
//        Stream.of("Pigier CI").forEach(v->{
//            Societe soc=new Societe();
//            soc.setCodeSociete(utilRandom.generatedRandomString(6));
//            soc.setDateCreationSociete(LocalDate.of((int) (Math.random()*(64)) + 1956,
//                    randomGenerator.nextInt(12) + 1,
//                    randomGenerator.nextInt(27) + 1));
//            soc.setDenomination(v);
//            soc.setSigle(utilRandom.generatedRandomString(4));
//            soc.setDescriptionActivite(utilRandom.generatedRandomString(16));
//            soc.setCodeFiscal(utilRandom.generetString(6));
//            soc.setMobile(utilRandom.generetString(10));
//            soc.setFax(utilRandom.generetString(10));
//            soc.setNumTel(utilRandom.generetString(10));
//            soc.setEmail(utilRandom.generatedRandomString(8)+"@"+
//                    utilRandom.generatedRandomString(5)+"."+
//                    utilRandom.generatedRandomString(3));
//            soc.setSteWeb("www."+utilRandom.generatedRandomString(7)+
//                            "."+utilRandom.generatedRandomString(4));
//            societeRepository.save(soc);
//
//        });
//
//    }
//
//    @Override
//    public void initBienImmobilier() {
//       String[] typeBien=new String[] {"UNIVERSITE","MAISON_INDIVIDUELLE","VILLA"};
//        String[] NomBien=new String[] {"Pigier Centre","Pigier Annexe","Pigier Yamoussoukro","Villa Pigier"};
//        societeRepository.findAll().forEach(societe -> {
//            for (int i = 0; i <3 ; i++) {
//                BienImmobilier bien = new BienImmobilier();
//                bien.setCodeBienImmobilier(utilRandom.generatedRandomString(6));
//                bien.setMobile("05"+utilRandom.generetString(8));
//                bien.setTelephone("07"+utilRandom.generetString(8));
//                bien.setNomBienImmobilier(NomBien[new Random().nextInt(NomBien.length)]);
//                bien.setNbreBatiments((int) (Math.random()*(5)+1));
//                bien.setNombrePiece((int) (Math.random()*(9)+10));
//                bien.setAutreInformation(utilRandom.generatedRandomString(16));
//                bien.setSociete(societe);
//                bien.setTypeBienImmobilier(typeBien[new Random().nextInt(typeBien.length)]);
//                bienImmobilierRepository.save(bien);
//            }
//
//        });
//
//    }
//
//    @Override
//    public void initPiece() {
//        String[] typeSalle=new String[] {"SALLE","BUREAU","AMPHI","SALLE_INFORMATIQUE","SHOW_ROOM","SECRETARIAT"};
//        String[] NomBatiment=new String[] {"A","B","C","préau"};
//     bienImmobilierRepository.findAll().forEach(bienImmobilier -> {
//                 for (int i=1;i<=bienImmobilier.getNombrePiece();i++ ){
//                     String typ=typeSalle[new Random().nextInt(typeSalle.length)];
//                     String Nbt=NomBatiment[new Random().nextInt(NomBatiment.length)];
//                     Pieces pieces = new Pieces();
//                     pieces.setCodePiece(utilRandom.generatedRandomString(6));
//                     pieces.setNomBatiment(Nbt);
//                     pieces.setNomPiece(typ+" "+i);
//                     pieces.setCapacitePiece((int) (Math.random()*(10)+40));
//                     pieces.setDescription(utilRandom.generatedRandomString(120));
//                     pieces.setPositionEtage((int) (Math.random()*(5)+1));
//                     pieces.setTypeSalle(typ);
//                     pieces.setBienImmobilier(bienImmobilier);
//                pieceRepository.save(pieces);
//                 }
//     });
//    }
//
//    @Override
//    public void initEquipement() {
//        String[] typeClim=new String[] {"Condensateur","Evaporateur"};
//        String[] modele=new String[] {"MONO BLOC","SPLIT"};
//        String[] marque=new String[] {"Sharp","Nasco","Daikan","Smart","Samsung"};
//        for (int i=1; i<12; i++){
//            Equipement equipe=new Equipement();
//            String typ = typeClim[randomGenerator.nextInt(typeClim.length)];
//            if(typ=="Condensateur"){
//                equipe.setTypeClim(typ);
//                equipe.setPoidsNetExterieur(randomGenerator.nextInt(10)+30.5);
//                equipe.setDateAchat(LocalDate.of((int) (Math.random()*(6)) + 2015,
//                        randomGenerator.nextInt(12) + 1,
//                        randomGenerator.nextInt(27) + 1));
//                equipe.setGarantie((int) (Math.random()*(3)+1));
//                equipe.setPuissance(utilRandom.generatedRandomNumber(1));
//                equipe.setMarque(modele[randomGenerator.nextInt(modele.length)]);
//                equipe.setMobile("05"+utilRandom.generatedRandomNumber(8));
//                equipe.setTelephone("21"+utilRandom.generatedRandomNumber(8));
//                equipe.setLivreAvecAccessoires(randomGenerator.nextBoolean());
//            }else {
//                equipe.setTypeClim(typ);
//                equipe.setPoidsNetInterieur(randomGenerator.nextInt(10)+2.5);
//                equipe.setDateAchat(LocalDate.of((int) (Math.random()*(6)) + 2015,
//                        randomGenerator.nextInt(12) + 1,
//                        randomGenerator.nextInt(27) + 1));
//                equipe.setGarantie((int) (Math.random()*(3)+1));
//                equipe.setPuissance(utilRandom.generatedRandomNumber(1));
//                equipe.setMarque(modele[randomGenerator.nextInt(modele.length)]);
//                equipe.setMobile("05"+utilRandom.generatedRandomNumber(8));
//                equipe.setTelephone("21"+utilRandom.generatedRandomNumber(8));
//                equipe.setLivreAvecAccessoires(randomGenerator.nextBoolean());
//            }
//            equipe.setCodeEquipement(utilRandom.generatedRandomString(6));
//
//
//            equipe.setHauteur(randomGenerator.nextInt(10)+0.5);
//            equipe.setLargeur(randomGenerator.nextInt(10)+0.5);
//            equipe.setLongueur(randomGenerator.nextInt(10)+0.5);
//            equipe.setNomFournisseur(utilRandom.generatedRandomString(9));
//            equipe.setPersonneRessource(utilRandom.generatedRandomString(9));
//            equipe.setDescription(utilRandom.generatedRandomString(19));
//            equipe.setMarque(marque[randomGenerator.nextInt(marque.length)]);
//            equipe.setNumeroSerie(utilRandom.generatedRandomString(7));
//            equipe.setModele(modele[randomGenerator.nextInt(modele.length)]);
//            equipementRepository.save(equipe);
//
//        }
//
//    }
//
//    @Override
//    public void initPieceEquipement() {
//       List<Equipement> listEquipement= equipementRepository.findAll();
//        pieceRepository.findAll().forEach(pieces -> {
//            for (int i=1;i<2;i++ ){
//                PieceEquipement pieceEqt= new PieceEquipement();
//                pieceEqt.setDateInstallation(LocalDate.now());
//               // log.info("**************************************");
//                //log.info("all epquipements before ",String.valueOf(listEquipement.size()));
//               // int find=new Random().nextInt(listEquipement.size());
//                pieceEqt.setEquipement(listEquipement.get(new Random().nextInt(listEquipement.size())));
//                //listEquipement.remove(find);
//              //  log.info("all epquipements After ",String.valueOf(listEquipement.size()));
//
//                pieceEqt.setPieces(pieces);
//                List<String> errors= PieceEquipementValidator.validator(pieceEqt);
//                if(errors.isEmpty()) {
//                    pieceEquipementRepository.save(pieceEqt);
//                }else {
//                    log.error(errors.toString());
//                }
//
//
//            }
//        });
//
//    }
//}
