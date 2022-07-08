package com.ryland.menu.domain;

import com.ryland.menu.dto.MenuDto;
import com.ryland.menu.entity.Menu;
import com.ryland.menu.repository.MenuRepository;
import com.ryland.menu.service.vo.MenuVo;
import lombok.Builder;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Ryland
 */
@Builder
public class MenuDomain {

    /**
     * 根节点 ID
     */
    public static final Long ROOT_MENU_ID = 0L;

    public static final String ROOT_MENU_NAME = "root";

    public static final Integer ROOT_MENU_LEVEL = 0;

    private MenuRepository menuRepository;

    /**
     * 获取根节点
     */
    public Optional<MenuDto> getRootMenu() {
        List<Menu> menus = menuRepository.getAllMenus();
        if (CollectionUtils.isEmpty(menus)) {
            return Optional.empty();
        }

        MenuDto rootMenu = MenuDto.buildRootMenu();
        Map<Long, List<Menu>> menusGroupingByParent = menus.stream().collect(Collectors.groupingBy(Menu::getParentId));

        fillUpChildren(rootMenu, menusGroupingByParent);

        return Optional.of(rootMenu);
    }

    /**
     * 填充子菜单
     *
     * @param parent 父菜单
     * @param menus  所有菜单
     */
    protected void fillUpChildren(MenuDto parent, Map<Long, List<Menu>> menus) {
        List<Menu> children = menus.get(parent.getId());
        if (CollectionUtils.isEmpty(children)) {
            // 当一个菜单没有子菜单时则直接返回
            return;
        }
        for (Menu child : children) {
            MenuDto childDto = MenuDto.fromEntity(child);
            parent.addChild(childDto);
            fillUpChildren(childDto, menus);
        }
    }

    public List<MenuVo> search(Map<Integer, String> categories, String keyword, String operator) {
        if (MapUtils.isNotEmpty(categories)
                && StringUtils.isBlank(keyword)
                && StringUtils.isBlank(operator)) {
            return menuRepository.getAllMenus()
                    .stream().map(menu -> MenuVo.from(menu, findCategories(menu)))
                    .collect(Collectors.toList());
        }
        if (MapUtils.isNotEmpty(categories)
                && StringUtils.isBlank(keyword)
                && StringUtils.isBlank(operator)) {
            return menuRepository.search(categories)
                    .stream().map(menu -> MenuVo.from(menu, findCategories(menu)))
                    .collect(Collectors.toList());
        }

        return menuRepository.search(categories, keyword, operator).stream()
                .map(menu -> MenuVo.from(menu, findCategories(menu)))
                .collect(Collectors.toList());
    }

    /**
     * 查找该菜单所属的每一级分类
     *
     * @param menu 菜单
     * @return 菜单分类
     */
    protected Map<Integer, String> findCategories(Menu menu) {
        Map<Integer, String> categories = new HashMap<>();
        findParent(menu, categories);
        return categories;
    }

    private void findParent(Menu menu, Map<Integer, String> categories) {
        if (1 == menu.getLevel()) {
            return;
        }
        Optional<Menu> menuOptional = menuRepository.getMenuById(menu.getParentId());
        if (!menuOptional.isPresent()) {
            return;
        }
        Menu parent = menuOptional.get();
        categories.put(parent.getLevel(), parent.getName());
        findParent(parent, categories);
    }

}
