package bb.com.donation.controller;


import bb.com.donation.dto.post.PostSaveDTO;
import bb.com.donation.model.Post;
import bb.com.donation.service.impl.PostServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/post")
public class PostController {
    final PostServiceImpl postService;

    public PostController(PostServiceImpl postService) {
        this.postService = postService;
    }

    @GetMapping("/getAll")
    @Operation(summary = "Get all posts", description = "Get all posts")
    public ResponseEntity<List<Post>> getAll() {
        try {
            log.info("Get all posts");
            return ResponseEntity.ok (postService.getAll ());
        }catch (Exception e){
            log.error (e.getMessage ());
            return ResponseEntity.status (HttpStatus.INTERNAL_SERVER_ERROR).build ();
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get post by id", description = "Get post by id")
    public ResponseEntity<Post> getById(@PathVariable @Valid Long id) {
        try {
            return ResponseEntity.ok (postService.getById (id));
        }catch (Exception e){
            log.error (e.getMessage ());
            return ResponseEntity.status (HttpStatus.INTERNAL_SERVER_ERROR).build ();
        }
    }

    @RequestMapping("filter/{name}")
    @Operation(summary = "Get post by name", description = "Get post by name")
    public ResponseEntity<Page<Post>> getByName(@PathVariable("name") @Valid String name, Pageable pageable) {

        try {
            Page<Post> post = postService.getByName (name, pageable);
            return ResponseEntity.ok(post);
        } catch (Exception e){
            log.error (e.getMessage());
            return ResponseEntity.status (HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/save")
    @Operation(summary = "Save post", description = "Save post")
    public ResponseEntity<Post> save(@RequestBody @Valid PostSaveDTO post) {
        try {
            return ResponseEntity.ok (postService.save (post));
        }catch (Exception e){
            log.error (e.getMessage ());
            return ResponseEntity.status (HttpStatus.INTERNAL_SERVER_ERROR).build ();
        }
    }

    @DeleteMapping
    @Operation(summary = "Delete post", description = "Delete post")
    public ResponseEntity<Void> delete(@RequestParam @Valid Long id) {
        try {
            postService.delete (id);
            return ResponseEntity.ok ().build ();
        }catch (Exception e){
            log.error (e.getMessage ());
            return ResponseEntity.status (HttpStatus.INTERNAL_SERVER_ERROR).build ();
        }
    }
}
