package org.example.springmvc.model;

import lombok.Data;

/**
 * @author gaoshaozhen
 */
@Data
public class PageRequest {
    private Integer pageNo = 1;
    private Integer pageSize = 10;
}
