package com.user.gps.mybatis;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * Generate table name by several end numbers.
 * Created by yusenhua.Gu on 2017/3/31.
 */
@Component
@Primary
public class EndNumberDivisibleTableGenerator implements DivisibleTable {

    private static final int AMOUNT_OF_END_NUMBERS = 4;

    @Override
    public String generateTableName(String baseTable, Long id, int totalTableAmount) {
        if (null == id || 0 == id) {
            return null;
        }
        if (null == baseTable || "".equals(baseTable)) {
            return null;
        }
        if (1 == totalTableAmount) {
            return baseTable;
        }
        return baseTable + "_" + this.generateTableNum(id, totalTableAmount);
    }

    @Override
    public Integer generateTableNum(Long id, int totalTableAmount) {
        if (null == id || 0 == id) {
            return null;
        }
        // cut off unnecessary numbers
        if ( id >= Double.valueOf(Math.pow(10, AMOUNT_OF_END_NUMBERS)).longValue() ) {
            String idString = String.valueOf(id);
            idString = idString.substring(idString.length() - 4);
            id = Long.valueOf(idString);
        }
        return (int) (id % totalTableAmount);
    }

}
