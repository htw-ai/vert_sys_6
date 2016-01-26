package Server;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import Person.Student;
import Person.Prof;

/**
 * The Class of the Server
 * It waits for a Client to send a XML of a Student or a Prof validates it an stores the object
 * if the xml is not valid it sends "error" to the client
 */
class Server
{

   public static void main(String argv[]) throws Exception
      {
          Logger logger = Logger.getInstance();
         String clientSentence;
         String capitalizedSentence;
         ServerSocket welcomeSocket = new ServerSocket(6789);

          /**
           * Starting the Server
           */
         while(true)
         {
            Socket connectionSocket = welcomeSocket.accept();
            BufferedReader inFromClient =
               new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
            clientSentence = inFromClient.readLine();
            logger.info("Received from client" + clientSentence);

             /**
              * Starting the unmarshalling and answering the client
              */
            try {
              try {
                JAXBContext jaxbContext = JAXBContext.newInstance(Student.class);
                Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                  StringReader reader = new StringReader(clientSentence);
                Student customer = (Student) jaxbUnmarshaller.unmarshal(reader);
                  if(customer.studentIsValid()) {
                      FileOutputStream fout = new FileOutputStream("c:\\tmp\\address.ser");
                      ObjectOutputStream oos = new ObjectOutputStream(fout);
                      oos.writeObject(customer);
                      String success = "success" + '\n';
                      outToClient.writeBytes(success);
                  }
                  else{
                      String error = "error" + '\n';
                      outToClient.writeBytes(error);
                  }
              } catch (JAXBException e) {
                JAXBContext jaxbContext = JAXBContext.newInstance(Prof.class);

                Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                  StringReader reader = new StringReader(clientSentence);
                Prof prof = (Prof) jaxbUnmarshaller.unmarshal(reader);

                  if(prof.profIsValid()) {
                      FileOutputStream fout = new FileOutputStream("c:\\tmp\\address.ser");
                      ObjectOutputStream oos = new ObjectOutputStream(fout);
                      oos.writeObject(prof);
                      String success = "success" + '\n';
                      outToClient.writeBytes(success);
                  }
                  else{
                      String error = "error" + '\n';
                      outToClient.writeBytes(error);
                  }

              }
            } catch (IOException | JAXBException e) {
                String error = "error" + '\n';
                outToClient.writeBytes(error);
            }

         }
      }
}
