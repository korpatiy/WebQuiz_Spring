package com.example.webquiz;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
public class QuizController {

    private List<Quiz> quizzes = new ArrayList<>();

    public QuizController() {
    }

    private boolean checkAnswer(int i, Answer answer) {
        List<Integer> serverAnswers = quizzes.get(i).getAnswer();
        return serverAnswers.equals(answer.getAnswer());
    }

    @RequestMapping(value = "/api/quizzes/{id}/solve", method = RequestMethod.POST)
    public ResponseQuiz getAnswer(@PathVariable int id, @RequestBody Answer answer) {
        for (int i = 0; i < quizzes.size(); i++) {
            if (quizzes.get(i).getId() == id && checkAnswer(i, answer)) {
                return new ResponseQuiz(true);
            }
        }
        return new ResponseQuiz(false);
    }

    @GetMapping(path = "/api/quizzes/{id}")
    public Object getQuiz(@PathVariable int id) {
        for (Quiz quiz : quizzes) {
            if (quiz.getId() == id)
                return quiz;
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/api/quizzes", consumes = "application/json")
    public Quiz createQuiz(@RequestBody Quiz quiz) {
        quiz.setId((int) (Math.random() * 10));
        quizzes.add(quiz);
        return quiz;
    }

    @RequestMapping(value = "/api/quizzes", method = RequestMethod.GET)
    public @ResponseBody
    List<Quiz> getQuizzes() {
        if (quizzes.isEmpty())
            return new ArrayList<>();
        return quizzes;
    }
}
