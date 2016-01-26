import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Person {

  String vorname;
  String nachname;
  String geburtsdatum;
  String strasse;
  int hausnummer;
  int plz;
  String stadt;
  int id;

	public String getNachname() {
		return nachname;
	}

	@XmlElement
	public void setNachname(String name) {
		this.nachname = name;
	}

	public String getVorname() {
		return vorname;
	}

	@XmlElement
	public void setVorname(String name) {
		this.vorname = name;
	}

	public String getGeburtsdatum() {
		return geburtsdatum;
	}

	@XmlElement
	public void setGeburtsdatum(String age) {
		this.geburtsdatum = age;
	}

	public String getStrasse() {
		return strasse;
	}

	@XmlElement
	public void setStadt(String age) {
		this.stadt = age;
	}

	public String getStadt() {
		return stadt;
	}

	@XmlElement
	public void setStrasse(String age) {
		this.strasse = age;
	}

	public int getHausnummer() {
		return hausnummer;
	}

	@XmlElement
	public void setHausnummer(int age) {
		this.hausnummer = age;
	}

	public int getPlz() {
		return plz;
	}

	@XmlElement
	public void setPlz(int age) {
		this.plz = age;
	}

	public int getId() {
		return id;
	}

	@XmlAttribute
	public void setId(int id) {
		this.id = id;
	}
}


