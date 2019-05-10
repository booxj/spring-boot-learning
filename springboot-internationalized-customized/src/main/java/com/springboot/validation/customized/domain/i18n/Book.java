package com.springboot.validation.customized.domain.i18n;

import com.springboot.validation.customized.utils.validator.DateTime;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @ClassName: Book.java
 * @Description: TODO
 * @Author: booxj
 * @CreateDate 2019/5/9 17:29
 * @version:
 */
public class Book {

    private Integer id;

    @NotBlank(message = "{book.name.not-blank}")
    @Length(min = 2, max = 10, message = "{book.name.length}")
    private String name;

    @NotNull(message = "{book.price.not-null}")
    @DecimalMin(value = "0.1", message = "{book.price.min}")
    private BigDecimal price;

    @DateTime
    private String publishDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }
}
