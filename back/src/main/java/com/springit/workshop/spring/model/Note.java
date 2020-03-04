package com.springit.workshop.spring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String content;

    private Boolean isPublic = false;

    @UpdateTimestamp
    private LocalDateTime updated;

    @ManyToOne
    @JsonIgnore
    private User owner;

    @JsonIgnore
    public Note getNoteWithShortContent() {
        Note note = new Note();
        note.setId(this.id);
        note.setContent(getShortContent());
        note.setIsPublic(this.isPublic);
        note.setUpdated(this.getUpdated());
        return note;
    }

    private String getShortContent() {
        if (this.content != null && !this.content.isBlank()) {
            if (this.content.length() > 20) {
                return this.content.substring(0, 20) + "...";
            }
            return this.content + "...";
        }
        return "...";
    }
}