create table menu
(
    id        bigint auto_increment
        primary key,
    name      varchar(100) default '' not null comment '菜单名',
    parent_id bigint                  not null comment '父菜单 ID',
    `rank`    int                     not null comment '同一级别下的显示顺序',
    level     int                     not null comment '菜单分类级别'
);

INSERT INTO testdb.menu (id, name, parent_id, `rank`, level) VALUES (1, '自营规则', 0, 0, 1);
INSERT INTO testdb.menu (id, name, parent_id, `rank`, level) VALUES (7, '开放平台规则', 0, 1, 1);
INSERT INTO testdb.menu (id, name, parent_id, `rank`, level) VALUES (17, '唯品国际规则', 0, 2, 1);
INSERT INTO testdb.menu (id, name, parent_id, `rank`, level) VALUES (21, '招商管理', 1, 0, 2);
INSERT INTO testdb.menu (id, name, parent_id, `rank`, level) VALUES (22, '经营管理', 1, 1, 2);
INSERT INTO testdb.menu (id, name, parent_id, `rank`, level) VALUES (23, '商品管理', 1, 2, 2);
INSERT INTO testdb.menu (id, name, parent_id, `rank`, level) VALUES (24, '总则协议', 21, 0, 3);
INSERT INTO testdb.menu (id, name, parent_id, `rank`, level) VALUES (25, '入驻合作', 21, 1, 3);
INSERT INTO testdb.menu (id, name, parent_id, `rank`, level) VALUES (26, '资质要求', 21, 2, 3);
INSERT INTO testdb.menu (id, name, parent_id, `rank`, level) VALUES (27, '资费相关', 21, 3, 3);
INSERT INTO testdb.menu (id, name, parent_id, `rank`, level) VALUES (28, '违规考核', 22, 0, 3);
INSERT INTO testdb.menu (id, name, parent_id, `rank`, level) VALUES (29, '奖励规则', 22, 1, 3);
INSERT INTO testdb.menu (id, name, parent_id, `rank`, level) VALUES (30, '信息安全', 22, 2, 3);
INSERT INTO testdb.menu (id, name, parent_id, `rank`, level) VALUES (31, '基础规则', 22, 3, 3);
INSERT INTO testdb.menu (id, name, parent_id, `rank`, level) VALUES (32, '商品发布', 23, 0, 3);
INSERT INTO testdb.menu (id, name, parent_id, `rank`, level) VALUES (33, '商品管控', 23, 1, 3);
INSERT INTO testdb.menu (id, name, parent_id, `rank`, level) VALUES (34, '质量标准', 23, 2, 3);
INSERT INTO testdb.menu (id, name, parent_id, `rank`, level) VALUES (35, '招商管理', 7, 0, 2);
INSERT INTO testdb.menu (id, name, parent_id, `rank`, level) VALUES (36, '运营管理', 7, 1, 2);
INSERT INTO testdb.menu (id, name, parent_id, `rank`, level) VALUES (37, '招商合作流程', 35, 0, 3);
INSERT INTO testdb.menu (id, name, parent_id, `rank`, level) VALUES (38, '品牌招商', 37, 0, 4);
INSERT INTO testdb.menu (id, name, parent_id, `rank`, level) VALUES (39, '档期运营管理', 36, 0, 3);
INSERT INTO testdb.menu (id, name, parent_id, `rank`, level) VALUES (40, '商品运营管理', 36, 1, 3);
INSERT INTO testdb.menu (id, name, parent_id, `rank`, level) VALUES (41, '商务运营管理', 36, 2, 3);
INSERT INTO testdb.menu (id, name, parent_id, `rank`, level) VALUES (42, '侵权销售行为管理', 39, 0, 4);
INSERT INTO testdb.menu (id, name, parent_id, `rank`, level) VALUES (43, '唯品会门店JITX管理', 39, 1, 4);
INSERT INTO testdb.menu (id, name, parent_id, `rank`, level) VALUES (44, '唯品会供应商退货收货', 39, 2, 4);
INSERT INTO testdb.menu (id, name, parent_id, `rank`, level) VALUES (45, '入网食品生产经营审查登记制度', 40, 0, 4);
INSERT INTO testdb.menu (id, name, parent_id, `rank`, level) VALUES (46, '唯品会平台地图使用管理办法V1.0', 40, 1, 4);
INSERT INTO testdb.menu (id, name, parent_id, `rank`, level) VALUES (47, '反商业贿赂协议', 41, 0, 4);