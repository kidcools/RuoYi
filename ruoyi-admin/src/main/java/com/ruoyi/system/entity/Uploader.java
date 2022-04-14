package com.ruoyi.system.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 创作者
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Uploader {
    private Long id;
    private String name;
    private Integer videos;
    private String country;
    private String image;
    private String description;
    private String idInPlat;
}
