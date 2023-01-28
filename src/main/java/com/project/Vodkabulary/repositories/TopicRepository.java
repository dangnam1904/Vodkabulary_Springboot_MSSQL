package com.project.Vodkabulary.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.Vodkabulary.models.Topic;

import jakarta.transaction.Transactional;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
    @Modifying
    @Transactional
    @Query(value = "update  topic   set nametopic=:nametopic,  description=:description where topicID=:topicId", nativeQuery = true)
    void customUpdateTopic(@Param("nametopic") String nameTopic,
                         @Param("description") String description,
                         @Param("topicId") Long topicID);

   List<Topic> findByNameTopicContaining(String search);
}
