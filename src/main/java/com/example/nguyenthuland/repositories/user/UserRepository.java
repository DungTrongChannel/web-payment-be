package com.example.nguyenthuland.repositories.user;

import com.example.nguyenthuland.domains.user.UserEntity;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, String> {

  Optional<UserEntity> findByUsername(String userName);
}
