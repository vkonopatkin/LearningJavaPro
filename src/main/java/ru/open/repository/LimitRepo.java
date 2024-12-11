package ru.open.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.open.entities.Limit;

import java.util.Optional;

@Repository
public interface LimitRepo extends JpaRepository<Limit, Long> {
	Optional<Limit> findByUserId(long userId);
}
