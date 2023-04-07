package domain;

/**
* Класс данных о статусе компьютеров
*/
public class ComputerStatus {
	// Идентификатор статуса компьютера
	private Long statusId;
	// Описание статуса компьютера 
	private String computerStatus;

	public ComputerStatus() {
		
	}
	public ComputerStatus(String computerStatus) {
		this.computerStatus = computerStatus;
	}
	public ComputerStatus(Long statusId, String computerStatus) {
		this.statusId = statusId;
		this.computerStatus = computerStatus;
	}
	
	public Long getStatusId() {
		return statusId;
	}
	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}

	public String getComputerStatus() {
		return computerStatus;
	}
	public void setComputerStatus(String computerStatus) {
		this.computerStatus = computerStatus;
	}
	
	@Override
	public String toString() {
		return "ComputerStatus {statusId = " + statusId + ", computerStatus = " + computerStatus + "}";
	}
}
