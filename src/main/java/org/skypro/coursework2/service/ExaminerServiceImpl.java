package org.skypro.coursework2.service;

import org.skypro.coursework2.model.Question;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final JavaQuestionService questionService;

    public ExaminerServiceImpl(JavaQuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        Set<Question> result = new HashSet<>();

        if (amount > questionService.getAll().size()) {

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Запрошено больше вопросов, чем доступно.");
        }

        while (result.size() < amount) {
            result.add(questionService.getRandomQuestion());
        }

        return result;
    }
}
