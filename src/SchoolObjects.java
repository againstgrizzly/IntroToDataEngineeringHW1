
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//root tag is Random-Data
@XmlRootElement (name="School-Data")
@XmlAccessorType(XmlAccessType.FIELD)
public class SchoolObjects {


    @XmlElementWrapper(name = "SchoolObjects")
    @XmlElement(name="SchoolObject")
    private List<SchoolObject> schoolObjectList;

    public List<SchoolObject> getSchoolList() {
        return schoolObjectList;
    }

    public void setSchoolList(List<SchoolObject> schoolObjects) {
        this.schoolObjectList = schoolObjects;
    }

    public static void main(String args[]) throws JAXBException,
            JsonParseException, JsonMappingException, IOException{

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        SchoolObject objArray[] = mapper.readValue(new File("res/schools.json"), SchoolObject[].class);

        List<SchoolObject> schoolObjects = Arrays.asList(objArray);
        SchoolObjects objs = new SchoolObjects();
        objs.setSchoolList(schoolObjects);

        JAXBContext context = JAXBContext.newInstance(SchoolObjects.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        // Write to File
        marshaller.marshal(objs, new File("res/schools.xml"));
    }
}
