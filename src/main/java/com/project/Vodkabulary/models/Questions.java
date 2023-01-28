package com.project.Vodkabulary.models;

import lombok.*;

import jakarta.persistence.*;
import java.util.Collection;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Questions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long questionId;
    @Column(name="question", columnDefinition = "nvarchar(100)  null")
    private  String question;
    @Column(name="answer1", columnDefinition = "nvarchar(40)  null")
    private  String answer1;
    @Column(name="answer2", columnDefinition = "nvarchar(40)  null")
    private  String answer2;
    @Column(name="answer3", columnDefinition = "nvarchar(40)  null")
    private  String answer3;
    @Column(name="trueanswer", columnDefinition = "nvarchar(40)  null")
    private  String trueAnswer;

//    @OneToMany(mappedBy = "questions", cascade = CascadeType.ALL)
//    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
//    @ToString.Exclude // Khoonhg sử dụng trong toString()
//    private Collection<Word> words;

    @ManyToOne
    @JoinColumn(name = "word_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Word word;

    // mappedBy trỏ tới tên biến persons ở trong Address.
    @ManyToMany(mappedBy = "questions")
    // LAZY để tránh việc truy xuất dữ liệu không cần thiết. Lúc nào cần thì mới query
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Test> tests;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Khoonhg sử dụng trong toString()

    @JoinTable(name = "testhistory_detail",

            joinColumns = @JoinColumn(name = "testhistory_id"),
            inverseJoinColumns = @JoinColumn(name = "question_id")
    )
    private Collection<TestHistory> testhistorys;



}
