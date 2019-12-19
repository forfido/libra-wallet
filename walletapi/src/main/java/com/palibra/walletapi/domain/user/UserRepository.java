package com.palibra.walletapi.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);

    //@Query("SELECT new com.palibra.walletapi.domain.user.UserDto(u.id, u.name, u.email, u.imageUrl, lb.libraAddress) FROM User u INNER JOIN u.LibraAccount lb WHERE u.id = lb.user_id and u.email LIKE CONCAT(:keyword,'%') AND u.enabled = true")
    @Query("SELECT new com.palibra.walletapi.domain.user.UserDto(u.id, u.name, u.email, u.imageUrl) FROM User u WHERE u.email LIKE CONCAT(:keyword,'%') AND u.enabled = true")
    List<UserDto> searchEmailsByKeyword(String keyword);

}
