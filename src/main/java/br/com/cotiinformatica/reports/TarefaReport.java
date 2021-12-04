package br.com.cotiinformatica.reports;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.net.URL;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import br.com.cotiinformatica.dtos.TarefaRelatorioDTO;
import br.com.cotiinformatica.entities.Tarefa;
import br.com.cotiinformatica.helpers.DateHelper;

public class TarefaReport {

	public ByteArrayInputStream createPdf(TarefaRelatorioDTO dto) throws Exception {

		// abrindo um documento PDF
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		Document document = new Document();
		PdfWriter writer = PdfWriter.getInstance(document, out);

		// escrevendo dentro do documento PDF
		document.open();
		
		Image logo = Image.getInstance(new URL("https://www.cotiinformatica.com.br/imagens/logo-coti-informatica.png"));
		document.add(logo);
		
		document.add(new Paragraph("Relatório de Tarefas", getFormatTitle()));
		document.add(new Paragraph("\n"));
		
		document.add(new Paragraph("Relatório gerado em: " + DateHelper.format(dto.getDataGeracao(), "E dd/MM/yyyy HH:mm")));
		document.add(new Paragraph("Nome do usuário: " + dto.getUsuario().getNome()));
		document.add(new Paragraph("Email: " + dto.getUsuario().getEmail()));
		document.add(new Paragraph("\n"));
		
		document.add(new Paragraph("Data inicial da pesquisa: " + DateHelper.format(dto.getDataMin(), "E dd/MM/yyyy")));
		document.add(new Paragraph("Data final da pesquisa: " + DateHelper.format(dto.getDataMax(), "E dd/MM/yyyy")));
		document.add(new Paragraph("\n"));
		
		PdfPTable table = new PdfPTable(5); //quantidade de colunas
		table.setWidthPercentage(100);
		
		//cabeçalhos da tabela
		table.addCell("NOME DA TAREFA");
		table.addCell("DATA");
		table.addCell("HORA");
		table.addCell("DESCRIÇÃO");
		table.addCell("PRIORIDADE");
		
		//corpo da tabela
		for(Tarefa item : dto.getTarefas()) {
			
			table.addCell(item.getNome());
			table.addCell(DateHelper.format(item.getData(), "dd/MM/yyyy"));
			table.addCell(item.getHora());
			table.addCell(item.getDescricao());
			table.addCell(item.getPrioridade().toString().toUpperCase());			
		}
		
		document.add(table);
		
		document.add(new Paragraph("Quantidade de tarefas: " + dto.getTarefas().size()));
		
		document.close();
		writer.close();

		// retornando o arquivo
		return new ByteArrayInputStream(out.toByteArray());
	}
	
	//método para retornar a formatação do titulo do relatorio
	private Font getFormatTitle() {
		
		Font font = new Font();
		
		font.setSize(20);
		font.setStyle("bold");
		font.setColor(15, 60, 120);
		
		return font;
	}

}
