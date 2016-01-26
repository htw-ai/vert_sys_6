import java.io.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Student extends Person implements Serializable {

  String fach;
  int semester;

	public int getSemester() {
		return semester;
	}

	public String getFach() {
		return fach;
	}

	@XmlElement
	public void setFach(String name) {
		this.fach = name;
	}

	@XmlElement
	public void setSemester(int age) {
		this.semester = age;
	}

	public boolean studentIsValid(){

		if(
				fach != null &&
						(semester <=0 || id >0 ) &&
						personIsValid()

				) return true;
		else return false;
	}
}
