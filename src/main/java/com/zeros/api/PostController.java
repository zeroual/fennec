package com.zeros.api;

import com.zeros.domain.entity.Post;
import com.zeros.domain.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping(value = EndpointsPath.POST_PATH)
public class PostController {
    @Autowired
    private PostRepository postRepository;

    public PostController() {

    }

    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Post savePost(@RequestBody Post post,@AuthenticationPrincipal Principal principal) {
        post.setUsername(principal.getName());
        return postRepository.save(post);
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody Iterable<Post> fetchAllPosts(){
        return postRepository.findAll();
    }

}
