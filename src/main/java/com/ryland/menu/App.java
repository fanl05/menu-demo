package com.ryland.menu;

import com.ryland.menu.service.MenuOpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Ryland
 */
@Slf4j
public class App {

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        MenuOpService menuOpService = ctx.getBean(MenuOpService.class);
        String menuTreeJson = menuOpService.queryMenuTree();
        log.debug("{}", menuTreeJson);
    }

}
