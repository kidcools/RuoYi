package com.ruoyi.system.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class XvideosPageInfo implements Serializable {
    private Integer nb_videos;
    private Integer nb_per_page;
    private Integer totalPage;
    private Integer current_page;
    private List<XvideosItem> videos;
}
