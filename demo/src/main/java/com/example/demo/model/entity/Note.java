package com.example.demo.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "notes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime created;

    @ManyToOne(fetch = FetchType.EAGER) // LAZY vs EAGER??? tarea :v
    @JoinColumn(name = "category_id")
    private Category category;
}

// @ManyToOne(fetch = FetchType.LAZY)
//LAZY significa que la relación se carga bajo demanda. Los datos
// relacionados no se cargan hasta que realmente accedas a ellos.
//Cuando usas fetch = FetchType.LAZY, al obtener una instancia de
// Note, la instancia de Category solo se carga cuando accedes a
// la propiedad category de Note.

// @ManyToOne(fetch = FetchType.EAGER)
// EAGER significa que la relación se carga de inmediato junto con
// la entidad principal. Cuando usas fetch = FetchType.EAGER, al
// obtener una instancia de Note, también se carga automáticamente
// la instancia relacionada de Category.

