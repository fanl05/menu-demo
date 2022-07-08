package com.ryland.menu.mapper;

import com.ryland.menu.entity.Menu;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Ryland
 */
@Repository
public interface MenuMapper {

    /**
     * 查询全部菜单
     *
     * @return 菜单列表
     */
    @Select("select id, name, parent_id, rank, level from menu")
    @Results(id = "queryAll", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "parentId", column = "parent_id"),
            @Result(property = "rank", column = "rank"),
            @Result(property = "level", column = "level")
    })
    List<Menu> queryAll();

    /**
     * 通过 id 查询菜单
     *
     * @param ids id 集合
     * @return 菜单集合
     */
    @Select("<script>" +
            "select id, name, parent_id, rank, level from menu where id in" +
            "<foreach collection='ids' item='id' open='(' close=')' separator=','>" +
            "#{id}" +
            "</foreach>" +
            "</script>")
    @Results(id = "queryByIds", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "parentId", column = "parent_id"),
            @Result(property = "rank", column = "rank"),
            @Result(property = "level", column = "level")
    })
    List<Menu> queryByIds(@Param("ids") List<Long> ids);

    @Select("select id, name, parent_id, rank, level from menu where id = #{id}")
    @Results(id = "queryById", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "parentId", column = "parent_id"),
            @Result(property = "rank", column = "rank"),
            @Result(property = "level", column = "level")
    })
    Menu queryById(@Param("id") Long id);

    @Select("select id, name, parent_id, rank, level from menu where level = #{level} and name = #{name}")
    @Results(id = "queryByLevelAndName", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "parentId", column = "parent_id"),
            @Result(property = "rank", column = "rank"),
            @Result(property = "level", column = "level")
    })
    List<Menu> queryByLevelAndName(@Param("level") Integer level, @Param("name") String name);

}
