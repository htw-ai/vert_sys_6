import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.*;
import java.net.*;

class Client {
  public static void main(String argv[]) throws Exception {
    String sentence;
    String modifiedSentence;

    //TODO nutzereingabe
    //TODO nutzereingabe

    java.io.StringWriter sw = new StringWriter();
    boolean addStudent = false;

    if(addStudent) {
      Student customer = new Student();
      customer.setId(100);
      customer.setVorname("mkyong");
      customer.setNachname("mkyong");
      customer.setStrasse("mkyong");
      customer.setHausnummer(32);
      customer.setPlz(32);
      customer.setStadt("32");
      customer.setGeburtsdatum("29-3-1990");
      customer.setFach("29");
      customer.setSemester(32);

      JAXBContext jaxbContext = JAXBContext.newInstance(Student.class);
      Marshaller marshaller = jaxbContext.createMarshaller();
      marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
      marshaller.marshal(customer, sw);
    } else {
      Prof customer = new Prof();
      customer.setId(100);
      customer.setVorname("mkyong");
      customer.setNachname("mkyong");
      customer.setStrasse("mkyong");
      customer.setHausnummer(32);
      customer.setPlz(32);
      customer.setStadt("32");
      customer.setGeburtsdatum("29-3-1990");
      customer.setFachbereich("29");

      JAXBContext jaxbContext = JAXBContext.newInstance(Prof.class);
      Marshaller marshaller = jaxbContext.createMarshaller();
      marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
      marshaller.marshal(customer, sw);
    }

    sentence = sw.toString();

    Socket clientSocket = new Socket("localhost", 6789);
    DataOutputStream outToServer  = new DataOutputStream(clientSocket.getOutputStream());
    BufferedReader   inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    outToServer.writeBytes(sentence + '\n');
    modifiedSentence = inFromServer.readLine();

    if(modifiedSentence == "success") {
      //TODO use log class
      System.out.println("Success");
    } else {
      //TODO error handling
    }

    clientSocket.close();
  }
}
