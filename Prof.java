import java.io.*;
import javax.xml.bind.annotation.XmlAttribute;
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
}

