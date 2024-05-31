package com.jpo.newjpo.repository;

import com.jpo.newjpo.enums.UserRole;
import com.jpo.newjpo.modele.User;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    User findByRole(UserRole role);

    User save(User user);

    Optional<User> findFirstByEmail(String email);
}
