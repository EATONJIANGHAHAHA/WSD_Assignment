package application;

import adapter.IDAdapter;
import sun.rmi.runtime.Log;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

public class BaseApplication<T> implements Serializable{


    private String filePath;
    private T items;
    private Class<T> clazz;

    public BaseApplication(){
        ParameterizedType type = (ParameterizedType) this.getClass()
                .getGenericSuperclass();
        clazz = (Class<T>) type.getActualTypeArguments()[0];
    }

    public BaseApplication(String filePath, T jaxbList) {
        this();
        this.filePath = filePath;
        this.items = jaxbList;
    }

    public BaseApplication(String filePath) throws JAXBException, IOException {
        this();
        setFilePath(filePath);
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) throws JAXBException, IOException {
        JAXBContext jc = JAXBContext.newInstance( clazz );
        Unmarshaller u = jc.createUnmarshaller();
        this.filePath = filePath;
        // Now unmarshal the object from the file
        FileInputStream fin = new FileInputStream(filePath);
        items = (T) u.unmarshal(fin);
        fin.close();
    }

    public void save() throws IOException, JAXBException{
        JAXBContext jc = JAXBContext.newInstance(clazz);
        Marshaller m = jc.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        m.setAdapter(new IDAdapter());
        FileOutputStream fout = new FileOutputStream(filePath);
        m.marshal(items, fout);
        fout.close();
        System.out.print("saved");
    }

    public T getItems() {
        return items;
    }

    public void setItems(T items) {
        this.items = items;
    }
}
