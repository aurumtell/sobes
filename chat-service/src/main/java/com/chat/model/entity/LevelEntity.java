//package com.chat.model.entity;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import lombok.experimental.Accessors;
//
//import java.util.HashSet;
//import java.util.Set;
//
//@Getter
//@Setter
//@Accessors(chain = true)
//@Entity
//@Table(name = "levels", schema = "public")
//@AllArgsConstructor
//@NoArgsConstructor
//public class LevelEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//
//    @Column(name = "experience")
//    private String experience;
//
//    @JsonIgnore
//    @OneToMany(mappedBy="experience", fetch= FetchType.EAGER)
//    private Set<UserEntity> users = new HashSet<>();
////
////    public void addUser(UserEntity user) {
////        users.add(user);
////        user.setExperience(this);
////    }
////    public void removeUser(UserEntity user) {
////        users.add(user);
////        user.setExperience(null);
////    }
//}