package com.codeup.codeupspringblog.controllers;
import com.codeup.codeupspringblog.Services.EmailService;
import com.codeup.codeupspringblog.models.Post;
import com.codeup.codeupspringblog.models.User;
import com.codeup.codeupspringblog.repositories.PostRepository;
import com.codeup.codeupspringblog.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {


    private final PostRepository postDao;
    private final UserRepository usersDao;

    private final EmailService emailService;


    public PostController(PostRepository postDao, UserRepository usersDao, EmailService emailService) {
        this.postDao = postDao;
        this.usersDao = usersDao;
        this.emailService = emailService;
    }



    @GetMapping("/posts")
    public String index(Model model) {

        model.addAttribute("posts", postDao.findAll());
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String show(@PathVariable long id, Model model) {
        Post post = new Post("Sample Post", "This is a sample post.");
        model.addAttribute("post", post);
        return "posts/show";
    }

    @GetMapping("/posts/create")
    public String create(Model model) {
        model.addAttribute("post", new Post());
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String createPost(@ModelAttribute Post post) {
        User user = usersDao.findById(1L);
        Post newPost = new Post(post.getTitle(), post.getBody(), user);
        postDao.save(newPost);
        return "redirect:/posts";
    }

    @PostMapping("/posts/{id}/edit")
    public String editPost(@ModelAttribute Post post, Long id) {
        Post editedPost = postDao.findById(id);
        editedPost.setTitle(post.getTitle());
        editedPost.setBody(post.getBody());
        postDao.save(editedPost);
        return "redirect:/posts";
    }

    public void prepareAndSend(String subject, String body){

    }


}
