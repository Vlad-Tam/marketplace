package com.vladtam.marketplace.views;

import com.vladtam.marketplace.dao.*;
import com.vladtam.marketplace.models.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Scanner;

public class MainView {
    private MainView(){}
    public static final Logger logger = LoggerFactory.getLogger(MainView.class);

    public static void mainMenu(){
        while (true) {
            logger.trace("MAIN MENU\n1 - Address info\n2 - Advertisement info\n3 - Category info\n4 - City info\n5 - Review info\n6 - User info\n0 - Exit");
            Scanner scan = new Scanner(System.in);
            int choice = scan.nextInt();
            scan.nextLine();
            BaseDAOInterface bsDao = null;
            BaseViewInterface bsView = null;
            switch (choice){
                case 0:
                    return;
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
                default:
                    logger.trace("Try again");
                    break;
            }
        }
    }

    public static void outputList(List<BaseModelInterface> list){
        for (int i = 0; i < list.size(); i++) {
            logger.trace("{}) {}", i+1, list.get(i));
        }
    }

    public static void actionChoice(BaseDAOInterface bsDao, BaseViewInterface bsView, Scanner scan) {
        while (true) {
            List<BaseModelInterface> modelList = bsDao.getListInfo();
            MainView.outputList(modelList);
            logger.trace("'1'-'{}' get full info\n'C'reate\n'R'eturn", modelList.size());
            String input = scan.nextLine();
            if (input.equalsIgnoreCase("R")) {
                return;
            } else if (input.equalsIgnoreCase("C")) {
                bsDao.createNew(bsView.createNew(scan));
            } else {
                handleAction(input, modelList, bsDao, bsView, scan);
            }
        }
    }

    private static void handleAction(String input, List<BaseModelInterface> modelList, BaseDAOInterface bsDao, BaseViewInterface bsView, Scanner scan) {
        try {
            int index = Integer.parseInt(input);
            if (index >= 1 && index <= modelList.size()) {
                int modelId = modelList.get(index - 1).getId();
                handleModelAction(modelId, bsDao, bsView, scan);
            } else throw new NumberFormatException();
        } catch (NumberFormatException e) {
            logger.trace("Try again");
        }
    }

    private static void handleModelAction(int modelId, BaseDAOInterface bsDao, BaseViewInterface bsView, Scanner scan) {
        while (true) {
            if (logger.isTraceEnabled()) {
                logger.trace("{}\n'U'pdate\n'D'elete\n'R'eturn\n", bsDao.getFullInfo(modelId).outputFullInfo());
            }
            String inputAction = scan.nextLine();
            if (inputAction.equalsIgnoreCase("R")) {
                return;
            } else if (inputAction.equalsIgnoreCase("D")) {
                bsDao.delete(modelId);
                return;
            } else if (inputAction.equalsIgnoreCase("U")) {
                bsDao.update(bsView.updateModel(bsDao.getFullInfo(modelId), scan));
            } else {
                return;
            }
        }
    }
}
