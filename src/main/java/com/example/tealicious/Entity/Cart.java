//package com.example.tealicious.Entity;
//
//import jakarta.persistence.*;
//import lombok.*;
//import org.hibernate.annotations.DynamicInsert;
//import org.hibernate.annotations.DynamicUpdate;
//
//@Builder
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity
//@DynamicInsert
//@DynamicUpdate
//@Table(name = "cart")
//public class Cart {
//
//    @Id
//    @SequenceGenerator(name = "cms_user_seq_gen", sequenceName = "cms_user_id_seq", allocationSize = 1)
//    @GeneratedValue(generator = "cms_user_seq_gen", strategy = GenerationType.SEQUENCE)
//    private Integer id;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "product_id",
//            referencedColumnName = "id",
//            foreignKey = @ForeignKey(name = "FK_productId"))
//    private Menu menu;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id",
//            referencedColumnName = "id",
//            foreignKey = @ForeignKey(name = "FK_userId"))
//    private User user;
//
//    @Column
//    private Integer quantity;
//
//    @Column(columnDefinition = "varchar(255) default 'Added'")
//    private String status;
//}
