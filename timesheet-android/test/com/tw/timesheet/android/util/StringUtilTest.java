package com.tw.timesheet.android.util;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class StringUtilTest {
    @Test
    public void should_return_false_when_test_is_not_empty() throws Exception {
        assertThat(StringUtil.isEmpty("content"), is(false));
    }

    @Test
    public void should_return_true_when_test_is_empty() throws Exception {
        assertThat(StringUtil.isEmpty(""), is(true));
        assertThat(StringUtil.isEmpty(null), is(true));
    }

    @Test
    public void should_return_empty_when_copy_is_empty() throws Exception {
        assertThat(StringUtil.copy(""), is(StringUtil.EMPTY));
    }

    @Test
    public void should_return_the_same_string_when_copy_is_string() throws Exception {
        assertThat(StringUtil.copy("my_string"), is("my_string"));
    }
}
