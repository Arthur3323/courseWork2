package org.skypro.coursework2.controller;

import org.skypro.coursework2.model.Question;
import org.skypro.coursework2.service.JavaQuestionService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/exam/java")
public class JavaQuestionController {

    private final JavaQuestionService javaQuestionService;

    public JavaQuestionController(JavaQuestionService javaQuestionService) {
        this.javaQuestionService = javaQuestionService;
    }

    @GetMapping("/add")
    public Question add(@RequestParam String question,
                        @RequestParam String answer) {
        return javaQuestionService.add(question, answer);
    }

    @GetMapping("/remove")
    public Question remove(@RequestParam String question,
                           @RequestParam String answer) {
        return javaQuestionService.remove(question, answer);
    }

    @GetMapping
    public Collection<Question> getAll() {
        return javaQuestionService.getAll();
    }


}
