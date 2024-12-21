package com.example.quanlyrung.infra.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table( name = "dvhc")
public class DVHC {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Ten")
    private String name;

    private String level;

    @Column(name = "Chu_thich")
    private String note;

    @ManyToOne
    private DVHC parent;

//    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
//    private List<DVHC> children;
}
