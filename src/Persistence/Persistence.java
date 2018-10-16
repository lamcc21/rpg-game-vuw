package Persistence;

import GameWorld.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * For SWEN225 Group Project - Michael Henrys
 * This Persistence class contains static methods for converting GameWorld objects to XML files
 * and XML files into GameWorld objects
 */
public class Persistence {

    /**
     * ObjectToXml method takes a GameWorld object and creates an XML file with
     * the name [filename].xml in the project root folder
     * @param gameWorld
     * @param filename
     */
    public static void ObjectToXml(GameWorld gameWorld, String filename){
        try {
            JAXBContext context = JAXBContext.newInstance(GameWorld.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(gameWorld, new FileOutputStream(filename));
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Takes a file object and returns a GameWorld object
     * @param file
     * @return GameWorld object
     */
    public static GameWorld XmlToObject(File file){
        try {
            JAXBContext context = JAXBContext.newInstance(GameWorld.class);
            Unmarshaller jaxbUnmarshaller = context.createUnmarshaller();
            return (GameWorld) jaxbUnmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

}
