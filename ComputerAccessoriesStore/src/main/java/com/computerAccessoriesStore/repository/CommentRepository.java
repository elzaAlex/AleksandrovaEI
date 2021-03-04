package com.computerAccessoriesStore.repository;

import com.computerAccessoriesStore.models.Comment;
import com.computerAccessoriesStore.transfer.CommentDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository  extends JpaRepository<Comment, Long> {
    @Query("SELECT a from Comment a where a.seller.id = :id")
    List<Comment> findAllBySellerId(@Param("id") Long id);
}
