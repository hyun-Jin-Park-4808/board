package com.example.board.model;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
//@DynamicInsert // Insert 시에 Null일 필드를 제외
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; // auto_increment

    @Column(nullable = false, length = 100, unique = true)
    private String username;

    @Column(length = 100)
    private String password;

    @Column(nullable = false, length = 50)
    private String email;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Enumerated(EnumType.STRING)
    private LoginType loginType;

    @CreationTimestamp
    private Timestamp createDate;
}
