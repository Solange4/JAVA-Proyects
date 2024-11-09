package com.example.demo.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.ISBN;

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

    @NotNull(message = "El campo 'title' no puede ser nulo")
    private String title;
    private String content;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime created;

    @ManyToOne(fetch = FetchType.EAGER)  // LAZY vs EAGER??? tarea :v
    @JoinColumn(name = "category_id")
    private Category category;
}


@NotNull
@NotEmpty
@NotBlank
@AssertTrue
@Size(min = 3, max = 100)
@Max(10)
@Min(1)
@Positive


// validaciones de fecha
@Past
@Future

@Email(message = "El campo 'email' no es un correo electrónico válido")
@Pattern(regexp = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$")

@ISBN