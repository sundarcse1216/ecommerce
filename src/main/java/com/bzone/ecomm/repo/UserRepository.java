package com.bzone.ecomm.repo;

import com.bzone.ecomm.entiry.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author sundar
 * @since 16-09-2022
 */
@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    Users findByEmail(String email);

    List<Users> findAllByStatus(boolean status);

}
