package com.ts.trans.types;

import lombok.Data;

/**
 * 机构
 */
@Data
public class Org {

    private String code;

    private String name;

    public Org(String code) {
        if (code == null || code.isEmpty()) {
            throw new IllegalArgumentException("机构编号不能为空");
        }
        this.code = code;
    }
}
