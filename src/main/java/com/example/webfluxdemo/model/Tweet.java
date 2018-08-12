package com.example.webfluxdemo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by rajeevkumarsingh on 08/09/17.
 */
@Data
@NoArgsConstructor
@Document(collection = "tweets")
public class Tweet {

    @Id
    private String id;

    @NotBlank
    @Size(max = 140)
    private String notebook;

    @NotNull
    private Date createdAt = new Date();

    public Tweet(String notebook) {
        this.notebook = notebook;
    }

}
