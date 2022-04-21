package org.zchzh.swagger.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SwaggerExportFileTest {

    private SwaggerExportFile exportFile = new SwaggerExportFile();


    @Test
    void exportSwagger2ToFile() {
        exportFile.exportSwaggerToFile("http://127.0.0.1:9990/api/v2/api-docs", "D:\\testdata\\swagger2test.docx");
    }


    @Test
    void exportSwagger3ToFile() {
        exportFile.exportSwaggerToFile("http://127.0.0.1:9990/api/v3/api-docs", "");
    }

}