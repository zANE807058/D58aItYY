// 代码生成时间: 2025-08-17 22:37:22
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

/**
 * A simple text file analyzer that counts the occurrences of each word in a file.
 * It uses Hibernate to interact with a database to store and retrieve the word counts.
 */
public class TextFileAnalyzer {
    
    private static final String HIBERNATE_CONFIG_FILE = 