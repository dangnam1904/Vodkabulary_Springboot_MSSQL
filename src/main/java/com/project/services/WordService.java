package com.project.services;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.project.models.Word;

public interface WordService {
    List<Word> getAllWord();

    void saveWord(Word word);

    Word getWordById(long id);

    void deleteWordById(long id);

    Page<Word> findPaginated(int pageNo, int PageSize, String sortField, String sortDirection);

    List<Word> searchByText(String s);

    void customUpdateWord(Long wordid, Long type_word, Long topic, String text,
                          String meaning, String pronunciation);

    List<Word> findWordByTopicTopicIDAndTypeWordTypeID(Long type_id, Long topic_id);

    List<Word> findWordByTopicTopicID(Long topic_id);
    Page<Word> findWordByTopicTopicID(Long topic_id, Pageable pageable);
    List<Word> findWordByTypeWordTypeID(Long type_id);

    List<Word> findWordWithSort(String field);

    Page<Word> findWordsWithPaginationAndSorting(int offset, int pageSize, String field);

    //    Page<Word> listAllWord(int pageNum, String sortField, String sortDir);
    Page<Word> listWordOnPage(int numberPage, String sortField, String sortDir);

    List<String> getWordByIdTopic(Long topicID);
}
