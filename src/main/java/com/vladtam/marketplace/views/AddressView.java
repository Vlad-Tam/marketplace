package com.vladtam.marketplace.views;

import com.vladtam.marketplace.dao.CityDAO;
import com.vladtam.marketplace.models.Address;
import com.vladtam.marketplace.models.BaseModelInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Scanner;

public class AddressView implements BaseViewInterface {
    public static final Logger logger = LoggerFactory.getLogger(AddressView.class);

    @Override
    public BaseModelInterface createNew(Scanner scan) {
        Address address = new Address();
        CityDAO cityDao = new CityDAO();
        List<BaseModelInterface> citiesList = cityDao.getListInfo();
        MainView.outputList(citiesList);
        logger.trace("Select city (1-{}) or create new(0): ", citiesList.size());
        int choice = scan.nextInt();
        if(choice == 0){
            CityView cityView = new CityView();
            address.setCity(cityDao.getFullInfo(cityDao.createNew(cityView.createNew(scan))));
        }else
            address.setCity(cityDao.getFullInfo(citiesList.get(choice - 1).getId()));
        scan.nextLine();

        logger.trace("Input street name: ");
        address.setStreet(scan.nextLine());
        logger.trace("Input house number: ");
        address.setHouseNumber(scan.nextInt());
        logger.trace("Input flat number: ");
        address.setFlatNumber(scan.nextInt());
        scan.nextLine();
        return address;
    }

    @Override
    public BaseModelInterface updateModel(BaseModelInterface bsModel, Scanner scan) {
        Address address = (Address) bsModel;
        while(true) {
            logger.trace("Choice field to update:\n1-City\n2-Street\n3-House number\n4-Flat number\n'R'eturn\n");
            String choice = scan.nextLine();
            if (choice.equalsIgnoreCase("R")) {
                return address;
            } else {
                try {
                    int index = Integer.parseInt(choice);
                    if (index >= 1 && index <= 4) {
                        switch (index) {
                            case 1:
                                CityDAO cityDao = new CityDAO();
                                List<BaseModelInterface> citiesList = cityDao.getListInfo();
                                MainView.outputList(citiesList);
                                logger.trace("Select city (1-{}) or create new(0): ", citiesList.size());
                                int cityChoice = scan.nextInt();
                                scan.nextLine();
                                if(cityChoice == 0){
                                    CityView cityView = new CityView();
                                    address.setCity(cityDao.getFullInfo(cityDao.createNew(cityView.createNew(scan))));
                                }else
                                    address.setCity(cityDao.getFullInfo(citiesList.get(cityChoice - 1).getId()));
                                break;
                            case 2:
                                logger.trace("Input street name: ");
                                address.setStreet(scan.nextLine());
                                break;
                            case 3:
                                logger.trace("Input house number: ");
                                address.setHouseNumber(scan.nextInt());
                                scan.nextLine();
                                break;
                            case 4:
                                logger.trace("Input flat number: ");
                                address.setFlatNumber(scan.nextInt());
                                scan.nextLine();
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
