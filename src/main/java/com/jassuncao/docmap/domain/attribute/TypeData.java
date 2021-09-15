package com.jassuncao.docmap.domain.attribute;

import com.jassuncao.docmap.domain.project.HibernateImport;

/**
 * @author jonathas.assuncao - jaa020399@gmail.com
 * 09/09/2021
 */

public enum TypeData {
    Binary("byte[]", null),
    Text("String", null),
    Long("long", null),
    Integer("int", null),
    Number("double", null),
    Datetime("LocalDateTime", HibernateImport.LocalDateTime),
    Date("LocalDate", HibernateImport.LocalDate),
    Timestamp("Instant", HibernateImport.Instant),
    Boolean("boolean", null);

    private final String type;
    private final HibernateImport pack;

    TypeData(String type, HibernateImport pack) {
        this.type = type;
        this.pack = pack;
    }

    public String getType() {
        return type;
    }

    public HibernateImport getPack() {
        return pack;
    }
}
