package com.project.Vodkabulary.servicesimpl;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.project.Vodkabulary.models.Word;
import com.project.Vodkabulary.repositories.WordRepository;
import com.project.Vodkabulary.services.WordService;

@Service
public class WordServiceImpl implements WordService {

    @Autowired
    private WordRepository wordRepository;

    @Override
    public List<Word> getAllWord() {
        return wordRepository.findAll();
    }

    @Override
    public void saveWord(Word word) {
        this.wordRepository.save(word);
    }

    @Override
    public Word getWordById(long id) {
        Optional<Word> optional = wordRepository.findById(id);
        Word word = null;

        if( optional.isPresent()) {
            word = optional.get();
        } else {
            throw new RuntimeException("word not found for id: " + id);
        }
        return word;
    }

    @Override
    public void deleteWordById(long id) {
        this.wordRepository.deleteById(id);
    }

    @Override
    public Page<Word> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending():
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo-1, pageSize);

        return this.wordRepository.findAll(pageable);
    }
    @Override
    public List<Word> searchByText(String s) {
       return wordRepository.findByTextContaining(s);
    }

    @Override
    public void customUpdateWord(Long wordid, Long idtype, Long topic_id,
                                 String text, String meaning, String pronunciation) {
        wordRepository.customUpdateWord(wordid,idtype,topic_id,text,meaning,pronunciation);
    }

    @Override
    public List<Word> findWordByTopicTopicIDAndTypeWordTypeID(Long type_id, Long topic_id) {
        return wordRepository.findWordByTopicTopicIDAndTypeWordTypeID(type_id,topic_id);
    }

    @Override
    public List<Word> findWordByTopicTopicID(Long topic_id) {
        return wordRepository.findWordByTopicTopicID(topic_id);
    }

    @Override
    public Page<Word> findWordByTopicTopicID(Long topic_id, Pageable pageable) {
        return null;
    }

    @Override
    public List<Word> findWordByTypeWordTypeID(Long type_id) {
        return wordRepository.findWordByTypeWordTypeID(type_id);
    }

    @Override
    public List<Word> findWordWithSort(String field) {
        return wordRepository.findAll(Sort.by(Sort.Direction.ASC,field));
    }

    public Page<Word> findWordsWithPaginationAndSorting(int offset,int pageSize,String field){
        Page<Word> words = wordRepository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field)));
        return  words;
    }

     public Page<Word> listWordOnPage(int numberPage, String sortField, String sortDir){
        Sort sort= Sort.by(sortField);
        sort= sortDir.equals("asc") ? sort.ascending() : sort.descending();
        Pageable pageable= PageRequest.of(numberPage-1,10,sort);
        return  wordRepository.findAll(pageable);
    }

//    @Override
//    Page<Word> findWordByTopicTopicID(Long topic_id, Pageable pageable){
//
//            return (Page<Word>) wordRepository.findWordByTopicTopicID(topic_id);
//    }


//    public Page<Word> listAllWord(int pageNum, String sortField, String sortDir) {
//
//        Pageable pageable = PageRequest.of(pageNum - 1, 5,
//                sortDir.equals("asc") ? Sort.by(sortField).ascending()
//                        : Sort.by(sortField).descending()
//        );
//
//        return wordRepository.findAll(pageable);
//    }

    @Override
    public List<String> getWordByIdTopic(Long topicID) {
        return wordRepository.findAWordByIdTopic(topicID);
    }

}
