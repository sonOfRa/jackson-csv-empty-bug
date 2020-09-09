package com.eventim;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Data {

    @JsonProperty("STRING_VALUE")
    private String stringValue;

    @JsonProperty("LONG_VALUE")
    private Long longValue;

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(final String stringValue) {
        this.stringValue = stringValue;
    }

    public Long getLongValue() {
        return longValue;
    }

    public void setLongValue(final Long longValue) {
        this.longValue = longValue;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Data data = (Data) o;
        return Objects.equals(stringValue, data.stringValue) &&
                Objects.equals(longValue, data.longValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stringValue, longValue);
    }
}
