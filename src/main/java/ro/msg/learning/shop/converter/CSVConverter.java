package ro.msg.learning.shop.converter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Collections;
import java.util.List;

@Component
public class CSVConverter<T> {

    public List<T> fromCsv(Class<T> type, InputStream filename) {
        CsvMapper csvMapper = new CsvMapper();
        CsvSchema schema = CsvSchema.emptySchema().withHeader(); // use first row as header; otherwise defaults are fine
        try {
            MappingIterator<T> readValues = csvMapper.readerFor(type)
                    .with(schema)
                    .readValue(filename);
            return readValues.readAll();
        } catch (IOException ex) {
            return Collections.emptyList();
        }
    }

    public void toCsv(Class<T> type, List<T> list, OutputStream filename) {
        CsvMapper mapper = new CsvMapper();
        mapper.configure(JsonGenerator.Feature.IGNORE_UNKNOWN, true);

        CsvSchema schema = mapper.schemaFor(type);
        ObjectWriter writer = mapper.writerFor(type).with(schema);

        try {
            writer.writeValues(filename).writeAll(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
