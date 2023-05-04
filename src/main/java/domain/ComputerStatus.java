package main.java.domain;

/**
* Класс данных о статусах компьютера
*/
public class ComputerStatus {
	// Идентификатор статуса компьютера
	private Long id;
	// Описание статуса компьютера 
	private String computerStatus;

	public ComputerStatus() {
		
	}
	public ComputerStatus(String computerStatus) {
		this.computerStatus = computerStatus;
	}
	public ComputerStatus(Long id, String computerStatus) {
		this.id = id;
		this.computerStatus = computerStatus;
	}

    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getComputerStatus() {
		return computerStatus;
	}
	public void setComputerStatus(String computerStatus) {
		this.computerStatus = computerStatus;
	}
	
	@Override
	public String toString() {
		return "ComputerStatus {id = " + id + ", computerStatus = " + computerStatus + "}";
	}
}
