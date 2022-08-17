package bb.com.donation.controller;

import bb.com.donation.dto.post.PostSaveDTO;
import bb.com.donation.dto.post.PostUpdateDTO;
import bb.com.donation.model.Post;
import bb.com.donation.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.bind.ValidationException;
import java.util.List;

@RestController
@Slf4j
@Tag(name = "Posts", description = "Gerenciamento dos Posts")
@RequestMapping("/post")
public class PostController {
    final PostService postService;
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping()
    @Operation(summary = "Save Post", tags = {"Posts"})
    public ResponseEntity<Post> save(PostSaveDTO postSaveDTO)  {
        try {
            Post post = postService.save (postSaveDTO);
            return ResponseEntity.ok (post);
        } catch (ValidationException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/{id}")
    @Operation(summary = "Update Post", tags = {"Posts"})
    public ResponseEntity<Post> edit(@RequestBody PostUpdateDTO postUpdateDTO) {
        try {
            Post post = postUpdateDTO.toPost ();
            return ResponseEntity.ok (postService.edit(post));
        }catch (Exception e){
            log.error (e.getMessage ());
            return ResponseEntity.status (HttpStatus.INTERNAL_SERVER_ERROR).build ();
        }
    }

    @GetMapping()
    @Operation(summary = "List All Posts", tags = {"Posts"})
    public List<Post> list() {
        return postService.getAll ();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Post by Id", tags = {"Posts"})
    public ResponseEntity<Post> getById(@PathVariable @Valid Long id) {
        try {
            return ResponseEntity.ok (postService.getById (id));
        }catch (Exception e){
            log.error (e.getMessage ());
            return ResponseEntity.status (HttpStatus.INTERNAL_SERVER_ERROR).build ();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Post by id", tags = {"Posts"})
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            postService.delete (id);
            return ResponseEntity.ok ().build ();
        }catch ( Exception e ) {
            log.error (e.getMessage ());
            return ResponseEntity.status (HttpStatus.INTERNAL_SERVER_ERROR).build ();
        }
    }
}
