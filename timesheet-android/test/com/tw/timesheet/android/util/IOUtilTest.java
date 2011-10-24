package com.tw.timesheet.android.util;

import com.tw.timesheet.android.domain.UserProfile;
import org.hamcrest.core.IsNull;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static junit.framework.Assert.assertFalse;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;

public class IOUtilTest {

    private OutputStream os = null;

    @Before
    public void setUp() {
        os = mock(OutputStream.class);
    }

    @Test
    public void should_close_properly_after_serialized_to_target_memory() throws IOException {
        IOUtil.writeObjectToDisk(os, new UserProfile());

        verify(os, atLeastOnce()).close();
    }

    @Test
    public void should_retrieve_result_object_from_memory() throws FileNotFoundException {
        UserProfile userProfile = new UserProfile("username", "password");

        FileOutputStream fos = new FileOutputStream("./temp");
        IOUtil.writeObjectToDisk(fos, userProfile);
        FileInputStream fis = new FileInputStream("./temp");
        UserProfile retrievedUserProfile = (UserProfile) IOUtil.readObjectFromMemory(fis);

        assertFalse(userProfile.isEmpty());
        assertThat(userProfile, is(retrievedUserProfile));
        new File("./temp").deleteOnExit();
    }

    @Test
    public void should_return_empty_string_when_call_get_string_from_null_input_screen() {
        assertThat(IOUtil.getStringFromStream(null), is(""));
    }

    @Test
    public void should_return_empty_string_when_call_get_string_from_error_read_input_screen() throws IOException {
        InputStream is = mock(InputStream.class);
        when(is.read((byte[]) any())).thenThrow(new IOException());
        assertThat(IOUtil.getStringFromStream(is), is(""));
    }

    @Test
    public void should_return_false_when_call_get_string_from_error_write_object() throws IOException {
        OutputStream os = mock(OutputStream.class);
        doThrow(new IOException()).when(os).write((byte[]) any(), anyInt(), anyInt());
        assertFalse(IOUtil.writeObjectToDisk(os, new UserProfile()));
    }

    @Test
    public void should_return_null_when_call_get_string_from_error_read_object() throws IOException {
        FileInputStream fis = mock(FileInputStream.class);
        doThrow(new IOException()).when(fis).read(((byte[]) any()), anyInt(), anyInt());

        assertThat(IOUtil.readObjectFromMemory(fis), IsNull.<Object>nullValue());
    }

    @Test
    public void should_return_string_from_input_stream() {
        byte[] buff = {'a', 'b', 'c'};
        ByteArrayInputStream bis = new ByteArrayInputStream(buff);

        assertThat(IOUtil.getStringFromStream(bis), is("abc"));
    }
}
