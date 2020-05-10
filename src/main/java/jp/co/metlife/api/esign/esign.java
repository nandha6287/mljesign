package jp.co.metlife.api.esign;

public class esign {
	
	private final String signerName;
	private final String documentId;
	private final String communicationChannel;
	private final String emailId;
	private final String mobileNumber;
	private final String lineId;

	public esign(String signerName, String documentId, String communicationChannel, String emailId, String mobileNumber, String lineId) {
		this.signerName = signerName;
		this.documentId = documentId;
		this.communicationChannel = communicationChannel;
		this.emailId = emailId;
		this.mobileNumber = mobileNumber;
		this.lineId = lineId;
	}
	
	public String getSignerName() {
		return signerName;
	}

	public String getDocumentId() {
		return documentId;
	}

	public String getCommunicationChannel() {
		return communicationChannel;
	}

	public String getEmailId() {
		return emailId;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public String getLineId() {
		return lineId;
	}

}
