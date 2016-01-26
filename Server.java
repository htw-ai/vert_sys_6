import java.io.*;
import java.net.*;
import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

class TCPServer
{
   public static void main(String argv[]) throws Exception
      {
         String clientSentence;
         String capitalizedSentence;
         ServerSocket welcomeSocket = new ServerSocket(6789);

         while(true)
         {
            Socket connectionSocket = welcomeSocket.accept();
            BufferedReader inFromClient =
               new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
            clientSentence = inFromClient.readLine();
            try {
              try {
                JAXBContext jaxbContext = JAXBContext.newInstance(Student.class);

                Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                Student customer = (Student) jaxbUnmarshaller.unmarshal(clientSentence);

                FileOutputStream fout = new FileOutputStream("c:\\address.ser");
                ObjectOutputStream oos = new ObjectOutputStream(fout);
                oos.writeObject(customer);

                outToClient.writeBytes("success");
              } catch (JAXBException e) {
                JAXBContext jaxbContext = JAXBContext.newInstance(Prof.class);

                Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                Prof prof = (Prof) jaxbUnmarshaller.unmarshal(clientSentence);

                FileOutputStream fout = new FileOutputStream("c:\\address.ser");
                ObjectOutputStream oos = new ObjectOutputStream(fout);
                oos.writeObject(prof);
              }
            } catch (IOException | JAXBException e) {
              outToClient.writeBytes("error");
            }
         }
      }
}
