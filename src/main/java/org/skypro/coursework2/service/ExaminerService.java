package org.skypro.coursework2.service;

import org.skypro.coursework2.model.Question;

import java.util.Collection;

public interface ExaminerService {
    Collection<Question> getQuestions(int amount);
}