package utils;

import static java.util.Collections.singletonList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.ConfigurationBuilder;
import io.qameta.allure.ReportGenerator;
import io.qameta.allure.core.Configuration;
//import main.Variaveis;
//import questions.CommonQuestions;


public class AllureGenerator {


	public static void gerarAllureReportHTML(String pathResults, String pathReport){
        List<Path> resultsDirectories = new ArrayList<>(singletonList(Paths.get(pathResults)));
    	Path reportDirectories = Paths.get(pathReport);
        final Configuration configuration = new ConfigurationBuilder().useDefault().build();
    	final ReportGenerator generator = new ReportGenerator(configuration);
    	generator.generateSingleFile(reportDirectories, resultsDirectories);
	}
		
	public static void generatorFileHTML(String pathResults, String pathReport, WebDriver driver){
		File file = new File(pathReport);
		String absolutePathReport= file.getAbsolutePath();
        System.out.println("absolutePathReport: " + absolutePathReport);
		File file1 = new File(pathResults);
		String absolutePathResults= file1.getAbsolutePath();
        System.out.println("absolutePathResults: " + absolutePathResults);
        
		String comando = "allure generate --single-file "+pathResults+" --clean -o "+pathReport+"";
		
		try {
	        ProcessBuilder builder = new ProcessBuilder("cmd", "/c",
	            String.join("& ", comando));
	        builder.redirectErrorStream(true);
	        Process p = builder.start();
	        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
	        String line;
	        while (true) {
	            line = r.readLine();
	            if (line == null) {
	                break;
            }
            System.out.println("Allure: " + line);
	        }
//	        if (driver != null) {
//	            GlobalDriver.close();
//		    }
	    } catch(Exception e) {
	        System.err.println(e);
	    }
	}
	
	public static void deletarArquivos(File file) {
	  	if (file.exists()) {

	        for (File subfile : file.listFiles()) {
	            if (subfile.isDirectory()) {
	            	deletarArquivos(subfile);
	            }
	            subfile.delete();
	        }
		}
	}
	
	public static boolean RenomearArquivo(String caminhoDoArquivo, String nomeNovoArquivo) throws IOException {
	      File pasta = new File(caminhoDoArquivo);
	
	      if (pasta.isDirectory()) {
	          File[] arquivos = pasta.listFiles();
	          for (File arquivo : arquivos) {
	              if (arquivo.isFile() && arquivo.getName().equals("index.html")) {
	                 
	                  File novoArquivo = new File(arquivo.getParent(), (nomeNovoArquivo));
	                  if (arquivo.renameTo(novoArquivo)) {
	                      System.out.println("Arquivo renomeado com sucesso.");
	                  } else {
	                      System.out.println("Erro ao renomear arquivo.");
	                  }
	                  break;
	              }
	          }
	      } else {
	          System.out.println("O caminho especificado não é uma pasta.");
	      }
		return false;
	  }
	
	public static void MoveArquivo(String arquivoSerMovido, String PastaDestino) throws InterruptedException {
	         // Arquivo a ser movido
	        File arquivo = new File(arquivoSerMovido);
	        if (!arquivo.exists()) {
	        	arquivo.wait(5000);
	            System.out.println("Arquivo não encontrado");
	        } else {
	            // Diretorio de destino
	            File diretorioDestino = new File(PastaDestino);
	            // Move o arquivo para o novo diretorio
	            boolean sucesso = arquivo.renameTo(new File(diretorioDestino, arquivo.getName()));
	            if (sucesso) {
	                System.out.println("Arquivo movido para '" + diretorioDestino.getAbsolutePath() + "'");
	            } else {
	                System.out.println("Erro ao mover arquivo '" + arquivo.getAbsolutePath() + "' para '"
	                        + diretorioDestino.getAbsolutePath() + "'");
	            }
	        }
	    }
	
	 public static void nomeDoCenario(Scenario scenario, String pathReportPipeline) throws IOException {
		 String status = null;
	        if(!scenario.isFailed()) {
	        	status = "OK";
	        }
	        else {
	        	status = "NOK";
	        }

	        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HHmmss"));
	        String tag = scenario.getSourceTagNames().toString().substring(3, 19).replaceAll("[^ a-zA-Z0-9]+","");
	        System.out.println(tag);
	       
	        File file1 = new File(pathReportPipeline + status + "-" + time + "-" + tag + ".txt"); 
	        String absolutePath= file1.getAbsolutePath();
	        System.out.println("absolutePath: " + absolutePath);
	        try {
	        	file1.createNewFile();
	    		System.out.print("Arquivo criado com sucesso!");
	    			
	    	} catch (IOException e) {
	    		e.printStackTrace();	
	    	}
	        System.out.println(file1);
	 }
	 	  
//	    public static void attachPdfToAllureReport(Scenario scenario, String filePath) throws IOException {
//          try {
//        	  
//        	  if(CommonQuestions.verificarPastaVazia(CommonQuestions.absolutePathDestinoDescompactado) == true) {
//            	  Thread.sleep(5000);
//              }else {
//		      File htmlFile = new File(filePath);
//		      byte[] htmlContent = Files.readAllBytes(htmlFile.toPath());
//        	  scenario.attach(htmlContent, "application/pdf", "PDF.pdf");	    
//              }
//          } catch (Exception e) {
// 		      e.printStackTrace();
// 		   }
//	      }
}