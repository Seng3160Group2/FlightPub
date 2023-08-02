package com.uon.seng3160.group2.flightpub.config;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;

public class PascalCasePhysicalNamingStrategy implements PhysicalNamingStrategy {

    @Override
    public Identifier toPhysicalCatalogName(final Identifier identifier, final JdbcEnvironment jdbcEnv) {
        return convertToPascalCase(identifier);
    }

    @Override
    public Identifier toPhysicalColumnName(final Identifier identifier, final JdbcEnvironment jdbcEnv) {
        return convertToPascalCase(identifier);
    }

    @Override
    public Identifier toPhysicalSchemaName(final Identifier identifier, final JdbcEnvironment jdbcEnv) {
        return convertToPascalCase(identifier);
    }

    @Override
    public Identifier toPhysicalSequenceName(final Identifier identifier, final JdbcEnvironment jdbcEnv) {
        return convertToPascalCase(identifier);
    }

    @Override
    public Identifier toPhysicalTableName(final Identifier identifier, final JdbcEnvironment jdbcEnv) {
        return convertToPascalCase(identifier);
    }

    private Identifier convertToPascalCase(final Identifier identifier) {
        if (identifier == null)
            return null;
        if (identifier.isQuoted())
            return identifier;
        String columnName = identifier.getText();
        String capitalizedColumnName = Character.toUpperCase(columnName.charAt(0)) + columnName.substring(1);

        return Identifier.toIdentifier(capitalizedColumnName);
    }
}
