package org.skypro.coursework2.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skypro.coursework2.model.Question;

import java.util.Collection;
import java.util.NoSuchElementException;



import static org.junit.jupiter.api.Assertions.*;

class JavaQuestionServiceTest {

    private JavaQuestionService service;

    @BeforeEach
    void setUp() {
        service = new JavaQuestionService();
    }

    @Test
    void add_ShouldAddQuestion() {
        Question q = service.add("Что такое Java?", "Язык программирования");

        assertEquals("Что такое Java?", q.getQuestion());
        assertEquals("Язык программирования", q.getAnswer());

        Collection<Question> all = service.getAll();
        assertTrue(all.contains(q));
    }

    @Test
    void remove_ShouldRemoveQuestion() {

        Question q = service.add("Вопрос", "Ответ");
        Question removed = service.remove("Вопрос", "Ответ");


        assertEquals(q, removed);

        assertFalse(service.getAll().contains(q));
    }

    @Test
    void remove_NonExistentQuestion_ShouldThrow() {

        assertThrows(NoSuchElementException.class, () -> {
            service.remove("Нет такого вопроса", "Нет такого ответа");
        });
    }

    @Test
    void getRandomQuestion_ShouldReturnQuestion_WhenNotEmpty() {
        service.add("Вопрос1", "Ответ1");
        Question random = service.getRandomQuestion();

        assertNotNull(random);
        assertTrue(service.getAll().contains(random));
    }

    @Test
    void getRandomQuestion_EmptyService_ShouldThrow() {
        assertThrows(NoSuchElementException.class, () -> {
            service.getRandomQuestion();
        });
    }

    @Test
    void getAll_ShouldReturnAllAddedQuestions() {
        Question q1 = service.add("Вопрос1", "Ответ1");
        Question q2 = service.add("Вопрос2", "Ответ2");

        Collection<Question> all = service.getAll();

        assertTrue(all.contains(q1));
        assertTrue(all.contains(q2));
    }
}