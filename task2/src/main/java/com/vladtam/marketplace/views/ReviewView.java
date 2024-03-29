package com.vladtam.marketplace.views;

import com.vladtam.marketplace.dao.UserDAO;
import com.vladtam.marketplace.models.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;

public class ReviewView implements BaseViewInterface {
    public static final Logger logger = LoggerFactory.getLogger(ReviewView.class);

    @Override
    public BaseModelInterface createNew(Scanner scan) {
        Review review = new Review();
        UserDAO userDao = new UserDAO();
        logger.trace("Input rating (1-10): ");
        review.setRate(scan.nextByte());
        scan.nextLine();
        logger.trace("Input comment: ");
        review.setComment(scan.nextLine());
        List<BaseModelInterface> usersList = userDao.getListInfo();
        MainView.outputList(usersList);
        logger.trace("Select sender (1-{}): ", usersList.size());
        review.setSender(userDao.getFullInfo(scan.nextInt()));
        logger.trace("Select receiver (1-{}): ", usersList.size());
        review.setReceiver(userDao.getFullInfo(scan.nextInt()));
        scan.nextLine();
        return review;
    }

    @Override
    public BaseModelInterface updateModel(BaseModelInterface bsModel, Scanner scan) {
        Review review = (Review) bsModel;
        while(true) {
            logger.trace("Choice field to update:\n1-Rating\n2-Comment\n3-Sender\n4-Receiver\n'R'eturn\n");
            String choice = scan.nextLine();
            if (choice.equalsIgnoreCase("R")) {
                return review;
            } else {
                handleUpdateChoice(choice, review, scan);
            }
        }
    }

    private void handleUpdateChoice(String choice, Review review, Scanner scan) {
        try {
            int index = Integer.parseInt(choice);
            if (index >= 1 && index <= 4) {
                switch (index) {
                    case 1:
                        updateReviewByte("Input rating: ", review::setRate, scan);
                        break;
                    case 2:
                        updateReviewString("Input comment: ", review::setComment, scan);
                        break;
                    case 3:
                        updateSender(review, scan);
                        break;
                    case 4:
                        updateReceiver(review, scan);
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

    private void updateReviewString(String message, Consumer<String> fieldSetter, Scanner scan) {
        logger.trace(message);
        fieldSetter.accept(scan.nextLine());
    }

    private void updateReviewByte(String message, Consumer<Byte> fieldSetter, Scanner scan) {
        logger.trace(message);
        fieldSetter.accept(scan.nextByte());
        scan.nextLine();
    }

    private void updateSender(Review review, Scanner scan) {
        UserDAO senderDao = new UserDAO();
        List<BaseModelInterface> sendersList = senderDao.getListInfo();
        MainView.outputList(sendersList);
        logger.trace("Select sender (1-{} ) or create new(0): ", sendersList.size());
        int senderChoice = scan.nextInt();
        scan.nextLine();
        if(senderChoice == 0){
            UserView senderView = new UserView();
            review.setSender(senderDao.getFullInfo(senderDao.createNew(senderView.createNew(scan))));
        }else
            review.setSender(senderDao.getFullInfo(sendersList.get(senderChoice - 1).getId()));
    }

    private void updateReceiver(Review review, Scanner scan) {
        UserDAO receiverDao = new UserDAO();
        List<BaseModelInterface> receiversList = receiverDao.getListInfo();
        MainView.outputList(receiversList);
        logger.trace("Select receiver (1-{} ) or create new(0): ", receiversList.size());
        int receiverChoice = scan.nextInt();
        scan.nextLine();
        if(receiverChoice == 0){
            UserView receiverView = new UserView();
            review.setReceiver(receiverDao.getFullInfo(receiverDao.createNew(receiverView.createNew(scan))));
        }else
            review.setReceiver(receiverDao.getFullInfo(receiversList.get(receiverChoice - 1).getId()));
    }
}