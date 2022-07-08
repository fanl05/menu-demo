package com.ryland.menu.entity;

import lombok.Data;

/**
 * @author Ryland
 */
@Data
public class Menu {

    private Long id;

    private String name;

    private Long parentId;

    private Integer rank;

    private Integer level;

}
