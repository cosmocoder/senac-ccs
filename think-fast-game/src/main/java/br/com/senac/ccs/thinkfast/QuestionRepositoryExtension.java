package br.com.senac.ccs.thinkfast;

public interface QuestionRepositoryExtension {
    Question findNext(Question answered);
    Question getCurrent();
}
