package application;

import adapter.IDAdapter;
import jaxblist.BaseJAXBList;
import org.xml.sax.SAXException;
import sun.rmi.runtime.Log;

import javax.xml.XMLConstants;
import javax.xml.bind.*;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.*;
import java.lang.reflect.ParameterizedType;

public class BaseApplication<T extends BaseJAXBList> implements Serializable{


    private String filePath;
    private String schemaPath;
    private T items;
    private Class<T> clazz;

    public BaseApplication(){
        ParameterizedType type = (ParameterizedType) this.getClass()
                .getGenericSuperclass();
        clazz = (Class<T>) type.getActualTypeArguments()[0];
    }

    public BaseApplication(String filePath, String schemaPath, T jaxbList) {
        this();
        this.filePath = filePath;
        this.schemaPath = schemaPath;
        this.items = jaxbList;
    }

    public BaseApplication(String filePath, String schemaPath) throws JAXBException, IOException {
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
    public void setFilePath(String filePath) throws JAXBException, IOException {
        JAXBContext jc = JAXBContext.newInstance( clazz );
        Unmarshaller u = jc.createUnmarshaller();
        this.filePath = filePath;
        // Now unmarshal the object from the file
        FileInputStream fin = new FileInputStream(filePath);
        items = (T) u.unmarshal(fin);
        fin.close();
    }

    /**
     * Update the xml file.
     * @throws IOException
     * @throws JAXBException
     * @throws SAXException
     */
    public void save() throws IOException, JAXBException, SAXException {

        SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = sf.newSchema(new File(schemaPath));

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

        FileOutputStream fout = new FileOutputStream(filePath);
        try{
            m.marshal(items, fout);}
        catch (MarshalException e){
            items.removeLast();
            m.setAdapter(new IDAdapter());
            m.marshal(items, fout);
            throw new ValidationException(e);
        }
        fout.close();
        System.out.print("saved");
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

}
