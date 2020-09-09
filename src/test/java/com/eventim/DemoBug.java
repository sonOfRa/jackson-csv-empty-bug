package com.eventim;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

class DemoBug {

    private static ObjectReader objectReader;

    @BeforeAll
    public static void before() {
        CsvMapper mapper = new CsvMapper();
        mapper.enable(CsvParser.Feature.FAIL_ON_MISSING_COLUMNS);

        CsvSchema bootstrapSchema = mapper.schemaWithHeader()
                .withArrayElementSeparator(",")
                .withColumnSeparator(';')
                .withEscapeChar('"');
        objectReader = mapper.readerFor(Data.class).with(bootstrapSchema);
    }

    @Test
    void expectJsonMappingExceptionFails() throws IOException {
        MappingIterator<Data> iterator = objectReader.readValues(getClass().getResource("/invalid-fails.csv"));
        Assertions.assertThrows(JsonMappingException.class, iterator::readAll);
    }

    @Test
    void expectJsonMappingException() throws IOException {
        MappingIterator<Data> iterator = objectReader.readValues(getClass().getResource("/invalid.csv"));
        Assertions.assertThrows(JsonMappingException.class, iterator::readAll);
    }

    @Test
    void expectList() throws IOException {
        MappingIterator<Data> iterator = objectReader.readValues(getClass().getResource("/valid.csv"));
        List<Data> data = iterator.readAll();
        Assertions.assertEquals(1, data.size());
    }
}
