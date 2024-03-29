<<<<<<<< HEAD:src/main/java/com/vladtam/jspapplication/daos/BaseDAOInterface.java
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
========
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
>>>>>>>> main:task2/src/main/java/com/vladtam/marketplace/dao/BaseDAOInterface.java
