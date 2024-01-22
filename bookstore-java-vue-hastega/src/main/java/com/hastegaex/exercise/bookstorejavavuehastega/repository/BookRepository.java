package com.hastegaex.exercise.bookstorejavavuehastega.repository;

import com.hastegaex.exercise.bookstorejavavuehastega.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<Book, Integer> {

    //    @Query(value = "SELECT b FROM Book b WHERE b.user.id = :userId AND b.deletedAt IS NULL")
//    List<Book> findAllByUserIdWhereDeletedAtIsNotNull(@Param("userId") Integer userId);
    @Query(value = "SELECT b FROM Book b WHERE b.user.id = :userId AND b.deletedAt IS NULL")
    Page<Book> findAllByUserIdAndDeletedAtIsNull(@Param("userId") Integer userId, Pageable pageable);

    Book findAllById(Integer id);
}
