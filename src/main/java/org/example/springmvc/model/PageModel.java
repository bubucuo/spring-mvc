package org.example.springmvc.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author gaoshaozhen
 */
@Accessors(chain = true)
@Data
public class PageModel<T> {
    private Integer pageNo;
    private Long total;
    private Integer pageSize;
    private List<T> content;
}
