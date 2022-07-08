package com.ryland.menu.dto;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.ryland.menu.domain.MenuDomain;
import com.ryland.menu.entity.Menu;
import lombok.Data;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;

import java.util.Objects;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * 用于图形展示
 *
 * @author Ryland
 */
@Data
public class MenuDto implements Comparable<MenuDto> {

    @JSONField(name = "id")
    private Long id;

    @JSONField(name = "name")
    private String name;

    @JSONField(name = "rank")
    private Integer rank;

    @JSONField(name = "level")
    private Integer level;

    @JSONField(name = "children")
    private TreeSet<MenuDto> children;

    public MenuDto(Long id, String name, Integer rank, Integer level) {
        this.id = id;
        this.name = name;
        this.rank = rank;
        this.level = level;
    }

    public static MenuDto buildRootMenu() {
        return new MenuDto(MenuDomain.ROOT_MENU_ID, MenuDomain.ROOT_MENU_NAME, 0, MenuDomain.ROOT_MENU_LEVEL);
    }

    public static MenuDto fromEntity(Menu menu) {
        return new MenuDto(menu.getId(), menu.getName(), menu.getRank(), menu.getLevel());
    }

    public void addChild(MenuDto menuDto) {
        if (Objects.isNull(children)) {
            children = new TreeSet<>();
        }
        children.add(menuDto);
    }

    @Override
    public int compareTo(MenuDto menu) {
        return NumberUtils.compare(this.rank, menu.rank);
    }

    public void prettyLog(Logger log) {
        if (log.isInfoEnabled()) {
            internalPrettyLog(this, log);
        }
    }

    private void internalPrettyLog(MenuDto menuDto, Logger log) {
        if (hasChildren()) {
            log.info("id: [{}], name: [{}], rank: [{}], children ids: [{}]", id, name, rank, children.stream().map(MenuDto::getId).collect(Collectors.toList()));
            menuDto.getChildren().forEach(child -> child.prettyLog(log));
        } else {
            log.info("id: [{}], name: [{}], rank: [{}]", id, name, rank);
        }
    }

    public boolean hasChildren() {
        return Objects.nonNull(children) && !children.isEmpty();
    }

    public String toJSONString() {
        return JSON.toJSONString(this);
    }

}
