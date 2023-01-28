package com.project.Vodkabulary.models;

import lombok.*;

import jakarta.persistence.*;
import java.util.Collection;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long notiID;
    @Column(name="content", columnDefinition = "nvarchar(400)  null")
    private String content;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    // Quan hệ n-n với đối tượng ở dưới (Notication) (1 thông báo có nhiều người có)
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Khoonhg sử dụng trong toString()

    @JoinTable(name = "notification_user",

            joinColumns = @JoinColumn(name = "notiid"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Collection<User> users;
}
