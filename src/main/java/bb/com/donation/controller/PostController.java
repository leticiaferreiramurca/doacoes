package bb.com.donation.controller;

import bb.com.donation.dto.post.PostSaveDTO;
import bb.com.donation.model.Post;
import bb.com.donation.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/post")
public class PostController {
    final PostService postService;
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping()
    @Operation(summary = "Save Post")
    public Post save(PostSaveDTO postSaveDTO) {
        return postService.save(postSaveDTO);
    }


    @GetMapping()
    @Operation(summary = "List All Posts")
    public List<Post> list() {
        return postService.getAll ();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Post by Id")
    public ResponseEntity<Post> getById(@PathVariable @Valid Long id) {
        try {
            return ResponseEntity.ok (postService.getById (id));
        }catch (Exception e){
            log.error (e.getMessage ());
            return ResponseEntity.status (HttpStatus.INTERNAL_SERVER_ERROR).build ();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Post by id")
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
