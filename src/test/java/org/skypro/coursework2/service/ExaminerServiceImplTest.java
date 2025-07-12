package org.skypro.coursework2.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skypro.coursework2.model.Question;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class ExaminerServiceImplTest {

    private ExaminerServiceImpl examinerService;
    private JavaQuestionService javaQuestionService;

    @BeforeEach
    void setUp() {
        javaQuestionService = new JavaQuestionService();
        examinerService = new ExaminerServiceImpl(javaQuestionService);

        javaQuestionService.add("Вопрос 1", "Ответ 1");
        javaQuestionService.add("Вопрос 2", "Ответ 2");
        javaQuestionService.add("Вопрос 3", "Ответ 3");
    }

    @Test
    void getQuestions_ShouldReturnRequestedAmount() {
        int amount = 2;
        Collection<Question> questions = examinerService.getQuestions(amount);

        assertNotNull(questions);
        assertEquals(amount, questions.size());

        assertEquals(questions.size(), questions.stream().distinct().count());
    }

    @Test
    void getQuestions_ShouldReturnAllQuestions_WhenAmountEqualsSize() {
        int amount = javaQuestionService.getAll().size();
        Collection<Question> questions = examinerService.getQuestions(amount);

        assertNotNull(questions);
        assertEquals(amount, questions.size());
    }

    @Test
    void getQuestions_ShouldThrowException_WhenAmountTooLarge() {
        int amount = javaQuestionService.getAll().size() + 1;

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            examinerService.getQuestions(amount);
        });

        assertEquals("Запрошено больше вопросов, чем доступно.", exception.getReason());
    }
}