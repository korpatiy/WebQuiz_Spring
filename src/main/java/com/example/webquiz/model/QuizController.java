package com.example.webquiz.model;

import com.example.webquiz.api.QuizRepository;
import com.example.webquiz.entity.Answer;
import com.example.webquiz.IDGeneration;
import com.example.webquiz.entity.ResponseQuiz;
import com.example.webquiz.entity.Quiz;
import org.hibernate.collection.internal.PersistentBag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController {

    @Autowired
    private QuizRepository quizRepository;

    private List<Quiz> quizzes = new ArrayList<>();

    public QuizController() {
    }



    @PostMapping(path = "/{id}/solve")
    public ResponseQuiz getAnswer(@PathVariable Long id, @RequestBody Answer answer) {
        Quiz quiz = quizRepository.findById(id).get();
        if (answer.getAnswer().equals(quiz.getAnswer()))
            return new ResponseQuiz(true);
        return new ResponseQuiz(false);

        /* for (var quiz : quizzes)
            if (quiz.getId() == id && quiz.getAnswer().equals(answer.getAnswer()))
                return new ResponseQuiz(true);
        return new ResponseQuiz(false);*/
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Quiz> getQuiz(@PathVariable Long id) {
        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Quiz not found for this id"));
        return ResponseEntity.ok().body(quiz);
        /*for (var quiz : quizzes)
            if (quiz.getId() == id)
                return quiz;*/
        //throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Quiz not found for this id");
    }

    @PostMapping()
    public Object createQuiz(@Valid @RequestBody Quiz quiz) {
        quiz.setId(IDGeneration.generateID());
        /*quizzes.add(quiz);
        return quiz;*/
        return quizRepository.save(quiz);
    }

    @GetMapping()
    public List<Quiz> getQuizzes() {
        /*if (quizzes.isEmpty())
            return new ArrayList<>();*/
        //return quizzes;
        return quizRepository.findAll();
    }

    /*@DeleteMapping("/{id}")
    public Map<String, Boolean> deleteQuiz(@PathVariable int id) {

    }*/
}
