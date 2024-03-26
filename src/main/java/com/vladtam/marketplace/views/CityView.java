package com.vladtam.marketplace.views;

import com.vladtam.marketplace.models.BaseModelInterface;
import com.vladtam.marketplace.models.City;

import java.util.Scanner;

public class CityView implements BaseViewInterface {
    @Override
    public BaseModelInterface createNew(Scanner scan) {
        City city = new City();
        System.out.println("Input city region: ");
        city.setRegion(scan.nextLine());
        System.out.println("Input city name: ");
        city.setName(scan.nextLine());
        return city;
    }

    @Override
    public BaseModelInterface updateModel(BaseModelInterface bsModel, Scanner scan) {
        City city = (City) bsModel;
        while(true) {
            System.out.println("Choice field to update:\n1-City name\n2-Region\n'R'eturn\n");
            String choice = scan.nextLine();
            if (choice.equalsIgnoreCase("R")) {
                return city;
            } else {
                try {
                    int index = Integer.parseInt(choice);
                    if (index >= 1 && index <= 2) {
                        switch (index) {
                            case 1:
                                System.out.println("Input city name: ");
                                city.setName(scan.nextLine());
                                break;
                            case 2:
                                System.out.println("Input region: ");
                                city.setRegion(scan.nextLine());
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
