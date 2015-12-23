package com.creationmachine.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "PAGE")
public class Page
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @Size(min = 3, max = 250)
    @Column(name = "TITLE", nullable = false)
    private String title;


    @NotNull
    @Size(min = 3, max = 5000)
    @Column(name = "BODY", nullable = false)
    private String body;

    @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "POST_DATE", nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    private LocalDate postDate;

    @NotNull
    @Digits(integer = 8, fraction = 2)
    @Column(name = "AUTHOR", nullable = false)
    private BigDecimal author;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getBody()
    {
        return body;
    }

    public void setBody(String body)
    {
        this.body = body;
    }

    public LocalDate getPostDate()
    {
        return postDate;
    }

    public void setPostDate(LocalDate postDate)
    {
        this.postDate = postDate;
    }

    public BigDecimal getAuthor()
    {
        return author;
    }

    public void setAuthor(BigDecimal author)
    {
        this.author = author;
    }

    @Override
    public String toString()
    {
        return "Page [id=" + id + ", title=" + title + ", body=" + body + ", postDate=" + postDate
                + ", author=" + author + "]";
    }

    
}
