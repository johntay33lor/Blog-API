package com.blog.api.Service;

import com.blog.api.Controller.BlogController;
import com.blog.api.Exception.PostNotFoundException;
import com.blog.api.Model.Post;
import com.blog.api.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;


    public List<Post> getAllPosts() {
        List<Post> posts = (List<Post>) postRepository.findAll();
        for (Post post : posts) {
            post.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BlogController.class)
                    .getPostById(post.getId())).withSelfRel());
        }
        return posts;
    }


    public void addPost(Post post) {
        postRepository.save(post);
    }

    public Post updatePost(Long id, Post updatedPost) {
        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isPresent()) {
            Post existingPost = optionalPost.get();
            existingPost.setTitle(updatedPost.getTitle());
            existingPost.setContent(updatedPost.getContent());
            existingPost.setAuthor(updatedPost.getAuthor());
            postRepository.save(existingPost);
            return existingPost;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found with id: " + id);
        }
    }


    public Post deletePost(Long id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        return optionalPost.orElseThrow(() -> new PostNotFoundException("Post not found with id: " + id));
        //postRepository.deleteById(id);
    }

    public Post getPostById(Long id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        return optionalPost.orElseThrow(() -> new PostNotFoundException("Post not found with id: " + id));
    }
}

