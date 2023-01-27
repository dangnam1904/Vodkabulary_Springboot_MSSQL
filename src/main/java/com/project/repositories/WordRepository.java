package com.project.repositories;

import com.project.models.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import java.util.List;

@Repository
public interface WordRepository extends JpaRepository<Word, Long> {

    @Modifying
    @Transactional
    @Query(value = "update words set text=:text, meaning=:meaning, pronunciation=:pronunciation" +
            ",topic_id=:topic,type_id=:type_word where wordid=:word_id", nativeQuery = true)
    void customUpdateWord(@Param("word_id") Long word_id, @Param("type_word") Long type_word,
                          @Param("topic") Long topic, @Param("text") String text,
                          @Param("meaning") String meaning, @Param("pronunciation") String pronunciation);

    List<Word> findByTextContaining(String s);
    List<Word> findWordByTopicTopicIDAndTypeWordTypeID(Long type_id, Long topic_id);
    List<Word> findWordByTopicTopicID( Long topic_id);
    List<Word> findWordByTypeWordTypeID(Long type_id);

    @Query(value = "select w.text, w.meaning, w.pronunciation ,w.img, w.sound from words w where w.topic_id = ?1",
            nativeQuery = true)
    public List<String> findAWordByIdTopic(@Param("topicID") Long topicID);


 }
