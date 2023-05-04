package main.java.domain;

/**
* Класс данных о посетителях
*/
public class Visitor {
	// Идентификатор посетителя
	private Long id;
	// Фамилия посетителя
	private String surname;
	// Имя посетителя
	private String name;
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
	public Visitor(String surname, String name, String patronymic, String identityDocument, String address, String phone) {
		this.surname = surname;
		this.name = name;
		this.patronymic = patronymic;
		this.identityDocument = identityDocument;
		this.address = address;
		this.phone = phone;
	}
	public Visitor(Long id, String surname, String name, String patronymic, String identityDocument, String address, String phone) {
		this.id = id;
		this.surname = surname;
		this.name = name;
		this.patronymic = patronymic;
		this.identityDocument = identityDocument;
		this.address = address;
		this.phone = phone;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
		return "Visitor {id = " + id + ", surname = " + surname + ", name = " + name + ", patronymic = "
				+ patronymic + ", identityDocument = " + identityDocument + ", address = " + address + ", phone = " + phone
				+ "}";
	}
}
