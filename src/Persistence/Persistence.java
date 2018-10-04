package Persistence;

import GameWorld.GameWorld;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

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
            JAXBContext context = JAXBContext.newInstance(JAXBTestGame.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(gameWorld, new FileOutputStream(filename+".xml"));
        } catch (JAXBException | FileNotFoundException e) {
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
            GameWorld game = (GameWorld) jaxbUnmarshaller.unmarshal(file);
            return game;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

//    public static void main(String[] args){
//        List<JAXBTestItem> items = new ArrayList<>();
//        items.add(new JAXBTestItem(2, 2, 3, "Box"));
//        JAXBTestRoom bedroom = new JAXBTestRoom("bedroom", 1, 1, items);
//        JAXBTestRoom livingRoom = new JAXBTestRoom("living room", 2, 1, items);
//        List<JAXBTestRoom> rooms = new ArrayList<>();
//
//        rooms.add(bedroom);
//        rooms.add(livingRoom);
//
//        JAXBTestGame game = new JAXBTestGame(rooms);
//
//        ObjectToXml(game, "game");
//
//        JAXBTestGame game = XmlToObject(new File("game.xml"));
//        for (JAXBTestRoom room: game.getRooms()) {
//            System.out.println(room.getName());
//            System.out.println(room.getX());
//            System.out.println(room.getY());
//            for (JAXBTestItem item: room.getItems()) {
//                System.out.println(item.getName());
//            }
//        }
//    }
}
