package domain;

/**
* Класс данных о посещениях компьютерного клуба
*/
public class Visit {
	// Идентификатор посещения
	private Long visitId;
	// Дата посещения компьютерного клуба
	private String visitDate;
	// Продолжительность посещения компьютерного клуба
	private String visitLenth;
	// Сумма к оплате за посещение компьютерного клуба
	private Integer pay;
	
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
	public Visit(String visitDate, String visitLenth, Integer pay, Long visitorId, Visitor visitor, Computer computer) {
		this.visitDate = visitDate;
		this.visitLenth = visitLenth;
		this.pay = pay;
		this.visitorId = visitorId;
		this.visitor = visitor;
		this.computer = computer;
	}
	public Visit(String visitDate, String visitLenth, Integer pay, Long visitorId, Visitor visitor, Long computerId,
			Computer computer) {
		this.visitDate = visitDate;
		this.visitLenth = visitLenth;
		this.pay = pay;
		this.visitorId = visitorId;
		this.visitor = visitor;
		this.computerId = computerId;
		this.computer = computer;
	}
	public Visit(Long visitId, String visitDate, String visitLenth, Integer pay, Long visitorId, Visitor visitor,
			Long computerId, Computer computer) {
		this.visitId = visitId;
		this.visitDate = visitDate;
		this.visitLenth = visitLenth;
		this.pay = pay;
		this.visitorId = visitorId;
		this.visitor = visitor;
		this.computerId = computerId;
		this.computer = computer;
	}
	
	public Long getVisitId() {
		return visitId;
	}
	public void setVisitId(Long visitId) {
		this.visitId = visitId;
	}
	
	public String getVisitDate() {
		return visitDate;
	}
	public void setVisitDate(String visitDate) {
		this.visitDate = visitDate;
	}
	
	public String getVisitLenth() {
		return visitLenth;
	}
	public void setVisitLenth(String visitLenth) {
		this.visitLenth = visitLenth;
	}
	
	public Integer getPay() {
		return pay;
	}
	public void setPay(Integer pay) {
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
		return "Visit {visitId = " + visitId + ", visitDate = " + visitDate + ", visitLenth = " + visitLenth + ", pay = " + pay
				+ ", visitorId = " + visitorId + ", visitor = " + visitor + ", computerId = " + computerId + ", computer = "
				+ computer + "}";
	}
}
