package com.ssl.finalproject.repository;

import com.ssl.finalproject.model.Tag;
import com.ssl.finalproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<User,Long> {

 boolean existsUserByUsernameAndPassword(String username,String password);

 User findUserByUsernameAndPassword(String username,String password);

}
