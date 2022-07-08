package com.ryland.menu.service.vo;

import com.ryland.menu.entity.Menu;
import lombok.Data;

import java.util.Map;

/**
 * @author Ryland
 */
@Data
public class MenuVo {

    private Long id;

    private String name;

    private Map<Integer, String> categories;

    public static MenuVo from(Menu menu, Map<Integer, String> categories) {
        MenuVo vo = new MenuVo();
        vo.setId(menu.getId());
        vo.setName(menu.getName());
        vo.setCategories(categories);
        return vo;
    }

}
