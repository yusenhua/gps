package com.user.gps.mybatis;

/**
 * Divisible table
 * Created by yusenhua on 2017/2/17.
 */
public interface DivisibleTable {

    /**
     * Generate table name
     * @param baseTable the table name to be divided
     * @param id id
     * @param totalTableAmount total sub-table amount
     * @return destination table name
     */
    String generateTableName(String baseTable, Long id, int totalTableAmount);

    /**
     * Generate table num
     * @param id id
     * @param totalTableAmount total sub-table amount
     * @return destination table num
     */
    Integer generateTableNum(Long id, int totalTableAmount);

}
