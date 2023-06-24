package sliit.spm.ecare.backend.util.enums;

public enum RestApiStatus {
	FETCH_SUCCESS("200", "Successfully fetched"),
	NOT_FOUND("404", "Request Resource Not Found");
	
	
	private String errorCode;
	private String Discription;
	
	private RestApiStatus(String errorCode,String Discription){
		this.errorCode = errorCode;
		this.Discription = Discription;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getDiscription() {
		return Discription;
	}

	public void setDiscription(String discription) {
		Discription = discription;
	}
}
