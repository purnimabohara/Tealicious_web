package com.example.tealicious.Pojo;


import com.example.tealicious.Entity.Menu;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MenuPojo {
    private Integer id;

    private String name;

    private String item_description;
    private String item_quantity;

    private String price;
    private String date;


    private MultipartFile image;

    public MenuPojo(Menu menu){
        this.id=menu.getId();
        this.item_description= menu.getItem_description();
        this.item_quantity=menu.getItem_quantity();
        this.price=menu.getPrice();
        this.date=menu.getDate();

    }

}

