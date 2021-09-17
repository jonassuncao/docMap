package com.jassuncao.docmap.domain.attribute;

/**
 * @author jonathas.assuncao - jaa020399@gmail.com
 * 09/09/2021
 */

public enum TypeData {
    Binary("Byte[]"),
    Text("String"),
    Long("Long"),
    Integer("Integer"),
    Number("Double"),
    Datetime("LocalDateTime"),
    Date("LocalDate"),
    Timestamp("Instant"),
    Boolean("boolean");

    private final String type;

    TypeData(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

}
