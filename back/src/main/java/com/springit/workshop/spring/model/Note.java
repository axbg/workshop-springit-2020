package com.springit.workshop.spring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Note extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String content;

    private boolean isPublic = false;

    @ManyToOne
    @JsonIgnore
    private User owner;

    @JsonIgnore
    public Note getNoteWithShortContent() {
        Note note = new Note();
        note.setId(this.id);
        note.setContent(this.content.substring(0, 20).trim() + "...");
        note.setPublic(this.isPublic);
        return note;
    }
}