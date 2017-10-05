package dao;

import adapter.IDAdapter;
import exception.DataValidationException;
import jaxblist.BaseJAXBList;
import model.BaseModel;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.*;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.*;
import java.lang.reflect.ParameterizedType;

/**
 * Read the date stored in the xml, and restored the new data.
 * @param <T>
 */
public class BaseDAO_Impl<T extends BaseJAXBList<V>, V extends BaseModel> implements Serializable, BaseDAO<T, V >{


    private String filePath;
    private String schemaPath;
    private T items;
    private Class<T> clazz;

    public BaseDAO_Impl(){
        ParameterizedType type = (ParameterizedType) this.getClass()
                .getGenericSuperclass();
        clazz = (Class<T>) type.getActualTypeArguments()[0];
    }

    public BaseDAO_Impl(String filePath){
        this();
        setFilePath(filePath);
    }

    public BaseDAO_Impl(String filePath, String schemaPath, T jaxbList) {
        this();
        this.filePath = filePath;
        this.schemaPath = schemaPath;
        this.items = jaxbList;
    }

    public BaseDAO_Impl(String filePath, String schemaPath) {
        this();
        setSchemaPath(schemaPath);
        setFilePath(filePath);
    }

    public String getFilePath() {
        return filePath;
    }

    /**
     * Initialize the items while setting the file path.
     * @param filePath
     * @throws JAXBException
     * @throws IOException
     */
    public void setFilePath(String filePath){
        JAXBContext jc = null;
        try {
            jc = JAXBContext.newInstance( clazz );
            Unmarshaller u = null;
            u = jc.createUnmarshaller();
            this.filePath = filePath;
        // Now unmarshal the object from the file
            FileInputStream fin = null;
            fin = new FileInputStream(filePath);
            items = (T) u.unmarshal(fin);
            fin.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Update the xml file.
     * @throws IOException
     * @throws JAXBException
     * @throws SAXException
     */
    @Override
    public void save() throws DataValidationException {

        try{
            SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = null;
            schema = sf.newSchema(new File(schemaPath));

            JAXBContext jc = JAXBContext.newInstance(clazz);

            Marshaller m = jc.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            m.setAdapter(new IDAdapter());
            m.setSchema(schema);
            m.setEventHandler( new ValidationEventHandler() {
            /**
             * To stop the marshal when some of the values are invalid.
             * @param event
             * @return
             */
            @Override
            public boolean handleEvent(ValidationEvent event) {
                return false;
            }
        });

            FileOutputStream fout = new FileOutputStream(filePath);;
            try{
                m.marshal(items, fout);
                items.setOldItem(null);
            }
            catch (MarshalException e){
                //Recover the list, recover the old xml document.
                fout = new FileOutputStream(filePath);
                e.printStackTrace();
                items.recoverList();
                m.setAdapter(new IDAdapter());
                m.marshal(items, fout);
                throw new MarshalException(e.getLinkedException().getMessage());

            }
            fout.close();
            System.out.print("saved");
        }
        catch (MarshalException e){
            throw new DataValidationException(e.getMessage());
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }



    public T getItems() {
        return items;
    }

    public void setItems(T items) {
        this.items = items;
    }

    public String getSchemaPath() {
        return schemaPath;
    }

    public void setSchemaPath(String schemaPath) {
        this.schemaPath = schemaPath;
    }

    @Override
    public T read(){
        if(items == null) setFilePath(getFilePath());
        return getItems();
    }

    @Override
    public T read(String filePath){
        setFilePath(filePath);
        return getItems();
    }

    @Override
    public V searchById(Integer id) {
        return items.findById(id);
    }

    @Override
    public void update(V oldItem, V newItem) throws DataValidationException {
        items.setOldItem(oldItem);
        items.update(newItem);
        save();
    }

    @Override
    public void create(V item) throws DataValidationException {
        items.add(item);
        save();

    }

    @Override
    public void delete(V item) throws DataValidationException {
        items.remove(item);
        save();
    }
}
