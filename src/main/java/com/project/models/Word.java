package com.project.models;

import lombok.*;

import jakarta.persistence.*;
import java.util.Collection;


@Entity
@Table(name="words")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Word {
    public long getWordID() {
		return wordID;
	}
	public void setWordID(long wordID) {
		this.wordID = wordID;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getMeaning() {
		return meaning;
	}
	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}
	public String getPronunciation() {
		return pronunciation;
	}
	public void setPronunciation(String pronunciation) {
		this.pronunciation = pronunciation;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getSound() {
		return sound;
	}
	public void setSound(String sound) {
		this.sound = sound;
	}
	public String getTextexample() {
		return textexample;
	}
	public void setTextexample(String textexample) {
		this.textexample = textexample;
	}
	public Collection<Questions> getQuestions() {
		return questions;
	}
	public void setQuestions(Collection<Questions> questions) {
		this.questions = questions;
	}
	public Collection<User> getUsers() {
		return users;
	}
	public void setUsers(Collection<User> users) {
		this.users = users;
	}
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long wordID;
    @Column(name="text", columnDefinition = "nvarchar(40)  null")
    private String text;
    @Column(name="meaning", columnDefinition = "nvarchar(40)  null")
    private String meaning;
    @Column(name="pronunciation", columnDefinition = "nvarchar(50)  null")
    private String pronunciation;
    @Column(name="img", columnDefinition = "nvarchar(300)  null")
    private String img;
    @Column(name="sound", columnDefinition = "nvarchar(100)  null")
    private String sound;

    @Column(name="textexample", columnDefinition = "nvarchar(600)  null")
    private String textexample;

    @ManyToOne // LAZY để tránh việc truy xuất dữ liệu không cần thiết. Lúc nào cần thì mới query
    @JoinColumn(name = "type_id") // thông qua khóa ngoại
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private TypeWord typeWord;

    @ManyToOne
    @JoinColumn(name = "topic_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Topic topic;


    public TypeWord getTypeWord() {
		return typeWord;
	}
	public void setTypeWord(TypeWord typeWord) {
		this.typeWord = typeWord;
	}
	public Topic getTopic() {
		return topic;
	}
	public void setTopic(Topic topic) {
		this.topic = topic;
	}
	@OneToMany(mappedBy = "word", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Khoonhg sử dụng trong toString()
    private Collection<Questions> questions;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Khoonhg sử dụng trong toString()

    @JoinTable(name = "studied_word",

            joinColumns = @JoinColumn(name = "word_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Collection<User> users;

    public Word(String text, String meaning, String pronunciation, String img, String sound, TypeWord typeWord, Topic topic, Collection<Questions> questions, Collection<User> users) {
        this.text = text;
        this.meaning = meaning;
        this.pronunciation = pronunciation;
        this.img = img;
        this.sound = sound;
        this.typeWord = typeWord;
        this.topic = topic;
    }
    public Word(String text, String mean, String pron, String img, String sound, Long id_type, Long topic_id) {
        this.text = text;
        this.meaning = mean;
        this.pronunciation = pron;
        this.img = img;
        this.sound = sound;
        this.setTypeWord(new TypeWord());
        this.typeWord.setTypeID(id_type);
        this.setTopic((new Topic()));
        this.topic.setTopicID(topic_id);
    }
	public Word() {
		// TODO Auto-generated constructor stub
	}
	
}

