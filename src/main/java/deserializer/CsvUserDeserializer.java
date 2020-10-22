package deserializer;


import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import model.User;
import model.UsersList;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class CsvUserDeserializer implements UserDeserializer {

    public Collection<User> deserialize(String filename) throws IOException {
        UsersList userCollection = new UsersList();
        CsvMapper csvMapper = new CsvMapper();
        csvMapper.disable(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY);
        CsvSchema schema = csvMapper.schemaFor(User.class);
        ObjectReader with = csvMapper.readerFor(User.class).forType(User.class).with(schema);
        MappingIterator<User> objectMappingIterator = with.readValues(new File(filename));
        List<User> users = objectMappingIterator.readAll();
        return users;
    }
}
