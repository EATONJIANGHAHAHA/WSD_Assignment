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

    /**
     * If the id is not null, return the id.
     * Else return an auto-increment id.
     * @param v
     * @return
     * @throws Exception
     */
    @Override
    public Integer marshal(Integer v) throws Exception {
        if(v != null && v != 0) {
            counter = v + 1;
            return v;
        }
        return counter ++;
    }
}
