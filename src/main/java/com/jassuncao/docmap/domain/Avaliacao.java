package com.jassuncao.docmap.domain;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Optional;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.ManyToMany;



/**
* Descrição
* 
* Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut libero justo, ultrices non urna id, elementum ultrices
* justo. Curabitur molestie velit odio. In ullamcorper vitae arcu vel rutrum. Sed quam metus, fermentum ac leo eu,
* fringilla rhoncus urna.
* 
* Generated by docMap
*/
@Entity
@Table(name="avaliacao")
public class Avaliacao implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * name
    */
    @Id
    @Column(name="attr_text", unique=true, nullable=false, length=200)
    private String attrText;

    /**
    * name
    */
    @Column(name="attr_long", nullable=false)
    private Long attrLong;

    /**
    * name
    */
    @Column(name="attr_integer", nullable=false)
    private Integer attrInteger;

    /**
    * name
    */
    @Column(name="attr_number", nullable=false, precision=5, scale=4)
    private Double attrNumber;

    /**
    * name
    */
    @Column(name="attr_datetime")
    private LocalDateTime attrDatetime;

    /**
     * Relação
     */
    @OneToOne
    @JoinColumn(name="pessoa_id")
    private Pessoa pessoa;

    /**
     * Relação
     */
    @OneToOne
    @JoinColumn(name="principal_id")
    private Avaliacao principal;

    Avaliacao() {
        super();
    }

    protected void setAttrText(String attrText){
        this.attrText = attrText;
    }

    protected String getAttrText(){
        return attrText;
    }

    protected void setAttrLong(Long attrLong){
        this.attrLong = attrLong;
    }

    protected Long getAttrLong(){
        return attrLong;
    }

    protected void setAttrInteger(Integer attrInteger){
        this.attrInteger = attrInteger;
    }

    protected Integer getAttrInteger(){
        return attrInteger;
    }

    protected void setAttrNumber(Double attrNumber){
        this.attrNumber = attrNumber;
    }

    protected Double getAttrNumber(){
        return attrNumber;
    }

    protected void setAttrDatetime(LocalDateTime attrDatetime){
        this.attrDatetime = attrDatetime;
    }

    protected Optional<LocalDateTime> getAttrDatetime(){
        return Optional.ofNullable(attrDatetime);
    }

    protected void setPessoa(Pessoa pessoa){
        this.pessoa = pessoa;
    }

    protected Optional<Pessoa> getPessoa(){
        return Optional.ofNullable(pessoa);
    }

    protected void setPrincipal(Avaliacao principal){
        this.principal = principal;
    }

    protected Avaliacao getPrincipal(){
        return principal;
    }
}
