package domain;

/**
* Класс данных о компьютерах
*/
public class Computer {
	// Идентификатор компьютера
	private Long computerId;
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
	public Computer(String computerName, String computerDescription, ComputerStatus computerStatus) {
		this.computerName = computerName;
		this.computerDescription = computerDescription;
		this.computerStatus = computerStatus;
	}
	public Computer(String computerName, String computerDescription, Long computerStatusId, ComputerStatus computerStatus) {
		this.computerName = computerName;
		this.computerDescription = computerDescription;
		this.computerStatusId = computerStatusId;
		this.computerStatus = computerStatus;
	}
	public Computer(Long computerId, String computerName, String computerDescription, Long computerStatusId,
			ComputerStatus computerStatus) {
		this.computerId = computerId;
		this.computerName = computerName;
		this.computerDescription = computerDescription;
		this.computerStatusId = computerStatusId;
		this.computerStatus = computerStatus;
	}
	
	public Long getComputerId() {
		return computerId;
	}
	public void setComputerId(Long computerId) {
		this.computerId = computerId;
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
		return "Computer {computerId = " + computerId + ", computerName = " + computerName + ", computerDescription = "
				+ computerDescription + ", computerStatusId = " + computerStatusId + ", computerStatus = " + computerStatus
				+ "}";
	}
}
