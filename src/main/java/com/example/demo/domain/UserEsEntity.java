package com.example.demo.domain;

import lombok.Data;
import org.elasticsearch.common.Nullable;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;


@Document(indexName = "my_user")
@Data
public class UserEsEntity implements Serializable {

    @Id
    @Nullable
    private String id;

    @Field(value = "last-name", type = FieldType.Keyword)
    private String lastName;

    @Field(type = FieldType.Keyword)
    private String type;

    @Field(type = FieldType.Integer)
    private Integer age;

    @Nullable @Field(name = "birth-date", type = FieldType.Date, format = DateFormat.basic_date)
    private LocalDate birthDate;

    @Field(type = FieldType.Boolean)
    private Boolean isDeleted;
    @Field(type = FieldType.Date)
    private Date createTime;
    @Field(type = FieldType.Date)
    private Date updateTime;
}