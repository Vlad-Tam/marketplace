package com.vladtam.marketplace.views;

import com.vladtam.marketplace.models.BaseModelInterface;
import com.vladtam.marketplace.models.City;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;
import java.util.function.Consumer;

public class CityView implements BaseViewInterface {
    public static final Logger logger = LoggerFactory.getLogger(CityView.class);

    @Override
    public BaseModelInterface createNew(Scanner scan) {
        City city = new City();
        logger.trace("Input city region: ");
        city.setRegion(scan.nextLine());
        logger.trace("Input city name: ");
        city.setName(scan.nextLine());
        return city;
    }

    @Override
    public BaseModelInterface updateModel(BaseModelInterface bsModel, Scanner scan) {
        City city = (City) bsModel;
        while(true) {
            logger.trace("Choice field to update:\n1-City name\n2-Region\n'R'eturn\n");
            String choice = scan.nextLine();
            if (choice.equalsIgnoreCase("R")) {
                return city;
            } else {
                handleUpdateChoice(choice, city, scan);
            }
        }
    }

    private void handleUpdateChoice(String choice, City city, Scanner scan) {
        try {
            int index = Integer.parseInt(choice);
            if (index >= 1 && index <= 2) {
                switch (index) {
                    case 1:
                        updateCityString("Input city name: ", city::setName, scan);
                        break;
                    case 2:
                        updateCityString("Input region: ", city::setRegion, scan);
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

    private void updateCityString(String message, Consumer<String> fieldSetter, Scanner scan) {
        logger.trace(message);
        fieldSetter.accept(scan.nextLine());
    }
}
