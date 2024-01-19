package com.hastegaex.exercise.bookstorejavavuehastega.repository;

import com.hastegaex.exercise.bookstorejavavuehastega.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    @Query(value = "SELECT b FROM Book b WHERE b.user.id = :userId AND b.deletedAt IS NULL")
    List<Book> findAllByUserIdWhereDeletedAtIsNotNull(@Param("userId") Integer userId);

    Book findAllById(Integer id);
}
