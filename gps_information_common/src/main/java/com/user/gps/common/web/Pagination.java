package com.user.gps.common.web;

import java.io.Serializable;

public class Pagination implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 当前页
     */
    private Integer currentPage;
    /**
     * 每页的数量
     */
    private Integer itemsPerPage;

    /**
     * 总记录数
     */
    private Long bigTotalItems;

    /**
     * 总页数
     */
    private Integer pages;

}
