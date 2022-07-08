package com.ryland.menu.repository;

import java.util.List;

public interface ElasticseachMock {

    List<Long> search(String keyword, String operator);

}
