package com.project.Vodkabulary.servicesimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.project.Vodkabulary.models.Topic;
import com.project.Vodkabulary.repositories.TopicRepository;
import com.project.Vodkabulary.services.TopicService;

@Service
public class TopicServiceImpl implements TopicService {
    @Autowired
    private TopicRepository topicRepository;
    @Override
    public List<Topic> getAllTopic() {
        return topicRepository.findAll();
    }

    @Override
    public void saveTopic(Topic topic) {
        topicRepository.save(topic);
    }

    @Override
    public Topic getTopicById(long id) {
        Optional<Topic> optional = topicRepository.findById(id);
        Topic topic= null;

        if( optional.isPresent()) {
            topic= optional.get();
        } else {
            throw new RuntimeException("word not found for id: " + id);
        }
        return topic;
    }

    @Override
    public void deleteTopicById(long id) {
        topicRepository.deleteById(id);
    }

    public void customUpdateTopic(String nametopic, String description, long topicid){
        topicRepository.customUpdateTopic(nametopic,description,topicid);
    }

    @Override
    public List<Topic> findByNameTopicContaining(String search) {
        return topicRepository.findByNameTopicContaining(search);
    }

    @Override
    public Page<Topic> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending():
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo-1, pageSize);

        return this.topicRepository.findAll(pageable);
    }
}
