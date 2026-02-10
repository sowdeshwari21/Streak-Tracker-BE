package intern.example.demo.dto;

public class ResponseStructure<T> {
	private Integer statucode;
	private String message;
	private T data;
	
	public ResponseStructure() {
		
	}
	public Integer getStatucode() {
		return statucode;
	}
	public void setStatucode(Integer statucode) {
		this.statucode = statucode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public ResponseStructure(Integer statucode, String message, T data) {
		super();
		this.statucode = statucode;
		this.message = message;
		this.data = data;
	}
	
	

}
