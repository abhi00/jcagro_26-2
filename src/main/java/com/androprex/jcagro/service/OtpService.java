package com.androprex.jcagro.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.management.Notification;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.androprex.jcagro.model.NotificationOtp;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class OtpService {
	
	@Async
	public String sendSms(NotificationOtp notificationOtp) {
		try {
			// Construct data
			String apiKey = "apikey=" + "fVKGvh4x6X4-wpV79dJ0S5ybuElWRWG81eyfFrKeOu";
			String message = "&message=" + notificationOtp.getMessage();
			String sender = "&sender=" + "TXTLCL";
			String numbers = "&numbers=" + notificationOtp.getMobileNumber();
			
			// Send data
			HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
			String data = apiKey + numbers + message + sender;
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
			conn.getOutputStream().write(data.getBytes("UTF-8"));
			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			final StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				stringBuffer.append(line);
			}
			rd.close();
			  
			
			return stringBuffer.toString();
		} catch (Exception e) {
			System.out.println("Error SMS "+e);
			return "Error "+e;
		}
	}

}
