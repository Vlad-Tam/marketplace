package com.vladtam.marketplace.views;

import com.vladtam.marketplace.dao.CategoryDAO;
import com.vladtam.marketplace.dao.UserDAO;
import com.vladtam.marketplace.models.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;

public class AdvertisementView implements BaseViewInterface {
    public static final Logger logger = LoggerFactory.getLogger(AdvertisementView.class);

    @Override
    public BaseModelInterface createNew(Scanner scan) {
        Advertisement advertisement = new Advertisement();
        CategoryDAO categoryDao = new CategoryDAO();
        UserDAO userDao = new UserDAO();

        logger.trace("Input name: ");
        advertisement.setName(scan.nextLine());
        logger.trace("Input description: ");
        advertisement.setDescription(scan.nextLine());
        logger.trace("Input price: ");
        advertisement.setPrice(scan.nextDouble());
        advertisement.setSaleStatus(false);

        List<BaseModelInterface> categoriesList = categoryDao.getListInfo();
        MainView.outputList(categoriesList);
        logger.trace("Select category (1-{}): ", categoriesList.size());
        advertisement.setCategory(categoryDao.getFullInfo(scan.nextInt()));

        List<BaseModelInterface> usersList = userDao.getListInfo();
        MainView.outputList(usersList);
        logger.trace("Select vendor (1-{}): ", usersList.size());
        advertisement.setVendor(userDao.getFullInfo(scan.nextInt()));
        scan.nextLine();

        return advertisement;
    }

    @Override
    public BaseModelInterface updateModel(BaseModelInterface bsModel, Scanner scan) {
        Advertisement advertisement = (Advertisement) bsModel;
        while(true) {
            logger.trace("Choice field to update:\n1-Product name\n2-Description\n3-Price\n4-Category\n5-Vendor\n'R'eturn\n");
            String choice = scan.nextLine();
            if (choice.equalsIgnoreCase("R")) {
                return advertisement;
            } else {
                handleUpdateChoice(choice, advertisement, scan);
            }
        }
    }

    private void handleUpdateChoice(String choice, Advertisement advertisement, Scanner scan) {
        try {
            int index = Integer.parseInt(choice);
            if (index >= 1 && index <= 5) {
                switch (index) {
                    case 1:
                        updateAdvertisementString("Input product name: ", advertisement::setName, scan);
                        break;
                    case 2:
                        updateAdvertisementString("Input description: ", advertisement::setDescription, scan);
                        break;
                    case 3:
                        updateAdvertisementDouble("Input price: ", advertisement::setPrice, scan);
                        break;
                    case 4:
                        updateCategory(advertisement, scan);
                        break;
                    case 5:
                        updateVendor(advertisement, scan);
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

    private void updateAdvertisementString(String message, Consumer<String> fieldSetter, Scanner scan) {
        logger.trace(message);
        fieldSetter.accept(scan.nextLine());
    }

    private void updateAdvertisementDouble(String message, DoubleConsumer fieldSetter, Scanner scan) {
        logger.trace(message);
        fieldSetter.accept(scan.nextDouble());
        scan.nextLine();
    }

    private void updateCategory(Advertisement advertisement, Scanner scan) {
        CategoryDAO categoryDao = new CategoryDAO();
        List<BaseModelInterface> categoriesList = categoryDao.getListInfo();
        MainView.outputList(categoriesList);
        logger.trace("Select category (1-{}) or create new(0): ", categoriesList.size());
        int categoryChoice = scan.nextInt();
        scan.nextLine();
        if(categoryChoice == 0){
            CategoryView categoryView = new CategoryView();
            advertisement.setCategory(categoryDao.getFullInfo(categoryDao.createNew(categoryView.createNew(scan))));
        }else
            advertisement.setCategory(categoryDao.getFullInfo(categoriesList.get(categoryChoice - 1).getId()));
    }

    private void updateVendor(Advertisement advertisement, Scanner scan) {
        UserDAO userDao = new UserDAO();
        List<BaseModelInterface> usersList = userDao.getListInfo();
        MainView.outputList(usersList);
        logger.trace("Select vendor (1-{}) or create new(0): ", usersList.size());
        int vendorChoice = scan.nextInt();
        scan.nextLine();
        if(vendorChoice == 0){
            UserView userView = new UserView();
            advertisement.setVendor(userDao.getFullInfo(userDao.createNew(userView.createNew(scan))));
        }else
            advertisement.setVendor(userDao.getFullInfo(usersList.get(vendorChoice - 1).getId()));
    }
}
