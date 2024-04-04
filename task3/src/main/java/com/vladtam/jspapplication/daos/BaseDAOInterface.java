package com.vladtam.jspapplication.daos;

import com.vladtam.jspapplication.models.BaseModelInterface;
import java.util.List;

public interface BaseDAOInterface {
    BaseModelInterface getFullInfo(int id);
    List<BaseModelInterface> getListInfo();
    int createNew(BaseModelInterface bsModel);
    void delete(int id);
    void update(BaseModelInterface bsModel);
}
