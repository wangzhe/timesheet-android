package com.tw.timesheet.android.storage;

import android.content.Context;
import com.tw.timesheet.android.domain.UserProfile;
import com.tw.timesheet.android.util.IOUtil;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FileRepositoryTest {

    private Context context;

    @Before
    public void setUp() {
        context = mock(Context.class);
    }

    @Test
    public void should_return_user_profile_when_using_user_profile_storage_repo() {
        when(context.fileList()).thenReturn(new String[]{});
        FileRepository<UserProfile> userProfileFileRepository = new FileRepository<UserProfile>(context);

        UserProfile userProfile = userProfileFileRepository.loadData(new UserProfile());
        assertThat(userProfile.getClass().getName(), is(UserProfile.class.getName()));
        UserProfile userProfile2 = userProfileFileRepository.loadData(new UserProfile());
        assertThat(userProfile2.getClass().getName(), is(UserProfile.class.getName()));
    }

    @Test
    public void should_return_empty_file_object_when_no_file_data_in_storage_repo() throws IOException {
        FileRepository<UserProfile> userProfileFileRepository = new FileRepository<UserProfile>(context);
        when(context.fileList()).thenReturn(new String[]{"user_a", "user_profile_data"});
        when(context.openFileInput("user_profile_data")).thenReturn(null);

        FileStorage fileStorage = userProfileFileRepository.loadData(new UserProfile());
        assertTrue(fileStorage.isEmpty());
    }

    @Test
    public void should_return_empty_file_object_when_no_file_in_storage_repo() throws IOException {
        FileRepository<UserProfile> userProfileFileRepository = new FileRepository<UserProfile>(context);
        when(context.fileList()).thenReturn(new String[]{"user_a", "user_b"});

        FileStorage fileStorage = userProfileFileRepository.loadData(new UserProfile());
        assertTrue(fileStorage.isEmpty());
    }

    @Test
    public void should_return_file_object_when_file_existed_in_storage_repo() throws IOException {
        FileRepository<UserProfile> userProfileFileRepository = new FileRepository<UserProfile>(context);
        when(context.fileList()).thenReturn(new String[]{"user_a", "user_profile_data"});
        FileStorage fileStorage = new UserProfile("username", "password");
        FileOutputStream fos = new FileOutputStream("user_profile_data");
        IOUtil.writeObjectToDisk(fos, fileStorage);
        FileInputStream fis = new FileInputStream("user_profile_data");
        when(context.openFileInput("user_profile_data")).thenReturn(fis);

        FileStorage loadedFileStorage = userProfileFileRepository.loadData(new UserProfile());

        assertFalse(loadedFileStorage.isEmpty());
        assertThat(loadedFileStorage, is(fileStorage));
        new File("./temp").deleteOnExit();
    }
}
