package com.vladtam.marketplace.views;

import com.vladtam.marketplace.dao.BaseDAO;
import com.vladtam.marketplace.models.BaseModel;
import com.vladtam.marketplace.models.Category;

import java.util.Scanner;

public class CategoryView implements BaseView{
    @Override
    public BaseModel createNew(Scanner scan) {
        Category category = new Category();
        System.out.println("Input category name: ");
        category.setName(scan.nextLine());
        System.out.println("Input category description: ");
        category.setDescription(scan.nextLine());
        return category;
    }


    @Override
    public BaseModel updateModel(BaseModel bsModel, Scanner scan) {
        Category category = (Category) bsModel;
        while(true) {
            System.out.println("Choice field to update:\n1-Category name\n2-Description\n'R'eturn\n");
            String choice = scan.nextLine();
            if (choice.equalsIgnoreCase("R")) {
                return category;
            } else {
                try {
                    int index = Integer.parseInt(choice);
                    if (index >= 1 && index <= 2) {
                        switch (index) {
                            case 1:
                                System.out.println("Input category name: ");
                                category.setName(scan.nextLine());
                                break;
                            case 2:
                                System.out.println("Input description: ");
                                category.setDescription(scan.nextLine());
                                break;
                        }
                    } else throw new NumberFormatException();
                } catch (NumberFormatException e) {
                    System.out.println("Try again");
                }
            }
        }
    }
}