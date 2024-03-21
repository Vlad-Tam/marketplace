package com.vladtam.marketplace.views;

import com.vladtam.marketplace.dao.*;
import com.vladtam.marketplace.models.*;

import java.util.List;
import java.util.Scanner;

public class MainView {
    public static void mainMenu(){
        while (true) {
            System.out.flush();
            System.out.println("MAIN MENU\n1 - Address info\n2 - Advertisement info\n" +
                    "3 - Category info\n4 - City info\n5 - Review info\n6 - User info\n0 - Exit");
            Scanner scan = new Scanner(System.in);
            int choice = scan.nextInt();
            scan.nextLine();
            BaseDAO bsDao = null;
            BaseView bsView = null;
            switch (choice){
                case 0:
                    System.exit(0);
                case 1:
                    bsDao = new AddressDAO();
                    bsView = new AddressView();
                    MainView.actionChoice(bsDao, bsView, scan);
                    break;
                case 2:
                    bsDao = new AdvertisementDAO();
                    bsView = new AdvertisementView();
                    MainView.actionChoice(bsDao, bsView, scan);
                    break;
                case 3:
                    bsDao = new CategoryDAO();
                    bsView = new CategoryView();
                    MainView.actionChoice(bsDao, bsView, scan);
                    break;
                case 4:
                    bsDao = new CityDAO();
                    bsView = new CityView();
                    MainView.actionChoice(bsDao, bsView, scan);
                    break;
                case 5:
                    bsDao = new ReviewDAO();
                    bsView = new ReviewView();
                    MainView.actionChoice(bsDao, bsView, scan);
                    break;
                case 6:
                    bsDao = new UserDAO();
                    bsView = new UserView();
                    MainView.actionChoice(bsDao, bsView, scan);
                    break;
            }
        }
    }

    public static void outputList(List<BaseModel> list){
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i+1 + ") " + list.get(i));
        }
    }

    public static void actionChoice(BaseDAO bsDao, BaseView bsView, Scanner scan){
        while (true) {
            List<BaseModel> modelList = bsDao.getListInfo();
            MainView.outputList(modelList);
            System.out.println("'1'-'" + modelList.size() + "' get full info\n'C'reate\n'R'eturn");
            String input = scan.nextLine();
            if (input.equalsIgnoreCase("R")) {
                return;
            } else if (input.equalsIgnoreCase("C")) {
                bsDao.createNew(bsView.createNew(scan));
            } else {
                try {
                    int index = Integer.parseInt(input);
                    if (index >= 1 && index <= modelList.size()) {
                        int modelId = modelList.get(index - 1).getId();
                        while(true) {
                            System.out.println(bsDao.getFullInfo(modelId).outputFullInfo());
                            System.out.println("'U'pdate\n'D'elete\n'R'eturn\n");
                            String inputAction = scan.nextLine();
                            if (inputAction.equalsIgnoreCase("R")) {
                                return;
                            } else if (inputAction.equalsIgnoreCase("D")) {
                                bsDao.delete(modelId);
                                return;
                            } else if (inputAction.equalsIgnoreCase("U")) {
                                bsDao.update(bsView.updateModel(modelList.get(index - 1), scan));
                            } else
                                return;
                        }
                    } else throw new NumberFormatException();
                } catch (NumberFormatException e) {
                    System.out.println("Try again");
                }
            }
        }
    }
}
