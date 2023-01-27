package com.project.models;

import lombok.*;

import jakarta.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "topic")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Topic {
    public String getNameTopic() {
		return nameTopic;
	}

	public void setNameTopic(String nameTopic) {
		this.nameTopic = nameTopic;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Collection<Word> getWord() {
		return word;
	}

	public void setWord(Collection<Word> word) {
		this.word = word;
	}

	public Collection<Games> getGames() {
		return games;
	}

	public void setGames(Collection<Games> games) {
		this.games = games;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long  topicID;
    @Column(name="nametopic", columnDefinition = "nvarchar(60)  null")
    private String nameTopic;
    @Column(name="img", columnDefinition = "nvarchar(1000)  null")
    private String img;
    @Column(name="description", columnDefinition = "nvarchar(100)  null")
    private String description;

    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Khoonhg sử dụng trong toString()
    private Collection<Word> word;

    @ManyToMany(mappedBy = "topics", cascade = CascadeType.ALL )
    // Quan hệ n-n với đối tượng ở dưới (Notication) (1 thông báo có nhiều người có)
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Khoonhg sử dụng trong toString()


    private Collection<Games> games;

	public long getTopicID() {
		return topicID;
	}

	public void setTopicID(long topicID) {
		this.topicID = topicID;
	}
    
}
