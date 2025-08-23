package com.salonms.salonmsBackend.repo;

import com.salonms.salonmsBackend.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    boolean existsByUserId(String userId);

    User findByUserId(String userId);

    Optional<User> findByUserName(String userName);

    List<User> findByRole(String role);
}
