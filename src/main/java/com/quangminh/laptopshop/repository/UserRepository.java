package com.quangminh.laptopshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.quangminh.laptopshop.domain.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User save(User user);

    List<User> findAllByEmail(String email);

    List<User> findAll();

    User findById(long id);

    User deleteById(long id);

    boolean existsByEmail(String email);

    User findByEmail(String email);
}
