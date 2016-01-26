package Client;

import sun.java2d.pipe.BufferedRenderPipe;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.*;
import java.net.*;
import Person.Student;
import Person.Prof;

/**
 * This Class represents the Client which is used to Input Date and send them to the Server
 */

class Client {
  BufferedReader br;
    Logger logger = Logger.getInstance();

    /**
     * Default constructor of the Class
     * Calls the guiMainMenu which is the start of the gui
     */
    public Client(){
        guiMainMenu();
    }

    /**
     * The Main Menu in the Console
     * Just calls the method you select in the console
     * 1. guiStudent, 2. guiProf. 3. Send a Test-XML which is not valid
     */

    private void guiMainMenu(){
    br = new BufferedReader(new InputStreamReader(System.in));
    String str;
    System.out.println("Enter 1. for Person.Student, 2. for Professors, 3. To send a xml with failures");
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
              logger.error(e.getLocalizedMessage());
          }

    } catch (IOException e) {
        logger.error(e.getLocalizedMessage());
    }


  }

    /**
     * The Gui to input Data of a Student in a String Array
     *
     */

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
        logger.error(e.getLocalizedMessage());
    }

      try {
          addStudent(studentinput);
      } catch (JAXBException e) {
          logger.error(e.getLocalizedMessage());
      }
  }

    /**
     * The Gui to input Data of a Prof in a String Array
     *
     */


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
          logger.error(e.getLocalizedMessage());
      }
      try {
          addProf(profinput);
      } catch (JAXBException e) {
          logger.error(e.getLocalizedMessage());
      }

  }

    /**
     * Marshalls the Data of a Student in a XML Document
     * @param data Stringarray of datas
     * @throws JAXBException Exception of the Marshalling
     */

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
            logger.error(e.getLocalizedMessage());
        }


    }

    /**
     * Marshalls the Data of a Prof in a XML Document
     * @param data Stringarray of datas
     * @throws JAXBException Exception of the Marshalling
     */
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
            logger.error(e.getLocalizedMessage());
        }


    }

    /**
     * Sends the data to the server and waits for the answer
     * if the server accepts the xml the client shuts down
     * if the server refuses the xml the Main GUI get`s started
     * @param sentence
     * @throws IOException
     */
    private void sendData(String sentence) throws IOException {

        String answer;

        Socket clientSocket = new Socket("localhost", 6789);
        DataOutputStream outToServer  = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader   inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        outToServer.writeBytes(sentence + '\n');
        answer= inFromServer.readLine();

        if(answer.equals("success")) {
            logger.info("Client: Server accepted the XML-Document");
        } else {
            logger.error("Client: Server refused the XML-Document");
            System.out.println("Server refused the Input. Please enter valid data");
            guiMainMenu();
        }

        clientSocket.close();

    }

    /**
     * Sends a XML of a Prof with not all data to the server
     * the server should refuse that xml
     * @throws JAXBException
     */
    private void sendfailure() throws JAXBException {
        Prof customer = new Prof();
        customer.setId(12);
        customer.setVorname("foo");
        customer.setNachname("manchu");
        customer.setStrasse("foostreet");


        java.io.StringWriter sw = new StringWriter();
        JAXBContext jaxbContext = JAXBContext.newInstance(Prof.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        marshaller.marshal(customer, sw);
        String sentence = sw.toString();
        try {
            sendData(sentence);
        } catch (IOException e) {
            logger.error(e.getLocalizedMessage());
        }

    }
}
