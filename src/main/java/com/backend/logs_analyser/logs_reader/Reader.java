package com.backend.logs_analyser.logs_reader;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;

import com.backend.logs_analyser.logs_parser.AddProductLogsParser;
import com.backend.logs_analyser.logs_parser.DeleteProductLogsParser;
import com.backend.logs_analyser.logs_parser.GetALLproductLogsParser;
import com.backend.logs_analyser.logs_parser.GetProductByidParser;
import com.backend.logs_analyser.logs_parser.UpdateProductLogsParser;
import com.backend.logs_analyser.logs_parser.Parser;


public class Reader {

    Collection<String> logsLines = new ArrayList<>();
    Collection<Parser> parsedLogs = new ArrayList<>();

    public Reader() {
        //Lire le fichier de logs et remplir la collection logsLines
        try {
            //afichier le PATH

            System.out.println("Log file path: " + Paths.get("backend/logs/app.log").toAbsolutePath());

            Path logFile = Paths.get("logs/app.log");
            logsLines = Files.readAllLines(logFile);
        } catch (java.io.IOException e) {
            System.err.println("Error reading log file: " + e.getMessage());
        }

        //On parse les lignes de logs
        logsLines.forEach(line -> {
            if (line.contains("deleteProductById")) {
                DeleteProductLogsParser parser = new DeleteProductLogsParser(line);
                parsedLogs.add(parser);
            }else{
                if(line.contains("getAllProducts")){
                GetALLproductLogsParser parser = new GetALLproductLogsParser(line);
                parsedLogs.add(parser);
                }else{
                    if(line.contains("updateProduct")){
                        UpdateProductLogsParser parser = new UpdateProductLogsParser(line);
                        parsedLogs.add(parser);
                    }else{
                        if(line.contains("addProduct")){
                            AddProductLogsParser parser = new AddProductLogsParser(line);
                            parsedLogs.add(parser);
                        }else{
                            //cas getProductById
                            if (line.contains("getProductById")) {
                                Parser parser = new GetProductByidParser(line);
                                parsedLogs.add(parser);
                            }
                        }
                    }
                }
        
            }
        });
    }

    public Collection<Parser> getParsedLogs() {
        return parsedLogs;
    }
}
