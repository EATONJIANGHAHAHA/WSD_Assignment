package dao;

import exception.DataValidationException;
import jaxblist.BaseJAXBList;
import model.BaseModel;

/**
 * Defines most operations of a dao.
 * @param <T> The class of jaxb list.
 * @param <V> The java bean.
 */
public interface BaseDAO<T, V> {

    T read();

    T read(String filePath);

    V searchById(Integer id);

    void update(V oldModel, V newModel) throws DataValidationException;

    void create(V model) throws DataValidationException;

    void delete(V model) throws DataValidationException;

    void save() throws DataValidationException;

}
