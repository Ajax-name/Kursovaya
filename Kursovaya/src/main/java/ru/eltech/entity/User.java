package ru.eltech.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_data", schema = "public", catalog = "postgres")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private static User instance = null;
    public static synchronized User getInstance(){
        if (instance == null){
            instance = new User();
        }
        return instance;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id",unique=true, nullable = false)
    private Long id;


    @Basic
    @Column(name = "name", nullable = false, length = -1)
    private String name;

    @Basic
    @Column(name = "jpg_path", nullable = false, length = -1)
    private String jpg_path;

    @Basic
    @Column(name = "description", nullable = false, length = -1)
    private String description;
}