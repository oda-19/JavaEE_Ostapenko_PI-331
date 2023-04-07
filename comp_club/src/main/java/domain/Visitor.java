package domain;

/**
* Класс данных о посетителях
*/
public class Visitor {
	// Идентификатор посетителя
	private Long visitorId;
	// Имя посетителя
	private String name;
	// Фамилия посетителя
	private String surname;
	// Отчество посетителя
	private String patronymic;
	// Документ, удостоверяющий личность посетителя
	private String identityDocument;
	// Адрес посетителя
	private String address;
	// Телефон посетителя
	private String phone;
	
	public Visitor() {
		
	}
	public Visitor(String name, String surname, String patronymic, String identityDocument,
			String address, String phone) {
		this.name = name;
		this.surname = surname;
		this.patronymic = patronymic;
		this.identityDocument = identityDocument;
		this.address = address;
		this.phone = phone;
	}
	public Visitor(Long visitorId, String name, String surname, String patronymic, String identityDocument,
			String address, String phone) {
		this.visitorId = visitorId;
		this.name = name;
		this.surname = surname;
		this.patronymic = patronymic;
		this.identityDocument = identityDocument;
		this.address = address;
		this.phone = phone;
	}
	
	public Long getVisitorId() {
		return visitorId;
	}
	public void setVisitorId(Long visitorId) {
		this.visitorId = visitorId;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public String getPatronymic() {
		return patronymic;
	}
	public void setPatronymic(String patronymic) {
		this.patronymic = patronymic;
	}
	
	public String getIdentityDocument() {
		return identityDocument;
	}
	public void setIdentityDocument(String identityDocument) {
		this.identityDocument = identityDocument;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Override
	public String toString() {
		return "Visitor {visitorId = " + visitorId + ", name = " + name + ", surname = " + surname + ", patronymic = "
				+ patronymic + ", identityDocument = " + identityDocument + ", address = " + address + ", phone = " + phone
				+ "}";
	}
}
