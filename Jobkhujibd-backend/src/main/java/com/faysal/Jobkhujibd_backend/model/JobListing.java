package com.faysal.Jobkhujibd_backend.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name= "jkbd_job_listing")

public class JobListing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //1
    private String jobTitle;
    //2
    private String category;
    //3
    private String location;
    //4
    private String experience;
    //5
    private String pay_scale;
    //6
    private String description;
    //7
    private String createDate;  //data type string for OMAR's shortcut
    //8
    private String action;
}
