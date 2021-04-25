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
import java.time.LocalDate;

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

        Tag tag1 = new Tag("Java", LocalDate.now(),LocalDate.now(),"salvi@gmail.com");
        Tag tag2 = new Tag("Angular",LocalDate.now(),LocalDate.now(),"pepe@gmail.com");
        Tag tag3 = new Tag("Spring",LocalDate.now(),LocalDate.now(),"manuel@gmail.com");
        Tag tag4 = new Tag("Cobol",LocalDate.now(),LocalDate.now(),"manuel@gmail.com");
        Tag tag5 = new Tag("Python",LocalDate.now(),LocalDate.now(),"joseluis@gmail.com");
        Tag tag6 = new Tag("Jquery",LocalDate.now(),LocalDate.now(),"alejandro@gmail.com");
        Tag tag7 = new Tag("Javascript",LocalDate.now(),LocalDate.now(),"almendra@gmail.com");
        Tag tag8 = new Tag("React",LocalDate.now(),LocalDate.now(),"luis@gmail.com");

        Expert expert1= new Expert("Marina López", Instant.now(),Instant.now(),"incompetecia","morning","modalidad1",true,111111111,"email1@gmail.com","contacto_ciudad1","contacto_linkedin1",2.0,2.0,90,"3545454564f","emaildos@gmail.com","password1","zoom1","zoom_password1","data:image/png;base64,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","fichero1","observaciones1","recursos humanos","validado");
        Expert expert2= new Expert("Jose Luis Martinez", Instant.now(),Instant.now(),"incompetecia","morning","modalidad2",true,222222222,"email2@gmail.com","contacto_ciudad2","contacto_linkedin2",2.0,2.0,60,"3545454564f","emailuno@gmail.com","password2","zoom2","zoom_password2","foto2","fichero2","observaciones2","Infojobs","validado");
        Expert expert3= new Expert("Salvador Sierra", Instant.now(),Instant.now(),"mala acitud","tardes","modalidad3",true,333333333,"email3@gmail.com","contacto_ciudad3","contacto_linkedin3",2.0,2.0,30,"3545454564f","salvilopezronda@gmail.com","password3","zoom3","zoom_password3","foto3","fichero3","observaciones3","Infojobs","pendiente");
        Expert expert4= new Expert("Adrian sastre", Instant.now(),Instant.now(),"mala acitud","tardes","modalidad4",false,444444444,"email4@gmail.com","contacto_ciudad4","contacto_linkedin4",2.0,2.0,5,"3545454564f","email4@gmail.com","password4","zoom4","zoom_password4","foto4","fichero4","observaciones4","recursos humanos","validado");
        Expert expert5= new Expert("Antonio merino", Instant.now(),Instant.now(),"incompetecia","todo","modalidad5",false,555555555,"email5@gmail.com","contacto_ciudad5","contacto_linkedin5",2.0,2.0,45,"3545454564f","elmail2@gmail.com","password5","zoom5","zoom_password5","foto5","fichero5","observaciones5","recursos humanos","pendiente");

        expert3.getTagList().add(tag3);
        expert1.getTagList().add(tag1);
        expert2.getTagList().add(tag2);
        expert2.getTagList().add(tag1);
        expert4.getTagList().add(tag5);
        expert4.getTagList().add(tag6);
        expert5.getTagList().add(tag7);
        expert5.getTagList().add(tag8);
        expert5.getTagList().add(tag4);

        tag3.getExpertList().add(expert3);
        tag1.getExpertList().add(expert1);
        tag2.getExpertList().add(expert2);
        tag1.getExpertList().add(expert2);
        tag5.getExpertList().add(expert4);
        tag6.getExpertList().add(expert4);
        tag7.getExpertList().add(expert5);
        tag8.getExpertList().add(expert5);
        tag4.getExpertList().add(expert5);


        expertRepository.save(expert1);
        expertRepository.save(expert2);
        expertRepository.save(expert3);
        expertRepository.save(expert4);
        expertRepository.save(expert5);


        User user= new User("salvi@gmail.com",encoder.encode("salvi"),"data:image/png;base64,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","Salvador Sierra López");
        userRepository.save(user);

    }
}
