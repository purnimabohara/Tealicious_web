package com.example.tealicious.Services;

import com.example.tealicious.Entity.Menu;
import com.example.tealicious.Pojo.MenuPojo;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface MenuService {
    MenuPojo saveMenu(MenuPojo menuPojo) throws IOException;




    void deleteById(Integer id);



    List<Menu> fetchNew();

    Map<Integer, Double> comparePrice(List<Menu> menus);


    List<Menu> fetchAll();
    Menu getSingle(Integer id);


}


