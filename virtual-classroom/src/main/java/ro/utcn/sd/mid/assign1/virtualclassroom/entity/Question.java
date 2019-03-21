package ro.utcn.sd.mid.assign1.virtualclassroom.entity;


import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Getter
@Setter
@Entity(name = "questions")
@AllArgsConstructor @NoArgsConstructor
public class Question implements IDEntity, Comparable<Question> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer userId;
    private String title;
    private String text;
    private Timestamp creationDate;
    @ManyToMany
    @JoinTable(
            name = "questionsTags",
            joinColumns = @JoinColumn(name = "questionId"),
            inverseJoinColumns = @JoinColumn(name = "tagId")
    )
    private List<Tag> tags;
    private Integer score;

    public Question(Integer userId, String title, String text) {
        this.userId = userId;
        this.title = title;
        this.text = text;
        creationDate = new Timestamp(System.currentTimeMillis());
        tags = new ArrayList<>();
        score = 0;
    }

    public void addTag(Tag tag) {
        tags.add(tag);
        tag.getTaggedQuestions().add(this);
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public int compareTo(Question o) {
        return -this.getCreationDate().compareTo(o.getCreationDate());
    }

    @Override
    public String toString() {
        String res = "Question: " + id +
                ", title: " + title +
                ", text: " + text +
                ", creation date: " + creationDate.toString() +
                ", tags: ";
        for(Tag t : tags) {
            res += t.getTagName() + " ";
        }
        return res;
    }
}
