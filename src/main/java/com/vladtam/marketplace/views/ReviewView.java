package com.vladtam.marketplace.views;

import com.vladtam.marketplace.dao.UserDAO;
import com.vladtam.marketplace.models.*;

import java.util.List;
import java.util.Scanner;

public class ReviewView implements BaseViewInterface {
    @Override
    public BaseModelInterface createNew(Scanner scan) {
        Review review = new Review();
        UserDAO userDao = new UserDAO();
        System.out.println("Input rating (1-10): ");
        review.setRate(scan.nextByte());
        scan.nextLine();
        System.out.println("Input comment: ");
        review.setComment(scan.nextLine());
        List<BaseModelInterface> usersList = userDao.getListInfo();
        MainView.outputList(usersList);
        System.out.println("Select sender (1-" + usersList.size() + "): ");
        review.setSender(userDao.getFullInfo(scan.nextInt()));
        System.out.println("Select receiver (1-" + usersList.size() + "): ");
        review.setReceiver(userDao.getFullInfo(scan.nextInt()));
        scan.nextLine();
        return review;
    }

    @Override
    public BaseModelInterface updateModel(BaseModelInterface bsModel, Scanner scan) {
        Review review = (Review) bsModel;
        while(true) {
            System.out.println("Choice field to update:\n1-Rating\n2-Comment\n3-Sender\n4-Receiver\n'R'eturn\n");
            String choice = scan.nextLine();
            if (choice.equalsIgnoreCase("R")) {
                return review;
            } else {
                try {
                    int index = Integer.parseInt(choice);
                    if (index >= 1 && index <= 4) {
                        switch (index) {
                            case 1:
                                System.out.println("Input rating: ");
                                review.setRate(scan.nextByte());
                                scan.nextLine();
                                break;
                            case 2:
                                System.out.println("Input comment: ");
                                review.setComment(scan.nextLine());
                                break;
                            case 3:
                                UserDAO senderDao = new UserDAO();
                                List<BaseModelInterface> sendersList = senderDao.getListInfo();
                                MainView.outputList(sendersList);
                                System.out.println("Select sender (1-" + sendersList.size() + ") or create new(0): ");
                                int senderChoice = scan.nextInt();
                                scan.nextLine();
                                if(senderChoice == 0){
                                    UserView senderView = new UserView();
                                    review.setSender(senderDao.getFullInfo(senderDao.createNew(senderView.createNew(scan))));
                                }else
                                    review.setSender(senderDao.getFullInfo(sendersList.get(senderChoice - 1).getId()));
                                break;
                            case 4:
                                UserDAO receiverDao = new UserDAO();
                                List<BaseModelInterface> receiversList = receiverDao.getListInfo();
                                MainView.outputList(receiversList);
                                System.out.println("Select receiver (1-" + receiversList.size() + ") or create new(0): ");
                                int receiverChoice = scan.nextInt();
                                scan.nextLine();
                                if(receiverChoice == 0){
                                    UserView receiverView = new UserView();
                                    review.setReceiver(receiverDao.getFullInfo(receiverDao.createNew(receiverView.createNew(scan))));
                                }else
                                    review.setReceiver(receiverDao.getFullInfo(receiversList.get(receiverChoice - 1).getId()));
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
