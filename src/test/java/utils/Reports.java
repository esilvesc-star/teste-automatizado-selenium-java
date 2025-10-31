package utils;

import io.qameta.allure.Allure;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPCell;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.io.ByteArrayInputStream;


	public class Reports{
	public static String imgname;
	public static String nomePastaEvidencia = null;
    private List<byte[]> screenshots = new ArrayList<>();
    private String pdfFilePath;
    static String comAllure = Helpers.carregueUmArquivoDaPropriedades("comAllure");
    private String module;        // Exemplo: "PFIN – [Backlog Técnico]"
    private String date;          // Data no formato dd/MM/yyyy
    private String description;   // Exemplo: "Validar permissão de acesso ao formulário"
    private String analyst;       // Nome do analista
    private String leftImagePath; // Caminho da imagem à esquerda
    private String rightImagePath; // Caminho da imagem à direita
    private String evidencePath; // Caminho da evidencia pdf

    public Reports(WebDriver driver, String useAllure) {
        this.module = YamlConfig.readConfig("testeYaml", "cabecalho.modulo");
        this.date = YamlConfig.readConfig("testeYaml", "cabecalho.data");
        this.description = YamlConfig.readConfig("testeYaml", "cabecalho.descricao");
        this.analyst = YamlConfig.readConfig("testeYaml", "cabecalho.analista");
        this.leftImagePath = YamlConfig.readConfig("testeYaml", "cabecalho.imgLE");
        this.rightImagePath = YamlConfig.readConfig("testeYaml", "cabecalho.imgLD");
        this.evidencePath = Helpers.carregueUmArquivoDaPropriedades("reportsPathPDF");
        
        // Define o caminho do PDF com um timestamp
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        this.pdfFilePath = ""+evidencePath+"Evidence_" + timestamp + ".pdf";
    }

    // Captura a evidência e salva conforme a flag `useAllure`
    public void captureEvidence(WebDriver driver, String stepName) {
        byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

        if (comAllure.equals("true")) {
            // Usa Allure para anexar a captura de tela
            Allure.addAttachment(stepName, new ByteArrayInputStream(screenshot));
        } else {
            // Armazena a captura de tela na lista para o PDF
            screenshots.add(screenshot);
        }
    }

    // Gera o arquivo PDF com todas as capturas de tela
    public void generatePdfReport() {
        if (comAllure.equals("true")) {
            System.out.println("Evidências foram geradas com o Allure. PDF não será criado.");
            return; // Não cria PDF se Allure está ativado
        }

        try (FileOutputStream fos = new FileOutputStream(pdfFilePath)) {
            Document document = new Document();
            PdfWriter.getInstance(document, fos);
            document.open();
            
            // Adiciona as imagens no cabeçalho
            PdfPTable headerTable = new PdfPTable(2);
            headerTable.setWidthPercentage(100);
            headerTable.setSpacingAfter(10);

            // Configura largura relativa das colunas (50% cada)
            headerTable.setWidths(new float[]{1, 1});

            // Adiciona imagem esquerda e direita com ajuste
            addImageToCell(headerTable, leftImagePath, Element.ALIGN_LEFT);
            addImageToCell(headerTable, rightImagePath, Element.ALIGN_RIGHT);

            document.add(headerTable);

            // Adiciona o cabeçalho de texto
            document.add(new Paragraph("Módulo: " + module));
            document.add(new Paragraph("Data: " + date));
            document.add(new Paragraph("Descrição: " + description));
            document.add(new Paragraph("Analista: " + analyst));
            document.add(new Paragraph("\n"));

            // Adiciona capturas de tela
            for (int i = 0; i < screenshots.size(); i++) {
                byte[] screenshot = screenshots.get(i);

                Image image = Image.getInstance(screenshot);
                image.scaleToFit(500, 500);
                document.add(new Paragraph("Step " + (i + 1)));
                document.add(image);
                document.add(new Paragraph("\n"));
            }

            document.close();
            System.out.println("Evidências salvas no arquivo: " + pdfFilePath);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addImageToCell(PdfPTable table, String imagePath, int alignment) {
        try {
            PdfPCell cell = new PdfPCell();
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setHorizontalAlignment(alignment);

            if (imagePath != null && !imagePath.isEmpty()) {
                Image image = Image.getInstance(imagePath);
                image.scaleToFit(70, 70); // Define o tamanho da imagem
                cell.addElement(image);
            }

            table.addCell(cell);
        } catch (Exception e) {
            System.err.println("Erro ao carregar imagem: " + imagePath);
            PdfPCell emptyCell = new PdfPCell();
            emptyCell.setBorder(Rectangle.NO_BORDER);
            table.addCell(emptyCell);
        }
    }
    
    public String getPdfFilePath() {
        return pdfFilePath;
    }
}