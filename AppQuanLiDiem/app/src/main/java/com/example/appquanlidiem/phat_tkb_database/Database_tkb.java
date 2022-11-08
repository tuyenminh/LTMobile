package com.example.appquanlidiem.phat_tkb_database;

// Thời khóa biểu
// Thời khóa biểu
// Thời khóa biểu

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.OpenableColumns;
import androidx.annotation.NonNull;
import com.google.gson.Gson;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;

public class Database_tkb<T> {
    public static final String TIMETABLE_FILE_NAME = "timetable.json";
    public static final String TIME_FILE_NAME = "time.json";
    public void saveToJson(Context context, T data, String fileName) {
        BufferedWriter bw = null;
        OutputStream os = null;
        try {
            os = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            bw = new BufferedWriter(new OutputStreamWriter(os));

            String json = new Gson().toJson(data);
            bw.write(json);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null)
                    bw.close();
                if (os != null)
                    os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public T readFromJson(Context context, String fileName, Type type) {
        File file = new File(context.getFilesDir().getPath(), fileName);
        if (!file.exists())
            return null;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(context.openFileInput(fileName)));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                stringBuilder.append(line);
            }
            return new Gson().fromJson(stringBuilder.toString(), type);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (br != null)
                    br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @NonNull
    public static String getFileExtension(String name) {
        if (null == name || name.isEmpty()) {
            return "";
        }
        int index = name.lastIndexOf('.');
        return index != -1 ? name.substring(index+1) : "";
    }

    public static String getNameFromUri(Context context, Uri uri) {
        try (Cursor cursor = context.getContentResolver()
                .query(uri, null, null, null, null)) {
            if (cursor != null && cursor.moveToFirst()) {
                int index = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
                if (index != -1) {
                    return cursor.getString(index);
                }
            }
        }
        return "";
    }

    public static boolean fileCopy(InputStream inputStream, String outPath) {
        if (inputStream == null) {
            return false;
        }
        File outfile = new File(outPath);
        File parentFile = outfile.getParentFile();
        if (parentFile == null || !parentFile.exists() && !parentFile.mkdirs()) {
            return false;
        }
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        byte[] buffer = new byte[1024];
        try {
            bis = new BufferedInputStream(inputStream);
            bos = new BufferedOutputStream(new FileOutputStream(outfile));
            int n;
            while ((n = bis.read(buffer, 0, buffer.length)) != -1) {
                bos.write(buffer, 0, n);
            }
            bos.flush();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bos != null)
                    bos.close();
                if (bis != null)
                    bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
