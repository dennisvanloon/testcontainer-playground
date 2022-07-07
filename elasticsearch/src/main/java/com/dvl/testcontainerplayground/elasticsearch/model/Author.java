package com.dvl.testcontainerplayground.elasticsearch.model;

import static org.springframework.data.elasticsearch.annotations.FieldType.Text;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Field;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Author {

    @Field(type = Text)
    private String name;

}