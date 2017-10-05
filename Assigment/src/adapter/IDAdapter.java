package adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * To assign an auto increment id for the xml records.
 */
public class IDAdapter extends XmlAdapter<Integer, Integer> {

    private int counter = 1;
    @Override
    public Integer unmarshal(Integer v) throws Exception {
        return v;
    }

    @Override
    public Integer marshal(Integer v) throws Exception {
        return counter ++;
    }
}
