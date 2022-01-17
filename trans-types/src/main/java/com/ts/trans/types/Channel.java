package com.ts.trans.types;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Channel {

    private String code;

    private String name;

    public Channel(String code) {
        if (code == null || code.isEmpty()) {
            throw new IllegalArgumentException("渠道编码不能为空");
        }
        this.code = code;
    }
}
