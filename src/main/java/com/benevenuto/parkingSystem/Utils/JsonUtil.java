package com.benevenuto.parkingSystem.Utils;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.time.format.DateTimeFormatter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JsonUtil {

    private static final String ZONED_TIME_FORMATTER = "yyyy-MM-dd'T'HH:mm:ss.SSSXXXXX";

    @SneakyThrows
    public static <T> String toJson(final  T object)
    {
        return getMapper().writeValueAsString(object);
    }

    private static ObjectMapper getMapper()
    {
        final var mapper = new ObjectMapper();
        final var timeModule = new JavaTimeModule();

        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        timeModule.addSerializer(new ZonedDateTimeSerializer(DateTimeFormatter.ofPattern(ZONED_TIME_FORMATTER)));

        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        return mapper;
    }
}
