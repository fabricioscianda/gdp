package mseg.erp.spring.schedulers;
//package mseg.erp.spring.schedulers;
//
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.List;
//import java.util.Locale;
//import java.util.Properties;
//import java.util.concurrent.TimeUnit;
//
//import javax.inject.Inject;
//import javax.mail.Authenticator;
//import javax.mail.Message;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//
//import org.quartz.JobExecutionContext;
//import org.quartz.JobExecutionException;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.scheduling.quartz.QuartzJobBean;
//
//public class VencimientoConveniosJob extends QuartzJobBean{
//	
//	private Logger _logger = LoggerFactory.getLogger(VencimientoConveniosJob.class);
//	
//	@Inject
//	IConvenioDAO convenioDAO;
//	
//	@Inject
//	IInternoDAO internoDAO;
//	
//	@Inject
//	IOficinaDAO oficinaDAO;
//
//	@Override
//	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
//			_logger.info("Scheduler de AMyS : intentando enviar emails para los convenios vencidos o por vencer");
//		  
//	      // Sender's email ID needs to be mentioned
//	      String from = "no-reply@amys.cespi.unlp.edu.ar";
//
//	      // Assuming you are sending email from localhost
//	      String host = "smtp.gmail.com";
//		
//		  // Get system properties
//	      Properties properties = System.getProperties();
//	
//	      //Authentication
//	      String user="axelcollardbovy@gmail.com";
//	      String pass = "CeSPIDesarrollo";
//	      Authenticator auth = new GmailAuthenticator(user, pass);	      
//	      
//	      // Setup mail server
//	      properties.setProperty("mail.smtp.host", host);
//	      properties.setProperty("mail.user", user);
//	      properties.setProperty("mail.password", pass);
//	      properties.setProperty("mail.smtp.port", "587");
//	      properties.setProperty("mail.smtp.auth", "true");
//	      properties.put("mail.smtp.starttls.enable", true);
//	      properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
//	      
//	      // Get the default Session object.
//	      Session session = Session.getDefaultInstance(properties,auth);
//	      
//	      try{
//				Calendar c = Calendar.getInstance();
//
//				// set the calendar to start of today
//				c.set(Calendar.HOUR_OF_DAY, 0);
//				c.set(Calendar.MINUTE, 0);
//				c.set(Calendar.SECOND, 0);
//				
//				// and get that as a Date
//				Calendar inSixDays = Calendar.getInstance();
//				c.add(Calendar.DAY_OF_YEAR, 7);
//				inSixDays.setTime(c.getTime());
//				
//				// add 1 day to the date/calendar
//			    c.add(Calendar.DAY_OF_YEAR, 1);
//			    
//			    // and get that as a Date
//			 	Calendar nextWeek = Calendar.getInstance();
//			 	nextWeek.setTime(c.getTime());
//			 	
//			 	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE dd 'de' MMMM 'de' yyyy",new Locale("es"));
//			 	
//			 	List<VOOficina> oficinas = oficinaDAO.listar();
//			 	
//			 	for (VOOficina voOficina : oficinas) {
//					
//			 		List<VOConvenio> conveniosPorVencer = convenioDAO.listarEntreDosFechas(inSixDays.getTimeInMillis(), nextWeek.getTimeInMillis(),voOficina);
//			 		
//			 		MimeMessage email = new MimeMessage(session);
//			 		// Set From: header field of the header.
//			 		email.setFrom(new InternetAddress(from));
//			 		
//			 		// Set Subject: header field
//			 		email.setSubject("AMyS - Convenios por Vencer","utf-8");
//			 		
//			 		if(!conveniosPorVencer.isEmpty()){
//			 			
////				        List<VOInterno> destinatarios = internoDAO.listarDeOficina(voOficina);
//	//			        for (VOInterno voInterno : destinatarios) {
//	//			        	email.addRecipient(Message.RecipientType.TO,new InternetAddress(voInterno.getEmail()));
//	//			        }
//			 			email.addRecipient(Message.RecipientType.TO,new InternetAddress("acollard@cespi.unlp.edu.ar"));
//			 			email.addRecipient(Message.RecipientType.TO,new InternetAddress("jlaguna@cespi.unlp.edu.ar"));
//			 			
//			 			String emailContent="";
//			 			for (VOConvenio voConvenio : conveniosPorVencer) {
//			 				if(voConvenio.getPlazo()>0){
//			 					Long fechaVencimientoMillis = voConvenio.getFechaInicio()+TimeUnit.MILLISECONDS.convert(voConvenio.getPlazo(), TimeUnit.DAYS);
//			 					emailContent = emailContent + "<h4>"+voConvenio.getRepresentante().getNombre()+" "+
//			 							voConvenio.getRepresentante().getApellido()+" - "+voConvenio.getCliente().getRazonSocial()+"</h4>"
//			 							+"Vence el "+simpleDateFormat.format(new Date(fechaVencimientoMillis))+"<br><br>";
//			 					emailContent = emailContent + "-----------------------------------------------------------------<br>";
//			 				}
//			 			}
//			 			emailContent = "<h3>Convenios Pr&oacute;ximos a vencer</h3>A continuación se detallan los convenios próximos a vencer<br><br>"+emailContent + "<footer>Enviado automáticamente desde AMyS</footer><br>";
//			 			email.setContent(emailContent,"text/html; charset=utf-8");
//			 			
//			 			// Send message
//			 			Transport.send(email);
//			 			_logger.info("Mail enviado con exito...");
//			 		}else{
//			 			_logger.info("No hay convenios por vencer hoy...");
//			 		}
//				}
//			 	
//	      }catch(Exception ex){
//				_logger.error("Error intentando enviar mail de convenio: ",ex);
//	      }
//	}
//
//}
