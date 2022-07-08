package com.ryland.menu.repository;


import com.ryland.menu.entity.Menu;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author Ryland
 */
public interface MenuRepository {

    /**
     * 获取所有菜单
     *
     * @return 菜单列表
     */
    List<Menu> getAllMenus();

    Optional<Menu> getMenuById(Long id);

    List<Menu> search(Map<Integer, String> categories, String keyword, String operator);

    List<Menu> search(Map<Integer, String> categories);

}
