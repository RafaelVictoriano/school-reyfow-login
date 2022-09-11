package com.br.schoolreyfowlogin.repository;

import com.br.schoolreyfowlogin.model.UserModel;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

@EnableScan
public interface UserRepository extends CrudRepository<UserModel, String> {
    Optional<UserModel> findByEmail(String username);
}