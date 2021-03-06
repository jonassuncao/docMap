package com.jassuncao.docmap.domain.project;

import com.jassuncao.docmap.UnitTests;
import com.jassuncao.docmap.infra.ValidationException;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * @author jonathas.assuncao - jaa020399@gmail.com
 * 15/09/2021
 */
class NormalizeTest extends UnitTests {

    static Stream<Arguments> packageForm() {
        return Stream.of(
                arguments("Entidade", "entidade"),
                arguments(" Entidade ", "entidade"),
                arguments(" E n t i d a   d e", "entidade"),
                arguments("Relação ", "relacao"),
                arguments("Ônibus", "onibus"),
                arguments(" Básico", "basico"),
                arguments(" Entidade   Relação ", "entidaderelacao"),
                arguments("entidadeRelacao", "entidaderelacao"),
                arguments("entidade_relacao", "entidaderelacao")
        );
    }

    static Stream<Arguments> classForm() {
        return Stream.of(
                arguments("entidade", "Entidade"),
                arguments(" Entidade ", "Entidade"),
                arguments("Relação ", "Relacao"),
                arguments("Ônibus", "Onibus"),
                arguments(" Básico", "Basico"),
                arguments(" Entidade    Relação ", "EntidadeRelacao"),
                arguments("entidadeRelacao", "EntidadeRelacao"),
                arguments("entidade_relacao", "EntidadeRelacao")
        );
    }

    static Stream<Arguments> dataBaseForm() {
        return Stream.of(
                arguments("entidade", "entidade"),
                arguments(" Entidade ", "entidade"),
                arguments("Relação ", "relacao"),
                arguments("Ônibus", "onibus"),
                arguments(" Básico", "basico"),
                arguments(" Entidade    Relação ", "entidade_relacao"),
                arguments("entidadeRelacao", "entidade_relacao"),
                arguments("entidade_relacao", "entidade_relacao")
        );
    }

    static Stream<Arguments> fieldForm() {
        return Stream.of(
                arguments("entidade", "entidade"),
                arguments(" Entidade ", "entidade"),
                arguments("Relação ", "relacao"),
                arguments("Ônibus", "onibus"),
                arguments(" Básico", "basico"),
                arguments(" Entidade    Relação ", "entidadeRelacao"),
                arguments("entidadeRelacao", "entidadeRelacao"),
                arguments("entidade_relacao", "entidadeRelacao")
        );
    }

    @ParameterizedTest
    @MethodSource("packageForm")
    public void testNormalizePackageForm(String data, String expected) throws Exception {
        assertThat(Normalize.packageForm(data)).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("classForm")
    public void testNormalizeClassForm(String data, String expected) throws Exception {
        assertThat(Normalize.classForm(data)).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("dataBaseForm")
    public void testNormalizeDataBaseForm(String data, String expected) throws Exception {
        assertThat(Normalize.dataBaseForm(data)).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("fieldForm")
    public void testNormalizeFieldFormForm(String data, String expected) throws Exception {
        assertThat(Normalize.fieldForm(data)).isEqualTo(expected);
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {"_", "\n", "1", "\"", "`", ","})
    public void testNormalizePackageFormException(String data) throws Exception {
        assertThrows(ValidationException.class, () -> Normalize.packageForm(data));
    }

    @Test
    void testNormalizeSplitPreserveTokens() throws Exception {
        final String text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit";
        final List<String> result = Normalize.splitPreserveTokens(text, 29);

        assertThat(result).hasSize(2);
        assertThat(result.get(0)).isEqualTo("Lorem ipsum dolor sit amet,");
        assertThat(result.get(1)).isEqualTo("consectetur adipiscing elit");
    }

    @Test
    void testNormalizeSplitPreserveTokens__withLf() throws Exception {
        final String text = "Lorem ipsum dolor sit amet, \n consectetur adipiscing elit";
        final List<String> result = Normalize.splitPreserveTokens(text, 15);

        assertThat(result).hasSize(5);
        assertThat(result.get(0)).isEqualTo("Lorem ipsum");
        assertThat(result.get(1)).isEqualTo("dolor sit amet,");
        assertThat(result.get(2)).isEqualTo(StringUtils.EMPTY);
        assertThat(result.get(3)).isEqualTo("consectetur");
        assertThat(result.get(4)).isEqualTo("adipiscing elit");
    }

}