package com.ts.trans.types;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 操作类型
 */
@Data
public class ControlType {

    private String code;

    public ControlType(String code) {
        if (code == null || code.isEmpty()) {
            throw new IllegalArgumentException("操作类型编码不能为空");
        }
    }

}
