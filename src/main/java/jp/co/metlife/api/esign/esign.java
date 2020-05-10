package jp.co.metlife.api.esign;

public class esign {
	
	private final String signerName;
	private final String documentLocation;
	private final String agentCode;
	private final String agentName;
	private final String communicationChannel;
	private final String emailId;
	private final String mobileNumber;
	private final String lineId;

	public esign(String signerName, String documentLocation, String communicationChannel, String emailId, String mobileNumber, String lineId) {
		this.agentCode = "";
		this.agentName = "";
		this.signerName = signerName;
		this.documentLocation = documentLocation;
		this.communicationChannel = communicationChannel;
		this.emailId = emailId;
		this.mobileNumber = mobileNumber;
		this.lineId = lineId;
	}
	
	public String getSignerName() {
		return signerName;
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

	public String getDocumentLocation() {
		return documentLocation;
	}

	public String getAgentCode() {
		return agentCode;
	}

	public String getAgentName() {
		return agentName;
	}

}
