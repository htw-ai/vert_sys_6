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
             System.out.println(clientSentence);

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
