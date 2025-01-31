package dk.clbo.controller;

import dk.clbo.model.Comment;
import dk.clbo.model.Post;
import dk.clbo.repository.CommentRepository;
import dk.clbo.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class BlogController {

    @Autowired
    PostRepository postRepository;
    @Autowired
    CommentRepository commentRepository;

    // Read All
    @CrossOrigin(origins = "*")
    @GetMapping("/post")
    public Iterable<Post> index(){
        return postRepository.findAll();
    }

    // Read One
    @CrossOrigin(origins = "*")
    @GetMapping("/post/{id}")
    public ResponseEntity<Optional<Post>> getOne(@PathVariable Long id){

        Optional<Post> res = postRepository.findById(id);

        if(res.isPresent())
            return ResponseEntity.status(200).body(res);
        else{
            return ResponseEntity.status(404).body(res);
        }

    }

    // Insert
    @CrossOrigin(origins = "*", exposedHeaders = "Location")
    @PostMapping("/post")
    public ResponseEntity<String> insert(@ModelAttribute Post p){
        Post post = postRepository.save(p);
        return ResponseEntity.status(201).header("Location", "/" + post.getId()).body("{msg : post created}");
    }

    // Delete
    @CrossOrigin(origins = "*")
    @DeleteMapping("/post/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        postRepository.deleteById(id);
        return ResponseEntity.status(200).body("{msg : Post Deleted}");
    }


    // Update
    @CrossOrigin(origins = "*")
    @PutMapping("/post/")
    public ResponseEntity<String> update(@ModelAttribute Post p){
        postRepository.save(p);
        return ResponseEntity.status(204).body("");
    }
    // create comment
    @CrossOrigin(origins = "*", exposedHeaders = "Location")
    @PutMapping("/comment/{id}")
    public ResponseEntity<String> updateComment(@ModelAttribute Comment comment){

        commentRepository.save(comment);
        return ResponseEntity.status(204).header("Location", "/" + comment.getId()).body("{msg : Comment created}");

    }

}
