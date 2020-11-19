package com.example.webquiz;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class QuizController {

    public QuizController() {
    }

    @RequestMapping(value = "/api/quiz", method = RequestMethod.POST)
    public Answer getAnswer(@RequestParam ("answer") int answer) {
        boolean x = false;
        if(answer == 3)
            x = true;
        return new Answer(x);
    }

    @GetMapping(path = "/api/quiz")
    public Quiz getQuiz() {
        List<String> options = new ArrayList<>(List.of("Robot","Tea leaf","Cup of coffee","Bug"));
        return new Quiz("The Java Logo","What is depicted on the Java logo?", options);
    }
}
