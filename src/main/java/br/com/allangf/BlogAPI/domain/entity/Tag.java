package br.com.allangf.BlogAPI.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tag")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name_tag")
    private String nameTag;

    @ManyToMany(mappedBy="tag")
    private List<Post> post;


}
