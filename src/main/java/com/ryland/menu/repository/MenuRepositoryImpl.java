package com.ryland.menu.repository;

import com.ryland.menu.entity.Menu;
import com.ryland.menu.mapper.MenuMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author Ryland
 */
@Repository
public class MenuRepositoryImpl implements MenuRepository {

    @Resource
    private MenuMapper menuMapper;

    private ElasticseachMock elasticseach = (keyword, operator) -> Collections.emptyList();

    @Override
    public List<Menu> getAllMenus() {
        List<Menu> menus = menuMapper.queryAll();
        return CollectionUtils.isEmpty(menus) ? Collections.emptyList() : menus;
    }

    @Override
    public Optional<Menu> getMenuById(Long id) {
        return Optional.ofNullable(menuMapper.queryById(id));
    }

    @Override
    public List<Menu> search(Map<Integer, String> categories, String keyword, String operator) {
        List<Long> ids = elasticseach.search(keyword, operator);
        if (CollectionUtils.isEmpty(ids)) {
            return Collections.emptyList();
        }
        List<Menu> menusSearched = menuMapper.queryByIds(ids);
        for (Map.Entry<Integer, String> cate : categories.entrySet()) {
            List<Menu> menus = menuMapper.queryByLevelAndName(cate.getKey(), cate.getValue());
            menusSearched.retainAll(menus);
        }
        return menusSearched;
    }

    @Override
    public List<Menu> search(Map<Integer, String> categories) {
        List<Menu> searched = null;
        for (Map.Entry<Integer, String> cate : categories.entrySet()) {
            List<Menu> menus = menuMapper.queryByLevelAndName(cate.getKey(), cate.getValue());
            if (CollectionUtils.isEmpty(menus)) {
                return Collections.emptyList();
            }
            if (Objects.isNull(searched)) {
                searched = menus;
            } else {
                searched = (List<Menu>) CollectionUtils.intersection(searched, menus);
            }
            if (CollectionUtils.isEmpty(searched)) {
                return Collections.emptyList();
            }
        }

        return searched;
    }


}
