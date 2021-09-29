package com.projeto.pessoa;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
* Descrição
* 
* Descrição
* 
* Generated by docMap
*/
@Entity
@Table(name="pessoa")
public class Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * name
    */
    @Id
    @Column(name="attr_date", unique=true, nullable=false)
    private LocalDate attrDate;

    /**
    * name
    */
    @Column(name="attr_timestamp")
    private Instant attrTimestamp;

    /**
    * name
    */
    @Column(name="attr_boolean")
    private Boolean attrBoolean;

    /**
    * name
    */
    @Lob
    @Column(name="attr_binary")
    private Byte[] attrBinary;

    Pessoa() {
        super();
    }

    protected void setAttrDate(LocalDate attrDate){
        this.attrDate = attrDate;
    }

    protected LocalDate getAttrDate(){
        return attrDate;
    }

    protected void setAttrTimestamp(Instant attrTimestamp){
        this.attrTimestamp = attrTimestamp;
    }

    protected Optional<Instant> getAttrTimestamp(){
        return Optional.ofNullable(attrTimestamp);
    }

    protected void setAttrBoolean(Boolean attrBoolean){
        this.attrBoolean = attrBoolean;
    }

    protected Optional<Boolean> isAttrBoolean(){
        return Optional.ofNullable(attrBoolean);
    }

    protected void setAttrBinary(Byte[] attrBinary){
        this.attrBinary = attrBinary;
    }

    protected Optional<Byte[]> getAttrBinary(){
        return Optional.ofNullable(attrBinary);
    }
}
