package com.example.webquiz;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class QuizController {

    private List<Quiz> quizzes = new ArrayList<>();
    private List<String> options = new ArrayList<>();

    public QuizController() {
    }

    @RequestMapping(value = "/api/quizzes/{id}/solve", method = RequestMethod.POST)
    public Answer getAnswer(@PathVariable int id, @RequestParam("answer") int answer) {
        for (int i = 0; i < quizzes.size(); i++) {
            if (quizzes.get(i).getId() == id && quizzes.get(i).getAnswer() == answer)
                return new Answer(true);
        }
        return new Answer(false);
    }

    @GetMapping(path = "/api/quizzes/{id}")
    public Object getQuiz(@PathVariable int id) {
        for (int i = 0; i < quizzes.size(); i++) {
            if (quizzes.get(i).getId() == id)
                return quizzes.get(i);
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
       /* options = new ArrayList<>(List.of("Robot", "Tea leaf", "Cup of coffee", "Bug"));
        quizzes.add(new Quiz(1, "The Java Logo", "What is depicted on the Java logo?", options, 2));
        options = List.of("Everything goes right", "42", "2+2=4", "11011100");
        quizzes.add(new Quiz((int) (Math.random() * 10), "The Ultimate Question",
                "What is the answer to the Ultimate Question of Life, the Universe and Everything?", options, 1));*/
        if (quizzes.isEmpty())
            return new ArrayList<>();
        return quizzes;
    }
}
