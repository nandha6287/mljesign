package jp.co.metlife.api.esign;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import jp.co.metlife.api.esign.esign;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
public class ESignController {

	private Log log = LogFactory.getLog(ESignController.class);
	@PostMapping("/esign")
	public Map<String, Boolean> createSignRequest(@RequestBody esign newSignReq)
	{
		boolean submitSignReq = submitSignRequest(newSignReq);
		Map<String, Boolean> response = new HashMap<>();
	    response.put("esign-requested", submitSignReq);
	    return response;		
	}
	
	@PostMapping("/docupload")
	public Map<String, String> handleFileUpload(@RequestParam("file") MultipartFile file,
			RedirectAttributes redirectAttributes) {
		SignNow sign = new SignNow();
		String docId = sign.documentUpload(file);
		Map<String,String> response = new HashMap<>();
		if (docId.equals("none"))
		{
			response.put("documentId", "none");
			response.put("uplaodStatus", "failed");
		}
		else
		{
			response.put("documentId", docId);
			response.put("uplaodStatus", "success");
		}
		
		
		return response;
	}
	
	private boolean submitSignRequest(esign newSignReq)
	{
		
		log.info(" has been requested for eSign"+newSignReq.getSignerName());
		boolean success=true;
		return success;
	}

}
