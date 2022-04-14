package com.ruoyi.system.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Channel {
    private Long id;
    private String name;
    private String detail;
    private String image;
    private String idInplat;
    private Integer platForm;
}
