package com.ryland.menu.service;

import com.ryland.menu.domain.MenuDomain;
import com.ryland.menu.dto.MenuDto;
import com.ryland.menu.repository.MenuRepository;
import com.ryland.menu.service.vo.MenuVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author Ryland
 */
@Service
@Slf4j
public class MenuOpServiceImpl implements MenuOpService {

    @Resource
    private MenuRepository menuRepository;

    @Override
    public String queryMenuTree() {
        MenuDomain menuDomain = MenuDomain.builder().menuRepository(menuRepository).build();
        Optional<MenuDto> rootMenuOptional = menuDomain.getRootMenu();
        if (rootMenuOptional.isPresent()) {
            MenuDto rootMenu = rootMenuOptional.get();
            rootMenu.prettyLog(log);
            return rootMenu.toJSONString();
        }
        return null;
    }

    @Override
    public boolean updateMenuTree(Integer id, String menuTree) {
        return false;
    }

    @Override
    public List<MenuVo> queryByCondition(Map<Integer, String> categories, String keyword, String operator) {
        MenuDomain menuDomain = MenuDomain.builder().menuRepository(menuRepository).build();
        return menuDomain.search(categories, keyword, operator);
    }
}
