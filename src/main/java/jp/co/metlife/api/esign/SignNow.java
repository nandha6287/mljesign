package jp.co.metlife.api.esign;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.exceptions.UnirestException;
public class SignNow {
	private Log log = LogFactory.getLog(SignNow.class);
	
	
	@Value("${client_credentials}")
	String clientCredentials;
	
	String accessToken = null;
	public SignNow()
	{
		
			try {
				Response res = Request.Post("https://api-eval.signnow.com/oauth2/token")

						//.addHeader("Authorization", "Basic NGFjOTVkMWU2ODIzYzg2OGY0ZGQzZWZlNGQ3N2FhNGY6OTg2ZTg1MTkxZTg5YWFjMzczNTkzYWRlYmI5Yjc4ZTQ=")
						.addHeader("Authorization", "Basic NGFjOTVkMWU2ODIzYzg2OGY0ZGQzZWZlNGQ3N2FhNGY6OTg2ZTg1MTkxZTg5YWFjMzczNTkzYWRlYmI5Yjc4ZTQ=") 
						.bodyForm(Form.form()
						.add("grant_type", "password")
						.add("username", "rajann@metlife.co.jp")
						.add("password", "Metlife1!")
						.build())
						.execute();
				log.info("Initialized");
				ObjectMapper mapper = new ObjectMapper();
				Map<String, String> map = mapper.readValue(res.returnContent().asString(), Map.class);
				log.info("AccessToken is"+map.get("access_token"));
				accessToken = map.get("access_token");
			} catch (ClientProtocolException e) { 
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//		} catch (UnirestException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	public String documentUpload(MultipartFile file)
	{
		String docId="none";
		File tmpFile = multipartFileToFile(file);
		log.info("File to be uploaded:"+tmpFile.getAbsolutePath());
		log.info("Accesstoken:"+accessToken);
		try {
			Response res = Request.Post("https://api-eval.signnow.com/document")
					.addHeader("Authorization", "Bearer "+accessToken) 
					.addHeader("content-type","multipart/form-data")
					.bodyForm(Form.form()
					.add("name", "file")
					.add("filename", tmpFile.getAbsolutePath())
					.build())
					.execute();
			ObjectMapper mapper = new ObjectMapper();
			//Map<String, String> map = mapper.readValue(res.returnContent().asString(), Map.class);
			log.info(res.returnContent().asString());
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return docId;
	}
	
	public File multipartFileToFile(MultipartFile file)
	{
		// ask JVM to ask operating system to create temp file
		SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMDDHHmmssm");
		String tmpFileName = sdf.format(new Date())+"_"+file.getOriginalFilename();
		log.info("tmpFileName"+tmpFileName);
		
		// transfer MultipartFile to File
		try {
			File tempFile = File.createTempFile(tmpFileName, "signnow");

			// ask JVM to delete it upon JVM exit if you forgot / can't delete due exception
			tempFile.deleteOnExit();

			file.transferTo(tempFile);
			return tempFile;
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
	}
	
}
