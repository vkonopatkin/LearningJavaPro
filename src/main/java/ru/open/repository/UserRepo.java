package ru.open.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.open.entities.User;

@Repository
public interface UserRepo extends JpaRepository <User, Long>{
}
