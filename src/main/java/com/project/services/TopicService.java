package com.project.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.project.models.Topic;

public interface TopicService {
    List<Topic>  getAllTopic();
    void saveTopic(Topic topic);
    Topic getTopicById(long id);
    void deleteTopicById(long id);
    void  customUpdateTopic(String nametopic, String description, long topicid);

    List<Topic> findByNameTopicContaining(String search);

    Page<Topic> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
