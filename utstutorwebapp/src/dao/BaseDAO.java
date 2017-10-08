package dao;

import exception.DataValidationException;
import jaxblist.BaseJAXBList;
import model.BaseModel;


public interface BaseDAO<T extends BaseJAXBList, V extends BaseModel> {

    T read();

    T read(String filePath);

    V searchById(Integer id);

    void update(V oldModel, V newModel) throws DataValidationException;

    void create(V model) throws DataValidationException;

    void delete(V model) throws DataValidationException;

    void save() throws DataValidationException;

}
