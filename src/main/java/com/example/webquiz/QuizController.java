package com.example.webquiz;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController {

    private List<Quiz> quizzes = new ArrayList<>();

    public QuizController() {
    }

    @PostMapping(path = "/{id}/solve")
    public ResponseQuiz getAnswer(@PathVariable int id, @RequestBody Answer answer) {
        for (var quiz : quizzes)
            if (quiz.getId() == id && quiz.getAnswer().equals(answer.getAnswer()))
                return new ResponseQuiz(true);
        return new ResponseQuiz(false);
    }

    @GetMapping(path = "/{id}")
    public Object getQuiz(@PathVariable int id) {
        for (var quiz : quizzes)
            if (quiz.getId() == id)
                return quiz;
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @PostMapping()
    public Object createQuiz(@Valid @RequestBody Quiz quiz) {
        quiz.setId(IDGeneration.generateID());
        quizzes.add(quiz);
        return quiz;
    }

    @GetMapping()
    public List<Quiz> getQuizzes() {
        if (quizzes.isEmpty())
            return new ArrayList<>();
        else return quizzes;
    }
}
