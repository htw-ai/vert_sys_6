import sun.java2d.pipe.BufferedRenderPipe;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.*;
import java.net.*;

class Client {
  BufferedReader br;

    public Client(){
        guiMainMenu();
    }
/*
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
*/
  private void guiMainMenu(){
    br = new BufferedReader(new InputStreamReader(System.in));
    String str;
    System.out.println("Enter 1. for Student, 2. for Professors, 3. To send a xml with failures");
    try {
      str = br.readLine();
      if(str.equals("1"))
        guiAddStudent();
      else if(str.equals("2"))
        guiAddProf();
      else
          try {
              sendfailure();
          } catch (JAXBException e) {
              //ToDo: Logging
              e.printStackTrace();
          }

    } catch (IOException e) {
      //ToDo: Logging
      e.printStackTrace();
    }


  }

  private void guiAddStudent(){
    String[] studentinput = new String[10];

    try {

      System.out.println("Enter ID:");
      studentinput[0] = br.readLine();
      System.out.println("Enter prename:");
      studentinput[1] = br.readLine();
      System.out.println("Enter surname:");
      studentinput[2] = br.readLine();
      System.out.println("Enter street:");
      studentinput[3] = br.readLine();
      System.out.println("Enter house number:");
      studentinput[4] = br.readLine();
      System.out.println("Enter postcode:");
      studentinput[5] = br.readLine();
      System.out.println("Enter city:");
      studentinput[6] = br.readLine();
      System.out.println("Enter birthday:");
      studentinput[7] = br.readLine();
      System.out.println("Enter subject:");
      studentinput[8] = br.readLine();
      System.out.println("Enter semester:");
      studentinput[9] = br.readLine();
    } catch (IOException e) {
      //ToDo: Logging
      e.printStackTrace();
    }

      try {
          addStudent(studentinput);
      } catch (JAXBException e) {
          //ToDo: Logging
          e.printStackTrace();
      }
  }

  private void guiAddProf(){
      String[] profinput = new String[9];

      try {

          System.out.println("Enter ID:");
          profinput[0] = br.readLine();
          System.out.println("Enter prename:");
          profinput[1] = br.readLine();
          System.out.println("Enter surname:");
          profinput[2] = br.readLine();
          System.out.println("Enter street:");
          profinput[3] = br.readLine();
          System.out.println("Enter house number:");
          profinput[4] = br.readLine();
          System.out.println("Enter postcode:");
          profinput[5] = br.readLine();
          System.out.println("Enter city:");
          profinput[6] = br.readLine();
          System.out.println("Enter birthday:");
          profinput[7] = br.readLine();
          System.out.println("Enter special field:");
          profinput[8] = br.readLine();
      } catch (IOException e) {
          //ToDo: Logging
          e.printStackTrace();
      }
      try {
          addProf(profinput);
      } catch (JAXBException e) {
          //ToDo: Logging
          e.printStackTrace();
      }

  }

    private void addStudent(String[] data) throws JAXBException {
        Student customer = new Student();
        customer.setId(Integer.parseInt(data[0]));
        customer.setVorname(data[1]);
        customer.setNachname(data[2]);
        customer.setStrasse(data[3]);
        customer.setHausnummer(Integer.parseInt(data[4]));
        customer.setPlz(Integer.parseInt(data[5]));
        customer.setStadt(data[6]);
        customer.setGeburtsdatum(data[7]);
        customer.setFach(data[8]);
        customer.setSemester(Integer.parseInt(data[9]));

        java.io.StringWriter sw = new StringWriter();
        JAXBContext jaxbContext = JAXBContext.newInstance(Student.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        marshaller.marshal(customer, sw);
        String sentence = sw.toString();
        try {
            sendData(sentence);
        } catch (IOException e) {
            //ToDo: Logging
            e.printStackTrace();
        }


    }

    private void addProf(String[] data) throws JAXBException {

        Prof customer = new Prof();
        customer.setId(Integer.parseInt(data[0]));
        customer.setVorname(data[1]);
        customer.setNachname(data[2]);
        customer.setStrasse(data[3]);
        customer.setHausnummer(Integer.parseInt(data[4]));
        customer.setPlz(Integer.parseInt(data[5]));
        customer.setStadt(data[6]);
        customer.setGeburtsdatum(data[7]);
        customer.setFachbereich(data[8]);

        java.io.StringWriter sw = new StringWriter();
        JAXBContext jaxbContext = JAXBContext.newInstance(Prof.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        marshaller.marshal(customer, sw);
        String sentence = sw.toString();
        try {
            sendData(sentence);
        } catch (IOException e) {
            //ToDo:Logging
            e.printStackTrace();
        }


    }

    private void sendData(String sentence) throws IOException {


        Socket clientSocket = new Socket("localhost", 6789);
        DataOutputStream outToServer  = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader   inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        outToServer.writeBytes(sentence + '\n');
        String modifiedSentence = inFromServer.readLine();

        if(modifiedSentence == "success") {
            //TODO use log class
            System.out.println("Success");
        } else {
            //TODO error handling
        }

        clientSocket.close();

    }

    private void sendfailure() throws JAXBException {

        Prof customer = new Prof();
        customer.setId(12);
        customer.setVorname("foo");
        customer.setNachname("manchu");
        customer.setStrasse("foo-street");
        customer.setHausnummer(42);


        java.io.StringWriter sw = new StringWriter();
        JAXBContext jaxbContext = JAXBContext.newInstance(Prof.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        marshaller.marshal(customer, sw);
        String sentence = sw.toString();
        try {
            sendData(sentence);
        } catch (IOException e) {
            //ToDo:Logging
            e.printStackTrace();
        }

    }
}
