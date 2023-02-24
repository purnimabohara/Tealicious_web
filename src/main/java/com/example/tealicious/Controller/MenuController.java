package com.example.tealicious.Controller;


import com.example.tealicious.Entity.Menu;
import com.example.tealicious.Services.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class MenuController {
    private final MenuService menuService;

    @GetMapping("/menuDetails")
    public String getMenuDetails(Model model) {
        List<Menu> menus = menuService.fetchAll();

        model.addAttribute("menulist", menus.stream().map(menu ->
                Menu.builder()
                        .id(menu.getId())
                        .name(menu.getName())
                        .item_description(menu.getItem_description())
                        .item_quantity(menu.getItem_quantity())
                        .price(menu.getPrice())
                        .date(menu.getDate())
                        .imageBase64(getMenuImageBase64(menu.getImage()))
                        .build()

        ));
        return "Admin/viewMenu";
    }

    @GetMapping("/deleteMenu/{id}")
    public String delMenu(@PathVariable("id") Integer id) {
        menuService.deleteById(id);
        return "redirect:/admin/menuDetails";
    }

    public String getMenuImageBase64(String fileName) {
        String filePath = System.getProperty("user.dir") + "Menu";
        File file = new File(filePath + fileName);
        byte[] bytes = new byte[0];
        try {
            bytes = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        String base64 = Base64.getEncoder().encodeToString(bytes);
        return base64;
    }


}