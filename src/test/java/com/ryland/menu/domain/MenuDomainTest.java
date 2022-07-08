package com.ryland.menu.domain;

import com.ryland.menu.domain.MenuDomain;
import com.ryland.menu.entity.Menu;
import com.ryland.menu.repository.MenuRepository;
import com.ryland.menu.service.vo.MenuVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ryland
 */
@Slf4j
public class MenuDomainTest {

    private MenuDomain menuDomain;

    @Before
    public void setUp() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        menuDomain = MenuDomain.builder().menuRepository(ctx.getBean(MenuRepository.class)).build();
    }

    @Test
    public void testFindCategories() {
        Menu menu = new Menu();
        menu.setId(47L);
        menu.setParentId(41L);
        menu.setLevel(4);
        Map<Integer, String> categories = menuDomain.findCategories(menu);
        log.info("{}", categories);
    }

    @Test
    public void testSearch() {
        Map<Integer, String> categories = new HashMap<>();
        categories.put(4, "唯品会平台地图使用管理办法V1.0");
        List<MenuVo> menuVos = menuDomain.search(categories, StringUtils.EMPTY, StringUtils.EMPTY);
        log.info("{}", menuVos);
    }

}
