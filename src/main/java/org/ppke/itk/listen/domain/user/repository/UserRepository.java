package org.ppke.itk.listen.domain.user.repository;

import org.ppke.itk.listen.domain.user.data.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAll();

    Optional<User> findById(Long id);

    /* 
    public List<User> getTeams() {
        return List.of(
            new User(0, "Marco Polo", "One of the greatest adventurer of our world", "https://www.worldometers.info/img/flags/br-flag.gif"),
            new User(1, "Colombus Christoph", "India is so nice", "https://www.worldometers.info/img/flags/br-flag.gif")
        );
    }
    */
}
