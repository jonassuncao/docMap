package com.jassuncao.docmap.domain.project;

import com.jassuncao.docmap.UnitTests;
import com.jassuncao.docmap.infra.ValidationException;
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

    @ParameterizedTest
    @MethodSource("packageForm")
    public void testNormalizePackageForm(String data, String expected) throws Exception {
        assertThat(Normalize.packageForm(data)).isEqualTo(expected);
    }

    static Stream<Arguments> packageForm() {
        return Stream.of(
                arguments("Entidade", "entidade"),
                arguments(" Entidade ", "entidade"),
                arguments(" E n t i d a   d e", "entidade"),
                arguments("Relação ", "relacao"),
                arguments("Ônibus", "onibus"),
                arguments(" Básico", "basico"),
                arguments(" Entidade   Relação ", "entidaderelacao")
        );
    }

    @ParameterizedTest
    @MethodSource("classForm")
    public void testNormalizeClassForm(String data, String expected) throws Exception {
        assertThat(Normalize.classForm(data)).isEqualTo(expected);
    }

    static Stream<Arguments> classForm() {
        return Stream.of(
                arguments("entidade", "Entidade"),
                arguments(" ENtiDaDe ", "Entidade"),
                arguments("Relação ", "Relacao"),
                arguments("Ônibus", "Onibus"),
                arguments(" BÁSICO", "Basico"),
                arguments(" ENTIDADE    RELAÇÃO ", "EntidadeRelacao")
        );
    }

    @ParameterizedTest
    @MethodSource("dataBaseForm")
    public void testNormalizeDataBaseForm(String data, String expected) throws Exception {
        assertThat(Normalize.dataBaseForm(data)).isEqualTo(expected);
    }

    static Stream<Arguments> dataBaseForm() {
        return Stream.of(
                arguments("entidade", "entidade"),
                arguments(" ENtiDaDe ", "entidade"),
                arguments("Relação ", "relacao"),
                arguments("Ônibus", "onibus"),
                arguments(" BÁSICO", "basico"),
                arguments(" ENTIDADE    RELAÇÃO ", "entidade_relacao")
        );
    }

    @ParameterizedTest
    @MethodSource("fieldForm")
    public void testNormalizeFieldFormForm(String data, String expected) throws Exception {
        assertThat(Normalize.fieldForm(data)).isEqualTo(expected);
    }

    static Stream<Arguments> fieldForm() {
        return Stream.of(
                arguments("entidade", "entidade"),
                arguments(" ENtiDaDe ", "entidade"),
                arguments("Relação ", "relacao"),
                arguments("Ônibus", "onibus"),
                arguments(" BÁSICO", "basico"),
                arguments(" ENTIDADE    RELAÇÃO ", "entidadeRelacao")
        );
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {"_", "\n", "1", "\"", "`", ","})
    public void testNormalizePackageFormException(String data) throws Exception {
        assertThrows(ValidationException.class, () -> Normalize.packageForm(data));
    }

    @Test
    void testNormalizeSplitPreserveTokens() throws Exception {
        final String text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut libero justo, ultrices non urna id, elementum ultrices justo. Curabitur molestie velit odio. In ullamcorper vitae arcu vel rutrum. Sed quam metus, fermentum ac leo eu, fringilla rhoncus urna. Ut lacinia maximus turpis in suscipit. Nullam vel velit nec neque hendrerit tempor nec a erat.";
        final List<String> result = Normalize.splitPreserveTokens(text, 120);

        assertThat(result).hasSize(2);
        assertThat(result.get(0)).isEqualTo("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut libero justo, ultrices non urna id, elementum ultrices");
        assertThat(result.get(1)).isEqualTo("justo. Curabitur molestie velit odio. In ullamcorper vitae arcu vel rutrum. Sed quam metus, fermentum ac leo eu,");
    }

}