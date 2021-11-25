package br.com.allangf.BlogAPI.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Table
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int postId;

    @Column(name = "title")
    private String title;

    @Column(name = "post_body", length = 10000)
    private String postBody;

    @Column(name = "post_date")
    private LocalDate postDate;

    @Column(name = "counter_hits")
    private int counterHits;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "post_tag",
            joinColumns = { @JoinColumn(name = "post_id") },
            inverseJoinColumns = { @JoinColumn(name = "tag_id") })
    private List<Tag> tag;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
