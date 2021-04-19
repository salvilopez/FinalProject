package com.ssl.finalproject;

import com.ssl.finalproject.model.Expert;
import com.ssl.finalproject.model.Tag;
import com.ssl.finalproject.model.User;
import com.ssl.finalproject.repository.ExpertRepository;
import com.ssl.finalproject.repository.TagRepository;
import com.ssl.finalproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.Instant;

@SpringBootApplication
public class FinalProjectApplication implements CommandLineRunner {


    @Autowired
    ExpertRepository expertRepository;

    @Autowired
    TagRepository tagRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    public static void main(String[] args) {
        SpringApplication.run(FinalProjectApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Tag tag1 = new Tag("tag1");
        Tag tag2 = new Tag("tag2");
        Tag tag3 = new Tag("tag1");
        Tag tag4 = new Tag("tag4");
        Expert expert1= new Expert("experto1", Instant.now(),Instant.now(),"estado-motivo1","disponibilidad1","modalidad1",false,111111111,"email1@gmail.com","contacto_ciudad1","contacto_linkedin1",2.0,2.0,90,"nif1","correo1","password1","zoom1","zoom_password1","foto1","fichero1","observaciones1","origen1","validado");
        Expert expert2= new Expert("experto2", Instant.now(),Instant.now(),"estado-motivo1","disponibilidad2","modalidad2",false,222222222,"email2@gmail.com","contacto_ciudad2","contacto_linkedin2",2.0,2.0,90,"nif2","correo2","password2","zoom2","zoom_password2","foto2","fichero2","observaciones2","origen2","validado");
        Expert expert3= new Expert("experto1", Instant.now(),Instant.now(),"estado-motivo1","disponibilidad3","modalidad3",false,333333333,"email3@gmail.com","contacto_ciudad3","contacto_linkedin3",2.0,2.0,90,"nif3","correo3","password3","zoom3","zoom_password3","foto3","fichero3","observaciones3","origen3","pendiente");
        Expert expert4= new Expert("experto4", Instant.now(),Instant.now(),"estado-motivo1","disponibilidad4","modalidad4",false,444444444,"email4@gmail.com","contacto_ciudad4","contacto_linkedin4",2.0,2.0,90,"nif4","correo4","password4","zoom4","zoom_password4","foto4","fichero4","observaciones4","origen4","validado");
        Expert expert5= new Expert("experto5", Instant.now(),Instant.now(),"estado-motivo1","disponibilidad5","modalidad5",false,555555555,"email5@gmail.com","contacto_ciudad5","contacto_linkedin5",2.0,2.0,90,"nif8","correo5","password5","zoom5","zoom_password5","foto5","fichero5","observaciones5","origen5","estado5");
      //  expert1.getTagList().add(tag1);
      //  expert2.getTagList().add(tag2);
        expert3.getTagList().add(tag3);
        expert1.getTagList().add(tag1);
        expert2.getTagList().add(tag2);
        expert2.getTagList().add(tag1);

      //   expertRepository.save(expert1);
       // expertRepository.save(expert2);
       // expertRepository.save(expert3);
        tag1.getExpertList().add(expert2);
        tag1.getExpertList().add(expert1);
        tag2.getExpertList().add(expert2);
        tag3.getExpertList().add(expert3);
       // tagRepository.save(tag1);
       // tagRepository.save(tag2);
        expertRepository.save(expert1);
        expertRepository.save(expert2);
        expertRepository.save(expert3);

        User user= new User("salvi@gmail.com",encoder.encode("salvi"));
        userRepository.save(user);

    }
}
