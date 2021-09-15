package com.jassuncao.docmap.domain.project;

/**
 * @author jonathas.assuncao - jaa020399@gmail.com
 * 15/09/2021
 */
public enum HibernateImport {
    Id("javax.persistence.Id"),
    Lob("javax.persistence.Lob"),
    Column("javax.persistence.Column"),
    Optional("java.util.Optional"),
    LocalDateTime("java.time.LocalDateTime"),
    LocalDate("java.time.LocalDate"),
    Instant("java.time.Instant"),
    ;

    private final String pack;

    HibernateImport(String pack) {
        this.pack = pack;
    }

    public String getPackage() {
        return pack;
    }
}
