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
        Expert expert1= new Expert("expert1");

        expert1.getTagList().add(tag1);

        expertRepository.save(expert1);
    }
}
