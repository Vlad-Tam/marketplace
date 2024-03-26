package com.vladtam.marketplace.views;

import com.vladtam.marketplace.models.BaseModelInterface;
import com.vladtam.marketplace.models.Category;

import java.util.Scanner;

public class CategoryView implements BaseViewInterface {
    @Override
    public BaseModelInterface createNew(Scanner scan) {
        Category category = new Category();
        System.out.println("Input category name: ");
        category.setName(scan.nextLine());
        System.out.println("Input category description: ");
        category.setDescription(scan.nextLine());
        return category;
    }

    @Override
    public BaseModelInterface updateModel(BaseModelInterface bsModel, Scanner scan) {
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
                            default:
                                System.out.println("Try again");
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