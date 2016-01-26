package Person;

import java.io.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Prof extends Person implements Serializable {

  String fachbereich;

	public String getFachbereich() {
		return fachbereich;
	}

	@XmlElement
	public void setFachbereich(String name) {
		this.fachbereich = name;
	}

	public boolean profIsValid(){

		if(
				fachbereich != null &&
						personIsValid()

				) return true;
		else return false;
	}
}

