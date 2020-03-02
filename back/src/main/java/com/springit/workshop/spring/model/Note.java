package com.springit.workshop.spring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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

    @JsonIgnore
    @ManyToOne
    private User owner;

    public Note getNoteWithShortContent() {
        Note note = new Note();

        note.setId(this.id);
        note.setContent(this.content.substring(0, 20));
        note.setPublic(this.isPublic);

        return note;
    }
}