package org.skypro.coursework2.service;

import org.skypro.coursework2.model.Question;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {

    private final Set<Question> questions = new HashSet<>();
    private final Random random = new Random();

    @Override
    public Question add(String question, String answer) {
        Question q = new Question(question, answer);
        questions.add(q);
        return q;
    }

    @Override
    public Question remove(String question, String answer) {
        Question q = new Question(question, answer);
        if (!questions.remove(q)) {
            throw new NoSuchElementException("Вопрос не найден: " + question);
        }
        return q;
    }

    @Override
    public Collection<Question> getAll() {
        return Collections.unmodifiableSet(questions);
    }

    @Override
    public Question getRandomQuestion() {
        if (questions.isEmpty()) {
            throw new NoSuchElementException("Вопросов нет.");
        }
        List<Question> list = new ArrayList<>(questions);
        return list.get(random.nextInt(list.size()));
    }
}
