package com.ts.trans.types;

import lombok.Data;

/**
 * 机构
 */
@Data
public class Org {

    private String code;

    private String name;

    public Org() {}

    public Org(String code) {
        this.code = code;
    }
}
