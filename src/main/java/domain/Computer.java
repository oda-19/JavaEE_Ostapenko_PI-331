package main.java.domain;

/**
* Класс данных о компьютерах
*/
public class Computer {
	// Идентификатор компьютера
	private Long id;
	// Наименование компьютера
	private String computerName;
	// Описание характеристик компьютера
	private String computerDescription;
	// Внешний ключ - ссылка на сущность ComputerStatus
	private Long computerStatusId;
	// Навигационное свойства - ссылка на статус компьютера
	private ComputerStatus computerStatus;
	
	public Computer() {
		
	}
	public Computer(Long id, String computerName, String computerDescription, Long computerStatusId) {
		this.id = id;
		this.computerName = computerName;
		this.computerDescription = computerDescription;
		this.computerStatusId = computerStatusId;
	}
	public Computer(Long id, String computerName, String computerDescription, Long computerStatusId, ComputerStatus computerStatus) {
		this.id = id;
		this.computerName = computerName;
		this.computerDescription = computerDescription;
		this.computerStatusId = computerStatusId;
		this.computerStatus = computerStatus;
	}

    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getComputerName() {
		return computerName;
	}
	public void setComputerName(String computerName) {
		this.computerName = computerName;
	}
	
	public String getComputerDescription() {
		return computerDescription;
	}
	public void setComputerDescription(String computerDescription) {
		this.computerDescription = computerDescription;
	}
	
	public ComputerStatus computerStatus () {
		return computerStatus;
	}
	public Long getComputerStatusId() {
		return computerStatusId;
	}
	public void setComputerStatusId(Long computerStatusId) {
		this.computerStatusId = computerStatusId;
	}
	public String getComputerStatus() {
		return computerStatus.getComputerStatus();
	}
	public void setStatus(ComputerStatus computerStatus) {
		this.computerStatus = computerStatus;
	}
	
	@Override
	public String toString() {
		return "Computer {id = " + id + ", computerName = " + computerName + ", computerDescription = "
				+ computerDescription + ", computerStatusId = " + computerStatusId + ", computerStatus = " + computerStatus
				+ "}";
	}
}
