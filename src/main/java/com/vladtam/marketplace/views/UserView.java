package com.vladtam.marketplace.views;

import com.vladtam.marketplace.dao.AddressDAO;
import com.vladtam.marketplace.models.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Scanner;

public class UserView implements BaseViewInterface {
    public static final Logger logger = LoggerFactory.getLogger(UserView.class);

    @Override
    public BaseModelInterface createNew(Scanner scan) {
        User user = new User();
        AddressDAO addressDao = new AddressDAO();
        logger.trace("Input name: ");
        user.setName(scan.nextLine());
        logger.trace("Input surname: ");
        user.setSurname(scan.nextLine());
        logger.trace("Input email: ");
        user.setEmail(scan.nextLine());
        logger.trace("Input phone number: ");
        user.setPhoneNumber(scan.nextLine());
        logger.trace("Input password: ");
        user.setPassword(scan.nextLine());

        List<BaseModelInterface> addressesList = addressDao.getListInfo();
        MainView.outputList(addressesList);
        logger.trace("Select address (1-{}) or create new(0): ", addressesList.size());
        int choice = scan.nextInt();
        if(choice == 0){
            AddressView addressView = new AddressView();
            user.setAddress(addressDao.getFullInfo(addressDao.createNew(addressView.createNew(scan))));
        }else
            user.setAddress(addressDao.getFullInfo(addressesList.get(choice - 1).getId()));
        scan.nextLine();
        return user;
    }

    @Override
    public BaseModelInterface updateModel(BaseModelInterface bsModel, Scanner scan) {
        User user = (User) bsModel;
        while(true) {
            logger.trace("Choice field to update:\n1-User name\n2-Surname\n3-Email\n4-Phone number\n5-Password\n6-Address\n'R'eturn\n");
            String choice = scan.nextLine();
            if (choice.equalsIgnoreCase("R")) {
                return user;
            } else {
                try {
                    int index = Integer.parseInt(choice);
                    if (index >= 1 && index <= 6) {
                        switch (index) {
                            case 1:
                                logger.trace("Input name: ");
                                user.setName(scan.nextLine());
                                break;
                            case 2:
                                logger.trace("Input surname: ");
                                user.setSurname(scan.nextLine());
                                break;
                            case 3:
                                logger.trace("Input email: ");
                                user.setEmail(scan.nextLine());
                                break;
                            case 4:
                                logger.trace("Input phone number: ");
                                user.setPhoneNumber(scan.nextLine());
                                break;
                            case 5:
                                logger.trace("Input password: ");
                                user.setPassword(scan.nextLine());
                                break;
                            case 6:
                                AddressDAO addressDao = new AddressDAO();
                                List<BaseModelInterface> addressesList = addressDao.getListInfo();
                                MainView.outputList(addressesList);
                                logger.trace("Select address (1-{}) or create new(0): ", addressesList.size());
                                int addressChoice = scan.nextInt();
                                scan.nextLine();
                                if(addressChoice == 0){
                                    AddressView addressView = new AddressView();
                                    user.setAddress(addressDao.getFullInfo(addressDao.createNew(addressView.createNew(scan))));
                                }else
                                    user.setAddress(addressDao.getFullInfo(addressesList.get(addressChoice - 1).getId()));
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
        }
    }
}