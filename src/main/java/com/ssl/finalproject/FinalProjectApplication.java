package com.ssl.finalproject;

import com.ssl.finalproject.model.Expert;
import com.ssl.finalproject.model.Tag;
import com.ssl.finalproject.repository.ExpertRepository;
import com.ssl.finalproject.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FinalProjectApplication implements CommandLineRunner {


    @Autowired
    ExpertRepository expertRepository;

    @Autowired
    TagRepository tagRepository;

    public static void main(String[] args) {
        SpringApplication.run(FinalProjectApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Tag tag1 = new Tag("tag1");
        Tag tag2 = new Tag("tag2");
        Tag tag3 = new Tag("tag3");
        Tag tag4 = new Tag("tag4");
        Expert expert1= new Expert("expert1");
        Expert expert2= new Expert("expert2");
        expert1.getTagList().add(tag1);

        expertRepository.save(expert1);


        expert2.getTagList().add(tag2);
        expert2.getTagList().add(tag3);
        expert2.getTagList().add(tag4);
        expertRepository.save(expert2);
    }
}
