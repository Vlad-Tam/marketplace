package com.vladtam.marketplace.views;

import com.vladtam.marketplace.models.BaseModelInterface;

import java.util.Scanner;

public interface BaseViewInterface {
    BaseModelInterface createNew(Scanner scan);
    BaseModelInterface updateModel(BaseModelInterface bsModel, Scanner scan);
}
