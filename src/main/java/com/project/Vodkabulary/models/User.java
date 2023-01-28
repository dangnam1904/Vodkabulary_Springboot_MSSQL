package com.project.Vodkabulary.models;

import lombok.*;

import jakarta.persistence.*;
import java.util.Collection;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name="users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    public long getUserID() {
		return userID;
	}

	public void setUserID(long userID) {
		this.userID = userID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getLearntime() {
		return learntime;
	}

	public void setLearntime(LocalDate learntime) {
		this.learntime = learntime;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Collection<Notification> getNotifications() {
		return notifications;
	}

	public void setNotifications(Collection<Notification> notifications) {
		this.notifications = notifications;
	}

	public Collection<Word> getWords() {
		return words;
	}

	public void setWords(Collection<Word> words) {
		this.words = words;
	}

	public Collection<TestHistory> getHistories() {
		return histories;
	}

	public void setHistories(Collection<TestHistory> histories) {
		this.histories = histories;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userID;
  

	@Column(name="username", columnDefinition = "nvarchar(30) not null",unique = true)
    private String username;
    @Column(name="password", columnDefinition = "nvarchar(200) not null")
    private String password;
    @Column(name="name", columnDefinition = "nvarchar(50)")
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;
    @Column(name="email", columnDefinition = "nvarchar(100)  null",unique = true)
    private String email;
    private LocalDate learntime;
    @Column(name="point", columnDefinition = "int  null ")
    private int point;

    @Column(name="avatar", columnDefinition = "nvarchar(MAX) null")
    private String avatar;
    @Column(name = "createdate", columnDefinition = "datetime null")
    private LocalDateTime createdate;
    @Column(name = "updatetime", columnDefinition = "datetime null")
    private  LocalDateTime updatetime;
    @Column(name = "image", columnDefinition = "nvarchar(500) null")
    private  String image;

    public LocalDateTime getCreatedate() {
        return createdate;
    }

    public void setCreatedate(LocalDateTime createdate) {
        LocalDateTime nowDate= LocalDateTime.now();
        this.createdate = nowDate;
    }

    public LocalDateTime getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(LocalDateTime updatetime) {
        LocalDateTime nowDate= LocalDateTime.now();
        this.updatetime = nowDate;
    }

    @ManyToOne(fetch = FetchType.EAGER) // LAZY để tránh việc truy xuất dữ liệu không cần thiết. Lúc nào cần thì mới query
    @JoinColumn(name = "role_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Role roles;

    // mappedBy trỏ tới tên biến User ở trong User.
    @ManyToMany(mappedBy = "users")
    // LAZY để tránh việc truy xuất dữ liệu không cần thiết. Lúc nào cần thì mới query
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Notification> notifications;

    // mappedBy trỏ tới tên biến User ở trong User.
    @ManyToMany(mappedBy = "users")
    // LAZY để tránh việc truy xuất dữ liệu không cần thiết. Lúc nào cần thì mới query
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Word> words;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Khoonhg sử dụng trong toString()
    private Collection<TestHistory> histories;

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.avatar = "https://images.unsplash.com/photo-1660756018872-6070f3b44110?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=408&q=80";
        this.name = "user";
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

	public User() {
		
	}

	public Role getRoles() {
		return roles;
	}

	public void setRoles(Role roles) {
		this.roles = roles;
	}


}
