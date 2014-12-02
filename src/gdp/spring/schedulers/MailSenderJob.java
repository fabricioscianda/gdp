//package gdp.spring.schedulers;
//
//import java.util.Calendar;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//import java.util.Map.Entry;
//import java.util.Properties;
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
//public class MailSenderJob extends QuartzJobBean {
//
//	@Inject
//	IAgendaDAO agendaDAO;
//	
//	@Inject
//	IRecordatorioDAO recordatorioDAO;
//	
//	@Inject
//	IOficinaDAO oficinaDAO;
//	
//	private Logger _logger = LoggerFactory.getLogger(MailSenderJob.class);
//	
//	@Override
//	public void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
//		  _logger.info("Scheduler de AMyS : intentando enviar emails para los recordatorios de hoy");
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
//		try{
//			List<VOOficina> oficinas = oficinaDAO.listar();
//			
//			for (VOOficina voOficina : oficinas) {
//				List<VORecordatorio> recordatorios = recordatorioDAO.listarRecordatoriosPorDiaParaMail(new Date(),voOficina);
//				Map<String, MimeMessage> emails = new HashMap<String, MimeMessage>();
//				Map<String,String> emailTextMap = new HashMap<String,String>();
//				
//				Calendar c = Calendar.getInstance();
//				
//				// set the calendar to start of today
//				c.set(Calendar.HOUR_OF_DAY, 0);
//				c.set(Calendar.MINUTE, 0);
//				c.set(Calendar.SECOND, 0);
//				
//				// and get that as a Date
//				Calendar today = Calendar.getInstance();
//				today.setTime(c.getTime());
//				
//				// add one day to the date/calendar
//				c.add(Calendar.DAY_OF_YEAR, 1);
//				
//				// and get that as a Date
//				Calendar tomorrow = Calendar.getInstance();
//				tomorrow.setTime(c.getTime());
//				
//				VOCliente voClienteActual=null;
//				if(!recordatorios.isEmpty()&&recordatorios.get(0)!=null){
//					voClienteActual = recordatorios.get(0).getCliente();
//				}else{
//					_logger.info("No hay recordatorios (para envío de email) para este día.");
//				}
//				
//				for (VORecordatorio voRecordatorio : recordatorios) {
//					
//					//		 		Calendar fechaAlerta = Calendar.getInstance();
//					//				fechaAlerta.setTimeInMillis(voRecordatorio.getFechaAlerta());
//					//				if(fechaAlerta.after(today) && fechaAlerta.before(tomorrow)){
//					
//					if(!voClienteActual.equals(voRecordatorio.getCliente())){
//						//Hay que usar un iterator para poder remover los elementos, sino tira ConcurrentModificationException
//						Iterator<Entry<String,MimeMessage>> emailMapIterator = emails.entrySet().iterator();
//						while(emailMapIterator.hasNext()){
//							Entry<String,MimeMessage> itemMap = emailMapIterator.next();
//							MimeMessage email = itemMap.getValue();
//							
//							String emailContent=emailTextMap.get(itemMap.getKey());
//							
//							//Agregando el pie del email
//							emailContent = "<h2>"+voClienteActual.getRazonSocial()+"</h2>"+emailContent +"<footer>Enviado automáticamente desde AMyS</footer><br>";
//							email.setContent(emailContent,"text/html; charset=utf-8");
//							
//							// Send message
//							Transport.send(email);
//							
//							emailMapIterator.remove();
//							_logger.info("Mail enviado con exito...");
//							
//						}
//						voClienteActual = voRecordatorio.getCliente();
//						
//					}
//					
//					String mailText="";
//					List<VOInternoRecordatorio> internoRecordatorios = voRecordatorio.getDestinatarios();
//					
//					//Arma el contenido del mail (para este recordatorio)
//					mailText=mailText
//							//		        		 	+"<strong>Cliente: </strong>"+voRecordatorio.getCliente().getRazonSocial()+"<br>"
//							+"<strong>Producto o Servicio:</strong>"+voRecordatorio.getPos().getNombre()+"<br>"
//							+"<strong>Urgencia: </strong>"+Urgencia.values()[voRecordatorio.getUrgencia()-1]+"<br>"
//							+"<strong>Importancia: </strong>"+Importancia.values()[voRecordatorio.getImportancia()-1]+"<br>"
//							+"<strong>Estado: </strong>"+voRecordatorio.getEstado().getNombre()+"<br>"
//							+"<strong>Etapa: </strong>"+voRecordatorio.getEtapa().getNombre()+"<br>"
//							+"<strong>Mensaje: </strong>"+voRecordatorio.getDescripcion()+"<br>"
//							+"<strong>Destinatarios: </strong>";
//					
//					for (VOInternoRecordatorio voInternoRecordatorio : voRecordatorio.getDestinatarios()) {
//						mailText=mailText+voInternoRecordatorio.getInterno().getNombre()+" "+
//								voInternoRecordatorio.getInterno().getApellido()+",";
//					}
//					
//					mailText= mailText.substring(0, mailText.length()-1)+"<br>"+"<br>";
//					mailText= mailText+"-------------------------------------------------------"+"<br>"+"<br>";
//					
//					//Fin del contenido del email (para este recordatorio)
//					
//					for (VOInternoRecordatorio voInternoRecordatorio : internoRecordatorios) {
//						VOInterno voInterno = voInternoRecordatorio.getInterno();
//						
//						if(voInterno.getAgenda().getTipoNotificacion().equals(TipoNotificacion.EMAIL)
//								||voInterno.getAgenda().getTipoNotificacion().equals(TipoNotificacion.TODAS)){
//							
//							// Recipient's email ID needs to be mentioned.
//							String to = voInterno.getEmail();
//							
//							//		        		String toHardCoded = "axelcollardbovy@gmail.com";
//							String emailContent="";
//							
//							MimeMessage email;
//							if(emails.containsKey(to)){
//								email=emails.get(to);
//								emailContent = emailTextMap.get(to);
//								
//							}else{
//								// Create a default MimeMessage object.
//								email= new MimeMessage(session);
//								
//								//Stores MimeMessage into hashmap
//								emails.put(to, email);
//								
//								// Set From: header field of the header.
//								email.setFrom(new InternetAddress(from));
//								
//								// Set To: header field of the header.
//								email.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
//								
//								// Set Subject: header field
//								email.setSubject("AMyS - "+voRecordatorio.getCliente().getRazonSocial()+" - Recordatorio","utf-8");
//								
//								//Set empty text into map
//								emailTextMap.put(to, emailContent);
//								
//							}
//							emailContent=emailContent+mailText;
//							emailTextMap.put(to, emailContent);
//						}
//					}
//				}
//				for (String emailKey : emails.keySet()) {
//					
//					MimeMessage email = emails.get(emailKey);
//					String emailContent=emailTextMap.get(emailKey);
//					
//					//Agregando el pie del email
//					emailContent = "<h2>"+voClienteActual.getRazonSocial()+"</h2>"+emailContent + "<footer>Enviado automáticamente desde AMyS</footer><br>";
//					email.setContent(emailContent,"text/html; charset=utf-8");
//					
//					// Send message
//					Transport.send(email);
//					_logger.info("Mail enviado con exito...");
//				}
//				_logger.info("El Scheduler de AMyS finalizó enviando los emails para los recordatorios de hoy");
//			}
//		}catch(Exception ex){
//			_logger.error("Error intentando enviar mail de recordatorio: ",ex);
//		}
//	}
//
//}
