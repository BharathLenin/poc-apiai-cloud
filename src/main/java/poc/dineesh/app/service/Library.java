package poc.dineesh.app.service;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import ai.api.AIConfiguration;
import ai.api.AIDataService;
import ai.api.AIServiceException;
import ai.api.model.AIRequest;
import ai.api.model.AIResponse;
@CrossOrigin
@RestController
@RequestMapping(value = "/apiai")
public class Library {
	AIConfiguration configuration = new AIConfiguration("09af47400a3441c6ad33b01ca04b3531");
	 AIDataService dataService = new AIDataService(configuration);
	
    
    @CrossOrigin
    @RequestMapping(value = "/getSpeech", method = RequestMethod.POST, consumes = "application/json; charset=UTF-8",produces = "application/json; charset=UTF-8")
    
    public ResponseClass fetchSkuDetails(@RequestBody Request requestTO) {
//      String text = "Sku not found in database";
//      if(printRequestTO.getResult().getParameters().getNumber() == 161640) {
//          text = "Sku Name is Hammer";
//      }
//      else if(printRequestTO.getResult().getParameters().getNumber() == 140150) {
//          text = "Sku Name is Sewing Machine";
//      }
//      ResponseClass response = new ResponseClass();
//      response.setDisplayText("Sku : " +"\n"+"Sku name: Hammer");
        //response.setSpeech(text);
        
        
//      try {
//          HttpClient httpclient = HttpClientBuilder.create().build(); 
//          HttpPost httpPost = new HttpPost("https://printpoc-develop.apps-np.homedepot.com/fetchSku/skuNumber");
//          RequestEntity<PrintRequestTO> reqEntity = new RequestEntity<PrintRequestTO>(null, HttpMethod.POST, null);
//          StringBody skuNumber = new StringBody("161640");
//          reqEntity.addPart("skuNumber", skuNumber);
//          httpPost.setEntity(reqEntity);
//          HttpResponse POSTresponse = httpclient.execute(httpPost);
//          HttpEntity resEntity = POSTresponse.getEntity();
//          EntityUtils.consume(resEntity);
//          } catch (Exception e) {
//              return null;
//          }
//        return response;
//    }
    	
    	

       
        AIRequest request = new AIRequest(requestTO.inputText);
        AIResponse response = null;
		try {
			response = dataService.request(request);	
		} catch (AIServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("exception");
		}
		
		if (response.getStatus().getCode() == 200) {
            System.out.println(response.getResult().getFulfillment().getSpeech());
          } else {
            System.err.println(response.getStatus().getErrorDetails());
          }
		
		ResponseClass responseclass = new ResponseClass();
		responseclass.setSpeech(response.getResult().getFulfillment().getSpeech());
        return responseclass;
        
        
    
}}