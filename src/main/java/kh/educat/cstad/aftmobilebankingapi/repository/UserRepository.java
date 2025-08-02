package kh.educat.cstad.aftmobilebankingapi.repository;

import kh.educat.cstad.aftmobilebankingapi.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository
        extends JpaRepository<User,Integer> {

    Optional<User> findByUsername(String username);

}