package com.vladtam.marketplace.views;

import com.vladtam.marketplace.dao.CityDAO;
import com.vladtam.marketplace.models.Address;
import com.vladtam.marketplace.models.BaseModel;

import java.util.List;
import java.util.Scanner;

public class AddressView implements BaseView{
    @Override
    public BaseModel createNew(Scanner scan) {
        Address address = new Address();
        CityDAO cityDao = new CityDAO();
        List<BaseModel> citiesList = cityDao.getListInfo();
        MainView.outputList(citiesList);
        System.out.println("Select city (1-" + citiesList.size() + ") or create new(0): ");
        int choice = scan.nextInt();
        if(choice == 0){
            CityView cityView = new CityView();
            address.setCity(cityDao.getFullInfo(cityDao.createNew(cityView.createNew(scan))));
        }else
            address.setCity(cityDao.getFullInfo(citiesList.get(choice - 1).getId()));
        scan.nextLine();

        System.out.println("Input street name: ");
        address.setStreet(scan.nextLine());
        System.out.println("Input house number: ");
        address.setHouseNumber(scan.nextInt());
        System.out.println("Input flat number: ");
        address.setFlatNumber(scan.nextInt());
        scan.nextLine();
        return address;
    }

    @Override
    public BaseModel updateModel(BaseModel bsModel, Scanner scan) {
        Address address = (Address) bsModel;
        while(true) {
            System.out.println("Choice field to update:\n1-City\n2-Street\n3-House number\n4-Flat number\n'R'eturn\n");
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
                                List<BaseModel> citiesList = cityDao.getListInfo();
                                MainView.outputList(citiesList);
                                System.out.println("Select city (1-" + citiesList.size() + ") or create new(0): ");
                                int cityChoice = scan.nextInt();
                                scan.nextLine();
                                if(cityChoice == 0){
                                    CityView cityView = new CityView();
                                    address.setCity(cityDao.getFullInfo(cityDao.createNew(cityView.createNew(scan))));
                                }else
                                    address.setCity(cityDao.getFullInfo(citiesList.get(cityChoice - 1).getId()));
                                break;
                            case 2:
                                System.out.println("Input street name: ");
                                address.setStreet(scan.nextLine());
                                break;
                            case 3:
                                System.out.println("Input house number: ");
                                address.setHouseNumber(scan.nextInt());
                                scan.nextLine();
                                break;
                            case 4:
                                System.out.println("Input flat number: ");
                                address.setFlatNumber(scan.nextInt());
                                scan.nextLine();
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
