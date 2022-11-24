package org.ppke.itk.listen.domain.usertrack.repository;

import java.util.List;
import java.util.Optional;

import org.ppke.itk.listen.domain.usertrack.data.UserTrack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTrackRepository extends JpaRepository<UserTrack, Long> {
    List<UserTrack> findAll();

    Optional<UserTrack> findById(Long id);
}
