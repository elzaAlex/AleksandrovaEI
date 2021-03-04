package com.computerAccessoriesStore.service.impl;

import com.computerAccessoriesStore.models.Comment;
import com.computerAccessoriesStore.models.User;
import com.computerAccessoriesStore.repository.CommentRepository;
import com.computerAccessoriesStore.service.CommentService;
import com.computerAccessoriesStore.transfer.CommentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public void add(CommentDTO dto) {
        Comment comment = Comment.builder()
                .message(dto.getMessage())
                .rating(dto.getRating())
                .created_at(new Timestamp(System.currentTimeMillis()))
                .buyer(User.builder().id(dto.getIdBuyer()).build())
                .seller(User.builder().id(dto.getIdSeller()).build()).build();
        commentRepository.save(comment);
    }

    @Override
    public void deleteById(Long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public Optional<Comment> getById(Long id) {
        return commentRepository.findById(id);
    }

    @Override
    public void edit(CommentDTO dto) {
        Comment comment = Comment.builder()
                .id(dto.getId())
                .message(dto.getMessage())
                .rating(dto.getRating())
                .created_at(new Timestamp(System.currentTimeMillis()))
                .buyer(User.builder().id(dto.getId()).build())
                .seller(User.builder().id(dto.getId()).build()).build();
        commentRepository.save(comment);
    }

    @Override
    public List<Comment> findAllBySellerId(Long id) {
        return commentRepository.findAllBySellerId(id);
    }

    @Override
    public List<Comment> getCommentSellerBySortDate(Long id){
        return StreamSupport.stream(commentRepository
                .findAllBySellerId(id).spliterator(), false)
                .sorted(Comparator.comparing(Comment::getCreated_at).reversed())
                .collect(Collectors.toList());
    }
}
