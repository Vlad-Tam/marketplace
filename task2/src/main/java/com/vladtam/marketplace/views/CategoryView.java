package com.vladtam.marketplace.views;

import com.vladtam.marketplace.models.BaseModelInterface;
import com.vladtam.marketplace.models.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;
import java.util.function.Consumer;

public class CategoryView implements BaseViewInterface {
    public static final Logger logger = LoggerFactory.getLogger(CategoryView.class);

    @Override
    public BaseModelInterface createNew(Scanner scan) {
        Category category = new Category();
        logger.trace("Input category name: ");
        category.setName(scan.nextLine());
        logger.trace("Input category description: ");
        category.setDescription(scan.nextLine());
        return category;
    }

    @Override
    public BaseModelInterface updateModel(BaseModelInterface bsModel, Scanner scan) {
        Category category = (Category) bsModel;
        while(true) {
            logger.trace("Choice field to update:\n1-Category name\n2-Description\n'R'eturn\n");
            String choice = scan.nextLine();
            if (choice.equalsIgnoreCase("R")) {
                return category;
            } else {
                handleUpdateChoice(choice, category, scan);
            }
        }
    }

    private void handleUpdateChoice(String choice, Category category, Scanner scan) {
        try {
            int index = Integer.parseInt(choice);
            if (index >= 1 && index <= 2) {
                switch (index) {
                    case 1:
                        updateCategoryString("Input category name: ", category::setName, scan);
                        break;
                    case 2:
                        updateCategoryString("Input category description: ", category::setDescription, scan);
                        break;
                    default:
                        logger.trace("Try again");
                        break;
                }
            } else throw new NumberFormatException();
        } catch (NumberFormatException e) {
            logger.trace("Try again");
        }
    }

    private void updateCategoryString(String message, Consumer<String> fieldSetter, Scanner scan) {
        logger.trace(message);
        fieldSetter.accept(scan.nextLine());
    }
}