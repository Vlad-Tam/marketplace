package com.vladtam.marketplace.dao;

import com.vladtam.marketplace.models.BaseModelInterface;
import java.util.List;

public interface BaseDAOInterface {
    BaseModelInterface getFullInfo(int id);
    List<BaseModelInterface> getListInfo();
    int createNew(BaseModelInterface bsModel);
    void delete(int id);
    void update(BaseModelInterface bsModel);
}
