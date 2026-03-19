package com.bulkadishs.blog.service;

import com.bulkadishs.blog.dto.CommentDto;
import com.bulkadishs.blog.exception.ResourceNotFoundException;
import com.bulkadishs.blog.model.Comment;
import com.bulkadishs.blog.model.Post;
import com.bulkadishs.blog.model.User;
import com.bulkadishs.blog.repository.CommentRepo;
import com.bulkadishs.blog.repository.PostRepo;
import com.bulkadishs.blog.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CommentService {
    private CommentRepo commentRepo;
    private PostRepo postRepo;
    private UserRepo userRepo;

    @Autowired

    public CommentService(CommentRepo commentRepo, PostRepo postRepo, UserRepo userRepo) {
        this.commentRepo = commentRepo;
        this.postRepo = postRepo;
        this.userRepo = userRepo;
    }

    public CommentDto createComment(Comment comment, Long postId, Long userId) {
        Post post = postRepo.findById(postId);
        User user = userRepo.findById(userId);

        if (user == null) {
            throw new ResourceNotFoundException("Unable to create post. User with id: " + userId + " does not exist!");
        } else if (post == null) {
            throw new ResourceNotFoundException("Unable to create post. Post with id: " + postId + " does not exist!");
        }

        comment.setPost(post);
        comment.setAuthor(user);
        comment.setCreatedAt(LocalDate.now());

        return CommentDto.from(commentRepo.save(comment));
    }

    public CommentDto getOne(Long id) {
        Comment foundComment = commentRepo.findById(id);

        if (foundComment == null) {
            throw new ResourceNotFoundException("Comment not found with this id: " + id);
        }

        return CommentDto.from(foundComment);
    }

    public List<CommentDto> getAll() {
        List<Comment> allComments = commentRepo.findAll();
        return allComments.stream()
                .map(CommentDto::from)
                .toList();
    }

    public void delete(Long id) {
        if(commentRepo.findById(id) == null) {
            throw new ResourceNotFoundException("Unable to delete! Comment with id: " + id + " not found");
        }
        commentRepo.deleteById(id);
    }

}
