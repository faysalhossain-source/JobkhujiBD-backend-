package com.faysal.Jobkhujibd_backend.controller;


	import jakarta.servlet.http.HttpServletResponse;
	import net.sf.jasperreports.engine.*;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.http.HttpHeaders;
	import org.springframework.http.MediaType;
	import org.springframework.http.ResponseEntity;
	import org.springframework.stereotype.Controller;
	import org.springframework.web.bind.annotation.GetMapping;
	import org.springframework.web.bind.annotation.RequestMapping;

	import javax.sql.DataSource;
	import java.io.InputStream;
	import java.sql.Connection;
	import java.util.HashMap;
	import java.util.Map;

	@Controller
	@RequestMapping("/reports")
	public class JasperReportController {

	    @Autowired
	    private DataSource dataSource;

	    @GetMapping("/employees")
	    public void generateEmployeeReport(HttpServletResponse response) throws Exception {
	        InputStream reportStream = getClass().getResourceAsStream("/reports/HR_Employee.jrxml");
	        JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);

	        try (Connection conn = dataSource.getConnection()) {
	            Map<String, Object> parameters = new HashMap<>();
	            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, conn);

	            response.setContentType(MediaType.APPLICATION_PDF_VALUE);
	            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=employee_report.pdf");

	            JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
	        }
	    }

	    @GetMapping("/employees/excel")
	    public ResponseEntity<byte[]> generateExcelEmployeeReport() throws Exception {
	        InputStream reportStream = getClass().getResourceAsStream("/reports/Blank_A4_a.jrxml.jrxml");
	        JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);

	        byte[] reportContent;
	        try (Connection conn = dataSource.getConnection()) {
	            Map<String, Object> parameters = new HashMap<>();
	            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, conn);
	            reportContent = JasperExportManager.exportReportToPdf(jasperPrint);

	            return ResponseEntity.ok()
	                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=employee_report.pdf")
	                    .contentType(MediaType.APPLICATION_PDF).body(reportContent);
	        }
	    }
	}
}
