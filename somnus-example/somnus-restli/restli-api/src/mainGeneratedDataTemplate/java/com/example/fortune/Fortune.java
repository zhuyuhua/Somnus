
package com.example.fortune;

import java.util.List;
import javax.annotation.Generated;
import com.linkedin.data.DataMap;
import com.linkedin.data.schema.PathSpec;
import com.linkedin.data.schema.RecordDataSchema;
import com.linkedin.data.template.DataTemplateUtil;
import com.linkedin.data.template.GetMode;
import com.linkedin.data.template.RecordTemplate;
import com.linkedin.data.template.SetMode;


/**
 * Generate a fortune cookie
 * 
 */
@Generated(value = "com.linkedin.pegasus.generator.JavaCodeUtil", comments = "Rest.li Data Template. Generated from E:\\svn\\git\\Somnus\\somnus-example\\somnus-restli\\restli-api\\src\\main\\pegasus\\com\\example\\fortune\\Fortune.pdsc.", date = "Sun Jul 26 17:09:54 CST 2015")
public class Fortune
    extends RecordTemplate
{

    private final static Fortune.Fields _fields = new Fortune.Fields();
    private final static RecordDataSchema SCHEMA = ((RecordDataSchema) DataTemplateUtil.parseSchema("{\"type\":\"record\",\"name\":\"Fortune\",\"namespace\":\"com.example.fortune\",\"doc\":\"Generate a fortune cookie\",\"fields\":[{\"name\":\"fortune\",\"type\":\"string\",\"doc\":\"The Fortune cookie string\"}]}"));
    private final static RecordDataSchema.Field FIELD_Fortune = SCHEMA.getField("fortune");

    public Fortune() {
        super(new DataMap(), SCHEMA);
    }

    public Fortune(DataMap data) {
        super(data, SCHEMA);
    }

    public static Fortune.Fields fields() {
        return _fields;
    }

    /**
     * Existence checker for fortune
     * 
     * @see Fields#fortune
     */
    public boolean hasFortune() {
        return contains(FIELD_Fortune);
    }

    /**
     * Remover for fortune
     * 
     * @see Fields#fortune
     */
    public void removeFortune() {
        remove(FIELD_Fortune);
    }

    /**
     * Getter for fortune
     * 
     * @see Fields#fortune
     */
    public String getFortune(GetMode mode) {
        return obtainDirect(FIELD_Fortune, String.class, mode);
    }

    /**
     * Getter for fortune
     * 
     * @see Fields#fortune
     */
    public String getFortune() {
        return getFortune(GetMode.STRICT);
    }

    /**
     * Setter for fortune
     * 
     * @see Fields#fortune
     */
    public Fortune setFortune(String value, SetMode mode) {
        putDirect(FIELD_Fortune, String.class, String.class, value, mode);
        return this;
    }

    /**
     * Setter for fortune
     * 
     * @see Fields#fortune
     */
    public Fortune setFortune(String value) {
        putDirect(FIELD_Fortune, String.class, String.class, value, SetMode.DISALLOW_NULL);
        return this;
    }

    @Override
    public Fortune clone()
        throws CloneNotSupportedException
    {
        return ((Fortune) super.clone());
    }

    @Override
    public Fortune copy()
        throws CloneNotSupportedException
    {
        return ((Fortune) super.copy());
    }

    public static class Fields
        extends PathSpec
    {


        public Fields(List<String> path, String name) {
            super(path, name);
        }

        public Fields() {
            super();
        }

        /**
         * The Fortune cookie string
         * 
         */
        public PathSpec fortune() {
            return new PathSpec(getPathComponents(), "fortune");
        }

    }

}