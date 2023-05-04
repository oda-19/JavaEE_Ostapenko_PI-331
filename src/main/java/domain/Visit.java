package main.java.domain;

import java.util.Date;

/**
* Класс данных о посещениях компьютерного клуба
*/
public class Visit {
	// Идентификатор посещения
	private Long id;
	// Дата посещения компьютерного клуба
	private Date visitDate;
	// Продолжительность посещения компьютерного клуба
	private String visitLength;
	// Сумма к оплате за посещение компьютерного клуба
	private Float pay;
	
	// Внешний ключ - ссылка на сущность Visitor
	private Long visitorId;
	// Навигационное свойства - ссылка на посетителя
	private Visitor visitor;	
		
	// Внешний ключ - ссылка на сущность Computer
	private Long computerId;
	// Навигационное свойства - ссылка на компьютер
	private Computer computer;
	
	public Visit() {
		
	}
	public Visit(Long id, Date visitDate, String visitLength, Float pay, Long visitorId, Long computerId) {
		this.id = id;
		this.visitDate = visitDate;
		this.visitLength = visitLength;
		this.pay = pay;
		this.visitorId = visitorId;
		this.computerId = computerId;
	}
	public Visit(Long id, Date visitDate, String visitLength, Float pay, Long visitorId, Visitor visitor,
				 Long computerId, Computer computer) {
		this.id = id;
		this.visitDate = visitDate;
		this.visitLength = visitLength;
		this.pay = pay;
		this.visitorId = visitorId;
		this.visitor = visitor;
		this.computerId = computerId;
		this.computer = computer;
	}

    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Date getVisitDate() {
		return visitDate;
	}
	public void setVisitDate(Date visitDate) {
		this.visitDate = visitDate;
	}
	
	public String getVisitLength() {
		return visitLength;
	}
	public void setVisitLength(String visitLength) {
		this.visitLength = visitLength;
	}
	
	public Float getPay() {
		return pay;
	}
	public void setPay(Float pay) {
		this.pay = pay;
	}
	
	public Visitor visitor () {
		return visitor;
	}
	public Long getVisitorId() {
		return visitorId;
	}
	public void setVisitorId(Long visitorId) {
		this.visitorId = visitorId;
	}
	public String getVisitor() {
		return visitor.getSurname() + " " + visitor.getName() + " " + visitor.getPatronymic();
	}
	public void setVisitor(Visitor visitor) {
		this.visitor = visitor;
	}
	
	public Computer computer () {
		return computer;
	}
	public Long getComputerId() {
		return computerId;
	}
	public void setComputerID(Long computerId) {
		this.computerId = computerId;
	}
	public String getComputer() {
		return computer.getComputerName();
	}
	public void setComputer(Computer computer) {
		this.computer = computer;
	}
	
	@Override
	public String toString() {
		return "Visit {id = " + id + ", visitDate = " + visitDate + ", visitLength = " + visitLength + ", pay = " + pay
				+ ", visitorId = " + visitorId + ", visitor = " + visitor + ", computerId = " + computerId + ", computer = "
				+ computer + "}";
	}
}
