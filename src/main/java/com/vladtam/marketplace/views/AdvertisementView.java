package com.vladtam.marketplace.views;

import com.vladtam.marketplace.dao.CategoryDAO;
import com.vladtam.marketplace.dao.UserDAO;
import com.vladtam.marketplace.models.*;

import java.util.List;
import java.util.Scanner;

public class AdvertisementView implements BaseViewInterface {
    @Override
    public BaseModelInterface createNew(Scanner scan) {
        Advertisement advertisement = new Advertisement();
        CategoryDAO categoryDao = new CategoryDAO();
        UserDAO userDao = new UserDAO();

        System.out.println("Input name: ");
        advertisement.setName(scan.nextLine());
        System.out.println("Input description: ");
        advertisement.setDescription(scan.nextLine());
        System.out.println("Input price: ");
        advertisement.setPrice(scan.nextDouble());
        advertisement.setSaleStatus(false);

        List<BaseModelInterface> categoriesList = categoryDao.getListInfo();
        MainView.outputList(categoriesList);
        System.out.println("Select category (1-" + categoriesList.size() + "): ");
        advertisement.setCategory(categoryDao.getFullInfo(scan.nextInt()));

        List<BaseModelInterface> usersList = userDao.getListInfo();
        MainView.outputList(usersList);
        System.out.println("Select vendor (1-" + usersList.size() + "): ");
        advertisement.setVendor(userDao.getFullInfo(scan.nextInt()));
        scan.nextLine();

        return advertisement;
    }

    @Override
    public BaseModelInterface updateModel(BaseModelInterface bsModel, Scanner scan) {
        Advertisement advertisement = (Advertisement) bsModel;
        while(true) {
            System.out.println("Choice field to update:\n1-Product name\n2-Description\n3-Price\n" +
                    "4-Category\n5-Vendor\n'R'eturn\n");
            String choice = scan.nextLine();
            if (choice.equalsIgnoreCase("R")) {
                return advertisement;
            } else {
                try {
                    int index = Integer.parseInt(choice);
                    if (index >= 1 && index <= 5) {
                        switch (index) {
                            case 1:
                                System.out.println("Input product name: ");
                                advertisement.setName(scan.nextLine());
                                break;
                            case 2:
                                System.out.println("Input description: ");
                                advertisement.setDescription(scan.nextLine());
                                break;
                            case 3:
                                System.out.println("Input price: ");
                                advertisement.setPrice(scan.nextDouble());
                                scan.nextLine();
                                break;
                            case 4:
                                CategoryDAO categoryDao = new CategoryDAO();
                                List<BaseModelInterface> categoriesList = categoryDao.getListInfo();
                                MainView.outputList(categoriesList);
                                System.out.println("Select category (1-" + categoriesList.size() + ") or create new(0): ");
                                int categoryChoice = scan.nextInt();
                                scan.nextLine();
                                if(categoryChoice == 0){
                                    CategoryView categoryView = new CategoryView();
                                    advertisement.setCategory(categoryDao.getFullInfo(categoryDao.createNew(categoryView.createNew(scan))));
                                }else
                                    advertisement.setCategory(categoryDao.getFullInfo(categoriesList.get(categoryChoice - 1).getId()));
                                break;
                            case 5:
                                UserDAO userDao = new UserDAO();
                                List<BaseModelInterface> usersList = userDao.getListInfo();
                                MainView.outputList(usersList);
                                System.out.println("Select vendor (1-" + usersList.size() + ") or create new(0): ");
                                int vendorChoice = scan.nextInt();
                                scan.nextLine();
                                if(vendorChoice == 0){
                                    UserView userView = new UserView();
                                    advertisement.setVendor(userDao.getFullInfo(userDao.createNew(userView.createNew(scan))));
                                }else
                                    advertisement.setVendor(userDao.getFullInfo(usersList.get(vendorChoice - 1).getId()));
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
