package com.vladtam.marketplace.views;

import com.vladtam.marketplace.dao.BaseDAO;
import com.vladtam.marketplace.models.BaseModel;

import java.util.Scanner;

public interface BaseView {
    BaseModel createNew(Scanner scan);
    BaseModel updateModel(BaseModel bsModel, Scanner scan);
}
