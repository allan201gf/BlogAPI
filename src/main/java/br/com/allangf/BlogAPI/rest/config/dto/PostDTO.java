package br.com.allangf.BlogAPI.rest.config.dto;

import br.com.allangf.BlogAPI.domain.entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {

    private String title;
    private String postBody;
    private List<String> tag;

}