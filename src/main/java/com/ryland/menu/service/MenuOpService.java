package com.ryland.menu.service;

import com.ryland.menu.service.vo.MenuVo;

import java.util.List;
import java.util.Map;

/**
 * @author Ryland
 */
public interface MenuOpService {

    /**
     * 查询菜单树
     *
     * @return 菜单 JSON 字符串
     */
    String queryMenuTree();

    /**
     * 更新菜单树
     *
     * @param id       需要更新的最顶层菜单 id
     * @param menuTree 该菜单下的 JSON 字符串
     * @return 是否更新成功
     */
    boolean updateMenuTree(Integer id, String menuTree);

    /**
     * 更具条件查询菜单
     *
     * @param categories 分类
     * @param keyword    关键字
     * @param operator   操作人
     * @return 菜单信息
     */
    List<MenuVo> queryByCondition(Map<Integer, String> categories, String keyword, String operator);

}
