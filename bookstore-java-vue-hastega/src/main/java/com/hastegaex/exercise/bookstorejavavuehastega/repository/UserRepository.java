package com.hastegaex.exercise.bookstorejavavuehastega.repository;

import com.hastegaex.exercise.bookstorejavavuehastega.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
