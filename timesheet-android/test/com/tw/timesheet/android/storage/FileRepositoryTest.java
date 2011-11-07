package com.tw.timesheet.android.storage;

import android.content.Context;
import com.tw.timesheet.android.domain.UserProfile;
import com.tw.timesheet.android.util.IOUtil;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
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

    @Test
    public void should_throw_file_not_found_exception_when_no_file_in_storage_repo() throws FileNotFoundException {
        FileRepository<UserProfile> userProfileFileRepository = new FileRepository<UserProfile>(context);
        when(context.fileList()).thenReturn(new String[]{"user_a", "user_b"});
        when(context.openFileInput("user_profile_data")).thenThrow(new FileNotFoundException());

        FileStorage loadedFileStorage = userProfileFileRepository.loadData(new UserProfile());

        assertTrue(loadedFileStorage.isEmpty());
    }

    @Test
    public void should_return_save_object_properly_when_save_object() throws FileNotFoundException {
        FileRepository<FileStorage> userProfileFileRepository = new FileRepository<FileStorage>(context);
        StorageStub storageStub = new StorageStub();
        FileOutputStream fos = new FileOutputStream("storage_stub_file");
        when(context.openFileOutput(anyString(), anyInt())).thenReturn(fos);

        assertTrue(userProfileFileRepository.saveData(storageStub));

        FileStorage storageStubFromFile = (FileStorage) IOUtil.readObjectFromMemory(new FileInputStream("storage_stub_file"));
        assertThat(storageStub, is(storageStubFromFile));

        new File("tw_te_stub_storage").deleteOnExit();
    }

    @Test
    public void should_return_save_object_unsuccessful_when_save_object() throws FileNotFoundException {
        FileRepository<FileStorage> userProfileFileRepository = new FileRepository<FileStorage>(context);
        StorageStub storageStub = new StorageStub();
        when(context.openFileOutput(anyString(), anyInt())).thenThrow(new FileNotFoundException());

        boolean saveResult = userProfileFileRepository.saveData(storageStub);

        assertFalse(saveResult);
    }
}

class StorageStub implements FileStorage {

    public String stringValue = "string_value";

    @Override
    public String getFileName() {
        return "tw_te_stub_storage";
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StorageStub)) return false;

        StorageStub that = (StorageStub) o;

        if (!stringValue.equals(that.stringValue)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return stringValue.hashCode();
    }
}
