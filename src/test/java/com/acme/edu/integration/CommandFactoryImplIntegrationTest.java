package com.acme.edu.integration;

import com.acme.edu.commands.CommandFactory;
import com.acme.edu.commands.CommandFactoryImpl;
import com.acme.edu.commands.LogIntegerCommand;
import com.acme.edu.commands.LogStringCommand;
import com.acme.edu.commands.LogUnaccumulatedTypeCommand;
import org.junit.Before;
import org.junit.Test;

import static org.fest.assertions.Assertions.*;

public class CommandFactoryImplIntegrationTest {
    private CommandFactory sut;

    @Before
    public void setUp() {
        sut = new CommandFactoryImpl();
    }

    @Test
    public void shouldCreateLogUnaccumulatedTypeCommandWhenObjectIsPassedAsArgument() {
        Object dummy = new Object();
        assertThat(sut.createCommand(dummy)).isInstanceOf(LogUnaccumulatedTypeCommand.class);
    }

    @Test
    public void shouldCreateLogUnaccumulatedTypeCommandWhenBoolenIsPassedAsArgument() {
        boolean dummy = false;
        assertThat(sut.createCommand(dummy)).isInstanceOf(LogUnaccumulatedTypeCommand.class);
    }

    @Test
    public void shouldCreateLogUnaccumulatedTypeCommandWhenCharIsPassedAsArgument() {
        char dummy = 'a';
        assertThat(sut.createCommand(dummy)).isInstanceOf(LogUnaccumulatedTypeCommand.class);
    }

    @Test
    public void shouldCreateLogIntegerCommandWhenIntIsPassedAsArgument() {
        int dummy = 1;
        assertThat(sut.createCommand(dummy)).isInstanceOf(LogIntegerCommand.class);
    }

    @Test
    public void shouldCreateLogUnaccumulatedTypeCommandWhenByteIsPassedAsArgument() {
        byte dummy = 1;
        assertThat(sut.createCommand(dummy)).isInstanceOf(LogIntegerCommand.class);
    }

    @Test
    public void shouldCreateLogStringCommandWhenStringIsPassedAsArgument() {
        String dummy = "test string";
        assertThat(sut.createCommand(dummy)).isInstanceOf(LogStringCommand.class);
    }
}
