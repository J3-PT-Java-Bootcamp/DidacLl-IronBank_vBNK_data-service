package com.ironhack.vbnk_dataservice.data.dao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Getter @Setter
public class Notification {
    @Id @GeneratedValue
    Long id;
    String title;
    String message;
}
